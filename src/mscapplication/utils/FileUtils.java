/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

/**
 *
 * @author Andrew
 */
public class FileUtils {
    
   

    
    public static String convertFileToString (String fileName) throws IOException {
        
       BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                   // sb.append("\n");
                    line = br.readLine();
                }
            return sb.toString();
                } finally {
                    br.close();
                } 
   
    }
    
    public static void writeStringToFile (String textValue, String fileName) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        
        try (//        PrintWriter writer = new PrintWriter(fileName, "UTF-8", true);
//        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)))) {
            
            
            
            writer.print(textValue);
        }
    }
    
    public static void appendStringToFile (String textValue, String fileName) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        
        try (//        PrintWriter writer = new PrintWriter(fileName, "UTF-8", true);
//        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
         //   System.out.println("text value " + textValue);
            writer.print(textValue);
        }
    }
    
    
    public static int getNumberOfBlocksFromExternalPlainTextFile(String fileName, int blockSize) throws FileNotFoundException {
    
     int initialBlockCount = FileUtils.getCharacterCount(fileName) / (blockSize/8);
     
     int remainder = FileUtils.getCharacterCount(fileName) % (blockSize/8);
     
     if (remainder == 0) {
         
         return initialBlockCount;
         
     }
     else {
         
         return initialBlockCount + 1;
     }   
     
     
    }
    
    public static int getNumberOfBlocksFromExternalCipherTextFile(String fileName, int blockSize) throws FileNotFoundException {
        
     int initialBlockCount = FileUtils.getCharacterCount(fileName) / (blockSize/4);
     
     int remainder = FileUtils.getCharacterCount(fileName) % (blockSize/4);
     
     if (remainder == 0) {
         
         return initialBlockCount;
         
     }
     else {
         
         return initialBlockCount + 1;
     }   
     
     
    }
    
    public static void writeStringToDiehardFile (String textValue, String fileName, int blockNumber) throws FileNotFoundException, IOException {
     
        
//        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
         
        String newLineChar;
         if (blockNumber % 5 == 0) {
//             System.out.println("character cout " + blockNumber);
//             System.out.println("character cout modulo " + blockNumber % 5);
             
//             String newLine = System.getProperty( "line.separator");
            
             newLineChar = System.getProperty("line.separator");
             
             appendStringToFile (textValue + newLineChar, fileName);
//             writer.newLine();
//             writeStringToFile (newLine, fileName);
   
             
         }
         else 
         {
//             System.out.println("how often do I get here");
             appendStringToFile (textValue, fileName);

             
         }
        
        
        
    }
    
    //gets the number of plain text characters in a file. There are 8 plain text characters to 16 hex
    public static int getCharacterCount(String fileName) throws FileNotFoundException {
	BufferedReader br = new BufferedReader(new FileReader(fileName));	
        
        FileReader fr = null;
		int charCount = 0;

		try {
                    fr = new FileReader(fileName);
		    
                    
                    while (fr.read() > -1) {
		       charCount++;
   		    }

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
                   if (null != fr)
                	try {
                           fr.close();
                           br.close();
			} catch (IOException e) {
			     e.printStackTrace();
		     }
		}
		return charCount;
	}
    
    public static String plainTextFilePadString (String fileName) throws FileNotFoundException {
        
        int numberOfCharacters = getCharacterCount(fileName);
//        int numberOfPlainTextBlocks = (numberOfCharacters/8);
        
        int remainder = (numberOfCharacters % 8) * 2;
        
        String padding = "";
    
        for (int i=0; i < ((16 - remainder)/2); i++) {
        
        padding = padding + NumberUtils.integerToHex((16 - remainder)/2);    
        
        }            
           
    
       return padding;
        
        
    }
    
    public static String addPaddingToPlainTextBlock (String textString, String paddingString) {
        
     int padLength = paddingString.length();
     
     String paddedString = textString.substring(0, 16 - padLength);
     
     return paddedString + paddingString;
        
        
    }
    
    //returns 8 plain text characters
    public static String readOnePlainTextBlockFromFileinHex (String fileName, int startingPosition, int blockSize) throws FileNotFoundException, IOException {
        
       BufferedReader reader = new BufferedReader(new FileReader(fileName));
       reader.skip(startingPosition); 
       StringBuilder sb = new StringBuilder();
       int c;
       char character;
       String hexCharacter;
       for(int i = startingPosition; i < startingPosition + (blockSize/8); i++)
           
       {
           
           c = reader.read();
//           while(c != -1) 
           
           {
           
           
      //     System.out.println("reader " + c);
           
           if (c == 13) {
               
    //           System.out.println("C = 13" + c);
                sb.append("0D"); 
           }
           
           else if (c == 10) {
               
              
               sb.append("0A"); 
           }
           
//           else if (c == 14) {
//               
//               sb.append("0E"); 
//           }
//           
//           else if (c == 20) {
//               System.out.println("was it 20 that crashed it?");
//               sb.append(""); 
//           }
               
           
           else {
           
     //          System.out.println("else");
               character = (char) c;
//           String hex = String.format("%04x", (int) c);
           
           
           hexCharacter = NumberUtils.asciiToHex(Character.toString(character));
           sb.append(hexCharacter); 
         
           
           
           
           }
           
      //     System.out.println(sb);
           
                   
           
           }
       
       



    }
     reader.close();  
    return sb.toString();
}
    
    public static String readOneHexTextBlockFromFileinHex (String fileName, int startingPosition, int blockSize) throws FileNotFoundException, IOException {
        
       BufferedReader reader = new BufferedReader(new FileReader(fileName));
       reader.skip(startingPosition); 
       StringBuilder sb = new StringBuilder();
       int c;
       char character;
       
       for(int i = startingPosition; i < startingPosition + (blockSize/4); i++)
           
       {
           
           c = reader.read();
//           while(c != -1) 
           
           {
           
           
        //   System.out.println("reader " + c);
           
//           if (c == 13) {
//               
//               System.out.println("C = 13" + c);
//                sb.append("0D"); 
//           }
           
//           else if (c == 10) {
//               
//              
//               sb.append("0A"); 
//           }
           
//           else {
           
            //   System.out.println("else");
               character = (char) c;
//           String hex = String.format("%04x", (int) c);
           
           
           sb.append((Character.toString(character)));   
           
//           }
           
         //  System.out.println(sb);
           
                   
           
           }
       
       



    }
    return sb.toString();
}
    
    
    public static void deleteAFile (String fileName) {
        
        
        File f = new File(fileName);
        
        f.delete();
        
    }
    
}