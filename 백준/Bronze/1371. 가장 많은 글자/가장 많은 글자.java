import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String str = "";

        while ((line = br.readLine()) != null) {
            str += line;
        }

        int[] alpha = new int[26];
        int max = 0;
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c != ' ') {
                alpha[c-'a']++;

                if (alpha[c-'a'] > max) {
                    max = alpha[c-'a'];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (max == alpha[i]) {
                sb.append((char)(i+'a'));
            }
        }

        System.out.println(sb.toString());
    }
}