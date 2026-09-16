package ie.atu.sw;

import java.io.IOException;
import java.util.Random;

public class Utils {
    char[][] PLAIN = {
            {'A','B','C','D','E'},
            {'F','G','H','I','K'},
            {'L','M','N','O','P'},
            {'Q','R','S','T','U'},
            {'V','W','X','Y','Z'}
    };

    // ** SHUFFLE RAND **
    // Adapted  -> https://www.geeksforgeeks.org/dsa/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
    public static String shuffleRand(String s) {
        Random r = new Random();

        char[] chars = s.toCharArray();

        // Fisher–Yates shuffle
        for (int i = chars.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    // ** FIND POSITION **
    // returns an array of ints which are the co-ords of the position
    public int[] findPosition(char[][] grid, char letter) {
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {
                if(grid[row][col] == letter) {
                    return new int[] {row, col};
                }
            }
        }
        // if no position is found
        return null;
    }

    // ** Create Bigrams **
    public String[] createBigrams(String text) throws IOException {
        int size;

        // account for odd or even length text
        if(text.length() % 2 == 0) {
            // divide by two because each slot will contain two chars
            size = text.length() / 2;
        } else {
            size = (text.length() / 2) + 1;
        }

        // create a string array called bigrams with the size of the String
        String[] bigrams = new String[size];

        int index = 0;
        for(int i = 0; i < text.length(); i += 2) {
            if (i + 1 < text.length()) {
                bigrams[index] = "" + text.charAt(i) + text.charAt(i + 1);
            } else {
                // odd one out at the end - pad with X
                bigrams[index] = text.charAt(i) + "X";
            }
            index++;
        }
        return bigrams;
    }
}
