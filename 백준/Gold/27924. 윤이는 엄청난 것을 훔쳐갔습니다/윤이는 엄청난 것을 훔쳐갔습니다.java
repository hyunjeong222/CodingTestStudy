import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] list;
    static boolean[] leaf;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        int u, v;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        leaf = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if (list[i].size() == 1) {
                leaf[i] = true;
            }
        }
        dist = new int[3][n+1];
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        dfs(0, a, 1);
        dfs(1, b, 1);
        dfs(2, c, 1);

        for (int i = 1; i <= n; i++) {
            if (leaf[i] && dist[0][i] < dist[1][i] && dist[0][i] < dist[2][i]) {
                System.out.println("YES");
                System.exit(0);
            }
        }
        System.out.println("NO");
    }
    private static void dfs(int o, int now, int dis) {
        dist[o][now] = dis;
        for (int i : list[now]) {
            if (dist[o][i] == 0) {
                dfs(o, i, dis+1);
            }
        }
    }
}