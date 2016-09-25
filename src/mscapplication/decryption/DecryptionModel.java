/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.decryption;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import mscapplication.application.ApplicationController;
import mscapplication.filelocation.FileLocationController;
import mscapplication.main.MainView;
import mscapplication.models.CAGrid;
//import mscapplication.models.HybridRules;
import mscapplication.models.RoundKeys;
import mscapplication.utils.NumberUtils;
import mscapplication.utils.StringUtils;

/**
 *
 * @author Andrew
 */
public class DecryptionModel {

    private int roundsComplete;
    private int blocksEncrypted;
    private int totalNumberOfBlocks;
    private int cipherTextBlockNumber;
    private String cipherTextBlock;
    private String plainTextBlock;
    private String previousCipherTextBlock;
    private String leftBlockInput;
    private String rightBlockInput;
    private String leftBlockAsBinary;
    private String binaryOutputString;
    private String hexOutputString;
    private String rightBlockOutput;
    private String leftBlockOutput;
    private String decryptTextString;
    private String plainTextString;
    private String completePlainText;
    private String IVString;
    private int encryptionMode;
    private int fileMode;
    private int[] ruleNumber;
    private int[] newRuleNumber;
    private int numberOfFeistalRounds;
    PrintWriter plainTextWriter;
    private int blockSize;
    private RoundKeys roundKeys;
    private Boolean xorSiteSpacing;
  
    
    private final ApplicationController applicationController;
    private final FileLocationController fileLocationController;
    private final DecryptionView decryptionView;
    private final MainView mainView;
    private CAGrid gridLabels;  
    private int boundaryConditions;
    private int timeSpacing;
    private int siteSpacing;

 public DecryptionModel() throws IOException {
      
        this.applicationController = ApplicationController.getApplicationController();
        decryptionView = this.applicationController.getDecryptionView();
        mainView = this.applicationController.getMainView();
        fileLocationController = this.applicationController.getFileLocationController();
        
        roundsComplete = 0;
        blocksEncrypted = 0;
        totalNumberOfBlocks = 0;
        cipherTextBlockNumber = 0;
        
        completePlainText = "";
        blockSize = mainView.getBlockSizeTextArea();
        
        plainTextWriter = new PrintWriter(new BufferedWriter(new FileWriter("plaintextoutput.txt", false)));
       
            
}    
    

/*runDecryption() method expects:
    - HybridRules - set of hybrid CA rules determined by the key
    - KeyString - secret key
    - IVString - initialisation vector for CBC mode
    - numberOfFeistalRounds - number of Feistal rounds when encrypting each block
    - encryptionMode - 0 = EBC mode, 1 = CBC mode
    */

    public void runDecryption(RoundKeys roundKeys, String IVString, int numberOfFeistalRounds, int encryptionMode, int fileMode, int boundaryConditions, int timeSpacing, int siteSpacing, Boolean  xorSiteSpacing) throws IOException {    
        this.IVString = IVString;
        this.roundKeys = roundKeys;
        this.numberOfFeistalRounds = numberOfFeistalRounds;
        this.timeSpacing = timeSpacing;
        this.siteSpacing = siteSpacing;
        this.boundaryConditions = boundaryConditions;
        this.xorSiteSpacing = xorSiteSpacing;
        
        gridLabels = new CAGrid(blockSize/2, boundaryConditions, siteSpacing,  xorSiteSpacing);
        
        
        //encryption mode 0 = EBC, 1 = CBC
        this.encryptionMode = encryptionMode;
        this.fileMode = fileMode;
        this.setTotalNumberOfBlocks();
        this.completeDecryption();       
    }    
    
    public void setTotalNumberOfBlocks () throws FileNotFoundException {
     
        //if the use external file checkbox is selected then the blocks are calculated by reading the file
        if(fileMode == 1) {
                totalNumberOfBlocks = fileLocationController.getNumberOfBlocksFromExternalCipherTextFile(blockSize);
            }    
        else
            {
                //otherwise the blocks are determined by taking the plain text as hex string               
                totalNumberOfBlocks = StringUtils.getNumberOfBlocks(decryptionView.getDecryptCipherTextAsHexTextArea(), (blockSize));
            } 
        
      }
      
  
  public void completeDecryption() throws IOException {
       while  (blocksEncrypted < totalNumberOfBlocks) {  
           cipherTextBlockNumber = blocksEncrypted + 1;
           //get a cipher text block to decrypt
           this.getCipherTextBlock();      
           //split the block into left and right blocks        
           this.cutCipherText();       
           

           //apply the left for decryption
           this.applyLeftBlockToGrid();  
         //  int ruleNumber;  
           while (roundsComplete < numberOfFeistalRounds)
                   {    
                   
                 //  int plainTextAvalancheNumber = NumberUtils.getPlaintextAvalancheNumber(leftBlockAsBinary);
                   
                       for (int t = 0; t < timeSpacing; t++)
                                  
                                   {    
                                        for(int i = 0; i < blockSize/2; i++) 
                                             {                   
                                                ruleNumber = roundKeys.getDecryptionRuleNumberBinary(roundsComplete, i);  
                                                
                                                
                        
                        
                        //System.out.println(plainTextAvalancheNumber);
                        
                      //  newRuleNumber = NumberUtils.binaryArrayToIntAddAvalanche(ruleNumber,plainTextAvalancheNumber);
                                                
                                                
                                              // gridLabels.iterateCellInt(i, 1, newRuleNumber);         
                                                gridLabels.iterateCellInt(i, 1, ruleNumber);
                                              }
                                        
                                        gridLabels.setGridInt();
                               }

                   binaryOutputString = gridLabels.getBinaryString_tmp();      
                   this.runFesitalXOR();       
                   this.setupNextFeistalRound();
                   this.applyLeftBlockToGrid();         
                   } 
           this.createPlainTextBlock();
           blocksEncrypted++;                  
           this.resetFeistalNetwork();         
       }
    
       decryptionView.setDecPlainTextAsHexTextArea(NumberUtils.hexToASCII(completePlainText));
       plainTextWriter.close();
       }      
  
