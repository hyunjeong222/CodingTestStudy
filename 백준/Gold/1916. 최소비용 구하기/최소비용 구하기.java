import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<pos>> list;
    static int[] dist;
    static boolean[] checked;
    static public class pos implements Comparable<pos>{
        int end; int cost;
        public pos(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        // 오름차순
        @Override
        public int compareTo(pos o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine()); // 도시의 개수
        m = Integer.parseInt(br.readLine()); // 버스의 개수

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 도착지 번호
            int end = Integer.parseInt(st.nextToken()); // 목적지 번호
            int cost = Integer.parseInt(st.nextToken()); // 버스 비용

            list.get(start).add(new pos(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int departure = Integer.parseInt(st.nextToken()); // 출발지
        int destination = Integer.parseInt(st.nextToken()); // 도착지

        bw.append(dijkstra(departure, destination) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dijkstra(int departure, int destination) {
        PriorityQueue<pos> pq = new PriorityQueue<>();
        checked = new boolean[n+1];
        pq.offer(new pos(departure, destination));
        dist[departure] = 0;

        while (!pq.isEmpty()) {
            pos now = pq.poll();

            if (!checked[now.end]) {
                checked[now.end] = true;

                for (pos next : list.get(now.end)) {
                    if (!checked[next.end] && dist[next.end] > dist[now.end] + next.cost) {
                        dist[next.end] = dist[now.end] + next.cost;
                        pq.offer(new pos(next.end, dist[next.end]));
                    }
                }
            }
        }
        return dist[destination];
    }
}