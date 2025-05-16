import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int len1 = str1.length;
        int len2 = str2.length;

        int[][] dp = new int[len1+1][len2+1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i-1] == str2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        String str = findStr(dp, str1, len1, len2);

        System.out.println(str);

        br.close();
    }

    private static String findStr(int[][] dp, char[] str1, int len1, int len2) {
        Stack<Character> stack = new Stack<>();
        while (len1 > 0 && len2 > 0) {
            if (dp[len1][len2] == dp[len1][len2-1]) len2--;
            else if (dp[len1][len2] == dp[len1-1][len2]) len1--;
            else {
                stack.push(str1[len1-1]);
                len1--; len2--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}