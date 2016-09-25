/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscapplication.encryption;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import mscapplication.application.ApplicationController;
import mscapplication.decryption.DecryptionView;
import mscapplication.filelocation.FileLocationController;
import mscapplication.main.MainView;
import mscapplication.models.CAGrid;
import mscapplication.models.RoundKeys;
import mscapplication.utils.FileUtils;
import mscapplication.utils.NumberUtils;
import mscapplication.utils.StringUtils;

/**
 *
 * @author Andrew
 */
public class EncryptionModel {

    private int roundsComplete;
    private int blocksEncrypted;
    private int totalNumberOfBlocks;
    private int plainTextBlockNumber;
    private String plainTextBlock;
    private String blockToBeEncrypted;
    private String leftBlockInput;
    private String rightBlockInput;
    private String rightBlockAsBinary;
    private String binaryOutputString;
    private String hexOutputString;
    private String rightBlockOutput;
    private String leftBlockOutput;
    private String cipherTextString;
    private String completeCipherText;
    private String IVString;
    private int encryptionMode;
    private int fileMode;
    private int numberOfFeistalRounds;
    private int[] ruleNumber;
    private int[] newRuleNumber;
    PrintWriter cipherWriter;
    PrintWriter binaryWriter;
    PrintWriter diehardWriter;

    private final ApplicationController applicationController;
    private final FileLocationController fileLocationController;
    private final EncryptionView encryptionView;
    private final DecryptionView decryptionView;
    private final MainView mainView;

    private CAGrid gridLabels;
    private RoundKeys roundKeys;
    private int blockSize;

    private int boundaryConditions;
    private int timeSpacing;
    private int siteSpacing;
    private Boolean xorSiteSpacing;

    public EncryptionModel() throws IOException {

        this.applicationController = ApplicationController.getApplicationController();
        encryptionView = this.applicationController.getEncryptionView();
        decryptionView = this.applicationController.getDecryptionView();
        mainView = this.applicationController.getMainView();

        fileLocationController = this.applicationController.getFileLocationController();

        roundsComplete = 0;
        blocksEncrypted = 0;
        totalNumberOfBlocks = 0;
        plainTextBlockNumber = 0;
        completeCipherText = "";
        blockSize = mainView.getBlockSizeTextArea();

        cipherWriter = new PrintWriter(new BufferedWriter(new FileWriter("ciphertext.txt", false)));
//        binaryWriter = new PrintWriter(new BufferedWriter(new FileWriter("binaryoutput.txt", true)));
        diehardWriter = new PrintWriter(new BufferedWriter(new FileWriter("diehardoutput.txt", false)));

    }

    /*runEncryption() method expects:
    - HybridRules - set of hybrid CA rules determined by the key
    - IVString - initialisation vector for CBC mode
    - numberOfFeistalRounds - number of Feistal rounds when encrypting each block
    - encryptionMode - 0 = EBC mode, 1 = CBC mode
     */
    public void runEncryption(RoundKeys roundKeys, String IVString, int numberOfFeistalRounds, int encryptionMode, int fileMode, int boundaryConditions, int timeSpacing, int siteSpacing, Boolean xorSiteSpacing) throws IOException {

        binaryWriter = new PrintWriter(new BufferedWriter(new FileWriter("NIST/4CipherBlockChainingtest.txt", true)));

        this.IVString = IVString;
        this.roundKeys = roundKeys;
        this.numberOfFeistalRounds = numberOfFeistalRounds;

        //encryption mode 0 = EBC, 1 = CBC
        this.encryptionMode = encryptionMode;
        this.fileMode = fileMode;
        this.timeSpacing = timeSpacing;
        this.boundaryConditions = boundaryConditions;
        this.siteSpacing = siteSpacing;
        this.xorSiteSpacing = xorSiteSpacing;

        gridLabels = new CAGrid(blockSize / 2, boundaryConditions, siteSpacing, xorSiteSpacing);

        this.setTotalNumberOfBlocks();
        this.completeEncryption();
    }

