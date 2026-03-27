import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr;
    static boolean[] checked;
    static int[] gom, moon;
    static int ans = Integer.MAX_VALUE; // 곰두리색과 문두리색의 차이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 물감의 개수

        StringTokenizer st;
        arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        gom = new int[3]; // 곰두리색
        moon = new int[3]; // 문두리색
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            gom[i] = Integer.parseInt(st.nextToken());
        }

        checked = new boolean[n];
        dfs(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int idx, int cnt) {
        if (cnt >= 2) {
            ans = Math.min(ans, getColor(cnt));
        }

        for (int i = idx; i < n; i++) {
            if (checked[i] || cnt > 6) continue;

            checked[i] = true;
            for (int j = 0; j < 3; j++) {
                moon[j] += arr[i][j];
            }

            dfs(i+1, cnt+1);

            checked[i] = false;
            for (int j = 0; j < 3; j++) {
                moon[j] -= arr[i][j];
            }
        }
    }

    private static int getColor(int cnt) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            res += Math.abs(moon[i]/cnt-gom[i]);
        }

        return res;
    }
}