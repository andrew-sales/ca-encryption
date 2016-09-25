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
import mscapplication.application.ApplicationController;
import mscapplication.application.ApplicationView;
import mscapplication.encryption.EncryptionController;
import mscapplication.encryption.EncryptionModel;
import mscapplication.encryption.EncryptionView;
import mscapplication.filelocation.FileLocationController;
import mscapplication.main.MainController;
import mscapplication.main.MainView;
import mscapplication.models.Key;
import mscapplication.models.RoundKeys;
import mscapplication.utils.FileUtils;
import mscapplication.utils.NumberUtils;

/**
 *
 * @author Andrew
 */
public class NISTTest3 {
    
      private ApplicationController applicationController;
    private FileLocationController fileLocationController;
    private MainController mainController;
    private MainView mainView;
    private EncryptionView encryptionView;
    private ApplicationView applicationView;
    private PrintWriter test3FileWriter1;
    private PrintWriter test3FileWriter2;
        private PrintWriter test3FileWriter3;
    private EncryptionModel encryptionModel;
//private ApplicationView applicationView;
//private EncryptionView encryptionView;
//private MainView mainView;
//private ApplicationController applicationController;
//private FileLocationController fileLocationController;
private RoundKeys roundKeys;
private String IVString;



public NISTTest3 (ApplicationController applicationController) throws IOException {
        
      this.applicationController = applicationController;   
        
        applicationView = applicationController.getApplicationView();
        mainController = applicationController.getMainController();
        mainView = mainController.getMainView();
//        encryptionControllerNew = applicationController.getEncryptionControllerNew();
//      
        encryptionView = applicationController.getEncryptionView();
        fileLocationController = applicationController.getFileLocationController();
      
//        applicationView.setEncryptionDelayCheckBox(false);
       
//        test1FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/1keyavalanchetest.txt", false)));
//        test1FileWriter2 = new PrintWriter(new BufferedWriter(new FileWriter("NIST/1keyavalanchetest2.txt", false)));
      //  test2FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/2plaintextavalanchetest.txt", false)));
        test3FileWriter1 = new PrintWriter(new BufferedWriter(new FileWriter("NIST/plaintextciphertextcorrelationplaintext.txt", false)));
//        
        test3FileWriter2 = new PrintWriter(new BufferedWriter(new FileWriter("NIST/3plaintextciphertextcorrelationtest.txt", false)));
         test3FileWriter3 = new PrintWriter(new BufferedWriter(new FileWriter("NIST/fortestingbinaryrandom.txt", false)));
//        test5FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/5randomplaintextrandomkeytest.txt", false)));
//        
//        test6FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/6lowdensityplaintexttest.txt", false)));
//        test7FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/7highdensityplaintexttest.txt", false)));
        
        
        
    }   
    
    
    
     /* TEST 3: PLAINTEXT-CIPHERTEXT CORRELATION
   
        In order to study the correlation of plaintext/ciphertext pairs, 128 sequences 
       (1,048,576 bits per sequence) were examined for each algorithm. Given a random 256-bit 
       key and 8,128 random plaintext blocks, a binary sequence was constructed concatenating 
       8,128 derived blocks (where a derived block is the result of applying the XOR operator 
       on the plaintext block and its corresponding ciphertext block computed in the ECB mode).
      Using the 8,128 (previously selected) plaintext blocks, the procedure was repeated 127 times, 
      i.e., once for each (additional) random 128-bit key.
      */
       public void plainTextCipherTextCorrelationTest() throws IOException {
     
        //this test is run in ECB mode
      //  mainView.setEBCRadioButton(true);      
       // applicationController.generateKey();
//        applicationController.generateHybridRule(); 
       
        int numberOfBlocks;
            numberOfBlocks = 8192;
            //8192
            
          String randomPlaintextAsHex;   
           String randomPlainText;
            String randomPlainText2;           
             String cipherText;            
            String randomPlaintextAsHex2;        
            String derivedBlock;
            String derivedBlockAsBinary;
            
        
        //generate random file needed for this test
        for (int i = 0; i < numberOfBlocks; i++)
            
        {           
            Key key = new Key(128,0);
            //System.out.println(key);
            randomPlaintextAsHex = key.getKey();
          //  System.out.println(randomPlaintextAsHex);
            randomPlainText = NumberUtils.hexToASCII(randomPlaintextAsHex);
           
            test3FileWriter1.print(randomPlainText);
//test3FileWriter3.print(NumberUtils.hexToBinary(NumberUtils.asciiToHex(randomPlainText)));
        }
        
        test3FileWriter1.close();
        test3FileWriter3.close();
        
        //need to append to a plain text file the first time and then use that for subsequent tests
        //with the same key
         encryptionModel = new EncryptionModel();
         
         
         for (int j = 0; j < mainView.getNumberOfDataSetsTextField(); j++){
        
        System.out.println("key number " + j + "of 10");
            applicationController.generateKey();
       
//         if (j==0) {   
//            applicationController.setKey("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc", "0");
//     //   applicationController.generateKey();
//        
//         }
//        
        
        roundKeys = this.applicationController.getRoundKeys();
        IVString = applicationController.getIVInstance();
       
        
        
        for (int i = 0; i < numberOfBlocks; i++) {
            randomPlainText2 = FileUtils.readOnePlainTextBlockFromFileinHex(
                    "NIST/plaintextciphertextcorrelationplaintext.txt", i, mainView.getBlockSizeTextArea());
            
            encryptionView.setPlainTextAsTextArea(randomPlainText2);
             encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());     
             
            cipherText = encryptionView.getCipherTextAsHexTextArea();  
            
         //   randomPlaintextAsHex2 = NumberUtils.asciiToHex(randomPlainText2);          
            derivedBlock = NumberUtils.xorHex(randomPlainText2, cipherText);          
            derivedBlockAsBinary = NumberUtils.hexToBinary(derivedBlock);
            test3FileWriter2.print(derivedBlockAsBinary);                   
            encryptionModel.resetEncryption();  
        }
         }
               
         test3FileWriter1.close();
         test3FileWriter2.close();
         System.out.println("Test 3 complete");
         
     }
       
}
       
       
    
    
    

