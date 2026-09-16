package ie.atu.sw;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private final Scanner s = new Scanner(System.in);
    private final InputOutput io = new InputOutput();
    private final Cipher c = new Cipher(io);

    private boolean keepRunning = true;

    public void start() {
        while (keepRunning) {
            init();
            processInput();
        }
    }

    public void processInput() {
        String choice = s.nextLine().trim();

        switch (choice) {
            case "1" -> {
                System.out.println("please type the name of the file, followed by it's extension\n");
                String filename = s.nextLine().trim();
                io.workWithFile(filename, true);
            }
            case "2" -> {
                c.encryptTextFile();
            }
            case "3" -> {
                c.decryptTextFile();
                System.out.print("Enter output filename: ");
                String decryptedFilename =  s.nextLine().trim();
                try {
                    io.write(c.decryptedText, "./output/" + decryptedFilename + "decrypted");
                } catch(IOException e) {
                    System.out.println("Failed to specify an output file: " + e.getMessage());
                }
            }
            case "4" -> {
                // NOTE: AS IS CURRENTLY IMPLEMENTED SEED IS UNUSED
                // Wanted to show how it would've been implemeted in
                // c.generateCipherKey
                String seed = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
                c.generateCipherKey(seed);
            }
            case "5" -> {
                System.out.print("Enter filename(stored in out): ");
                String filename =  s.nextLine().trim();

               try {
                    io.write(c.encryptedText, "./output/" + filename);
               } catch(IOException e) {
                    System.out.println("Failed to specify an output file: " + e.getMessage());
               }
            }
            case "6" -> {
                System.out.print("Specify filename to decrypt: ");
                String filename =  s.nextLine().trim();
                io.workWithFile(filename, false);
            }
            case "?" -> {
                keepRunning = false;
            }
            default -> System.out.println("Invalid option");
        }
    }

    public void init() {
        System.out.println("************************************************************");
        System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
        System.out.println("*                                                          *");
        System.out.println("*       Encrypting Files with a Four Square Cipher         *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
        System.out.println("(1) Specify Text File to Encrypt");
        System.out.println("(2) Encrypt Text File");
        System.out.println("(3) Decrypt Text File");
        System.out.println("(4) Generate Cipher Key");
        System.out.println("(5) Create (Encrypted text) Output File (output folder) -> Refresh program");
        System.out.println("(6) Create (Decrypted text) Output File (output folder) -> Refresh program");
        System.out.println("(?) Quit");

        //Output a menu of options and solicit text from the user
        System.out.print("Select Option [1-?]>");
        System.out.println();
    }
}
