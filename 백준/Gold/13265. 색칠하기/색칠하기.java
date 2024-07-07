import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static int[] color;
    static boolean isPossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            while (m --> 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.get(x).add(y);
                list.get(y).add(x);
            }

            color = new int[n+1];
            isPossible = true;
            for (int i = 1; i <= n; i++) {
                if (!isPossible) break;
                if (color[i] == 0) {
                    color[i] = 1;
                    dfs(i);
                }
            }

            sb.append(isPossible ? "possible" : "impossible").append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int node) {
        for (int i = 0; i < list.get(node).size(); i++) {
            int next = list.get(node).get(i);
            if (color[next] == color[node]) {
                isPossible = false;
                return;
            }
            if (color[next] == 0) {
                color[next] = 3-color[node]; // 2
                dfs(next);
            }
        }
    }
}