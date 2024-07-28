import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toLowerCase();
        int[] checked = new int[26];
        for (int i = 0; i < str.length(); i++) {
            checked[str.charAt(i)-'a']++;
        }

        int max = -1;
        char ans = '?';
        for (int i = 0; i < 26; i++) {
            if (checked[i] > max) {
                max = checked[i];
                ans = (char)(i + 65);
            } else if (checked[i] == max) ans = '?';
        }

        System.out.println(ans);
    }
}