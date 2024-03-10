import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, l, r, x;
    static int[] arr;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(ans);
    }

    private static void dfs(int idx, int depth, int sum, int max, int min) {
        if (depth >= 2) {
            if (sum >= l && sum <= r && max-min >= x) {
                ans++;
            }
        }
        for (int i = idx; i < n; i++) {
            dfs(i + 1, depth + 1, sum + arr[i], Math.max(max, arr[i]), Math.min(min, arr[i]));
        }
    }
}