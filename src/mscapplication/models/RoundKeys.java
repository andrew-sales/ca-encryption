/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.models;

import java.util.Arrays;
import java.util.HashMap;
import mscapplication.utils.NumberUtils;

/**
 *
 * @author Andrew
 */
public final class RoundKeys {
    
private String lastDecimalRulesFromKey;
private  HashMap RoundKeyHashMap;
private HashMap roundKeysMap;
private HashMap <Integer, HashMap> binaryRuleKeyMap;
//private HashMap  <Integer, int[]> binaryRuleKeyRoundMap;
private  HashMap <Integer, HashMap> RoundKeyDecimalRulesHashMap;
private String lastRoundKey;
    private    String nextRoundKey;
    private HashMap <Integer, HashMap> decimalRuleKeyMap;
//    private HashMap <Integer, Integer> decimalRuleKeyRoundMap;
    private String roundKey;
    private    String decimalRulesString;
//private final HashMap <Integer, HashMap> RoundKeyBinaryRulesHashMap;

    public RoundKeys (int numberOfRounds, String key, int roundKeyOnOff) {
        
        roundKeysMap = new HashMap ();
        decimalRuleKeyMap = new HashMap<>();
        
        binaryRuleKeyMap = new HashMap<>();
//        binaryRuleKeyRoundMap = new HashMap();
//        decimalRuleKeyRoundMap = new HashMap();
        
    
      //  System.out.println("string key for round key" + key);
        
   if (roundKeyOnOff == 1) {     
        
 //  System.out.println("Round keys changed");    
       
   RoundKeyHashMap = this.createRoundKeyMap(numberOfRounds, key);  
   
   RoundKeyDecimalRulesHashMap = this.createRoundKeyDecimalRuleMap(RoundKeyHashMap);   
   
   }
   
   else
       
   {
       RoundKeyHashMap = this.createRoundKeyMapOff(numberOfRounds, key);    
       
   RoundKeyDecimalRulesHashMap = this.createRoundKeyDecimalRuleMap(RoundKeyHashMap);   
       
   }
   
   
        
    }

    
    public void updateRoundKeys(int numberOfRounds, String key, int roundKeyOnOff) {
        
        if (roundKeyOnOff == 1) {     
        
 //  System.out.println("Round keys changed");    
       
   RoundKeyHashMap = this.createRoundKeyMap(numberOfRounds, key);  
   
   RoundKeyDecimalRulesHashMap = this.createRoundKeyDecimalRuleMap(RoundKeyHashMap);   
   
   }
   
   else
       
   {
       RoundKeyHashMap = this.createRoundKeyMapOff(numberOfRounds, key);    
       
   RoundKeyDecimalRulesHashMap = this.createRoundKeyDecimalRuleMap(RoundKeyHashMap);   
       
   }
   
        
        
        
    }
    
    public HashMap createRoundKeyMap (int numberOfRounds, String SecretKey)
    {          
        
        
        lastRoundKey = SecretKey;
        
        
        for (int i = 0; i < numberOfRounds; i++)
        {          
            nextRoundKey = RoundKeys.createNextRoundKey(lastRoundKey);
            roundKeysMap.put(i, nextRoundKey);
            lastRoundKey = nextRoundKey;
          //  System.out.println("last key" + lastRoundKey);
        }
        return roundKeysMap;
    }
    
    
    ///haven't put the binary key in here yet
    
        public HashMap createRoundKeyMapOff (int numberOfRounds, String SecretKey)
    {          
        
        
        lastRoundKey = SecretKey;
       
        
        for (int i = 0; i < numberOfRounds; i++)
        {          
           // nextRoundKey = RoundKeys.createNextRoundKey(lastRoundKey);
            roundKeysMap.put(i, lastRoundKey);
//            lastRoundKey = nextRoundKey;          
        }
        return roundKeysMap;
    }
    
    
    
    public HashMap getRoundKeyMap () {
        
        return RoundKeyHashMap;
    }
    
