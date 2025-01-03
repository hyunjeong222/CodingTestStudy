import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new int[n+1];
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int cycle = 0;
            checked = new boolean[n+1];
            for (int i = 1; i <= n; i++) {
                if (!checked[i]) {
                    dfs(i);
                    cycle++;
                }
            }

            sb.append(cycle).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int start) {
        checked[start] = true;

        int next = arr[start];
        if (!checked[next]) {
            dfs(next);
        }
    }
}