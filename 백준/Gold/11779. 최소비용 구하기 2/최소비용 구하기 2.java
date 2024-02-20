import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static int[] dist;
    static int[] parent;
    static boolean[] checked;
    static public class Pos implements Comparable<Pos> {
        int ed; int dist;
        public Pos(int ed, int dist) {
            this.ed = ed; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static int x, y;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        dist = new int[n+1];
        parent = new int[n+1]; // 경로 추적을 위한 부모 노드 정보
        checked = new boolean[n+1];
        int u, v, w;
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            list.get(u).add(new Pos(v, w));
        }
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dijkstra(x);
        sb.append(dist[y]).append("\n");
        // 역추적
        Stack<Integer> stack = new Stack<>();
        stack.push(y);
        while (parent[y] != 0) {
            cnt += 1;
            stack.push(parent[y]);
            y = parent[y];
        }
        sb.append(cnt+1).append("\n");
        while (!stack.isEmpty()) {
            int city = stack.pop();
            sb.append(city).append(" ");
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.ed;
            if (dist[end] < now.dist) continue; //
            if (checked[end]) continue;
            checked[end] = true;
            for (Pos next : list.get(end)) {
                if (!checked[next.ed] && dist[next.ed] > dist[end] + next.dist) {
                    dist[next.ed] = dist[end] + next.dist;
                    parent[next.ed] = end; // 이전마을 기록
                    pq.offer(new Pos(next.ed, dist[next.ed]));
                }
            }
        }
    }
}