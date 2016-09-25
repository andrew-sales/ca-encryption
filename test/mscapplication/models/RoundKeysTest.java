/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.models;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class RoundKeysTest {
    
    public RoundKeysTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of CreateRoundKeyMap method, of class RoundKeys.
     */
    @Test
    public void testCreateRoundKeyMap() {
        System.out.println("CreateRoundKeyMap");
        int numberOfRounds = 0;
        String Key = "";
        int blockSize = 0;
        RoundKeys instance = null;
        HashMap expResult = null;
        HashMap result = instance.CreateRoundKeyMap(numberOfRounds, Key, blockSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRoundKeyHashMap method, of class RoundKeys.
     */
    @Test
    public void testCreateRoundKeyHashMap() {
        System.out.println("createRoundKeyHashMapForFirstRound");
        int roundNumber = 1;
        int blockSize = 16;
        String key = "0E35F47DDA1E1BD0";
        String expResult = "135155122191109143140104";
        //System.out.println("createRoundKeyHashMapForFirstRound");
        RoundKeys instance = new RoundKeys();
        HashMap result = instance.createRoundKeyHashMap(roundNumber, blockSize, key);
        assertEquals(expResult, result.toString());
        // TODO review the generated test code and remove the default call to fail.
        
      

    }

    /**
     * Test of createNextRoundKey method, of class RoundKeys.
     */
    @Test
    public void testCreateNextRoundKey() {
        System.out.println("createNextRoundKey");
        String key = "5A0B";
        RoundKeys instance = new RoundKeys();
        String expResult = "2d86";
        String result = instance.createNextRoundKey(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }
    
}
