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
import mscapplication.utils.NumberUtils;
import mscapplication.utils.StringUtils;




/**
 *
 * @author Andrew
 */
public class NISTTest1 {
    
 private ApplicationController applicationController;
    private FileLocationController fileLocationController;
    private MainController mainController;
    private EncryptionController encryptionController;
    private MainView mainView;
    private EncryptionView encryptionView;
    private ApplicationView applicationView;
    private PrintWriter test1FileWriter;
    private EncryptionModel encryptionModel;
private RoundKeys roundKeys;
private String IVString;
    
    
    
    public NISTTest1(ApplicationController applicationController) throws IOException  {
    
     this.applicationController = applicationController;   
        
        applicationView = applicationController.getApplicationView();
        mainController = applicationController.getMainController();
        mainView = mainController.getMainView();
        encryptionController = applicationController.getEncryptionControllerNew();
      
        encryptionView = applicationController.getEncryptionView();
        fileLocationController = applicationController.getFileLocationController();
      
//        applicationView.setEncryptionDelayCheckBox(false);
       
        test1FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/1keyavalanchetest.txt", false)));
    
    
    
}
    
    
 /* TEST 1: KEY AVALANCHE TEST
    
    
    To examine the sensitivity of individual algorithms to changes in input 
    parameters (i.e., the key), 384 binary sequences are created in each case. 
    In the key avalanche case, 384 sequences (1,048,576 bits per sequence) 
    are parsed to a resulting string, constructed as follows: 
    
    given 12,288 random keys, and a plaintext of all zeroes, 
    3,145,728 derived blocks (which means each plaintext is 128 blocks long - 16,384 bits) are concatenated. Each derived block is based on 
    the XOR of the “ciphertext formed using the fixed plaintext and a random 
    256-bit key,” and the “ciphertext formed using the fixed plaintext and the 
    perturbed random key with a random ith bit changed.”   
     */
    
    
    public void keyAvalancheTest1() throws IOException, InterruptedException {
    
        String cipherText;
        String perturbedCipherText;
        int randomPositionInKey;

        String keyAsHex;
        String keyAsBinary;
        String perturbedKey;
        String perturbedKeyString;
        String randomPlaintextAsHex;
        String randomPlainText;
        String xorResult;
        String xorKeyAvalancheAsBinary;
        
        encryptionModel = new EncryptionModel();
        encryptionView.setPlainTextAsTextArea("0000000000000000");
        
//        Key key = new Key(128,0);
//            randomPlaintextAsHex = key.getKey();
////
////            
////             //convert the plain text to ascii so it can be displayed in the plain text area
//             randomPlainText = NumberUtils.hexToASCII(randomPlaintextAsHex);
//             encryptionView.setPlainTextAsTextArea(randomPlainText);
//        
        
        //if doing the full 384 squences then 24576 (64 * 384) keys need to be tested.
        // this is 64 keys per sequence
        // for each sequence each key is used for 128 blocks
        
        for (int i = 0; i < 16 * mainView.getNumberOfDataSetsTextField(); i++) {
        encryptionModel.resetEncryption();
        
//         Key key = new Key(128,0);
//            randomPlaintextAsHex = key.getKey();
////
////            
////             //convert the plain text to ascii so it can be displayed in the plain text area
//             randomPlainText = NumberUtils.hexToASCII(randomPlaintextAsHex);
//             encryptionView.setPlainTextAsTextArea(randomPlainText);
      //       System.out.println("Random Plain Text" + randomPlainText);
     //        System.out.println("getrandomPlaingtext" + encryptionView.getPlainTextAsTextArea());
        
        
        
         //   System.out.println("key number" + i + " of 8192");
        applicationController.generateKey();
        
        roundKeys = this.applicationController.getRoundKeys();
        
        IVString = applicationController.getIVInstance();
        
        keyAsHex = applicationController.getKeyInstance();
     //   System.out.println("keyashex" + keyAsHex);
        
        keyAsBinary = NumberUtils.hexToBinary(keyAsHex);
    //    System.out.println("keyAsBinary" + keyAsBinary);
        
        encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());       
        
        cipherText = encryptionModel.getCompleteCipherText();
        
      //  System.out.println("cipher text" + cipherText);
 
          encryptionModel.resetEncryption();
          
       //   System.out.println("cipher text after reset" + encryptionModel.getCompleteCipherText());
        
        for (int j = 0; j < 512; j++) {
            
         //   encryptionModel = new EncryptionModel();
         encryptionModel.resetEncryption();
            
      //   System.out.println("J" + j);
         
     //    System.out.println("getrandomPlaingtext" + encryptionView.getPlainTextAsTextArea());

        //choose a random position to be changed in key   
//            randomPositionInKey = NumberUtils.randInt(0,511);
            
      //  System.out.println("random position to be changed" + randomPositionInKey);
    //  System.out.println("key bit changed" + randomPositionInKey);
        
//            keyAsHex = applicationController.getKeyInstance();

      //  System.out.println("starting key" + keyAsHex);
          
          
     //     System.out.println("starting key" + keyAsHex);
          
            
           
   //     System.out.println("key as binary" + keyAsBinary);

      // System.out.println("Random" + randomPositionInKey);
       perturbedKey = StringUtils.replaceCharacterInBinaryString(keyAsBinary, j);
     //  System.out.println("perturbed key as binary" + perturbedKey);

            //convert binary key back to hex via bytes

        perturbedKeyString = NumberUtils.longBinaryStringToHex(perturbedKey);
     //   System.out.println("perturbed key as hex" + perturbedKeyString);

            //second encryption with perturbedKeyString
        applicationController.setKey(perturbedKeyString, "0");
//            applicationController.generateHybridRule();
         //   encryptionControllerNew.startEncryption();
            
            
        
        
        
        
        roundKeys = this.applicationController.getRoundKeys();
      //  System.out.println("round keys" + roundKeys);
        
        
        
        IVString = applicationController.getIVInstance();
        
       
    //    encryptionView.setPlainTextAsTextArea("0000000000000000");
        
       
        encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());    

        //perturbed cipher text
        perturbedCipherText = encryptionModel.getCompleteCipherText();
        
       // System.out.println("Perturbed cipher text" + perturbedCipherText);

   //     encryptionController.resetEncryption();  

            //write the xor of the ciphertext and the pertubded cipher text is written to file
        
            
      //      System.out.println("cipher text" + cipherText);
            xorResult = NumberUtils.xorHex(cipherText, perturbedCipherText);
        xorKeyAvalancheAsBinary = NumberUtils.hexToBinary(xorResult);   
        
    //    System.out.println("output" + xorKeyAvalancheAsBinary);
        
        test1FileWriter.print(xorKeyAvalancheAsBinary);
        
           encryptionModel.resetEncryption(); 
        
        }
        
        }    
        
         test1FileWriter.close();
     //   System.out.println("Test 1 part 1 complete");
    }
       
    }
  