   public void getCipherTextBlock() throws IOException{         
        
        if(fileMode == 1) {                
                        int startingPosition = ((cipherTextBlockNumber - 1)* (blockSize/4));
                        cipherTextBlock = fileLocationController.getCipherTextBlockFromFile(startingPosition, blockSize);  
            }        
        else
             
            {
                //if external files are not used then the whole plain text is converted to hex and padding added
                String cipherText = decryptionView.getDecryptCipherTextAsHexTextArea(); 
                cipherTextBlock = (StringUtils.getBlockNumber(cipherText, cipherTextBlockNumber, blockSize));
         }       
    }
   
    public void getPreviousCipherTextBlock() throws IOException{         
        
        if(fileMode == 1) {                
//                if (cipherTextBlockNumber < totalNumberOfBlocks) {                       
                        //8 plain text chars are taken from file which = 16 hex chars
                        int startingPosition = ((cipherTextBlockNumber - 2)* (blockSize/4));
                        previousCipherTextBlock = fileLocationController.getCipherTextBlockFromFile(startingPosition, blockSize);                                               
            }        
        else
             
            {
                //if external files are not used then the whole plain text is converted to hex and padding added
                String cipherText = decryptionView.getDecryptCipherTextAsHexTextArea(); 
                previousCipherTextBlock = (StringUtils.getBlockNumber(cipherText, cipherTextBlockNumber-1, blockSize));
         }       
    }
    
  public void cutCipherText(){
        //block is divided in half
        leftBlockInput = StringUtils.cutPlainText(cipherTextBlock)[0]; 
        rightBlockInput = StringUtils.cutPlainText(cipherTextBlock)[1];
        leftBlockAsBinary = NumberUtils.hexToBinary(leftBlockInput);
    }
   
   public void applyLeftBlockToGrid() {
        gridLabels.setBinaryGrid_tmp(leftBlockAsBinary);         
    }
  
     public void runFesitalXOR() {      
       roundsComplete = roundsComplete + 1;  
       rightBlockOutput = leftBlockInput;
       hexOutputString = NumberUtils.longBinaryStringToHex(binaryOutputString);
       leftBlockOutput = NumberUtils.xorHex(rightBlockInput,hexOutputString);
   }    
   
   public void setupNextFeistalRound() {
       leftBlockInput = leftBlockOutput;
       rightBlockInput = rightBlockOutput;
       leftBlockAsBinary =  NumberUtils.hexToBinary(leftBlockInput);    
   }  
     
    public void createPlainTextBlock () throws IOException {
       decryptTextString = leftBlockOutput + rightBlockOutput;
       plainTextString = decryptTextXORWithIV();
       
       if (fileMode == 1) {
           plainTextWriter.print(NumberUtils.hexToASCII(plainTextString));               
       }       
       else {
           completePlainText = completePlainText + plainTextString;
           }
    }
  
     public String decryptTextXORWithIV () throws IOException {
       
        if (encryptionMode == 1) {     
            if (cipherTextBlockNumber == 1) 
                {
                    plainTextBlock = NumberUtils.xorHex(decryptTextString,IVString);      
                    return plainTextBlock;
                }           
            else
                {
                    this.getPreviousCipherTextBlock();
                    plainTextBlock = NumberUtils.xorHex(decryptTextString, previousCipherTextBlock);      
                    return plainTextBlock;         
                }
        }       
        else {
            
            plainTextBlock = decryptTextString;
            return plainTextBlock;                   
        }   
    }
     
     
     public void resetFeistalNetwork () {
    
        roundsComplete = 0;
    }  
     
     public void resetDecryption(){
        
       roundsComplete = 0;
       blocksEncrypted = 0;
       totalNumberOfBlocks = 0;
       cipherTextBlockNumber = 0;
       completePlainText = "";
       decryptionView.setDecryptCipherTextAsHexTextArea("");
       decryptionView.setDecPlainTextAsHexTextArea("");
         
    }
     
     public String getCompletePlainText() {
        
        return completePlainText;
    }

     
}
