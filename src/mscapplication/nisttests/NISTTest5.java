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
public class NISTTest5 {
    
  private ApplicationController applicationController;
    private FileLocationController fileLocationController;
    private MainController mainController;
    private EncryptionController encryptionController;
    private MainView mainView;
    private EncryptionView encryptionView;
    private ApplicationView applicationView;
    private PrintWriter test5FileWriter;
    private EncryptionModel encryptionModel;
    private RoundKeys roundKeys;
private String IVString;
    

 public NISTTest5(ApplicationController applicationController) throws IOException  {
    
     this.applicationController = applicationController;   
        
        applicationView = applicationController.getApplicationView();
        mainController = applicationController.getMainController();
        mainView = mainController.getMainView();
        encryptionController = applicationController.getEncryptionControllerNew();
      
        encryptionView = applicationController.getEncryptionView();
        fileLocationController = applicationController.getFileLocationController();
      
//        applicationView.setEncryptionDelayCheckBox(false);
       
 //       test1FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/1keyavalanchetest.txt", false)));
    
    test5FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/5randomplaintextrandomkeytest.txt", false)));
    
}
    
    
    /*TEST 5: RANDOM PLAINTEXT/RANDOM 128-BIT KEYS 
       
       In order to examine the randomness of ciphertext (based on random 
       plaintext and random 128-bit keys), 128 sequences were constructed. 
       Each sequence was a result of the concatenation of 16,256 ciphertext 
       blocks using 16,256 random plaintexts and a random 128-bit key in the 
       ECB mode.
       */
       
       public void randomPlainTextRandomKey () throws IOException  
       
       {
           
           
           String cipherText;
        String perturbedCipherText;
        String randomPlaintextAsHex;
        String randomPlainText;
        int randomPositionInPlainText;
        String plainTextAsBinary;
        String perturbedPlainText;
        String cipherTextAsBinary;
           
           
                  encryptionModel = new EncryptionModel();  
                  
               for (int j = 0; j < mainView.getNumberOfDataSetsTextField(); j++) {
                   
                   System.out.println("Key number " + j + "of 128");
                  
                   applicationController.generateKey();
                   
//                   if (j==0){
//                   applicationController.setKey("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc", "0");
//                   }
                   
                   roundKeys = this.applicationController.getRoundKeys();
                IVString = applicationController.getIVInstance();
                  
                  
                  
                  for (int i = 0; i < 8192; i++){
                  
    

//  Key key = new Key(128,0);
//            randomPlaintextAsHex = key.getKey();
////            System.out.println("random plain text" + randomPlaintextAsHex);
//            
//             //convert the plain text to ascii so it can be displayed in the plain text area
//             randomPlainText = NumberUtils.hexToASCII(randomPlaintextAsHex);
             
                        randomPlainText = FileUtils.readOnePlainTextBlockFromFileinHex(
                    "NIST/plaintextciphertextcorrelationplaintext.txt", i, 128);  //this file looks odd
                        
                      encryptionView.setPlainTextAsTextArea(NumberUtils.hexToASCII(randomPlainText));
            //          System.out.println("random" + randomPlainText);
        
        
        
        
        
        encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox()); 

        cipherText = encryptionModel.getCompleteCipherText();
            
           // randomPlaintextAsHex2 = NumberUtils.asciiToHex(randomPlainText2);          
          //  derivedBlock = NumberUtils.xorHex(randomPlaintextAsHex2, cipherText);          
            cipherTextAsBinary = NumberUtils.hexToBinary(cipherText);
      //      test3FileWriter2.print(derivedBlockAsBinary);                   
          //  encryptionModel.resetEncryption();
        
        test5FileWriter.print(cipherTextAsBinary);
        
        
        
        
        
encryptionModel.resetEncryption();


        
       }
               }    
                   test5FileWriter.close();
        System.out.println("Test 5 complete");
               }
        
//    mainView.setUseExternalFilesCheckBox(false);
       // fileLocationController.setUseExternalFilesFlag(false);
            
       
           
//         //   encryptionController.resetEncryption();  
//         //   applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0"); 
//            
//             //this test is run in ECB mode
//             mainView.setEBCRadioButton(true);
////        applicationView.setUseExternalFilesCheckBox(true);
//         
//            applicationController.generateKey();
////            applicationController.generateHybridRule(); 
//       
//        int numberOfBlocks;
//            numberOfBlocks = 16256;
//        
//           String randomPlainText;
//           
//           String cipherText;
//           String cipherTextAsBinary;
//           
//           
//           
//            
//            
//        //generate random file needed for this test
////        for (int i = 0; i < numberOfBlocks; i++)
////            
////        {           
////            String randomPlaintextAsHex = Key.generateKey(64);
////            String randomPlainText = NumberUtils.hexToASCIINewMethod(randomPlaintextAsHex);
////            fileLocationController.outputNISTplainTextCipherTextCorrelationPlainTextFile(randomPlainText);
////            
////   
////        }
//        
//        
//        //need to append to a plain text file the first time and then use that for subsequent tests
//        //with the same key
//        for (int i = 0; i < numberOfBlocks; i++) {
//            
//    
////            String randomPlaintextAsHex = Key.generateKey(64);
////            String randomPlainText = NumberUtils.hexToASCIINewMethod(randomPlaintextAsHex);
//             
//            randomPlainText = FileUtils.readOnePlainTextBlockFromFileinHex(
//                    "NIST/plaintextciphertextcorrelationplaintext.txt", i);
//            
//          //  System.out.println("5 plaintext block" + randomPlainText);
//            
//            encryptionView.setPlainTextAsTextArea(randomPlainText);
//            
//            
//            encryptionControllerNew.startEncryption();
//            
//            cipherText = encryptionControllerNew.getCompleteCipherText();
////            getCompleteCipherText
//            
////            cipherText = encryptionView.getCipherTextAsHexTextArea();
//            
//           // System.out.println("number 5" + cipherText);
////            
////            String randomPlaintextAsHex = NumberUtils.asciiToHex(randomPlainText);
////            
////            String derivedBlock = NumberUtils.xorHex(randomPlaintextAsHex, cipherText);
////             
////            String derivedBlockAsBinary = NumberUtils.hexToBinary(derivedBlock);
//            
//System.out.println("ciphertext " + cipherText);
//            cipherTextAsBinary = NumberUtils.hexToBinary(cipherText);
//          //  fileLocationController.outputNISTrandomPlainTextRandomKeyFile(cipherTextAsBinary);
//        
//        test5FileWriter.print(cipherTextAsBinary);
//        
//            encryptionControllerNew.resetEncryption();  
//            //applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0");        
//            
//        }
//
//       test5FileWriter.close();
//        System.out.println("Test 5 complete");
       }

    

