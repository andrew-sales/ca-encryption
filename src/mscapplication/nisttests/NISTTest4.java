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

/**
 *
 * @author Andrew
 */
public class NISTTest4 {
    
        private ApplicationController applicationController;
    private FileLocationController fileLocationController;
    private MainController mainController;
    private EncryptionController encryptionController;
    private MainView mainView;
    private EncryptionView encryptionView;
    private ApplicationView applicationView;
    private PrintWriter test4FileWriter;
    private EncryptionModel encryptionModel;
    private RoundKeys roundKeys;

    
     public NISTTest4(ApplicationController applicationController) throws IOException  {
    
     this.applicationController = applicationController;   
        
        applicationView = applicationController.getApplicationView();
        mainController = applicationController.getMainController();
        mainView = mainController.getMainView();
        encryptionController = applicationController.getEncryptionControllerNew();
      
        encryptionView = applicationController.getEncryptionView();
        fileLocationController = applicationController.getFileLocationController();
      
//        applicationView.setEncryptionDelayCheckBox(false);
       
 //       test1FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/1keyavalanchetest.txt", false)));
//     test4FileWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/4cipherblockchainingmodetest.txt", false)));
    
    
}
    
     /*TEST 4: CIPHER BLOCK CHAINING MODE
       
       
       Given a random 256-bit key, a 64-bit initialization vector (IV) of all 
       zeroes, and a 64bit plaintext block (PT) of all zeroes, a binary sequence 
       of 1,048,576 bits was constructed using ciphertext computed in the CBC mode. 
       That is, a binary sequence consisted of 16,256 concatenated 64-bit ciphertext 
       blocks. The first ciphertext block (CT1) is defined by CT1 = Ek(IV ¯ PT). 
       Subsequent ciphertext blocks were defined by CTi+1 = Ek(CTi ¯ PT) 
       for 1 <= i <= 16,256. In all, 300 binary sequences were constructed, 
       each with a different random 128-bit key.

       */  
   
       public void cipherBlockChainingMode() throws IOException {
        
//      applicationView.setEncryptionDelayCheckBox(false); 
        
        int n = 128;
        char[] chars = new char[n];
        Arrays.fill(chars, '0');
        String zeroIV = new String(chars);
       // applicationController.setKey(zeroKey, "0");   
      
                encryptionModel = new EncryptionModel();  
        
        for (int i = 0; i < mainView.getNumberOfDataSetsTextField(); i++){
//                for (int i = 0; i < 1; i++){
        
        System.out.println("round " + i + " of 300");
        
        applicationController.generateKey();
        roundKeys = this.applicationController.getRoundKeys();
      //  IVString = applicationController.getIVInstance();

        
        
//        encryptionView.setPlainTextAsTextArea(randomPlainText2);
             
        //run this with an external plaintext file of 1,048,576 bits
        //use the binary output file for this ones
        //it will produce 300 sequences in one file
        
        encryptionModel.runEncryption(roundKeys, zeroIV, Integer.parseInt(mainView.getNumberOfFeistalRounds()), 1, 1,mainView.getPeriodicBoundaryCondition(),mainView.getTimeSpacing(),mainView.getSiteSpacing(),mainView.getXorSiteSpacingCheckBox());    
           
           
//        mainView.setCBCRadioButton(true);
//        mainView.setUseExternalFilesCheckBox(true);
//        fileLocationController.setUseExternalFilesFlag(true);

//        applicationController.generateKey();
//        applicationController.setIVInstance("0000000000000000");          
//        applicationController.generateHybridRule();

//            try {
//                encryptionControllerNew.startEncryption();
//            } catch (IOException ex) {
//                Logger.getLogger(NISTTests.class.getName()).log(Level.SEVERE, null, ex);
//            }
      
            encryptionModel.resetEncryption();  
            
              
        }
      
//        encryptionModel.closeBinaryFile();
       
      //  encryptionModel.closeBinaryFile();
        System.out.println("Test 4 complete");

    
}
}
