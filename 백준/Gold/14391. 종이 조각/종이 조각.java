import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }

        int ans = 0;
        int checkNum = (1 << n*m);
        for (int s = 0; s < checkNum; s++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int now = 0;
                for (int j = 0; j < m; j++) {
                    int k = i*m+j;
                    if ((s & (1 << k)) == 0) {
                        now *= 10;
                        now += map[i][j];
                    } else {
                        sum += now;
                        now = 0;
                    }
                }
                sum += now;
            }

            for (int j = 0; j < m; j++) {
                int now = 0;
                for (int i = 0; i < n; i++) {
                    int k = i*m+j;
                    if ((s & (1 << k)) != 0) {
                        now *= 10;
                        now += map[i][j];
                    } else {
                        sum += now;
                        now = 0;
                    }
                }
                sum += now;
            }

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);

        br.close();
    }
}