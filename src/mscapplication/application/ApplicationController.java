/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.application;


import mscapplication.decryption.DecryptionController;
import mscapplication.main.MainController;
import mscapplication.encryption.EncryptionView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mscapplication.main.MainView;
import mscapplication.filelocation.FileLocationController;
import mscapplication.decryption.DecryptionView;
import mscapplication.models.CAGrid;
import mscapplication.encryption.EncryptionController;
import mscapplication.models.Key;
import mscapplication.models.RoundKeys;
import mscapplication.nisttests.*;
import mscapplication.utils.NumberUtils;
import mscapplication.utils.StringUtils;

/**
 *
 * @author Andrew
 */
public class ApplicationController {
    
   private final ApplicationView applicationView;
   private final EncryptionView encryptionView;
   private final MainView mainView;
   private final EncryptionController encryptionController;
   private final DecryptionController decryptionControllerNew;
   private final MainController mainController;
   private final FileLocationController fileLocationController;
   private final DecryptionView decryptionView;
   private final NumberUtils numberUtils;
   private final StringUtils stringUtils;
   private NISTTest1 nistTest;
   private NISTTest2 nistTest2;
   private NISTTest3 nistTest3;
   private NISTTest4 nistTest4;
   private NISTTest5 nistTest5;
   private NISTTest6 nistTest6;
   private NISTTest7 nistTest7;
   private CAGrid  gridLabels;
   private Key key;
   private String keyInstance;
   private String IVInstance;
   private RoundKeys roundKeysInstance;
   private HashMap RoundKeyHashMap;
   private HashMap  RoundKeyDecimalRulesHashMap;
   private static ApplicationController applicationControllerInstance;

    
   public ApplicationController () { 
  
       
   //might be better to pass this in the constructor    
   
    applicationControllerInstance = this;   
       
   //instatiate views
   //appication view - application GUI structure    
   applicationView = new ApplicationView();
   mainView = new MainView();
   encryptionView = new EncryptionView();
   decryptionView = new DecryptionView();
 
  
   //combine views
   //add tabs to the main central tabbed pane
   applicationView.addTabToMainCentralTabbedPane("Main", mainView);
   applicationView.addTabToMainCentralTabbedPane("Encryption", encryptionView);
   applicationView.addTabToMainCentralTabbedPane("Decryption", decryptionView);
   applicationView.setVisible(true);
   
   //instantiate controllers
   //encryption controller - currently passing that an applicationView, to be replaced
   //with encryptionView at some point
   fileLocationController = new FileLocationController();
   
   mainController = new MainController(this, mainView, fileLocationController);
   
    encryptionController = new EncryptionController();
    decryptionControllerNew = new DecryptionController();
    numberUtils = new NumberUtils();
    stringUtils = new StringUtils();
   
    this.applicationView.addEncryptionButtonActionListener(new EncryptionButtonNewListener());
    this.applicationView.decryptButtonActionListener (new DecryptButtonListener());
    this.applicationView.generateKeyButtonActionListener (new GenerateKeyButtonListener());
    this.mainView.useExternalFilesCheckBoxActionListener (new UseExternalFilesCheckBoxActionListener()); 
    this.applicationView.addResetApplicationButtonActionListener (new resetApplicationButtonListener());  
    this.applicationView.addRunNISTTestsButtonListener(new runNISTTestsButtonListener());
    
    
    //disable all buttons on launch except for Key generation   
    this.applicationView.setDecryptButtonState(false);
    this.applicationView.setEncryptButtonState(false); 
    
   }
   
   public void generateKey() {
        int keyLength = mainView.getRuleKeyLength();
        int IVLength = mainView.getIVLength();        
        
        key = new Key(keyLength, IVLength);
        
        keyInstance = key.getKey();
        IVInstance = key.getIV();
        
        if (mainView.getRoundKeyCheckBox() == true) {
        
        roundKeysInstance = new RoundKeys(Integer.parseInt(mainView.getNumberOfFeistalRounds()),keyInstance, 1);
        
        }
        else
        {
            
           roundKeysInstance = new RoundKeys(Integer.parseInt(mainView.getNumberOfFeistalRounds()),keyInstance, 0); 
        }    
    }
   
   
   public void setKeysOnMainView () {
       
    mainView.setKeyTextArea(keyInstance);
    mainView.setIVTextArea(IVInstance);
    mainView.setRoundKeysTextArea(roundKeysInstance.getRoundKeyMap());
    mainView.setHybridRulesTable(mainView.getHybridRulesTable(roundKeysInstance.getRoundKeyDecimalRuleMap()));
    this.applicationView.setEncryptButtonState(true);
    this.applicationView.setDecryptButtonState(true);
       
   }
   
   
   public HashMap getRoundKeyDecimalRulesHashMap () {
       
       return RoundKeyDecimalRulesHashMap;      
   }
   
