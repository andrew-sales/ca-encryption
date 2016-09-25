/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import mscapplication.application.ApplicationController;
import mscapplication.filelocation.FileLocationController;

/**
 *
 * @author Andrew
 */
public class MainController {
    
    private final ApplicationController applicationController;
    private final FileLocationController fileLocationController;
    private final MainView mainView;
    
     public MainController(ApplicationController applicationController, MainView mainView, FileLocationController fileLocationController) {
    
        this.mainView = mainView;
        this.applicationController = applicationController;
        this.fileLocationController = fileLocationController;

        this.mainView.addLoadKeyFromFileButtonActionListener (new LoadKeyFromFileButton());
        this.mainView.addSaveKeyToTextFileButtonActionListener (new SaveKeyTextToFileButton());
//        this.mainView.addRefreshDecimalRulesButtonActionListener (new RefreshDecimalRulesButton());
        
        
        
//        this.mainView.keyTextAreaDocumentListener (new keyTextAreaDocumentListener ());
         
         
     }

     //this needs to be fixed to actually generate the key from the file.
     
     public void setKeyTextAreaFromFile () {
        try {
            mainView.setKeyTextArea(MainController.this.fileLocationController.readKeyFromFile());
//            this.applicationController.setGenerateHybridRulesButtonState(true);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
   
     }

     
     class LoadKeyFromFileButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            MainController.this.setKeyTextAreaFromFile();
        }
        
        }
     
       class SaveKeyTextToFileButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            try {
                MainController.this.fileLocationController.saveKeyToFile(MainController.this.mainView.getKeyTextArea());
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        }
       
       
//       class RefreshDecimalRulesButton implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {  
//            MainController.this.applicationController.setKey(MainController.this.mainView.getKeyTextArea());
//           
//            MainController.this.applicationController.generateDecimalRulesFromKey();
//  
//            
//        }
        
//        }
//     
     
//     class keyTextAreaDocumentListener implements DocumentListener{
//
//         int maxLength;
//   
//        @Override
//        public void insertUpdate(DocumentEvent e) {
////            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            updateLog(e, "inserted into");
//            maxLength = e.getLength();
//            
//        }

//        @Override
//        public void removeUpdate(DocumentEvent e) {
//            
//            updateLog(e, "removed from");
//            maxLength = maxLength - e.getLength();
//          //  System.out.println("remaining " + maxLength);
//            
//            
//            if (maxLength == 0)
//            {
//                applicationController.setGenerateHybridRulesButtonState(false);
//                
//                
//            }
//    
//        }

//        @Override
//        public void changedUpdate(DocumentEvent e) {
////            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//        
//        public void updateLog(DocumentEvent e, String action) {
//        
//            Document doc = (Document)e.getDocument();
//            int changeLength = e.getLength();
//       //     System.out.println("dcument length " + e.getLength());
//            
//            
//        
//        
//        }
//        } 
     
     
     public MainView getMainView () {
         
         return this.mainView;
     }
     
    
}
