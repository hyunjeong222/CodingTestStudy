import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k, x;
    static ArrayList<ArrayList<pos>> list;
    static int[] dist;
    static boolean[] checked;
    static public class pos implements Comparable<pos>{
        int end; int dist;

        public pos (int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(pos o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수
        k = Integer.parseInt(st.nextToken()); // 거리의 정보
        x = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작점
            int b = Integer.parseInt(st.nextToken()); // 목적지

            list.get(a).add(new pos(b, 1));
        }

        dijkstra(x);

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == k) {
                sb.append(i).append("\n");
            }
        }

        if (sb.length() == 0) sb.append(-1).append("\n");

        System.out.println(sb.toString());
        br.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<pos> pq = new PriorityQueue<>();
        checked = new boolean[n+1];
        pq.offer(new pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            pos now = pq.poll();
            int num = now.end;
            checked[num] = true;

            for (pos next : list.get(num)) {
                if (!checked[next.end] && dist[next.end] > dist[num] + next.dist) {
                    dist[next.end] = dist[num] + next.dist;
                    pq.offer(new pos(next.end, dist[next.end]));
                }
            }
        }
    }
}