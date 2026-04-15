import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] alphabet = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 인식할 수 있는 알파벳 개수
        String str = br.readLine();

        int start = 0, end = 0;
        int cnt = 1, ans = 0; // 현재 알파벳 개수
        alphabet[str.charAt(0)-'a']++; // 첫번째 문자
        while (end < str.length()-1) {
            if (alphabet[str.charAt(++end)-'a']++ == 0) cnt++;

            // 알파벳 종류가 n개 초과
            while (n < cnt && start < end) {
                if (--alphabet[str.charAt(start++)-'a'] == 0) cnt--;
            }

            // 최대 길이 갱신
            ans = Math.max(ans, end-start+1);
        }

        System.out.println(ans);

        br.close();
    }
}