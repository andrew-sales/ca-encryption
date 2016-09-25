/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.encryption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mscapplication.application.ApplicationController;
import mscapplication.application.ApplicationView;
import mscapplication.main.MainView;
import mscapplication.filelocation.FileLocationController;
import mscapplication.main.MainController;
import mscapplication.models.RoundKeys;

/**
 *
 * @author Andrew
 */
public class EncryptionController {
    
private EncryptionModel encryptionModel;
private ApplicationView applicationView;
private EncryptionView encryptionView;
private MainView mainView;
private ApplicationController applicationController;
private FileLocationController fileLocationController;
private RoundKeys roundKeys;
private String IVString;
private int boundaryConditions;
private int timeSpacing;
private int siteSpacing;
private Boolean xorSiteSpacing;

public EncryptionController () {
    
      
        this.applicationController = ApplicationController.getApplicationController();
        
        this.applicationView = applicationController.getApplicationView();
        this.encryptionView = applicationController.getEncryptionView();
        this.mainView = applicationController.getMainView();
                
        fileLocationController = this.applicationController.getFileLocationController();
        
        encryptionView.addSavePlainTextToFileButtonActionListener (new SavePlainTextToFileButton());
        encryptionView.addLoadPlainTextFromFileButtonActionListener (new LoadPlainTextFromFileButton());
}
    
    
    public void startEncryption () throws IOException {

        roundKeys = this.applicationController.getRoundKeys();
        IVString = applicationController.getIVInstance();
        encryptionModel = new EncryptionModel();
        timeSpacing = mainView.getTimeSpacing();
        siteSpacing = mainView.getSiteSpacing();
        boundaryConditions = mainView.getPeriodicBoundaryCondition();
        xorSiteSpacing = mainView.getXorSiteSpacingCheckBox();
        
//        if(mainView.getPeriodicBoundaryRadioButton() == true ) {
//            
//           boundaryConditions = 1;
//        }
//       else {
//            boundaryConditions = 0;
//        }  
//        
   
        
        int encryptionMode;
        
        if(mainView.getCBCRadioButton() == true ) {
            encryptionMode = 1;
        }
       else {
            encryptionMode = 0;
        }  
        
        int fileMode;
        
        if(mainView.getUseExternalFilesCheckBox() == true) {
            
            fileMode = 1;
        }
        else {
            fileMode = 0;
        }
        
        encryptionModel.runEncryption(
                
                roundKeys, 
                IVString, 
                Integer.parseInt(mainView.getNumberOfFeistalRounds()), 
                encryptionMode, 
                fileMode, 
                boundaryConditions, 
                timeSpacing,
                siteSpacing,
                xorSiteSpacing
        ); 
        
        
    }
    
    public void resetEncryption () {
        
        encryptionModel.resetEncryption();
        
    }
    
    public String getCompleteCipherText () {
        
       return encryptionModel.getCompleteCipherText();
    }
    
    class LoadPlainTextFromFileButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            EncryptionController.this.setPlainTextAreaFromFile();
        }
        
        } 
    
    public void setPlainTextAreaFromFile () {
        try {
            System.out.println("Load");
            encryptionView.setPlainTextAsTextArea(EncryptionController.this.fileLocationController.readPlainTextFromFile());
        } catch (IOException ex) {
            Logger.getLogger(EncryptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
     }
    
    class SavePlainTextToFileButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            try {
                System.out.println("Save?");
                EncryptionController.this.fileLocationController.savePlainTextToFile(EncryptionController.this.encryptionView.getPlainTextAsTextArea());
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        }
    
    
    
    
}
