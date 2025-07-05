import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 2; i < str.length(); i++) {
            for (int j = 1; j < i; j++) {
                StringBuilder sb1 = new StringBuilder(str.substring(0, j));
                StringBuilder sb2 = new StringBuilder(str.substring(j, i));
                StringBuilder sb3 = new StringBuilder(str.substring(i));
                String str1 = sb1.reverse().toString();
                String str2 = sb2.reverse().toString();
                String str3 = sb3.reverse().toString();
                list.add(str1+str2+str3);
            }
        }

        Collections.sort(list);
        System.out.println(list.get(0));

        br.close();
    }
}