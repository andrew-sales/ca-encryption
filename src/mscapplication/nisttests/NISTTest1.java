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
import mscapplication.models.RoundKeys;
import mscapplication.utils.NumberUtils;
import mscapplication.utils.StringUtils;




/**
 *
 * @author Andrew
 */
public class NISTTest1 {
    
private final ApplicationController applicationController;
private final FileLocationController fileLocationController;
private final MainController mainController;
private final EncryptionController encryptionController;
private final MainView mainView;
private final EncryptionView encryptionView;
private final ApplicationView applicationView;
private final PrintWriter test1FileWriter;
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
    
    
    public void keyAvalancheTest1() throws IOException, InterruptedException 
    
    {
    
        String cipherText;
        String perturbedCipherText;
        String keyAsHex;
        String keyAsBinary;
        String perturbedKey;
        String perturbedKeyString;
        String xorResult;
        String xorKeyAvalancheAsBinary;
        
        encryptionModel = new EncryptionModel();
        encryptionView.setPlainTextAsTextArea("0000000000000000");
        
        //if doing the full 384 squences then 24576 (64 * 384) keys need to be tested.
        // this is 64 keys per sequence
        // for each sequence each key is used for 128 blocks
        
        for (int i = 0; i < 16 * mainView.getNumberOfDataSetsTextField(); i++) {
        encryptionModel.resetEncryption();
        applicationController.generateKey();       
        roundKeys = this.applicationController.getRoundKeys();      
        IVString = applicationController.getIVInstance();    
        keyAsHex = applicationController.getKeyInstance();
        keyAsBinary = NumberUtils.hexToBinary(keyAsHex);

        encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());       
        
        cipherText = encryptionModel.getCompleteCipherText();
        encryptionModel.resetEncryption();
          
        for (int j = 0; j < 512; j++) 
            {
            
         encryptionModel.resetEncryption();
            
        perturbedKey = StringUtils.replaceCharacterInBinaryString(keyAsBinary, j);
        perturbedKeyString = NumberUtils.longBinaryStringToHex(perturbedKey);
        applicationController.setKey(perturbedKeyString, "0");
        roundKeys = this.applicationController.getRoundKeys();
        IVString = applicationController.getIVInstance();
        encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());    
        perturbedCipherText = encryptionModel.getCompleteCipherText();

        xorResult = NumberUtils.xorHex(cipherText, perturbedCipherText);
        xorKeyAvalancheAsBinary = NumberUtils.hexToBinary(xorResult);   
        
        test1FileWriter.print(xorKeyAvalancheAsBinary);
        
        encryptionModel.resetEncryption(); 
        
            }
        
        }           
         test1FileWriter.close();
    }      
    }
  
