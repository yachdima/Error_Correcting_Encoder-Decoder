package correcter;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void encode() {
        Path pathToFile = Paths.get("send.txt");
        File outputFileName = new File("encoded.txt");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(String.valueOf(pathToFile));
            FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);

            byte[] bytes = inputStream.readAllBytes();
            String bin = "";
            String bin2 = "";
            for (byte b : bytes) {
                String hex = Integer.toHexString(b);
                bin = Integer.toBinaryString(Integer.parseInt(hex, 16));
                bin = String.format("%8s", bin).replace(" ","0");
                // System.out.println(hex + " " + bin);

                for (int i = 0; i < bin.length(); i++) {
                    bin2 += String.valueOf(bin.charAt(i)) + bin.charAt(i);
                }
                //System.out.println(bin2 + " ");

            }
            inputStream.close();

            bin = "";
            String temp = "";
            int n = 0;
            String hex = "";
            for (int i = 0; i < bin2.length(); i++) {
                if (i%6 == 0 && temp.length() > 0) {
                    n = temp.charAt(0) == '1' ? 1 : 0;
                    n += temp.charAt(2) == '1' ? 1 : 0;
                    n += temp.charAt(4) == '1' ? 1 : 0;

                    // System.out.println(temp);
                    // System.out.println(n + "%2 = " + n%2);
                    if (n%2 == 1) {
                        temp += "11";
                    } else {
                        temp += "00";
                    }
                    bin += temp;
                    int decimal = Integer.parseInt(temp,2);
                    hex += Integer.toString(decimal,16);

                    // System.out.println(temp);
                    // System.out.println(hex);

                    byte b = (byte) decimal;
                    fileOutputStream.write(b);
                    temp = "";
                    hex = "";
                }
                temp += bin2.charAt(i);

            }

            // System.out.println(bin);
            // System.out.println(hex);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send() {
        // String pathToFile = "D:/encoded.txt";
        // String outputFileName = "D:/file.txt";
        Path pathToFile = Paths.get("encoded.txt");
        File outputFileName = new File("received.txt");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(String.valueOf(pathToFile));
            FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);

            byte[] bytes = inputStream.readAllBytes();

            for (byte b : bytes) {
                //System.out.print(b); // 5
                b ^= 1<< 2;
                System.out.println(" = " + b);
                fileOutputStream.write(b);
            }
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // String pathToFile = "D:/send.txt";
        // String outputFileName = "D:/encoded.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write a mode: ");
        String action = scanner.nextLine();
        if ("encode".equals(action)) {
            encode();
        } else if ("send".equals(action)) {
            send();
        }
    }
}
