import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        String str = s.substring(1);

        int ans = 1;

        char pre = s.charAt(0);
        int preScore = ans;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (pre < c) {
                preScore += 1;
                ans += preScore;
            } else {
                ans += 1;
                preScore = 1;
            }
            
            pre = c;
        }

        System.out.println(ans);

        br.close();
    }
}