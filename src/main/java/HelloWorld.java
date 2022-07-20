import java.io.*;

public class HelloWorld {
    private static String FILE = "test.dat";

    public static void main(String[] args) throws IOException {
        write();
        read();
    }
    
    private static void read() throws IOException {
        FileInputStream in = new FileInputStream(FILE);
        int x;
        while ((x = in.read()) != -1) {
            System.out.print(x + " ");
        }
        in.close();
    }

    private static void write() throws IOException {
        FileOutputStream out = new FileOutputStream(FILE);
        for (int i = 1; i <= 50; i++) {
            out.write(i);
        }
        out.close();
    }
}