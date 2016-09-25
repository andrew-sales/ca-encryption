/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.utils;

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
public class NumberUtilsTest {
    
    public NumberUtilsTest() {
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
     * Test of bytesToHex method, of class NumberUtils.
     */
    @Test
    public void testBytesToHex() {
        System.out.println("bytesToHex");
        byte[] bytes = null;
        String expResult = "";
        String result = NumberUtils.bytesToHex(bytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of xorHex method, of class NumberUtils.
     */
    @Test
    public void testXorHex() {
        System.out.println("xorHex");
        String a = "";
        String b = "";
        String expResult = "";
        String result = NumberUtils.xorHex(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hexToASCII method, of class NumberUtils.
     */
    @Test
    public void testHexToASCII() {
        System.out.println("hexToASCII");
        String hexValue = "";
        String expResult = "";
        String result = NumberUtils.hexToASCII(hexValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hexToASCIINewMethod method, of class NumberUtils.
     */
    @Test
    public void testHexToASCIINewMethod() {
        System.out.println("hexToASCIINewMethod");
        String hexValue = "";
        String expResult = "";
        String result = NumberUtils.hexToASCIINewMethod(hexValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intToBinary method, of class NumberUtils.
     */
    @Test
    public void testIntToBinary() {
        System.out.println("intToBinary");
        int value = 0;
        Boolean[] expResult = null;
        Boolean[] result = NumberUtils.intToBinary(value);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of byteToString method, of class NumberUtils.
     */
    @Test
    public void testByteToString() {
        System.out.println("byteToString");
        byte[] bytes = null;
        String expResult = "";
        String result = NumberUtils.byteToString(bytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of byteToBit method, of class NumberUtils.
     */
    @Test
    public void testByteToBit() {
        System.out.println("byteToBit");
        byte bytes = 0;
        String expResult = "";
        String result = NumberUtils.byteToBit(bytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bytearray2intarray method, of class NumberUtils.
     */
    @Test
    public void testBytearray2intarray() {
        System.out.println("bytearray2intarray");
        byte[] barray = null;
        int[] expResult = null;
        int[] result = NumberUtils.bytearray2intarray(barray);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of byteArrayToBit method, of class NumberUtils.
     */
    @Test
    public void testByteArrayToBit() {
        System.out.println("byteArrayToBit");
        byte[] bytes = null;
        String expResult = "";
        String result = NumberUtils.byteArrayToBit(bytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stringToBytes method, of class NumberUtils.
     */
    @Test
    public void testStringToBytes() {
        System.out.println("stringToBytes");
        String stringValue = "";
        byte[] expResult = null;
        byte[] result = NumberUtils.stringToBytes(stringValue);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of longBinaryStringToBytes method, of class NumberUtils.
     */
    @Test
    public void testLongBinaryStringToBytes() {
        System.out.println("longBinaryStringToBytes");
        String bin = "";
        byte[] expResult = null;
        byte[] result = NumberUtils.longBinaryStringToBytes(bin);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asciiToHex method, of class NumberUtils.
     */
    @Test
    public void testAsciiToHex() {
        System.out.println("asciiToHex");
        String asciiValue = "";
        String expResult = "";
        String result = NumberUtils.asciiToHex(asciiValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hexToBinary method, of class NumberUtils.
     */
    @Test
    public void testHexToBinary() {
        System.out.println("hexToBinary");
        String Hex = "";
        String expResult = "";
        String result = NumberUtils.hexToBinary(Hex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of binaryToHex method, of class NumberUtils.
     */
    @Test
    public void testBinaryToHex() {
        System.out.println("binaryToHex");
        String bin = "";
        String expResult = "";
        String result = NumberUtils.binaryToHex(bin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of longBinaryStringToHex method, of class NumberUtils.
     */
    @Test
    public void testLongBinaryStringToHex() {
        System.out.println("longBinaryStringToHex");
        String bin = "";
        String expResult = "";
        String result = NumberUtils.longBinaryStringToHex(bin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hexToDecimal method, of class NumberUtils.
     */
    @Test
    public void testHexToDecimal() {
        System.out.println("hexToDecimal");
        String hexValue = "";
        Integer expResult = null;
        Integer result = NumberUtils.hexToDecimal(hexValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of integerToHex method, of class NumberUtils.
     */
    @Test
    public void testIntegerToHex() {
        System.out.println("integerToHex");
        int intValue = 0;
        String expResult = "";
        String result = NumberUtils.integerToHex(intValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bitShift method, of class NumberUtils.
     */
    @Test
    public void testBitShift() {
        System.out.println("bitShift");
        
        String binaryRule = "11100011100010";
        String expResult = "01110001110001";
        String result = NumberUtils.bitShift(binaryRule);
        assertEquals(expResult, result);
        
        String binaryRule2 = "101010101010101";
        String expResult2 = "110101010101010";
        String result2 = NumberUtils.bitShift(binaryRule2);
        assertEquals(expResult2, result2);
     
    }

    /**
     * Test of randInt method, of class NumberUtils.
     */
    @Test
    public void testRandInt() {
        System.out.println("randInt");
        int min = 0;
        int max = 0;
        int expResult = 0;
        int result = NumberUtils.randInt(min, max);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementCARules method, of class NumberUtils.
     */
    @Test
    public void testIncrementCARules() {
        System.out.println("incrementCARules");
        String decimalSecretKey = "100200";
        String expResult = "101201";
        String result = NumberUtils.incrementCARules(decimalSecretKey);
        assertEquals(expResult, result);
        
        //this test check for looping around if the rule is 255
        System.out.println("incrementCARulesLoopTest");
        String decimalSecretKey2 = "100200255";
        String expResult2 = "101201000";
        String result2 = NumberUtils.incrementCARules(decimalSecretKey2);
        assertEquals(expResult2, result2);
        
        //this test checks that the 3 digits are preserved if the number is less than 100
        System.out.println("incrementCARulesLessThan100Test");
        String decimalSecretKey3 = "056043067";
        String expResult3 = "057044068";
        String result3 = NumberUtils.incrementCARules(decimalSecretKey3);
        assertEquals(expResult3, result3);
        
    }
    
    @Test
    public void bumpCARules() {
        System.out.println("bumpCARules");
        String decimalSecretKey = "100200303201";
        String expResult = "201100200303";
        String result = NumberUtils.bumpCARules(decimalSecretKey);
        assertEquals(expResult, result);
    
  
    }  
}