    public HashMap createRoundKeyDecimalRuleMap (HashMap RoundKeyMap) {
        
//        
//        String roundKey;
//        String decimalRulesString;
       
        int decimalRule;
        
        for (int i = 0; i < RoundKeyMap.size(); i++){
            
          roundKey = RoundKeyMap.get(i).toString();
      //    System.out.println("roundkey" + roundKey);
          decimalRulesString = Key.createDecimalRulesString(roundKey);
      //    System.out.println("drs" + decimalRulesString);
          
//          HashMap <Integer, HashMap> decimalRuleKeyMap;
//    HashMap <Integer, Integer> decimalRuleKeyRoundMap;
         
          HashMap <Integer, Integer> decimalRuleKeyRoundMap = new HashMap();
          HashMap <Integer, int []> binaryRuleKeyRoundMap = new HashMap();
{
            for (int j = 0; j < decimalRulesString.length()/3; j++) {
 
                decimalRule =  Integer.parseInt(decimalRulesString.substring(j * 3, (j*3)+ 3));
                
                decimalRuleKeyRoundMap.put(j, decimalRule);
           //     
            //    System.out.println("decimal rule get" + j + " " + decimalRuleKeyRoundMap.get(j));
                
              //System.out.println("binary key" + Arrays.toString(NumberUtils.intToBinaryArray(decimalRule)));
                binaryRuleKeyRoundMap.put(j,NumberUtils.intToBinaryArray(decimalRule));
                
              //  System.out.println("binary rule map " + Arrays.toString(binaryRuleKeyRoundMap.get(j)));
                
            }
          //  System.out.println("values" + binaryRuleKeyRoundMap.values());
            
         //   System.out.println("added another map");
            decimalRuleKeyMap.put(i,decimalRuleKeyRoundMap);
          //  System.out.println(decimalRuleKeyMap.values());
            binaryRuleKeyMap.put(i,binaryRuleKeyRoundMap);
}
        }
     
        return decimalRuleKeyMap;
        

    }
    
    
    
    public HashMap getRoundKeyDecimalRuleMap () {
        
        return RoundKeyDecimalRulesHashMap;
    }
    
    
    public static String createNextRoundKey (String key) {
        
        
       String decimalRulesString = Key.createDecimalRulesString(key);
       String incrementedRules = NumberUtils.incrementCARules(decimalRulesString);
  String HexincrementedRules = Key.createHexKeyStringFromDecimalRuleString(incrementedRules);
       String bumpedRules = NumberUtils.bumpCARules(HexincrementedRules);
       //  String bumpedRules = NumberUtils.bumpCARules(key);
       
//       System.out.println("bump")
       
       
  //     String incrementedRulesAsBinary = NumberUtils.hexToBinary(bumpedRules);
       
    //  String bitShiftedRules = NumberUtils.bitShift(incrementedRulesAsBinary);
      
       
//       String nextRoundKey = NumberUtils.longBinaryStringToHex(bumpedRules);

      //  return bumpedRules;
        return bumpedRules;
    }
    
    
    public int getEncryptionRuleNumber (int RoundsComplete, int cellNumber) {
 
    HashMap<Integer, Integer> decimalRuleKeyRoundMap;
    
    decimalRuleKeyRoundMap = RoundKeyDecimalRulesHashMap.get(RoundsComplete);    
        
    Integer ruleNumber = (decimalRuleKeyRoundMap.get(cellNumber));
  
    return ruleNumber;  
        
    }
    
    public int[] getEncryptionRuleNumberBinary (int RoundsComplete, int cellNumber) {
    

        
    
  // System.out.println("cell number" + cellNumber);
        
   HashMap<Integer, int []>  binaryRuleKeyRoundMap;
  // System.out.println(binaryRuleKeyRoundMap.values());
   binaryRuleKeyRoundMap = (binaryRuleKeyMap.get(RoundsComplete));    
        
     
   
    int[] ruleNumber = (binaryRuleKeyRoundMap.get(cellNumber));
 //   System.out.println("key values" + binaryRuleKeyRoundMap.values());
    
    //System.out.println("rule numbe from get method" + Arrays.toString(ruleNumber));
  
    return ruleNumber;  
        
    }
    
    public int getDecryptionRuleNumber (int RoundsComplete, int cellNumber) {
  
    HashMap<Integer, Integer> decimalRuleKeyRoundMap;
    
//    int HashMapSize = RoundKeyDecimalRulesHashMap.size();
    
    decimalRuleKeyRoundMap = RoundKeyDecimalRulesHashMap.get((RoundKeyDecimalRulesHashMap.size() - (RoundsComplete + 1)));    
        
    Integer ruleNumber = (decimalRuleKeyRoundMap.get(cellNumber));
  
    return ruleNumber;  
        
    }
    
    public int[] getDecryptionRuleNumberBinary (int RoundsComplete, int cellNumber) {
  
 HashMap<Integer, int []>  binaryRuleKeyRoundMap;
    
//    int HashMapSize = binaryRuleKeyMap.size();
    
    binaryRuleKeyRoundMap = binaryRuleKeyMap.get((binaryRuleKeyMap.size() - (RoundsComplete + 1)));    
        
    int[] ruleNumber = (binaryRuleKeyRoundMap.get(cellNumber));
  
    return ruleNumber;  
        
    }
    
  
    
    
    
    
}
