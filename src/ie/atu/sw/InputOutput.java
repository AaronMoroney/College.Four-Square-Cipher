package ie.atu.sw;

import java.io.*;
import java.util.*;

public class InputOutput {
    public boolean hasFileToEncrypt = false;
    public boolean hasFileToDecrypt = false;
    public String fileToEncrypt = "";
    public String fileToDecrypt = "";
    // ** PARSE **
    public String parse(String filepath) throws IOException {
        // a class which simplifies reading text from a character input stream
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));

        String line;

        // build a new string
        StringBuilder sb = new StringBuilder();

        // loop through line by line
        while((line = br.readLine()) != null) {
            // loop through a line itself
            for(int i = 0; i< line.length(); i++) {
                // upper case it
                char ch = Character.toUpperCase(line.charAt(i));
                // remove non char
                if(ch >= 'A' && ch <= 'Z') {
                    // if we find a J
                    if(ch == 'J') {
                        // make it an I
                        ch = 'I';
                    }
                    sb.append(ch);
                }
            }
        }

        br.close();

        String cleaned = sb.toString();
        return cleaned;
    }

    // ** WRITE **
    public void write(String content, String filepath) throws IOException {
        System.out.println(filepath);
        // write to a new file and put it in the output folder
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        bw.write(content);
        bw.close();
    }

    // ** CHOOSE A FILE **
    public void workWithFile(String filename, boolean encrypt) {
        try {
            if(encrypt) {
                fileToEncrypt = parse("." + "/textfiles/" + filename);
                hasFileToEncrypt = true;
            } else {
                fileToDecrypt = parse("." + "/output/" + filename);
                hasFileToDecrypt = true;
            }
        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
        }
    }
}