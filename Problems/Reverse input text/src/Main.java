import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] c = new char[50];
        int number = reader.read(c);
        String s = "";
      //  System.out.println("number="+number);
        for (int i = number - 1; i >= 0; i--) {
           // System.out.print(i + "=" + c[i]);
            s += c[i];
        }
        System.out.print(s);
       // System.out.print("|");
        reader.close();
    }
}
