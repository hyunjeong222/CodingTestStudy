import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[] str1, str2;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str1 = str.toCharArray();
        str2 = br.readLine().toCharArray();

        sb.append(dynamic(str1.length, str2.length)).append("\n");
        strAdd(str, str1.length, str2.length);

        System.out.println(sb);
    }

    private static void strAdd(String str, int l1, int l2) {
        Stack<Character> stack = new Stack<>();

        while (l1 > 0 && l2 > 0) {
            if (dp[l1][l2] == dp[l1-1][l2]) l1--;
            else if (dp[l1][l2] == dp[l1][l2-1]) l2--;
            else {
                stack.push(str.charAt(l1-1));
                l1--;
                l2--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }

    private static int dynamic(int l1, int l2) {
        dp = new int[l1+1][l2+1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (str1[i-1] == str2[j-1]) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[l1][l2];
    }
}