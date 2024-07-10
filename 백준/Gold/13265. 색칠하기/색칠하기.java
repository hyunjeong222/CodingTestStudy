import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static int[] colors;
    static boolean isPossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 정점 수
            int m = Integer.parseInt(st.nextToken()); // 간선 수

            list = new ArrayList<>();
            for (int i = 0; i <= n ;i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                list.get(u).add(v);
                list.get(v).add(u);
            }

            colors = new int[n+1];
            isPossible = true;
            for (int i = 1; i <= n; i++) {
                if (colors[i] == 0) {
                    colors[i] = 1;
                    dfs(i);
                }
            }

            sb.append(isPossible ? "possible" : "impossible").append("\n");
        }
        
        System.out.println(sb);
    }

    private static void dfs(int node) {
        for (int next : list.get(node)) {
            if (colors[next] == colors[node]) {
                isPossible = false;
                return;
            }

            if (colors[next] == 0) {
                colors[next] = 3 - colors[node];
                dfs(next);
            }
        }
    }
}