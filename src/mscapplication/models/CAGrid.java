/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.models;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mscapplication.utils.NumberUtils;

/**
 *
 * @author andrsale
 */
public class CAGrid {


private HashMap <Integer, Boolean> cellHash;  //changing these to cell number and boolean from labels
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
//private int xorSiteSpacing;
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

//public void setBinaryGrid(String binaryString) {
//// this method has been updated
//
//    
//for (int i = 0; i < binaryString.length(); i++) {
//    
//    a_char = binaryString.charAt(i);
//    
//   value = Character.getNumericValue(a_char);
//    
//    if (value == 1) {
//    cellHash.put(i,true);
//    }
//    else {
//        
//       cellHash.put(i,false); 
//    }
//    
//}       
////    System.out.println(cellHash.toString());
//}

public void setBinaryGrid_tmp(String binaryString) {
// this method has been updated
//String section;
//    
//    for (int i = 0; i < binaryString.length(); i++) {
//        
//    section = binaryString.substring(i, (i + 1));
//      
//    cellHashInteger.put(i,Integer.parseInt(section));
//        
//    }
 
    
    
    for (int i=0; i<binaryString.length(); i++) {
    cellHashInteger.put(i,binaryString.charAt(i)=='1' ? (int)1 : (int)0);
}
    
    
//    System.out.println(cellHash.toString());
}



//public String getBinaryString() {
//
//binaryString = "";
//      
//      for(int i = 0; i < width; i++) {
//          
//       if(cellHash.get(i) == true) {
//           
//           binaryString = binaryString + "1";
//           
//       }   
//          
//       else {
//           binaryString = binaryString + "0";
//           
//       }  
//
//}
// return binaryString;
// 
//}


public String getBinaryString_tmp() {

//binaryString = "";

        
binaryString.setLength(0);

//System.out.println(value);
      
      for(int i = 0; i < width; i++) {
          
       if(cellHashInteger.get(i) == 1) {
           
//           binaryString = binaryString + "1";
           binaryString.append("1");
           
       }   
          
       else {
//           binaryString = binaryString + "0";
           
           binaryString.append("0");
           
       }  

}
 return binaryString.toString();
 
}




//public void setGrid () {
//    
//cellHash.putAll(tmp);  
////cellHashInteger.putAll(cellHashTmp);
//    
//}

public void setGridInt () {
    
//cellHash.putAll(tmp);  
cellHashInteger.putAll(cellHashTmp);
    
}

//public void iterateCell (int xPos, int yPos, int ruleNumber) {
//
//int i = xPos;
//int j = yPos;
//
//
//    
//cellState = this.getNextState(ruleNumber, getLeftValue(i, j), getMiddleValue(i, j), getRightValue(i, j));
//    
////cellStateInt = this.getNextStateInteger(ruleNumber, getLeftValueInt(i, j), getMiddleValueInt(i, j), getRightValueInt(i, j));
//
//
////cellHashTmp.put(i,cellStateInt);
//
//tmp.put(i,cellState);
//
//}

public void iterateCellInt (int xPos, int yPos, int[] ruleNumber) {

int i = xPos;
int j = yPos;


    
//cellState = this.getNextState(ruleNumber, getLeftValue(i, j), getMiddleValue(i, j), getRightValue(i, j));
    
cellStateInt = this.getNextStateInteger(ruleNumber, getLeftValueInt(i, j), getMiddleValueInt(i, j), getRightValueInt(i, j));


cellHashTmp.put(i,cellStateInt);

//tmp.put(i,cellState);

}

//public Boolean getLeftValue (int xPos, int yPos) {
//    
//int i = xPos;
//int j = yPos;
//    
//Boolean left;
//
//
//if (i < siteSpacing)
//    
//{    
//    if (boundaryConditions == 1)
//    {   
//        left = this.getCellValue((width - (siteSpacing - i)),j);
//    }
//    else    //null boundary
//    {
//        left = false;
//    }
//}
//
////else if (i == (width - 1))
////{   
////          left = this.getCellValue(i-1,j);
////
////}
// 
//else
//{
//          left = this.getCellValue(i - siteSpacing,j);
//}
//return left;   
//}


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
      //     System.out.println("hope not here");
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
                      //        System.out.println("new value" + newValue);
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
        //      System.out.println("hope not here");
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

//public Boolean getMiddleValue (int xPos, int yPos) {
//    
//int i = xPos;
//int j = yPos;   
//
//Boolean middle;
//   
//middle = this.getCellValue(i,j);
//
//return middle;   
//}

public int getMiddleValueInt (int xPos, int yPos) {
    
int i = xPos;
int j = yPos;   

int middle;
   
middle = this.getCellValueInt(i,j);

return middle;   

}

//public Boolean getCellValue (int xPos, int yPos)
//{
//Boolean cellValue;    
//cellValue = cellHash.get(xPos);
//return cellValue;
//
//}

public int getCellValueInt (int xPos, int yPos)
{
int cellValue;    

cellValue = cellHashInteger.get(xPos);

return cellValue;

}

//public Boolean getNextState (int ruleNumber, Boolean leftValue, Boolean middleValue, Boolean rightValue)
//{
//    
////    int ruleNumber = caRule;   
//    
//    ruleCode = NumberUtils.intToBinary(ruleNumber);
//    
////    Boolean nextState;
//    
//    if (leftValue == false && middleValue == false & rightValue == false)
//    {
//        nextState = ruleCode[0];     
//    }
//    else if (leftValue == false && middleValue == false & rightValue == true)
//    {
//        nextState = ruleCode[1];     
//    }
//    else if (leftValue == false && middleValue == true & rightValue == false)
//    {
//        nextState = ruleCode[2];     
//    }
//    else if (leftValue == false && middleValue == true & rightValue == true)
//    {
//        nextState = ruleCode[3];     
//    }
//    else if (leftValue == true && middleValue == false & rightValue == false)
//    {
//        nextState = ruleCode[4];     
//    }
//    else if (leftValue == true && middleValue == false & rightValue == true)
//    {
//        nextState = ruleCode[5];     
//    }
//    else if (leftValue == true && middleValue == true & rightValue == false)
//    {
//        nextState = ruleCode[6];     
//    }
//    else if (leftValue == true && middleValue == true & rightValue == true)
//    {
//        nextState = ruleCode[7];     
//    }
//    
//    else
//    {
//       nextState = true; 
//    }
//    
//  return nextState;
//    
//}


public int getNextStateInteger (int[] ruleCodeInteger, int leftValue, int middleValue, int rightValue)
{
    
//    int ruleNumber = caRule;   
    
  
    
  //  Boolean nextStateI;
    
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


