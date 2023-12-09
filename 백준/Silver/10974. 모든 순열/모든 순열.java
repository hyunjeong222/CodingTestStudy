import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        checked = new boolean[n];
        dfs(0);
        System.out.println(sb.toString());
    }

    private static void dfs(int depth) {
        if (depth == n) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                checked[i] = true;
                arr[depth] = i+1;
                dfs(depth+1);
                checked[i] = false;
            }
        }
    }
}