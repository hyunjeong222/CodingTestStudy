import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] alphabet = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine()); // 인식할 수 있는 알파벳 개수
        String str = br.readLine();

        int left = 0, right = 0;
        int cnt = 1, ans = 0;
        alphabet[str.charAt(0)-'a']++;
        while (right < str.length()-1) {
            if (alphabet[str.charAt(++right)-'a']++ == 0) cnt++;

            while (n < cnt && left < right) {
                if (--alphabet[str.charAt(left++)-'a'] == 0) cnt--;
            }

            ans = Math.max(ans, right-left+1);
        }

        System.out.println(ans);

        br.close();
    }
}