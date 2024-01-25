import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v, e, p;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    static boolean[] checked;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos (int end, int dist) {
            this.end = end; this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수
        p = Integer.parseInt(st.nextToken()); // 건우가 위치한 정점
        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // 거리
            list.get(a).add(new Pos(b, c));
            list.get(b).add(new Pos(a, c));
        }
        int[] dist1 = dijkstra(1);
        int[] dist2 = dijkstra(p);
        if (dist1[v] >= dist1[p]+dist2[v]) {
            sb.append("SAVE HIM");
        } else sb.append("GOOD BYE");
        System.out.println(sb);
    }

    private static int[] dijkstra(int start) {
        dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq = new PriorityQueue<>();
        checked = new boolean[v+1];
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int num = now.end;
            checked[num] = true;

            for (Pos next : list.get(num)) {
                if (!checked[next.end] && dist[next.end] > dist[num] + next.dist) {
                    dist[next.end] = dist[num] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }
        return dist;
    }
}