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
public class NISTTest6 {
    
 private ApplicationController applicationController;
    private FileLocationController fileLocationController;
    private MainController mainController;
    private EncryptionController encryptionController;
    private MainView mainView;
    private EncryptionView encryptionView;
    private ApplicationView applicationView;
    private PrintWriter test6FileWriter;

    private EncryptionModel encryptionModel;
//private ApplicationView applicationView;
//private EncryptionView encryptionView;
//private MainView mainView;
//private ApplicationController applicationController;
//private FileLocationController fileLocationController;
private RoundKeys roundKeys;
private String IVString;   
    
     public NISTTest6(ApplicationController applicationController) throws IOException  {
    
     this.applicationController = applicationController;   
        
        applicationView = applicationController.getApplicationView();
        mainController = applicationController.getMainController();
        mainView = mainController.getMainView();
        encryptionController = applicationController.getEncryptionControllerNew();
      
        encryptionView = applicationController.getEncryptionView();
        fileLocationController = applicationController.getFileLocationController();
      
//        applicationView.setEncryptionDelayCheckBox(false);
       
     //   test1FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/1keyavalanchetest.txt", false)));
    
         test6FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/6lowdensityplaintexttest.txt", false)));
    
}
    
    
    public void lowDensityPlainTextTest () throws IOException  
       
       {
           encryptionModel = new EncryptionModel();  
           
           
           
           for (int z = 0; z < mainView.getNumberOfDataSetsTextField(); z++) {
           
               System.out.println("key number " + z + "of 10");
               
               applicationController.generateKey();
                roundKeys = this.applicationController.getRoundKeys();
                IVString = applicationController.getIVInstance();
                  
                
                 

           
           
           //this test is run in ECB mode
          //  mainView.setEBCRadioButton(true);
//        applicationView.setUseExternalFilesCheckBox(true);
         
         //   applicationController.generateKey();
//            applicationController.generateHybridRule(); 
           
           
           
          char[] chars = new char[128];
          Arrays.fill(chars, '0');
          String lowDensityString1 = new String(chars); 
       //   System.out.println(lowDensityString1);
          
          String lowDensityString1AsHex = NumberUtils.longBinaryStringToHex(lowDensityString1);
        //  System.out.println("6 hex" + lowDensityString1AsHex);
          
          
          String randomPlainText = NumberUtils.hexToASCII(lowDensityString1AsHex);
          
          
          // do this as separate files
          encryptionView.setPlainTextAsTextArea(randomPlainText); 
           
          encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox()); 
            
         String cipherText = encryptionModel.getCompleteCipherText();
//          String cipherText = encryptionView.getCipherTextAsHexTextArea(); 
          
        //  System.out.println("Cipher text 6 " + cipherText);
          
          String cipherTextAsBinary = NumberUtils.hexToBinary(cipherText);
          
//         fileLocationController.outputNISTLowDensityPlainTextFile(cipherTextAsBinary);
         
         test6FileWriter.print(cipherTextAsBinary);
      //   System.out.println(cipherText);
          
          encryptionModel.resetEncryption();
     //     applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0");
         
         //64 blocks all 0s with a 1 in every space exactly once

         StringBuilder sb = new StringBuilder(lowDensityString1);
        
          String lowDensityString2;
          String lowDensityString2AsHex;
          String randomPlainText2;
          String cipherText2; 
          String cipherTextAsBinary2;
          
         
         
         
         for(int i = 0; i<128; i ++)
        {
                sb.setCharAt(i, '1');
                
                lowDensityString2 = sb.toString();
            //    System.out.println(lowDensityString2);
            //    System.out.println("lds2" + lowDensityString2);
    
         lowDensityString2AsHex = NumberUtils.longBinaryStringToHex(lowDensityString2);
         
        // System.out.println("6 hex long " + lowDensityString2AsHex);
          
          randomPlainText2 = NumberUtils.hexToASCII(lowDensityString2AsHex);
          
          encryptionView.setPlainTextAsTextArea(randomPlainText2); 
           
          encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox()); 
            
          cipherText2 = encryptionModel.getCompleteCipherText(); 
          
         // System.out.println("Cipher text 6 " + cipherText2);
          
          cipherTextAsBinary2 = NumberUtils.hexToBinary(cipherText2);
          
//         fileLocationController.outputNISTLowDensityPlainTextFile(cipherTextAsBinary2);
         
                  test6FileWriter.print(cipherTextAsBinary2);
            //       System.out.println(cipherText2);

         
         
         sb.setCharAt(i, '0');
         
         encryptionModel.resetEncryption();
      //   applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0");
             
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
                sb2.setCharAt(i, '1');
                
            //    System.out.println("i value is " + 1);
             //   lowDensityString2 = sb.toString();
                
                
                for(int j = i+1; j<128; j ++)
        {
                sb2.setCharAt(j, '1');
                 
          //     System.out.println("j value is " + j);
                
                lowDensityString3 = sb2.toString();
      //           System.out.println(lowDensityString3);
                
                
                
                
                
         //       System.out.println("lds with the 2 1s" + lowDensityString3);
    
         lowDensityString3AsHex = NumberUtils.longBinaryStringToHex(lowDensityString3);
         
        // System.out.println("6 hex long " + lowDensityString3AsHex);
          
          randomPlainText3 = NumberUtils.hexToASCII(lowDensityString3AsHex);
          
          encryptionView.setPlainTextAsTextArea(randomPlainText3); 
           
          encryptionModel.runEncryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 0, 0,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox()); 
            
          cipherText3 = encryptionModel.getCompleteCipherText(); 
          
       //   System.out.println("Cipher text 6 " + cipherText3);
          
          cipherTextAsBinary3 = NumberUtils.hexToBinary(cipherText3);
          
//         fileLocationController.outputNISTLowDensityPlainTextFile(cipherTextAsBinary3);
         
                  test6FileWriter.print(cipherTextAsBinary3);
      //            System.out.println(cipherText3);

         
         
         
         sb2.setCharAt(j, '0');
         
         encryptionModel.resetEncryption();  
      //   applicationView.setBlocksEncryptedLabel("Blocks Processed 0 of 0");
             
         }
        
        sb2.setCharAt(i, '0');
        }
           }
           test6FileWriter.close();
System.out.println("Test 6 complete");
}

    
}
