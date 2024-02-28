import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int a, b, c;
    static ArrayList<ArrayList<Pos>> list;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos>{
        int ed; int dist; int max;
        public Pos(int ed, int dist, int max) {
            this.ed = ed; this.dist = dist; this.max = max;
        }
        @Override
        public int compareTo(Pos o) {
            return this.max - o.max;
        }
    }
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 교차로 개수
        m = Integer.parseInt(st.nextToken()); // 골목길 개수
        a = Integer.parseInt(st.nextToken()); // 시작 교차로 번호
        b = Integer.parseInt(st.nextToken()); // 도착 교차로 번호
        c = Integer.parseInt(st.nextToken()); // 가진 돈
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        checked = new boolean[n+1];
        int u, v, w;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            // 양방향
            list.get(u).add(new Pos(v,w,0));
            list.get(v).add(new Pos(u,w,0));
        }
        int ans = dijkstra(a, b);
        System.out.println(ans);
    }

    private static int dijkstra(int start, int ed) {
        int min = Integer.MAX_VALUE;
        pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0, 0));

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.ed;

            if (now.ed == ed) {
                min = now.max;
                break;
            }

            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (checked[next.ed]) continue;

                if (now.dist + next.dist <= c) {
                    pq.offer(new Pos(next.ed, now.dist+next.dist, Math.max(now.max, next.dist)));
                }
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        return min;
    }
}