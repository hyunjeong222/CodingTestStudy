import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][m+1];
        for (int i = 1 ; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = str.charAt(j-1) - '0';
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1 ; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] == 1) {
                    arr[i][j] = Math.min(Math.min(arr[i-1][j],arr[i][j-1]),arr[i-1][j-1])+1;
                    ans = Math.max(ans, arr[i][j]);
                }
            }
        }
        bw.append(ans*ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}