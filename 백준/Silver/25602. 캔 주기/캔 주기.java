import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int[] can;
    static int[][] preferRang, preferMary;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 캔의 종류
        k = Integer.parseInt(st.nextToken()); // k일

        can = new int[n]; // 집사가 가진 캔의 수 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            can[i] = Integer.parseInt(st.nextToken());
        }

        preferRang = new int[k][n]; // i번째 날 j번째 캔을 먹은 랑이의 만족도
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                preferRang[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        preferMary = new int[k][n]; // i번째 날 j번째 캔을 먹은 메리 만족도
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                preferMary[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int day, int rang, int mary) {
        if (day == k) {
            ans = Math.max(ans, rang+mary);
            return;
        }

        for (int i = 0; i < n; i++) { // i번째 캔
            int newRang = rang;
            int newMary = mary;

            if (can[i] > 0) {
                can[i]--;
                newRang += preferRang[day][i];

                for (int j = 0; j < n; j++) {
                    if (can[j] > 0) {
                        can[j]--;
                        newMary += preferMary[day][j];
                        dfs(day + 1, newRang, newMary);
                        newMary -= preferMary[day][j];
                        can[j]++;
                    }
                }

                newRang -= preferRang[day][i];
                can[i]++;
            }
        }
    }
}