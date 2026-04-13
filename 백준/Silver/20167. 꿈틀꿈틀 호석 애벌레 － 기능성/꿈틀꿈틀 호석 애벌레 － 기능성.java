import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 먹이 개수
        k = Integer.parseInt(st.nextToken()); // 최소 만족도

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0);

        System.out.println(ans);

        br.close();
    }

    // 인덱스, 만족도, 에너지
    private static void dfs(int idx, int satisfaction, int energy) {
        if (idx == n) {
            // 축적된 탈피 에너지 최대
            ans = Math.max(ans, energy);
            return;
        }

        // 선택 안함
        dfs(idx+1, 0, energy);

        if (satisfaction+arr[idx] >= k) {
            // 직후 만족도 다시 0
            dfs(idx+1, 0, energy+((satisfaction+arr[idx])-k));
        } else {
            dfs(idx+1, satisfaction+arr[idx], energy);
        }
    }
}