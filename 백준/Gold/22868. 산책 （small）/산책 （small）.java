import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static int s, e;
    static boolean[] checked;
    static int[] route;
    static public class Pos {
        int node; int cost;
        public Pos (int node, int cost) {
            this.node = node; this.cost = cost;
        }
    }
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // 양방향
            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 정점 미리 오름차순
        for (int i = 0; i <= n; i++) {
            Collections.sort(list.get(i));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 출발
        e = Integer.parseInt(st.nextToken()); // 도착

        checked = new boolean[n+1];
        route = new int[n+1];
        // 시작, 도착
        bfs(s, e);

        // 방문배열 초기화
        checked = new boolean[n+1];
        int node = route[e];
        while (node > 0) {
            checked[node] = true;
            node = route[node];
        }

        checked[s] = false;
        bfs(e, s);

        System.out.println(ans);

        br.close();
    }

    private static void bfs(int start, int end) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(start, 0));
        checked[start] = true;

        int node, cost;
        while (!que.isEmpty()) {
            Pos now = que.poll();
            node = now.node; cost = now.cost;

            // 도착 지점
            if (node == end) {
                ans += cost;
                return;
            }

            for (int next : list.get(node)) {
                if (checked[next]) continue;

                checked[next] = true;
                route[next] = node;
                que.offer(new Pos(next, cost+1));
            }
        }
    }
}