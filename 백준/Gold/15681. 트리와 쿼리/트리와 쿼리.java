import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> tree;
    static int[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점의 수
        int r = Integer.parseInt(st.nextToken()); // 루트 번호
        int q = Integer.parseInt(st.nextToken()); // 쿼리 수

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 가중치와 방향성이 없는
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        size = new int[n+1];
        dfs(r, -1); // -1은 부모가 없음, 즉 루트노드
        
        StringBuilder sb = new StringBuilder();
        while (q --> 0) {
            int u = Integer.parseInt(br.readLine());
            sb.append(size[u]).append("\n");
        }
        
        System.out.println(sb);
    }

    private static void dfs(int now, int p) {
        // 정점의 수는 자기 자신도 포함
        size[now] = 1;

        for (int next : tree.get(now)) {
            if (next == p) continue;

            dfs(next, now);
            size[now] += size[next];
        }
    }
}
