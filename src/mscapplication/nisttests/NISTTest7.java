/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.nisttests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import mscapplication.application.ApplicationController;
import mscapplication.application.ApplicationView;
import mscapplication.encryption.EncryptionController;
import mscapplication.encryption.EncryptionModel;
import mscapplication.encryption.EncryptionView;
import mscapplication.filelocation.FileLocationController;
import mscapplication.main.MainController;
import mscapplication.main.MainView;
import mscapplication.models.RoundKeys;
import mscapplication.utils.NumberUtils;

/**
 *
 * @author Andrew
 */
public class NISTTest7 {
    
    
        
 private ApplicationController applicationController;
    private FileLocationController fileLocationController;
    private MainController mainController;
    private EncryptionController encryptionController;
    private MainView mainView;
    private EncryptionView encryptionView;
    private ApplicationView applicationView;
    private PrintWriter test7FileWriter;
    private EncryptionModel encryptionModel;
//private ApplicationView applicationView;
//private EncryptionView encryptionView;
//private MainView mainView;
//private ApplicationController applicationController;
//private FileLocationController fileLocationController;
private RoundKeys roundKeys;
private String IVString;   


 public NISTTest7(ApplicationController applicationController) throws IOException  {
    
     this.applicationController = applicationController;   
        
        applicationView = applicationController.getApplicationView();
        mainController = applicationController.getMainController();
        mainView = mainController.getMainView();
        encryptionController = applicationController.getEncryptionControllerNew();
      
        encryptionView = applicationController.getEncryptionView();
        fileLocationController = applicationController.getFileLocationController();
      
//        applicationView.setEncryptionDelayCheckBox(false);
       
     //   test1FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/1keyavalanchetest.txt", false)));
    
         test7FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/7highdensityplaintexttest.txt", false)));
    
}
    
     /*TEST 7: HIGH DENSITY PLAINTEXT INPUT 
       
        Two data sets were created based on high-density blocks used as either plaintext or 128­
        bit keys. Each data set consisted of 128 sequences. Each sequence consisted of 8,257
        ciphertext blocks computed in the ECB mode. These ciphertext blocks were formed from
        one all ones plaintext block (128-bit key), 128 plaintext blocks (128-bit keys) of a single
        zero and 127 ones (the zero appearing in each of the possible 128 bit positions), and
        8,128 plaintext blocks (128-bit keys) of two zeroes and 126 ones (the two zeroes
        appearing in each combination of two bit positions within the 128-bit positions). 
       */

 public void highDensityPlainTextTest () throws IOException  
       
       {
           
            encryptionModel = new EncryptionModel();  
           
           
           
           for (int z = 0; z < mainView.getNumberOfDataSetsTextField(); z++) {
               
               System.out.println("key number " + z + "of 128");
           
               applicationController.generateKey();
                roundKeys = this.applicationController.getRoundKeys();
                IVString = applicationController.getIVInstance();
           
           
          char[] chars = new char[128];
          Arrays.fill(chars, '1');
          String lowDensityString1 = new String(chars); 
          
     //     System.out.println("high density first string " + lowDensityString1);
          
          String lowDensityString1AsHex = NumberUtils.longBinaryStringToHex(lowDensityString1);
         // System.out.println("6 hex" + lowDensityString1AsHex);
          
          
          String randomPlainText = NumberUtils.hexToASCII(lowDensityString1AsHex);
          
          encryptionView.setPlainTextAsTextArea(randomPlainText); 
           
         encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox()); 
            
//          String cipherText = encryptionView.getCipherTextAsHexTextArea(); 
          
           String cipherText = encryptionModel.getCompleteCipherText();
          
          //System.out.println("Cipher text 6 " + cipherText);
          
          String cipherTextAsBinary = NumberUtils.hexToBinary(cipherText);
          
     //    fileLocationController.outputNISTHighDensityPlainTextFile(cipherTextAsBinary);
         
                  test7FileWriter.print(cipherTextAsBinary);

          
          encryptionModel.resetEncryption();
         // applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0");
         
         //64 blocks all 0s with a 1 in every space exactly once

         StringBuilder sb = new StringBuilder(lowDensityString1);
        
          String lowDensityString2;
          String lowDensityString2AsHex;
         String randomPlainText2;
          String cipherText2; 
          String cipherTextAsBinary2;
          
         
         
         
         for(int i = 0; i<128; i ++)
        {
                sb.setCharAt(i, '0');
                
                lowDensityString2 = sb.toString();
                
          //      System.out.println("High density string 2" + lowDensityString2);
    
         lowDensityString2AsHex = NumberUtils.longBinaryStringToHex(lowDensityString2);
         
         //System.out.println("6 hex long " + lowDensityString2AsHex);
          
          randomPlainText2 = NumberUtils.hexToASCII(lowDensityString2AsHex);
          
          encryptionView.setPlainTextAsTextArea(randomPlainText2); 
           
          encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox()); 
            
          cipherText2 = encryptionModel.getCompleteCipherText();
          
         // System.out.println("Cipher text 6 " + cipherText2);
          
          cipherTextAsBinary2 = NumberUtils.hexToBinary(cipherText2);
          
         //fileLocationController.outputNISTHighDensityPlainTextFile(cipherTextAsBinary2);
         
              test7FileWriter.print(cipherTextAsBinary2);
         
         
         sb.setCharAt(i, '1');
         
         encryptionModel.resetEncryption(); 
    //     applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0");
             
         }
        
        
        //need to finish the example where the 2 1s are spread amongst the plaintext
        
        
        StringBuilder sb2 = new StringBuilder(lowDensityString1);
        
          String lowDensityString3;
          String lowDensityString3AsHex;
         String randomPlainText3;
          String cipherText3; 
          String cipherTextAsBinary3;
          
         
         
         
         for(int i = 0; i<128; i ++)
        {
                sb2.setCharAt(i, '0');
                
              //  System.out.println("i value is " + 1);
             //   lowDensityString2 = sb.toString();
                
                
                for(int j = i+1; j<128; j ++)
        {
                sb2.setCharAt(j, '0');
                 
              // System.out.println("j value is " + j);
                
                lowDensityString3 = sb2.toString();
                
                
                
                
                
            //    System.out.println("High density string 3 " + lowDensityString3);
    
         lowDensityString3AsHex = NumberUtils.longBinaryStringToHex(lowDensityString3);
         
        // System.out.println("6 hex long " + lowDensityString3AsHex);
          
          randomPlainText3 = NumberUtils.hexToASCII(lowDensityString3AsHex);
          
          encryptionView.setPlainTextAsTextArea(randomPlainText3); 
           
          encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());
            
          cipherText3 = encryptionModel.getCompleteCipherText();
          
        //  System.out.println("Cipher text 6 " + cipherText3);
          
          cipherTextAsBinary3 = NumberUtils.hexToBinary(cipherText3);
          
      //   fileLocationController.outputNISTHighDensityPlainTextFile(cipherTextAsBinary3);
         
              test7FileWriter.print(cipherTextAsBinary3);
         
         sb2.setCharAt(j, '1');
         
         encryptionModel.resetEncryption();  
         //applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0");
             
         }
        
        sb2.setCharAt(i, '1');
        }
           }
         test7FileWriter.close();
         System.out.println("Test 7 complete");


       
    
    
    
       }  
}
