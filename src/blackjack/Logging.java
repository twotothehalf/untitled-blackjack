package blackjack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Logging {
    private final String FILE_LOCATION = "c:/blackjack.txt";    
    PrintWriter pw;
    Scanner s;

    public Logging() {
        try {
            pw = new PrintWriter(new FileOutputStream(this.FILE_LOCATION));
            s = new Scanner(new FileInputStream(this.FILE_LOCATION));
        } catch(Exception e) {System.out.println(e.getMessage());}
    }
       
    public void saveFile(String data) {
        try {
            pw.write(data);
            pw.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void readFile() {
        try {
            while(s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
