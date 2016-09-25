/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.models;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Andrew
 */
public class RandomNumber {
    
private int PRNLength;

 public RandomNumber(int randomLength) {
     
 PRNLength = randomLength;
     
 }
    
public byte[] getRandomNumber ()
{
    
    byte[] randomBytes = new byte[(PRNLength)/8]; 
    try {
	// Create a secure random number generator using the SHA1PRNG algorithm
	SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");	
	secureRandomGenerator.nextBytes(randomBytes);
   
    } catch (NoSuchAlgorithmException e) {
       
    }
    
 return randomBytes;   
    
}
    

}
