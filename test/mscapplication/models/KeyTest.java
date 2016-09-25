/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.models;

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
public class KeyTest {
    
    public KeyTest() {
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
     * Test of getKey method, of class Key.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Key instance = null;
        String expResult = "";
        String result = instance.getKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIV method, of class Key.
     */
    @Test
    public void testGetIV() {
        System.out.println("getIV");
        Key instance = null;
        String expResult = "";
        String result = instance.getIV();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateDecimalRulesFromKey method, of class Key.
     */
    @Test
    public void testGenerateDecimalRulesFromKey() {
        System.out.println("generateDecimalRulesFromKey");
        String keyString = "C7DB2AA5C3B81417";
        Key instance = new Key(64, 32);
        String expResult = "199 219 042 165 195 184 020 023";
        String result = instance.generateDecimalRulesFromKey(keyString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getKeyByte method, of class Key.
     */
    @Test
    public void testGetKeyByte() {
        System.out.println("getKeyByte");
        int position = 0;
        String ruleKey = "";
        Key instance = null;
        byte expResult = 0;
        byte result = instance.getKeyByte(position, ruleKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDecimalRulesString method, of class Key.
     */
    @Test
    public void testCreateDecimalRulesString() {
        System.out.println("createDecimalRulesString");
        String key = "2D86";
        String expResult = "045134";
        String result = Key.createDecimalRulesString(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of createHexKeyStringFromDecimalRuleString method, of class Key.
     */
    @Test
    public void testCreateHexKeyStringFromDecimalRuleString() {
        System.out.println("createHexKeyStringFromDecimalRuleString");
        String decimalRuleString = "000012034";
        String expResult = "000c22";
        String result = Key.createHexKeyStringFromDecimalRuleString(decimalRuleString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