    public void setTotalNumberOfBlocks() throws FileNotFoundException {

        if (fileMode == 1) {
            totalNumberOfBlocks = fileLocationController.getNumberOfBlocksFromExternalPlainTextFile(blockSize);
//                System.out.println("total number of blocks " + totalNumberOfBlocks);
        } else {
            //otherwise the blocks are determined by taking the plain text as hex string              
            totalNumberOfBlocks = StringUtils.getNumberOfBlocks(NumberUtils.asciiToHex(encryptionView.getPlainTextAsTextArea()), blockSize);
//                System.out.println("total number of blocks " + totalNumberOfBlocks);
        }
    }

    public void completeEncryption() throws IOException {
        while (blocksEncrypted < totalNumberOfBlocks) {
            plainTextBlockNumber = blocksEncrypted + 1;
            //get a plain text block to encrypt
            this.getPlainTextBlock();
            //XOR the plain text block with IV
            this.plainTextXORWithIV();
            //split the block into left and right blocks        
            this.cutPlainText();
            //apply the right block to the CAGrid
            this.applyRightBlockToGrid();

            while (roundsComplete < numberOfFeistalRounds) {

             //   int plainTextAvalancheNumber = NumberUtils.getPlaintextAvalancheNumber(rightBlockAsBinary);
               // System.out.println("rule change" + plainTextAvalancheNumber%255);
                
                for (int t = 0; t < timeSpacing; t++) {
                    for (int i = 0; i < blockSize / 2; i++) {
                        
                        ruleNumber = roundKeys.getEncryptionRuleNumberBinary(roundsComplete, i);
                   //     System.out.println("old rule number" + Arrays.toString(ruleNumber));
                        
                        
                        
                        
                        
                        
                        //System.out.println(plainTextAvalancheNumber);
                        
                      //  newRuleNumber = NumberUtils.binaryArrayToIntAddAvalanche(ruleNumber,plainTextAvalancheNumber);
                    //    System.out.println("new rule number" + Arrays.toString(newRuleNumber));
                        
                        //    System.out.println("decimal number " + roundKeys.getEncryptionRuleNumber(roundsComplete, i) );

                        //   System.out.println("Rule number for round" + roundsComplete + " rules for cell " + i + " is" + Arrays.toString(ruleNumber));
                      //  gridLabels.iterateCellInt(i, 1, newRuleNumber);
                        gridLabels.iterateCellInt(i, 1, ruleNumber);
                    }
                    gridLabels.setGridInt();
                }

                binaryOutputString = gridLabels.getBinaryString_tmp();
                //   System.out.println("bos" + binaryOutputString);
                this.runFesitalXOR();
                this.setupNextFeistalRound();

                this.applyRightBlockToGrid();
            }
            this.createCipherTextBlock();
            blocksEncrypted++;
            this.resetFeistalNetwork();
        }

        encryptionView.setCipherTextAsHexTextArea(completeCipherText);
        decryptionView.setDecryptCipherTextAsHexTextArea(completeCipherText);
        cipherWriter.close();
        binaryWriter.close();
        diehardWriter.close();
        //     System.out.println("encryption complete");

    }

