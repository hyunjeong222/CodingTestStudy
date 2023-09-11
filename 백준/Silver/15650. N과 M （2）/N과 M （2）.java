import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        dfs(1, 0);

        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int num, int depth) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(val + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = num; i <= n; i++) {
            arr[depth] = i;
            dfs(i+1, depth+1);
        }
    }
}