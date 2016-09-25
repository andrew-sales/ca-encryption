/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.decryption;


import java.io.IOException;
import mscapplication.application.ApplicationController;
import mscapplication.main.MainView;
import mscapplication.application.ApplicationView;
import mscapplication.models.CAGrid;
import mscapplication.models.RoundKeys;

/**
 *
 * @author Andrew
 */
public class DecryptionController {
    
private DecryptionModel decryptionModel;
private MainView mainView;
private ApplicationController applicationController;
private RoundKeys roundKeys;
private String IVString;    
private int boundaryConditions;
private int timeSpacing;
private int siteSpacing;
private Boolean xorSiteSpacing;
    
public DecryptionController () {
    
    
    
}
    
    public void startDecryption () throws IOException {
        
        this.applicationController = ApplicationController.getApplicationController();
        this.mainView = applicationController.getMainView();
        roundKeys = this.applicationController.getRoundKeys();

        
        IVString = applicationController.getIVInstance();
  
        decryptionModel = new DecryptionModel();
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
        
        
        
        int EncryptionMode;
        
        
        
   
        
        if(mainView.getCBCRadioButton() == true ) 
        {
            EncryptionMode = 1;
        }
        else {
            EncryptionMode = 0;
        } 
        
         int fileMode;
        
         if(mainView.getUseExternalFilesCheckBox() == true) {
            
            fileMode = 1;
        }
        else {
            fileMode = 0;
        }
        
        
        
        
        decryptionModel.runDecryption(roundKeys, IVString, Integer.parseInt(mainView.getNumberOfFeistalRounds()), EncryptionMode, fileMode, boundaryConditions, timeSpacing, siteSpacing,  xorSiteSpacing);
    }
    
    //if the decryption hasn't happened then there is no model
    //need to move the model to the Application controller
    
    public void resetDecryption () {
        
        decryptionModel.resetDecryption();
         
    }
    
    public String getCompleteCipherText () {
        
       return decryptionModel.getCompletePlainText();
       
    } 
    
    
    
}
