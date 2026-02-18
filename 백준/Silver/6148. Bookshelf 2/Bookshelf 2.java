import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, b;
    static int[] arr;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb  = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 소
        b = Integer.parseInt(st.nextToken()); // 책장의 높이

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int start, int total) {
        if (total >= b) {
            ans = Math.min(ans, total-b);
            return;
        }

        for (int i = start; i < n; i++) {
            dfs(i+1, total+arr[i]);
        }
    }
}