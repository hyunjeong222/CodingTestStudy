import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int[] A, B, C;
    static boolean[] checked;
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 시식대 수
        k = Integer.parseInt(st.nextToken()); // 뻔뻔함

        A = new int[n]; // 단맛
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        B = new int[n]; // 짠맛
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        C = new int[n]; // 눈치 주는 정도
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        checked = new boolean[n];

        for (int i = 0; i < n; i++) {
            checked[i] = true;
            dfs(1, A[i], C[i], 0);
            checked[i] = false;
        }

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int idx, int prevA, int prevC, int sum) {
        if (idx == n) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i] && (prevC*C[i] <= k)) {
                checked[i] = true;
                dfs(idx+1, A[i], C[i], sum+(prevA*B[i]));
                checked[i] = false;
            }
        }
    }
}