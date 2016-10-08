/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.models;

import java.util.HashMap;
import mscapplication.utils.NumberUtils;

/**
 *
 * @author andrsale
 */
public class CAGrid {


private HashMap <Integer, Boolean> cellHash;
private HashMap <Integer,Integer> cellHashInteger;
private HashMap<Integer, Boolean> tmp;
private HashMap <Integer,Integer> cellHashTmp;
private int width;
private Boolean cellState;
private int cellStateInt;
private Boolean nextState;
private int nextStateInteger;
private StringBuilder binaryString;
private char a_char;
private int value;
private int boundaryConditions;
private int siteSpacing;
private Boolean[] ruleCode;
private int[] ruleCodeInteger;
private Boolean xorSiteSpacing;
    
public CAGrid(int widthValue, int boundaryConditions, int siteSpacing, Boolean xorSiteSpacing) {
    
width = widthValue;

tmp = new HashMap();
cellHash = new HashMap();

cellHashInteger = new HashMap();
cellHashTmp = new HashMap();

binaryString = new StringBuilder("");

// 0 = Null Boundary Conditions, 1 = Peridic Boundary

this.boundaryConditions = boundaryConditions;
this.siteSpacing = siteSpacing;
this.xorSiteSpacing = xorSiteSpacing;

}


public void setBinaryGrid_tmp(String binaryString) {

    for (int i=0; i<binaryString.length(); i++) 
    {
    cellHashInteger.put(i,binaryString.charAt(i)=='1' ? (int)1 : (int)0);
    }
}


public String getBinaryString_tmp() {
        
binaryString.setLength(0);
      
      for(int i = 0; i < width; i++) {
          
       if(cellHashInteger.get(i) == 1) {
           
           binaryString.append("1");
           
       }            
       else {
           
           binaryString.append("0");      
       }  
}
 return binaryString.toString();
 
}

public void setGridInt () {
    cellHashInteger.putAll(cellHashTmp);   
}


public void iterateCellInt (int xPos, int yPos, int[] ruleNumber) {

int i = xPos;
int j = yPos;
    
cellStateInt = this.getNextStateInteger(ruleNumber, getLeftValueInt(i, j), getMiddleValueInt(i, j), getRightValueInt(i, j));

cellHashTmp.put(i,cellStateInt);

}

public int getLeftValueInt (int xPos, int yPos) {
    
int i = xPos;
int j = yPos;
    
int left;


if (i < siteSpacing)
    
{    
    if (boundaryConditions == 1)
    {   
        
       if (xorSiteSpacing)
           
       {
           int startingValue = this.getCellValueInt(i,j);
          
                int newValue;
          
                String finalValue = "";
          
                for (int z = 0 ; z <= i; z++) {
              
              
                     newValue = this.getCellValueInt(i - z,j);
              
                    finalValue =  NumberUtils.xorHex(NumberUtils.integerToHex(startingValue), NumberUtils.integerToHex(newValue));
             
                    startingValue = Integer.parseInt(finalValue);
                     }            
                    for (int t = 1; t < siteSpacing - i; t++){
                 
                   
                    newValue = this.getCellValueInt(width - t,j);
                    finalValue =  NumberUtils.xorHex(NumberUtils.integerToHex(startingValue), NumberUtils.integerToHex(newValue));
                 
                    startingValue = Integer.parseInt(finalValue);                    
                     }                             
                
                     left = Integer.parseInt(finalValue);
         }
    
    else {
        
        left = this.getCellValueInt((width - (siteSpacing - i)),j);
       }
       
       }

else    //null boundary
    {
        
        if (xorSiteSpacing)
        {
            int startingValue = this.getCellValueInt(i,j);
          
                int newValue;
          
                String finalValue = "";
          
                for (int z = 0 ; z <= i; z++) {
              
              
                     newValue = this.getCellValueInt(i - z,j);
              
                    finalValue =  NumberUtils.xorHex(NumberUtils.integerToHex(startingValue), NumberUtils.integerToHex(newValue));
             
                    startingValue = Integer.parseInt(finalValue);
                     }
            
            
            
            left = Integer.parseInt(finalValue);
            
        }
        
     else
        {
        
            left = 0;
        }
        
        }

}
else
{
         
   
         if (xorSiteSpacing) { 
          
                int startingValue = this.getCellValueInt(i,j);
          
                int newValue;
          
                String finalValue = "";
          
                for (int z = 0 ; z < siteSpacing; z++) {
              
//              int startingValue;
              
              
              newValue = this.getCellValueInt(i - siteSpacing + z,j);
              
             finalValue =  NumberUtils.xorHex(NumberUtils.integerToHex(startingValue), NumberUtils.integerToHex(newValue));
             
             startingValue = Integer.parseInt(finalValue);
                      
         
          }
                
                     left = Integer.parseInt(finalValue);
         }
                
         
         else {
                        
                         left = this.getCellValueInt(i - siteSpacing,j);
                        }
          
}

return left;   
}


public int getRightValueInt (int xPos, int yPos) {
    
int i = xPos;
int j = yPos;

int right;
   
      
if (((width - 1) - siteSpacing) < i)
{         
    
     if (boundaryConditions == 1)
    {        
        
        if (xorSiteSpacing)
           
       {
        
         int startingValue = this.getCellValueInt(i,j);
          
                int newValue;
          
                String finalValue = "";
          
                for (int z = 0 ; z < width - i; z++) {
              
              
                     newValue = this.getCellValueInt(i + z,j);
              
                    finalValue =  NumberUtils.xorHex(NumberUtils.integerToHex(startingValue), NumberUtils.integerToHex(newValue));
             
                    startingValue = Integer.parseInt(finalValue);
                     }
                
                
             
                    for (int t = 1; t < siteSpacing - i; t++){
                 
                   
                        newValue = this.getCellValueInt(t,j);
                           //   System.out.println("new value" + newValue);
                    finalValue =  NumberUtils.xorHex(NumberUtils.integerToHex(startingValue), NumberUtils.integerToHex(newValue));
                 
                     startingValue = Integer.parseInt(finalValue);
                     
                     }
                               
                
                     right = Integer.parseInt(finalValue);
           

        
       }
        else {     
        
        right = this.getCellValueInt((width - 1) - (i - siteSpacing),j);
        }
    }
     else    //null boundary
    {
        right = 0;
    }
         
}                                   

else
{
    
         if (xorSiteSpacing) { 
          
                int startingValue = this.getCellValueInt(i,j);
          
                int newValue;
          
                String finalValue = "";
          
                for (int z = 0 ; z < siteSpacing; z++) {
              
//              int startingValue;
              
              
              newValue = this.getCellValueInt(i + siteSpacing - z,j);
              
             finalValue =  NumberUtils.xorHex(NumberUtils.integerToHex(startingValue), NumberUtils.integerToHex(newValue));
             
             startingValue = Integer.parseInt(finalValue);
                      
         
          }
                
                     right = Integer.parseInt(finalValue);
         }
                
         
         else {
                        
                         right = this.getCellValueInt(i + siteSpacing,j);
                        }
       
          
}
return right;   
}


public int getMiddleValueInt (int xPos, int yPos) {
    
int i = xPos;
int j = yPos;   

int middle;
   
middle = this.getCellValueInt(i,j);

return middle;   

}

public int getCellValueInt (int xPos, int yPos)
{
int cellValue;    

cellValue = cellHashInteger.get(xPos);

return cellValue;

}

public int getNextStateInteger (int[] ruleCodeInteger, int leftValue, int middleValue, int rightValue)
{
    
    if (leftValue == 0 && middleValue == 0 & rightValue == 0)
    {
        nextStateInteger = ruleCodeInteger[0];     
    }
    else if (leftValue == 0 && middleValue == 0 & rightValue == 1)
    {
        nextStateInteger = ruleCodeInteger[1];     
    }
    else if (leftValue == 0 && middleValue == 1 & rightValue == 0)
    {
        nextStateInteger = ruleCodeInteger[2];     
    }
    else if (leftValue == 0 && middleValue == 1 & rightValue == 1)
    {
        nextStateInteger = ruleCodeInteger[3];     
    }
    else if (leftValue == 1 && middleValue == 0 & rightValue == 0)
    {
        nextStateInteger = ruleCodeInteger[4];     
    }
    else if (leftValue == 1 && middleValue == 0 & rightValue == 2)
    {
        nextStateInteger = ruleCodeInteger[5];     
    }
    else if (leftValue == 1 && middleValue == 1 & rightValue == 0)
    {
        nextStateInteger = ruleCodeInteger[6];     
    }
    else if (leftValue == 1 && middleValue == 1 & rightValue == 1)
    {
        nextStateInteger = ruleCodeInteger[7];     
    }
    
    else
    {
       nextStateInteger = 1; 
    }
    
  return nextStateInteger;
    
}


}


