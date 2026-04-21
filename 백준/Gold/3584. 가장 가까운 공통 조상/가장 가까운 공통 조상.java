import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static int[] parent, depth;
    static boolean[] hasParent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());

            graph = new List[n+1];
            hasParent = new boolean[n+1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < n-1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                hasParent[b] = true;
            }

            depth = new int[n+1];
            parent = new int[n+1];
            int root = 1;
            for (int i = 1; i <= n; i++) {
                if (!hasParent[i]) {
                    root = i;
                    break;
                }
            }
            dfs(root, 1, 0);

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b)).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int LCA(int a, int b) {
        // 높이 맞추기
        int ah = depth[a];
        int bh = depth[b];

        while (ah > bh) {
            a = parent[a];
            ah--;
        }

        while (ah < bh) {
            b = parent[b];
            bh--;
        }

        // 공통 조상 찾기
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void dfs(int now, int h, int par) {
        depth[now] = h;
        parent[now] = par;
        for (int next : graph[now]) {
            dfs(next, h+1, now);
        }
    }
}