   public String getKeyInstance () {
       
       return keyInstance;
   }
   
   public String getIVInstance () {
       
       return IVInstance;
   }
   
   
   public void setKey (String keyValue, String IVValue) {
       
        this.keyInstance = key.generateStartingKey(keyValue);       
        this.IVInstance = IVValue;
       
        if (mainView.getRoundKeyCheckBox() == true) {
        
        
        this.roundKeysInstance.updateRoundKeys(Integer.parseInt(mainView.getNumberOfFeistalRounds()),keyInstance, 1);
        
        }
        else
        {
           this.roundKeysInstance.updateRoundKeys(Integer.parseInt(mainView.getNumberOfFeistalRounds()),keyInstance, 1);
        }    
   
   }
   
   public RoundKeys getRoundKeys () {
       
       return this.roundKeysInstance;
   }
    
    
    public void resetApplication () {
       
     this.encryptionController.resetEncryption();   
     this.decryptionControllerNew.resetDecryption();
     this.fileLocationController.cleanUpFiles();     
    }
   
    
    public void runNISTTest () throws IOException, InterruptedException {
        
        nistTest = new NISTTest1 (this); 
        nistTest2 = new NISTTest2 (this); 
        nistTest3 = new NISTTest3 (this); 
        nistTest4 = new NISTTest4 (this); 
        nistTest5 = new NISTTest5 (this); 
        nistTest6 = new NISTTest6 (this); 
        nistTest7 = new NISTTest7 (this); 
       
        nistTest.keyAvalancheTest1();
        nistTest2.plainTextAvalancheTest();
        nistTest3.plainTextCipherTextCorrelationTest();
        nistTest4.cipherBlockChainingMode();
        nistTest5.randomPlainTextRandomKey();
        nistTest6.lowDensityPlainTextTest();   
        nistTest7.highDensityPlainTextTest(); 
    }

   
    class EncryptionButtonNewListener implements ActionListener{
        @Override     
            public void actionPerformed(ActionEvent e) {
  
            try {
                //first step in the encryption process
                ApplicationController.this.encryptionController.startEncryption();
            } catch (IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            }    
    }
   
    class DecryptButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {          
               
            try {
                ApplicationController.this.decryptionControllerNew.startDecryption();
            } catch (IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }
   
    class GenerateKeyButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            ApplicationController.this.generateKey();
            ApplicationController.this.setKeysOnMainView();
        }
        
    }  
    
        class UseExternalFilesCheckBoxActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
  
                ApplicationController.this.fileLocationController.setUseExternalFilesFlag(
                        ApplicationController.this.mainView.getUseExternalFilesCheckBox());
      
        }
        
    }
        
      
        
         class runNISTTestsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                try {
                    ApplicationController.this.runNISTTest();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        }
         
         }
        
        
 class resetApplicationButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            ApplicationController.this.resetApplication();
 
        }
        
    }
 

 
 public MainController getMainController() {
     
     return this.mainController;
 }
 
 public MainView getMainView() {
     
     return this.mainView;
 }
 
 public EncryptionController getEncryptionControllerNew() {
     
     return this.encryptionController;
 }
 
 public EncryptionView getEncryptionView() {
     
     return this.encryptionView;
 }

 
  public DecryptionView getDecryptionView() {
     
     return this.decryptionView;
 }
 
  public ApplicationView getApplicationView() {
     
     return this.applicationView;
 }

   public FileLocationController getFileLocationController() {
     
     return this.fileLocationController;
 }
 
   public static ApplicationController getApplicationController () {
       
       
       return applicationControllerInstance;

   }
}
