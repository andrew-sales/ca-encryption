/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.filelocation;

/**
 *
 * @author Andrew
 */
public class FileLocation {

    private String keyFileLocation;
    private String plainTextFileLocation;
    private String cipherTextFileLocation;
    private String binaryOutputFileLocation;
    private String diehardOutputFileLocation;
    private String decryptedPlainTextAsHexFileLocation;
    private String decryptedPlainTextAsHexAsciiLocation;
    private String NISTKeyAvalacheTestLocation;
    private String NISTPlainTextAvalacheTestLocation;
    private String NISTPlainTextCipherTextTestLocation;
    private String NISTplainTextCipherTextCorrelationPlainText;
    private String NISTrandomPlainTextRandomKeyTestLocation;
    private String NISTLowDensityPlainTextTestLocation;
    private String NISTHighDensityPlainTextTestLocation;
    
    public FileLocation() {
    
    keyFileLocation = "key.txt";
    plainTextFileLocation = "plaintext.txt";
    cipherTextFileLocation = "ciphertext.txt";
    binaryOutputFileLocation = "binaryoutput.txt";
    diehardOutputFileLocation = "diehardoutput.txt";
    decryptedPlainTextAsHexFileLocation = "decryptedplaintextashex.txt";
    decryptedPlainTextAsHexAsciiLocation = "decryptedplaintextasascii.txt";
    NISTKeyAvalacheTestLocation = "NIST/1keyavalanchetest.txt";
    NISTPlainTextAvalacheTestLocation = "NIST/2plaintextavalanchetest.txt";
    NISTPlainTextCipherTextTestLocation = "NIST/3plaintextciphertextcorrelationtest.txt";
    NISTplainTextCipherTextCorrelationPlainText = "NIST/plaintextciphertextcorrelationplaintext.txt";
    NISTrandomPlainTextRandomKeyTestLocation = "NIST/5randomplaintextrandomkeytest.txt";
    NISTLowDensityPlainTextTestLocation = "NIST/6lowdensityplaintexttest.txt";
    NISTHighDensityPlainTextTestLocation = "NIST/7highdensityplaintexttest.txt";
    }

    public String getKeyFileLocation () {       
        return keyFileLocation;
    }
    
    public void setKeyFileLocation (String fileLocation) {       
        keyFileLocation = fileLocation;
    }
    
    public String getPlainTextFileLocation () {       
        return plainTextFileLocation;
    }
    
    public void setPlainTextFileLocation (String fileLocation) {       
        plainTextFileLocation = fileLocation;
    }
    
    public String getCipherTextFileLocation () {       
        return cipherTextFileLocation;
    }
    
    public void setCipherTextFileLocation (String fileLocation) {       
        cipherTextFileLocation = fileLocation;
    }
    
    public String getBinaryOutputFileLocation () {       
        return binaryOutputFileLocation;
    }
   
    public void setBinaryOutputFileLocation (String fileLocation) {       
        binaryOutputFileLocation = fileLocation;
    }

    public String getDiehardOutputFileLocation () {       
        return diehardOutputFileLocation;
    }
   
    public void setDiehardOutputFileLocation (String fileLocation) {       
        diehardOutputFileLocation = fileLocation;
    }
    
    public String getDecryptedPlainTextAsHexFileLocation () {       
        return decryptedPlainTextAsHexFileLocation;
    }
    
    public String getDecryptedPlainTextAsAsciiFileLocation () {       
        return decryptedPlainTextAsHexAsciiLocation;
    }
    
    public String getNISTKeyAvalacheTestLocation () {       
        return NISTKeyAvalacheTestLocation;
    }
    
    public String getNISTPlainTextAvalacheTestLocation () {       
        return NISTPlainTextAvalacheTestLocation;
    }
    
     public String getNISTPlainTextCipherTextTestLocation () {       
        return NISTPlainTextCipherTextTestLocation;
    }
     
     public String getNISTplainTextCipherTextCorrelationPlainTextLocation () {       
        return NISTplainTextCipherTextCorrelationPlainText;
    }
   
     
     public String getNISTrandomPlainTextRandomKeyTestLocation () {       
        return NISTrandomPlainTextRandomKeyTestLocation;
    }
   
 public String getNISTLowDensityPlainTextTestLocation () {       
        return NISTLowDensityPlainTextTestLocation;
    }
 
     public String getNISTHighDensityPlainTextTestLocation () {       
        return NISTHighDensityPlainTextTestLocation;
    }
   
}
