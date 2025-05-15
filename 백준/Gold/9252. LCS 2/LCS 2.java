import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dp;
    static char[] str1, str2;
    static int len1, len2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        len1 = str1.length;
        len2 = str2.length;

        dp = new int[len1+1][len2+1];

        int len = findLen();
        String str = findStr();

        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n").append(str).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static int findLen() {
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 두 문자가 같다면 대각선 + 1
                if (str1[i-1] == str2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else { // 두 문자가 다르다면 이전 행과 이전 열 중 큰 것
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }

    private static String findStr() {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (len1 > 0 && len2 > 0) {
            if (dp[len1][len2] == dp[len1][len2-1]) len2--;
            else if (dp[len1][len2] == dp[len1-1][len2]) len1--;
            else {
                stack.push(str1[len1-1]);
                len1--; len2--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}