import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, d;
    static ArrayList<ArrayList<Pos>> list;
    static int[] dist;
    static public class Pos implements Comparable<Pos>{
        int to; int cost;
        public Pos (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pos o) {
            return this.cost - o.cost;
        }
    }
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지름길의 개수
        d = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        list = new ArrayList<>();
        for (int i = 0; i <= d; i++) {
            list.add(new ArrayList<>());
        }

        dist = new int[d+1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 지름길의 시작 위치
            int end = Integer.parseInt(st.nextToken()); // 지름길의 도착 위치
            int dist = Integer.parseInt(st.nextToken()); // 지름길의 길이

            // 도착 위치는 고속도로의 길이를 넘으면 안됨, 역주행 불가
            if (end > d) continue;

            // 지름길이 모두 최단 거리는 아님
            // 110 140 90은 지름길이 더 멀음
            // 지름길인 경우에만 list 추가
            if (end - start > dist) {
                list.get(start).add(new Pos(end, dist));
            }
        }

        dijkstra(0);

        System.out.println(dist[d]);

        br.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            // 목표 위치 도달
            if (now.to == d) break;

            if (now.cost > dist[now.to]) continue;

            // 고속도로 기본 이동
            if (dist[now.to+1] > now.cost+1) {
                dist[now.to+1] = now.cost+1;
                pq.offer(new Pos(now.to+1, dist[now.to+1]));
            }

            // 지름길 이동
            for (Pos next : list.get(now.to)) {
                if (dist[next.to] > dist[now.to] + next.cost) {
                    dist[next.to] = dist[now.to] + next.cost;
                    pq.offer(new Pos(next.to, dist[next.to]));
                }
            }
        }
    }
}