import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }

        int ans = 0;
        int checkNum = (1 << n*m); // n, m은 최대 각각 4
        for (int s = 0; s < checkNum; s++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int num = 1; // 자릿수
                for (int j = m-1; j >= 0; j--) {
                    int k = j+i*m;
                    if ((s & (1 << k)) == 0) {
                        sum += (map[i][j]*num);
                        num *= 10;
                    } else {
                        num = 1; // 세로면 자릿수 초기화
                    }
                }
            }

            for (int j = 0; j < m; j++) {
                int num = 1; // 자릿수
                for (int i = n-1; i >= 0; i--) {
                    int k = j+i*m;
                    if ((s & (1 << k)) != 0) {
                        sum += (map[i][j]*num);
                        num *= 10;
                    } else {
                        num = 1;
                    }
                }
            }

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);

        br.close();
    }
}