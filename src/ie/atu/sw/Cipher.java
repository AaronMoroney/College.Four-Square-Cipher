package ie.atu.sw;

import java.io.*;
import java.util.*;

public class Cipher {
    private final InputOutput io;
    private final Utils u = new Utils();

    public String encryptedText = "";
    public String decryptedText = "";

    // constructor
    public Cipher(InputOutput io) {
        // share state
        this.io = io;
    }

    private String[] bigrams;

    // TL / BR
    char[][] BOTTOMRIGHT = u.PLAIN;
    char[][] TOPLEFT = u.PLAIN;
    // TR / BL
    char[][] TOPRIGHT  = new char[5][5];
    char[][] BOTTOMLEFT = new char[5][5];

    // ** ENCRYPT **
    public String encryptTextFile() {
        StringBuilder sb = new StringBuilder();
        try {
             // Guard clause
            if(!io.hasFileToEncrypt) {
                throw new IllegalArgumentException("Please add a file to encrypt [option 1] and build a cipher key [option 4]");
            }

            bigrams = u.createBigrams(io.fileToEncrypt);

            // ** BEGIN ENCRYPTION **
            // step1: find the co-ordinates of the two chars in the bigram elements
            for(int i = 0; i < bigrams.length; i++) {
                String bigram = bigrams[i];

                char letter1 = bigram.charAt(0); // e.g. 'T' in "TH"
                char letter2 = bigram.charAt(1); // e.g. 'H' in "TH"

                int[] pos1 = u.findPosition(TOPLEFT, letter1);
                int[] pos2 = u.findPosition(BOTTOMRIGHT, letter2);

                // step2: pass the co-ordinates through TR, BL
                char cipher1 = TOPRIGHT[pos1[0]][pos2[1]];
                char cipher2 = BOTTOMLEFT[pos2[0]][pos1[1]];

                // build a new string
                sb.append(cipher1);
                sb.append(cipher2);
            }
            encryptedText = sb.toString();
            System.out.println(encryptedText);
            return encryptedText;
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    // ** DECRYPT **
    public String decryptTextFile() {
        StringBuilder sb = new StringBuilder();
        try {
            // Guard clause
            if(!io.hasFileToDecrypt) {
                throw new IllegalArgumentException("Please add a file to decrypt [option 5]");
            }

            bigrams = u.createBigrams(io.fileToDecrypt);

            // ** BEGIN ENCRYPTION **
            // step1: find the co-ordinates of the two chars in the bigram elements
            for(int i = 0; i < bigrams.length; i++) {
                String bigram = bigrams[i];

                char letter1 = bigram.charAt(0); // e.g. 'E' in "ES"
                char letter2 = bigram.charAt(1); // e.g. 'S' in "ES"

                int[] pos1 = u.findPosition(TOPRIGHT, letter1); //TR
                int[] pos2 = u.findPosition(BOTTOMLEFT, letter2); //BL

                // step2: pass the co-ordinates through TR, BL
                char cipher1 = TOPLEFT[pos1[0]][pos2[1]];
                char cipher2 = BOTTOMRIGHT[pos2[0]][pos1[1]];

                sb.append(cipher1);
                sb.append(cipher2);
            }
            decryptedText = sb.toString();
            System.out.println(decryptedText);
            return decryptedText;
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    // ** CREATE CIPHER KEY **
    // NOTE: HADN'T TIME TO IMPLEMENT SO WENT WITH EXAMPLE FROM LECTURE
    // NOTE: SHUFFLE RAND DOES SHUFFLE
    public void generateCipherKey(String key) {
        // String TRKey  = Utils.shuffleRand(key);
        // String BLKey = Utils.shuffleRand(key);

        String TRKey  = "ZGPTFOIHMUWDRCNYKEQAXVSBL";
        String BLKey =  "MFNBDCRHSAXYOGVITUEWLQZKP";

        int TRindex = 0;
        int BLindex = 0;

        // populate the quadrants
        // TOPRIGHT
        for(int TRrow = 0; TRrow < 5; TRrow++) {
            for(int TRcol = 0; TRcol < 5; TRcol++) {
                TOPRIGHT[TRrow][TRcol] = TRKey.charAt(TRindex);
                TRindex++;
            }
        }

        // BOTTOM RIGHT
        for(int BLrow = 0; BLrow < 5; BLrow++) {
            for(int BLcol = 0; BLcol < 5; BLcol++) {
                BOTTOMLEFT[BLrow][BLcol] = BLKey.charAt(BLindex);
                BLindex++;
            }
        }
    }
}