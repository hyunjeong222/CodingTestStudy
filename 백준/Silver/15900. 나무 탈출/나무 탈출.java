import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, ans;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 정점 개수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        int u, v;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        ans = 0; // 이동 횟수
        checked = new boolean[n+1];
        dfs(1, 0);

        System.out.println((ans % 2 == 0) ? "No" : "Yes");

        br.close();
    }

    private static void dfs(int node, int cnt) {
        checked[node] = true;

        for (int next : list.get(node)) {
            if (!checked[next]) {
                dfs(next, cnt+1);
            }
        }

        // 특정 노드가 루트 노드가 아니고,
        // 노드의 인접 리스트 사이즈가 1이면, 그 노드는 리프 노드
        if (node != 1 && list.get(node).size() == 1) {
            ans += cnt;
        }
    }
}