/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.models;

import mscapplication.utils.NumberUtils;

/**
 *
 * @author andrsale
 */
public class Key {
    
private String fullKeyString;
private String keyString;
private int keyLength;
private String IVString;
private int IVLength;

public Key (int keyLength, int IVLength) {
 
 this.keyLength = keyLength;
 this.IVLength = IVLength;
 RandomNumber random = new RandomNumber(this.keyLength + this.IVLength);
 
 
 fullKeyString = NumberUtils.bytesToHex(random.getRandomNumber());
 //System.out.println(fullKeyString);
 
 keyString = fullKeyString.substring((0),(this.keyLength)/4);        
         
 IVString = fullKeyString.substring(((this.keyLength)/4),((this.keyLength + this.IVLength)/4)); 
 
    
}


public String generateStartingKey(String keyString){
    
    String startingKey = keyString;
    
    
    for (int i = 0; i < keyString.length()/2; i++) {
        
        String newString = RoundKeys.createNextRoundKey(startingKey);
        
        String finalString = NumberUtils.xorHex(newString, startingKey);
        
        startingKey = finalString;
       
        
    }
    
    return startingKey;
    
}

public String getKey () {
   
  //  return this.keyString; 
   return this.generateStartingKey(keyString); 
    
}

public String getStartingKey () {
    
   return this.generateStartingKey(keyString); 
    
}

public String getIV () {
    
  return IVString;  
    
}

public static String createDecimalRulesString (String key) {
    
    String decimalRulesString ="";
    String subString;
    int decimalRules;
       
       for (int i = 0; i < key.length(); i=i+2)
           
       {
       
     decimalRules = NumberUtils.hexToDecimal(key.substring(i, i+2));
       
      subString = String.valueOf(decimalRules);
      
      if (subString.length() == 1)
          
      {
          subString = "00" + subString;
          
      }
      
       if (subString.length() == 2)
          
      {
          subString = "0" + subString;
          
      }
      
      decimalRulesString = decimalRulesString + subString;
      
       }        
    
    return decimalRulesString;
}


public static String createHexKeyStringFromDecimalRuleString (String decimalRuleString) {
    
String hexString = "";
String hexSubString;
int decimalRules;
    
 for (int i = 0; i < decimalRuleString.length(); i=i+3)
           
       {
       
      decimalRules =  Integer.parseInt((decimalRuleString.substring(i, i+3)));
        
      hexSubString = String.valueOf(NumberUtils.integerToHex(decimalRules));
      
      hexString = hexString + hexSubString;
      
       }
 
 return hexString;

    
}


public byte getKeyByte (int position, String ruleKey) {
    
String plainText = NumberUtils.hexToASCII(ruleKey);
byte [] byteValue = NumberUtils.stringToBytes(plainText);
    
return byteValue[position]; 
    
}  
    
}
