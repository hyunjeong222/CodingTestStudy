import java.io.*;

public class Main {
    static char[] str1, str2;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 두 문자열 입력
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();

        bw.append(lcs(str1.length, str2.length) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lcs(int a, int b) {
        dp = new int[a+1][b+1];
        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                if (str1[i-1] == str2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[a][b];
    }
}