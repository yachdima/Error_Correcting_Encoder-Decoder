import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split("\\s");
        reader.close();
        int n = 0;
        for (String s : str) {
            if (s.trim().length() > 0) {
                n++;
            }
        }
        System.out.println(n);
    }
}