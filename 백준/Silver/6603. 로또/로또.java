import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[] s;
    static int[] result;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) break; // 종료조건

            s = new int[k];
            for (int i = 0; i < k; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            result = new int[6];
            checked = new boolean[k];
            dfs(0, 0);
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == 6) {
            for (int val : result) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < k; i++) {
            if (!checked[i]) {
                checked[i] = true;
                result[depth] = s[i];
                dfs(i, depth+1);
                checked[i] = false;
            }
        }
    }
}