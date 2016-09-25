/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.utils;

/**
 *
 * @author Andrew
 */
public class StringUtils {
    
    private static StringBuilder padding;
    private static String paddedPlainTextAsHex;
    private static StringBuilder blockString;
     private static StringBuilder newString;
    
    
    public StringUtils(){
        
        padding = new StringBuilder("");
         blockString = new StringBuilder("");
        newString = new StringBuilder("");
        
    }


// number of blocks i.e. 16 Hex characters, in any given string    
public static int getNumberOfBlocks (String textValue, int blockSize) {
    
    int numberOfBlocks;
    int numberOfWholeBlocks = textValue.length()/ (blockSize / 4);
    
        if (numberOfWholeBlocks * (blockSize / 4) != textValue.length())
        {
            
           numberOfBlocks = numberOfWholeBlocks + 1;
            
        }
    
        else {
            numberOfBlocks = numberOfWholeBlocks;
        }
       
        
        return numberOfBlocks;        
    }    

// plain text padding using PKCS7 http://en.wikipedia.org/wiki/Padding_%28cryptography%29

public static String padPlainTextAsHex(String textValue, int blockSize) {
   
    
   // System.out.println("starting text " + textValue);
    
//    int hexTextNumberOfBlocks = StringUtils.getNumberOfBlocks(textValue, blockSize);
    
   // System.out.println("number of blocks  " + hexTextNumberOfBlocks);
   // System.out.println("textlength  " + textValue.length());
    int remainder = textValue.length() % (blockSize / 4);
    
   // System.out.println("remainder  " + remainder);
    
    //String padding = "";
    padding.setLength(0);
    
    if(remainder == 0)
    {
        paddedPlainTextAsHex = textValue;
    }
    
    else {
    for (int i=0; i < (((blockSize / 4) - remainder)/2); i++) {
        
        padding.append(NumberUtils.integerToHex(((blockSize / 4) - remainder)/2));
        
//        padding = padding + NumberUtils.integerToHex(((blockSize / 4) - remainder)/2);    
    
    
    }            
    paddedPlainTextAsHex = textValue + padding;
    
     //System.out.println("padded  " + paddedPlainTextAsHex);
    
    }
    
    return paddedPlainTextAsHex;
    
}

//returns a block i.e. 16 Hex characters, of given a string and a block number


public static String getBlockNumber (String textValue, int blockNumber, int blockSize) {
    
       // String blockString = "";  
        
        
        blockString.setLength(0);
        
        char a_char;
        int stringPosition = (blockNumber - 1)* (blockSize/4);
        for(int i = stringPosition; i < (stringPosition + (blockSize/4)); i++) 
        
            {
                a_char = textValue.charAt(i);
                blockString.append(a_char);           
            }
    
       // System.out.println("blockString" + blockString.toString());
        
        return blockString.toString();
 
}
    
  // seperates a string into two equal halves      

  public static String[] cutPlainText(String textValue){
        
//        String plainTextBlock = applicationView.getPlainTextXORIVText();

        
        int len = textValue.length();
        String leftBlock = textValue.substring(0, len/2);
        String rightBlock = textValue.substring(len/2,len );
//        System.out.println(leftBlock);
//        System.out.println(rightBlock);
        
        String[] arr = new String[2];
        arr[0] = leftBlock;
        arr[1] = rightBlock;
        return arr;

    }
    
  public static String replaceCharacterInBinaryString (String textValue, int positionValue) {
      
     // String newString;
     // System.out.println(textValue);
      
      newString.setLength(0);
      
      String aLetter = Character.toString(textValue.charAt(positionValue));
      
      
      if ("0".equals(aLetter)){
      
      newString.append(textValue.substring(0,(positionValue)));
      newString.append("1");
      newString.append(textValue.substring(positionValue + 1));

      }
      
      else {
          
      newString.append(textValue.substring(0,(positionValue)));
      newString.append("0");
      newString.append(textValue.substring(positionValue + 1)); 
          
      }
      
// System.out.println("newString" + newString.toString());
 
    return newString.toString();
  }
  
  
}
