/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import mscapplication.models.Key;

/**
 *
 * @author Andrew
 */
public class NumberUtils { 
    
    
    
    private static char[] chars;
    private static int[] bits;
    private  static char[] hexArray;
    private static StringBuilder output;
    private static String binaryRuleString;
    private static StringBuilder hex;
    private static  StringBuilder main;
    private static  StringBuilder section;
    
    
    
    public NumberUtils() {
    
    bits = new int[8];
    hexArray = "0123456789ABCDEF".toCharArray();
    
  main = new StringBuilder("");
     
  section  = new StringBuilder(""); 
    
    
}
    
    
    public static String bytesToHex(byte[] bytes) {
        
//    final char[] hexArray = "0123456789ABCDEF".toCharArray();
    
        char[] hexChars = new char[bytes.length * 2];
  //  System.out.println(bytes.length);
    int v;
    
    
    for ( int j = 0; j < bytes.length; j++ ) {
        v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
}
    
    
    public static String xorHex(String a, String b) {
    chars = new char[a.length()];
    
    for (int i = 0; i < chars.length; i++) {
    chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
    }
     return new String(chars);
}
    
    
    
    
    

private static int fromHex(char c) {
    if (c >= '0' && c <= '9') {
        return c - '0';
    }
    if (c >= 'A' && c <= 'F') {
        return c - 'A' + 10;
    }
    if (c >= 'a' && c <= 'f') {
        return c - 'a' + 10;
    }
    throw new IllegalArgumentException();
}

private static char toHex(int nybble) {
    if (nybble < 0 || nybble > 15) {
        throw new IllegalArgumentException();
    }
    return "0123456789ABCDEF".charAt(nybble);
}

public static String hexToASCII(String hexValue)
{
    output = new StringBuilder("");
    
    for (int i = 0; i < hexValue.length(); i += 2)
    {
        String str = hexValue.substring(i, i + 2);
        output.append((char) Integer.parseInt(str, 16));
      //  System.out.println("position " + i + ":" + (char) Integer.parseInt(str, 16));
    }
   // System.out.println("Ascii message:" +  output.toString());
    return output.toString();
}


//public static String hexToASCIINewMethod (String hexValue)
//{
//String hex = hexValue;
// output = new StringBuilder();
//String str;
//    for (int i = 0; i < hex.length(); i+=2) {
//       // System.out.println("hex length" + hex.length());
//        
//        str = hex.substring(i, i+2);
//      //  System.out.println("str" + str);
//        output.append((char)Integer.parseInt(str, 16));
//     //   System.out.println((char)Integer.parseInt(str, 16));
//    }
//    return output.toString();
//}




//public static Boolean[] intToBinary (int value)
//{
//    int input = value;
//
//    Boolean[] bits = new Boolean[8];
//    for (int i = 7; i >= 0; i--) {
//        bits[i] = (input & (1 << i)) != 0;
//    }
//
//    return bits;
//}



//working here at the moment

public static int[] intToBinaryArray (int value)
{

// int[] bits;
      bits = new int[8];

    binaryRuleString = (Integer.toBinaryString(value));
  
//   for (int j = 0; j < 8 - binaryRuleString.length(); j++ )
//       
//   {
//       bits[j] = 0;
//   }
   
   
//    for (int i = 0; i < binaryRuleString.length(); i++) {
//        
//    String section = binaryRuleString.substring(i, (i + 1));
//      
//    bits[i] =  Integer.parseInt(section);
//        
//    }
    
    for (int i = 0; i < binaryRuleString.length(); i++) {
        
    
    bits[8 - binaryRuleString.length() + i] = (binaryRuleString.charAt(i)=='1' ? (int)1 : (int)0);
    
    }
    
   // System.out.println("bits " + Arrays.toString(bits));
    
    return bits;


}

//public static String byteToString (byte[] bytes) {
//    String str;
//    try {
//        str = new String(bytes, "UTF-8");
//    } catch (UnsupportedEncodingException e) {
//        // this should never happen because "UTF-8" is hard-coded.
//        throw new IllegalStateException(e);
//    }
//    return str;
//}

    
//public static String byteToBit (byte bytes) {
//
//
//String s1 = String.format("%8s", Integer.toBinaryString(bytes & 0xFF)).replace(' ', '0');
// 
//
//return s1; // 10000001
//    
//    
//}

// public static int[] bytearray2intarray(byte[] barray)
// {
//   int[] iarray = new int[barray.length];
//   int i = 0;
//   for (byte b : barray)
//       iarray[i++] = b & 0xff;
//   return iarray;
// }



//public static String byteArrayToBit (byte [] bytes) {
//
//String s1 = null; 
//String s2 ="";    
//for(byte b : bytes){
//
//s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
// 
//s2 = s1 + s2;   
//}
//
//return s2; // 10000001
//    
//    
//}

public static byte [] stringToBytes (String stringValue) {
    
    byte[] b = stringValue.getBytes(Charset.forName("UTF-8"));
    
    return b;
}

//public static byte [] longBinaryStringToBytes (String bin) {
//    
//    byte[] bval = new BigInteger(bin, 2).toByteArray();
//    return bval;
//    
//}

public static String asciiToHex(String asciiValue)
{
    char[] chars = asciiValue.toCharArray();
    hex = new StringBuilder();
    for (int i = 0; i < chars.length; i++)
    {
    
        if (Integer.toHexString((int) chars[i]).length() < 2) {
            
            hex.append(0); 
            hex.append(Integer.toHexString((int) chars[i])); 
      
        }
        else {
        
        hex.append(Integer.toHexString((int) chars[i]));

        }
        
    }
    
    
    return hex.toString();
}


//This method below ensures that leading 0's are not lost from the Hex conversion
public static String hexToBinary(String Hex) {
    
    
    
    String toReturn = new BigInteger(Hex, 16).toString(2);
    
    
    
    return String.format("%" + (Hex.length()*4) + "s", toReturn).replace(' ', '0');
}




//public static String binaryToHex(String bin) {
//   String returnString = String.format("%8X", Long.parseLong(bin,2));
//
//   returnString = returnString.replaceAll(" ", "0");
//
//    return returnString;
//}


public static String longBinaryStringToHex (String bin) {

//String hexString = "";  



String binaryBlock;
String hexBlock;
int decimal;

 
main.setLength(0);
section.setLength(0);
 
 
//System.out.println("bin length" + bin.length());
        
        for(int i = 0; i < bin.length(); i = i + (8)) 
            
        
            {             
                binaryBlock = bin.substring(i, i + (8));              
                decimal = Integer.parseInt(binaryBlock,2);

     hexBlock = Integer.toString(decimal,16);
      
      
     
     if (hexBlock.length() < 2) {  
            
         section.append("0"); 
         
         
//            hexBlock = '0' + hexBlock; 
        }
     
section.append(hexBlock);

//main.append(section);     
//hexString = hexString + hexBlock;
    
}

return section.toString();

}


public static int hexToDecimal (String hexValue){

    return Integer.parseInt(hexValue, 16);

}


public static String integerToHex (int intValue) { 
    
    StringBuilder sb = new StringBuilder();
    sb.append(Integer.toHexString(intValue));
if (sb.length() < 2) {
    sb.insert(0, '0'); // pad with leading zero if needed
}
String hex = sb.toString();

return hex;
}

public static String bitShift(String binaryRule){
    
    String subString1 = binaryRule.substring(binaryRule.length() - 15, binaryRule.length());
    String subString2 = binaryRule.substring(0, binaryRule.length() - 15);
    
    String bitShiftedString = subString1 + subString2;
     
    return bitShiftedString;
    
}

public static String incrementCARules(String decimalSecretKey) {
    
    
    String finalRuleString = "";
    String incrementedRuleString = "";
    
    int decimalKeyLength = decimalSecretKey.length();
    String ruleNumberString;
    int ruleNumber;
    
    for (int i = 0; i < decimalKeyLength/3; i++)
    {
        
        ruleNumberString = decimalSecretKey.substring((i * 3), (i * 3) + 3);

        ruleNumber = Integer.parseInt(ruleNumberString);
        
        
            incrementedRuleString = String.valueOf(ruleNumber + 1 + i);
            
            if (Integer.parseInt(incrementedRuleString) > 255){
                 
                     incrementedRuleString = String.valueOf(Integer.parseInt(incrementedRuleString)-255);

                }
            
                if (incrementedRuleString.length() == 1) {
                    
                    incrementedRuleString = "00" + incrementedRuleString;

                }
                
                if (incrementedRuleString.length() == 2) {
                    
                    incrementedRuleString = "0" + incrementedRuleString;

                }
                
                 
//                else {
//                    
//                    incrementedRuleString = incrementedRuleString;   
//                }            
       
           
        finalRuleString = finalRuleString + incrementedRuleString;
    }
    
    return finalRuleString;
}

public static String bumpCARules(String decimalSecretKey) {
    StringBuilder newHexRuleString = new StringBuilder();
    

       newHexRuleString.append(decimalSecretKey.substring(decimalSecretKey.length() - 2,decimalSecretKey.length()));
       
       newHexRuleString.append((decimalSecretKey.substring(0,decimalSecretKey.length() - 2)));
//  System.out.println("before" + decimalSecretKey);
   //    System.out.println("bumped rules" + newHexRuleString.toString());
       
    return newHexRuleString.toString();
}


public static int randInt(int min, int max) {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}

public static int getPlaintextAvalancheNumber(String rightBlockAsBinary)
{
    int avalancheNumber;
    
   String decimalString = Key.createDecimalRulesString(NumberUtils.longBinaryStringToHex(rightBlockAsBinary));
   
   String ruleNumberString;
   int ruleNumber;
   int ruleNumberTotal = 0;
   
   for (int i = 0; i < decimalString.length()/3; i++)
   {
              
       
       ruleNumberString = decimalString.substring((i * 3), (i * 3) + 3);

        ruleNumber = Integer.parseInt(ruleNumberString);
       
       ruleNumberTotal = ruleNumber + ruleNumberTotal;
   }
   
   
  // System.out.println("rnt" + ruleNumberTotal); 
   return ruleNumberTotal; 
    
}



public static int[] binaryArrayToIntAddAvalanche (int[] binaryArray, int AvalancheNumber) {
    
   String binaryString = Arrays.toString(binaryArray);
    String strRet="";
        for(int i : binaryArray) {
            strRet+=Integer.toString(i);
        }
   
  
   
  // System.out.println("binary string" + strRet);
   
   String hexString = NumberUtils.longBinaryStringToHex(strRet);
   
   int decimalInt = NumberUtils.hexToDecimal(hexString);
   
   int newRuleNumber = (decimalInt + AvalancheNumber)%255;
   
  return NumberUtils.intToBinaryArray(newRuleNumber);
   
   
    
    
}

}
