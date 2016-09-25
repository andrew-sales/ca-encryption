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
import mscapplication.models.Key;
import mscapplication.models.RoundKeys;
import mscapplication.utils.NumberUtils;
import mscapplication.utils.StringUtils;

/**
 *
 * @author Andrew
 */
public class NISTTest2 {
     private ApplicationController applicationController;
    private FileLocationController fileLocationController;
    private MainController mainController;
    private MainView mainView;
    private EncryptionView encryptionView;
    private ApplicationView applicationView;
    private PrintWriter test2FileWriter;
    private EncryptionModel encryptionModel;
//private ApplicationView applicationView;
//private EncryptionView encryptionView;
//private MainView mainView;
//private ApplicationController applicationController;
//private FileLocationController fileLocationController;
private RoundKeys roundKeys;
private String IVString;


public NISTTest2 (ApplicationController applicationController) throws IOException {
        
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
        test2FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/2plaintextavalanchetest.txt", false)));
//        test3FileWriter1 = new PrintWriter(new BufferedWriter(new FileWriter("NIST/plaintextciphertextcorrelationplaintext.txt", false)));
//        
//        test3FileWriter2 = new PrintWriter(new BufferedWriter(new FileWriter("NIST/3plaintextciphertextcorrelationtest.txt", false)));
//        test5FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/5randomplaintextrandomkeytest.txt", false)));
//        
//        test6FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/6lowdensityplaintexttest.txt", false)));
//        test7FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/7highdensityplaintexttest.txt", false)));
        
        
        
    }   



// Test 2, PlainText Avalanche Test

   
//    To examine the sensitivity of individual algorithms to changes in input 
//    parameters (i.e., the key), 384 binary sequences are created in each case. 
//    In the key avalanche case, 384 sequences (1,048,576 bits per sequence) 
//    are parsed to a resulting string, constructed as follows: 
//    
//    given 12,288 random plain-texts, and a key of all zeroes, 
//    3,145,728 derived blocks (which means we perturb each plaintext 28 times blocks long - 16,384 bits) are concatenated. Each derived block is based on 
//    the XOR of the “ciphertext formed using the fixed key and a random 
//    plaintext,” and the “ciphertext formed using the fixed key and the 
//    perturbed random plaintext with a random ith bit changed.”   


    
    public void plainTextAvalancheTest() throws IOException 
        
   {
        
        String cipherText;
        String perturbedCipherText;
        String randomPlaintextAsHex;
        String randomPlainText;
        int randomPositionInPlainText;
        String plainTextAsBinary;
        String perturbedPlainText;

  
        String perturbedPlainTextString;
        String xorResult;
        
        String xorPlainTextAvalancheAsBinary;
        
//        int n = 128;
//        char[] chars = new char[n];
//        Arrays.fill(chars, '0');
       // String zeroKey = new String(chars);
        
//        applicationController.generateKey();
//
////applicationController.setKey(zeroKey, "0");
//      //  applicationController.generateKey();
//        roundKeys = this.applicationController.getRoundKeys();
//        IVString = applicationController.getIVInstance();
        encryptionModel = new EncryptionModel();

        //if doing the full 384 squences then 24576 (64 * 384) keys need to be tested.
        // this is 64 keys per sequence
        // for each sequence each key is used for 128 blocks
       
        for (int i = 0; i < 64 * mainView.getNumberOfDataSetsTextField(); i++) {
         encryptionModel.resetEncryption();
            //random plain text but currently in hex    
     //      System.out.println("random plain text number" + i + " of 8192"); 
            applicationController.generateKey();

//applicationController.setKey(zeroKey, "0");
      //  applicationController.generateKey();
        roundKeys = this.applicationController.getRoundKeys();
        IVString = applicationController.getIVInstance();
      
            
            Key key = new Key(128,0);
            randomPlaintextAsHex = key.getKey();

            
             //convert the plain text to ascii so it can be displayed in the plain text area
             randomPlainText = NumberUtils.hexToASCII(randomPlaintextAsHex);
             encryptionView.setPlainTextAsTextArea(randomPlainText);
           //  System.out.println("Random plain text" + randomPlainText);

        
        
        encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());  
 

              cipherText = encryptionModel.getCompleteCipherText();
          //     System.out.println("normal" + cipherText);
          //     System.out.println("round key object" + roundKeys);

            //   encryptionModel.resetEncryption();
              //maybe make this random
             for (int j = 0; j < 128; j++) {
             
             encryptionModel.resetEncryption();
             
             //   randomPositionInPlainText = NumberUtils.randInt(0,127);

              //  System.out.println("j" + j);
                
                plainTextAsBinary = NumberUtils.hexToBinary(randomPlaintextAsHex);
                
                perturbedPlainText = StringUtils.replaceCharacterInBinaryString(plainTextAsBinary, j);

                perturbedPlainTextString = NumberUtils.hexToASCII(NumberUtils.longBinaryStringToHex(perturbedPlainText));

                encryptionView.setPlainTextAsTextArea(perturbedPlainTextString);

              //  encryptionModel.resetEncryption();

                
                encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());  

                perturbedCipherText = encryptionModel.getCompleteCipherText();
            //    System.out.println("perturbed" + perturbedCipherText);

                //write the xor of the ciphertext and the pertubded cipher text is written to file
                xorResult = NumberUtils.xorHex(cipherText, perturbedCipherText);
                xorPlainTextAvalancheAsBinary = NumberUtils.hexToBinary(xorResult);
//System.out.println("xor" + xorPlainTextAvalancheAsBinary);

                test2FileWriter.print(xorPlainTextAvalancheAsBinary);
                
                encryptionModel.resetEncryption();

             }
             }
              test2FileWriter.close();
              System.out.println("Test 2 complete");
   }
    
    
    
    
    
}