    public void getPlainTextBlock() throws IOException {

        if (fileMode == 1) {
            if (plainTextBlockNumber < totalNumberOfBlocks) {
                //8 plain text chars are taken from file which = 16 hex chars
                int startingPosition = ((plainTextBlockNumber - 1) * (blockSize / 8));  //this has been updated
                plainTextBlock = fileLocationController.getPlainTextBlockFromFile(startingPosition, blockSize);
//                        System.out.println("plaintext block" + plainTextBlock);
            } else {
                //if it is the last block in the file then padding is added
                int startingPosition = ((plainTextBlockNumber - 1) * (blockSize / 8)); //this has been updated
                String unPaddedPlainTextBlock = fileLocationController.getPlainTextBlockFromFile(startingPosition, blockSize);
                plainTextBlock = FileUtils.addPaddingToPlainTextBlock(unPaddedPlainTextBlock, fileLocationController.plainTextFilePadString());
//                        System.out.println("plaintext block" + plainTextBlock);
            }
        } else {
            //if external files are not used then the whole plain text is converted to hex and padding added
            //doing this every round is also inefficient
            String plainText = encryptionView.getPlainTextAsTextArea();
            String hexText = NumberUtils.asciiToHex(plainText);
            String paddedHexText = StringUtils.padPlainTextAsHex(hexText, blockSize);             //padding needs sorting - sorted now
            plainTextBlock = (StringUtils.getBlockNumber(paddedHexText, plainTextBlockNumber, blockSize));
//                System.out.println("plaintext block" + plainTextBlock);

        }
    }

    public String plainTextXORWithIV() throws IOException {

        if (encryptionMode == 1) {
            if (plainTextBlockNumber == 1) {
                blockToBeEncrypted = NumberUtils.xorHex(plainTextBlock, IVString);
                return blockToBeEncrypted;
            } else {
                blockToBeEncrypted = NumberUtils.xorHex(plainTextBlock, cipherTextString);
                return blockToBeEncrypted;
            }
        } else {

            blockToBeEncrypted = plainTextBlock;
            return blockToBeEncrypted;
        }
    }

    public void cutPlainText() {
        //block is divided in half
        leftBlockInput = StringUtils.cutPlainText(blockToBeEncrypted)[0];
        rightBlockInput = StringUtils.cutPlainText(blockToBeEncrypted)[1];
        rightBlockAsBinary = NumberUtils.hexToBinary(rightBlockInput);
    }

    public void applyRightBlockToGrid() {
        gridLabels.setBinaryGrid_tmp(rightBlockAsBinary);
    }

    public void runFesitalXOR() {
        roundsComplete = roundsComplete + 1;
        leftBlockOutput = rightBlockInput;
        hexOutputString = NumberUtils.longBinaryStringToHex(binaryOutputString);
        rightBlockOutput = NumberUtils.xorHex(leftBlockInput, hexOutputString);
    }

    public void setupNextFeistalRound() {
        leftBlockInput = leftBlockOutput;
        rightBlockInput = rightBlockOutput;
        rightBlockAsBinary = NumberUtils.hexToBinary(rightBlockInput);
    }

    public void createCipherTextBlock() {
        cipherTextString = leftBlockOutput + rightBlockOutput;

        if (fileMode == 1) {
            cipherWriter.print(cipherTextString);
            this.outputBinaryToFile(NumberUtils.hexToBinary(cipherTextString));
        } else {
            completeCipherText = completeCipherText + cipherTextString;
            // encryptionView.setCipherTextAsHexTextArea(completeCipherText);
        }
        //creates a binary output file

        //creates a DieHard compatible file of 32 bit integers in hex
//           this.outputDiehard((cipherTextString));     
    }

    public void outputBinaryToFile(String outputText) {

        binaryWriter.print(outputText);

    }

    public void closeBinaryFile() {

        binaryWriter.close();
    }

//    public void outputDiehard (String outputText) {
//         if ((blocksEncrypted + 1) % ((64/(blockSize / 4)) + 1) == 0) {
//             String newLineChar = System.getProperty("line.separator");           
//             diehardWriter.print(outputText + newLineChar);  
//         }
//         else 
//         {
//             diehardWriter.print(outputText); 
//         } 
//    }
    public void resetFeistalNetwork() {

        roundsComplete = 0;
    }

    //not being used?
    public void resetEncryption() {

        roundsComplete = 0;
        blocksEncrypted = 0;
        totalNumberOfBlocks = 0;
        plainTextBlockNumber = 0;
        completeCipherText = "";
//       encryptionView.setCipherTextAsHexTextArea("");

    }

    public String getCompleteCipherText() {

        return completeCipherText;
    }

}
