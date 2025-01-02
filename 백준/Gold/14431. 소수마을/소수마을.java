import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int sx, sy, ex, ey;
    static ArrayList<Pos>[] arr;
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
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(12000); i++) {
            if (!isPrime[i]) {
                for (int j = i*i; j <= 12000; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        n = Integer.parseInt(br.readLine())+2;

        arr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }

        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{sx, sy}); // 시작점
        for (int i = 1; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.add(new int[]{u, v});
        }
        list.add(new int[]{ex, ey}); // 도착점

        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                int cost = (int)(Math.sqrt(Math.pow(list.get(i)[0] - list.get(j)[0], 2)
                        + Math.pow(list.get(i)[1] - list.get(j)[1], 2)));

                if (isPrime[cost]) continue;

                arr[i].add(new Pos(j, cost));
                arr[j].add(new Pos(i, cost));
            }
        }

        int ans = dijkstra();

        System.out.println(ans);
    }

    private static int dijkstra() {
        dist = new int[n];
        Arrays.fill(dist, INF);

        pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.dist > dist[now.end]) continue;

            if (now.end == n-1) return dist[n-1];

            for (Pos next : arr[now.end]) {
                if (dist[next.end] > dist[now.end] + next.dist) {
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }

        return -1;
    }
}