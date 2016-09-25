/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.filelocation;

import java.io.FileNotFoundException;
import java.io.IOException;
import mscapplication.utils.FileUtils;

/**
 *
 * @author Andrew
 */
public class FileLocationController {
    
    private final FileLocation fileLocation;
    private Boolean useExternalFilesFlag;

    public FileLocationController() {
        
        fileLocation = new FileLocation();
        useExternalFilesFlag = false;
    
    }
    
    public String readPlainTextFromFile () throws IOException {
        return FileUtils.convertFileToString(fileLocation.getPlainTextFileLocation());            
    }
    
    public void savePlainTextToFile (String textValue) throws IOException {
        FileUtils.writeStringToFile(textValue, fileLocation.getPlainTextFileLocation());
    }
    
    public void saveDecryptedPlainTextToFile (String textValue) throws IOException {
        FileUtils.writeStringToFile(textValue, fileLocation.getDecryptedPlainTextAsHexFileLocation());
    }
    
    public void appendDecryptedPlainTextAsHexToFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getDecryptedPlainTextAsHexFileLocation());
    }
    
    public void appendDecryptedPlainTextAsAsciiToFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getDecryptedPlainTextAsAsciiFileLocation());
    }
    
    
    
    public String readKeyFromFile () throws IOException {
        return FileUtils.convertFileToString(fileLocation.getKeyFileLocation());
    }
    
    public void saveKeyToFile (String textValue) throws IOException {
        FileUtils.writeStringToFile(textValue, fileLocation.getKeyFileLocation());
    }
    
    public void saveCipherTextToFile (String textValue) throws IOException {
        FileUtils.writeStringToFile(textValue, fileLocation.getCipherTextFileLocation());
    }
    
    public void appendCipherTextToFile (String textValue) throws IOException {
      //  System.out.println("append cipher text " + textValue);
        
        FileUtils.appendStringToFile(textValue, fileLocation.getCipherTextFileLocation());
        
        
    }
   
   
    public void outputBinaryToFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getBinaryOutputFileLocation());
    }
    
    public void outputDiehardFile (String textValue, int blockNumber) throws IOException {
        FileUtils.writeStringToDiehardFile(textValue, fileLocation.getDiehardOutputFileLocation(), blockNumber);
       
    }
    
   public String getDiehardOutputFileLocation () {       
        return fileLocation.getDiehardOutputFileLocation();
    }
    
    
    public void setUseExternalFilesFlag (Boolean booleanValue) {
        useExternalFilesFlag = booleanValue;  
    }
    
    public Boolean getUseExternalFilesFlag () {     
        return useExternalFilesFlag;
    }
    
    
    public String getOutputNISTplainTextCipherTextCorrelationPlainTextFileLocation () {       
        return fileLocation.getNISTplainTextCipherTextCorrelationPlainTextLocation();
    }
    
    
    
    
    public int getNumberOfBlocksFromExternalPlainTextFile (int blockSize) throws FileNotFoundException {
        
        
        return FileUtils.getNumberOfBlocksFromExternalPlainTextFile(fileLocation.getPlainTextFileLocation(),blockSize);
        
    }
    
    public int getNumberOfBlocksFromExternalCipherTextFile (int blockSize) throws FileNotFoundException {
        
        
        return FileUtils.getNumberOfBlocksFromExternalCipherTextFile(fileLocation.getCipherTextFileLocation(), blockSize);
        
    }

    public String plainTextFilePadString() throws FileNotFoundException {
        
       return FileUtils.plainTextFilePadString(fileLocation.getPlainTextFileLocation());
   
    }
    
    
    public String getPlainTextBlockFromFile (int startingPosition, int blockSize) throws IOException {
    
      
            return FileUtils.readOnePlainTextBlockFromFileinHex(fileLocation.getPlainTextFileLocation(),
                    startingPosition, blockSize);

    }
    
    public String getCipherTextBlockFromFile (int startingPosition, int blockSize) throws IOException {
    
      
            return FileUtils.readOneHexTextBlockFromFileinHex(fileLocation.getCipherTextFileLocation(),
                    startingPosition, blockSize);

    }
    
    public String getHexTextBlockFromFile (int startingPosition, int blockSize) throws IOException {
    
      
            return FileUtils.readOneHexTextBlockFromFileinHex(fileLocation.getCipherTextFileLocation(),
                    startingPosition, blockSize);

    }
    
     public void outputNISTKeyAvalancheFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getNISTKeyAvalacheTestLocation());
       
    }
     
     public void outputNISTPlainTextAvalancheFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getNISTPlainTextAvalacheTestLocation());
       
    }
     
       public void outputNISTPlainTextCipherTextCorrelationFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getNISTPlainTextCipherTextTestLocation());
       
    }
       
          public void outputNISTplainTextCipherTextCorrelationPlainTextFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getNISTplainTextCipherTextCorrelationPlainTextLocation());
       
    }
      
              public void outputNISTrandomPlainTextRandomKeyFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getNISTrandomPlainTextRandomKeyTestLocation());
       
    }
          
              public void outputNISTLowDensityPlainTextFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getNISTLowDensityPlainTextTestLocation());
       
    }  
              
                     public void outputNISTHighDensityPlainTextFile (String textValue) throws IOException {
        FileUtils.appendStringToFile(textValue, fileLocation.getNISTHighDensityPlainTextTestLocation());
       
    }    
              
    
    public void cleanUpFiles () {
        FileUtils.deleteAFile(fileLocation.getCipherTextFileLocation());
       FileUtils.deleteAFile(fileLocation.getBinaryOutputFileLocation());
       FileUtils.deleteAFile(fileLocation.getDiehardOutputFileLocation());
       FileUtils.deleteAFile(fileLocation.getDecryptedPlainTextAsHexFileLocation());
       FileUtils.deleteAFile(fileLocation.getDecryptedPlainTextAsAsciiFileLocation()); 
    }
}