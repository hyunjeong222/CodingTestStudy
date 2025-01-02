import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int sx, sy, ex, ey;
    static ArrayList<Pos> list;
    static int[] dist;
    static boolean[] isPrime;
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
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 에라스토테네스의 체
        isPrime = new boolean[12001];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= Math.sqrt(12000); i++) {
            if (isPrime[i]) {
                for (int j = i*i; j <= 12000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        list.add(new Pos(sx, sy)); // 시작점
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.add(new Pos(u, v));
        }

        list.add(new Pos(ex, ey)); // 도착점

        dijkstra();

        System.out.println(dist[n+1] == INF ? "-1" : dist[n+1]);

    }

    private static void dijkstra() {
        dist = new int[n+2];
        Arrays.fill(dist, INF);

        pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.dist > dist[now.end]) continue;

            dist[now.end] = now.dist;

            int nextEnd = list.get(now.end).end;
            int nextDist = list.get(now.end).dist;

            for(int i = 0; i < list.size(); i++) {
                if (i == now.end) continue;

                int l = getDistance(nextEnd, nextDist, list.get(i).end, list.get(i).dist);

                if (!isPrime[l] || now.dist+l >= dist[i] || now.dist+l >= dist[n+1]) continue;

                pq.offer(new Pos(i, now.dist+l));
            }
        }
    }

    private static int getDistance(double sx, double sy, double ex, double ey) {
        return (int)(Math.sqrt(Math.pow(sx-ex, 2) + Math.pow(sy-ey, 2)));
    }
}