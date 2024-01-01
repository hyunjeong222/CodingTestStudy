import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] combi;
    static boolean[] checked;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        checked = new boolean[n];
        combi = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cnt) {
        if (cnt == n) {
            int sum = 0;
            for (int i = 0; i < n-1; i++) {
                sum += Math.abs(combi[i] - combi[i+1]);
            }
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                combi[cnt] = arr[i];
                checked[i] = true;
                dfs(cnt+1);
                checked[i] = false;
            }
        }
    }
}