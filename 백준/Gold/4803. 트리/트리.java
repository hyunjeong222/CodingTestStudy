import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        int testCase = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());// 정점의 개수
            int m = Integer.parseInt(st.nextToken()); // 간선의 개수

            if (n == 0 && m == 0) break;

            sb.append("Case ").append(testCase).append(": ");
            testCase++;

            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                list.get(u).add(v);
                list.get(v).add(u);
            }

            checked = new boolean[n+1];

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!checked[i]) {
                    checked[i] = true;
                    if (dfs(i, 0)) cnt += 1;
                }
            }

            if (cnt == 0) sb.append("No trees.").append("\n");
            else if (cnt == 1) sb.append("There is one tree.").append("\n");
            else sb.append("A forest of ").append(cnt).append(" trees.").append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static boolean dfs(int start, int prev) {
        for (int next : list.get(start)) {
            if (next == prev) continue;

            if (checked[next]) return false;
            checked[next] = true;
            if (!dfs(next, start)) return false;
        }

        return true;
    }
}