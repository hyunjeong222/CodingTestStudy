import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, h;
    static List<Integer>[] graph;
    static int[][] parent; // parent[node][i] : node의 2^i번째 부모
    static int[] depth; // 깊이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        graph = new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        h = getTreeHeight();
        parent = new int[n+1][h];
        depth = new int[n+1];

        dfs(1, 1, 0);
        fillParents();

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(LCA(a, b)).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int getTreeHeight() {
        // ceil(log₂(n)) + 1
        return (int)Math.ceil(Math.log(n)/Math.log(2))+1;
    }

    private static void dfs(int now, int h, int par) {
        depth[now] = h;
        for (int next : graph[now]) {
            if (next != par) {
                dfs(next, h+1, now);
                // 2^0 = 1칸 위 부모 : next의 바로 위 부모는 now
                parent[next][0] = now;
            }
        }
    }

    private static void fillParents() {
        for (int i = 1; i < h; i++) {
            for (int j = 1; j <= n; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    private static int LCA(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];

        // 항상 a를 더 깊게
        if (ah < bh) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // a를 위로 끌어올려서 b랑 같은 깊이로 맞추기
        for (int i = h-1; i >= 0; i--) {
            if ((1 << i) <= depth[a]-depth[b]) {
                a = parent[a][i];
            }
        }

        if (a == b) return a;

        // LCA 찾기
        // 둘을 같이 움직이면서 부모가 같아지기 직전까지 올림
        for (int i = h-1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}