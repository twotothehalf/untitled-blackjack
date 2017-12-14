package blackjack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Logging {
    private final String FILE_LOCATION = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "blackjack.txt";    
    PrintWriter pw;
    Scanner s;
       
    public void saveFile(String data) {
        try {
            pw = new PrintWriter(new FileOutputStream(this.FILE_LOCATION));
            pw.println(data);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            pw.close();
        }
    }
    
    public void readFile() {
        try {
            s = new Scanner(new FileInputStream(this.FILE_LOCATION));
            while(s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
    }
}
