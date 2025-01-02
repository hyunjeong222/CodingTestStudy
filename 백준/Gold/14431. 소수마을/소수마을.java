import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Pos>[] arr;
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
        // 소수 X true, 소수 O false
        boolean[] isPrime = new boolean[12001];
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(12000); i++) {
            if (!isPrime[i]) {
                for (int j = i*i; j <= 12000; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 소수 마을의 위치
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        // A 마을의 위치
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());

        n = Integer.parseInt(br.readLine())+2; // 경유할 수 있는 마을의 개수 + 시작점과 도착점

        arr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }

        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{sx, sy}); // 시작점
        for (int i = 0; i < n-2; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list.add(new int[]{u, v});
        }
        list.add(new int[]{ex, ey}); // 도착점

        // 모든 정점 간의 길이 구하기 - 소수인 경우만 연결
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                int cost = (int)(Math.sqrt(Math.pow(list.get(i)[0] - list.get(j)[0], 2)
                        + Math.pow(list.get(i)[1] - list.get(j)[1], 2)));

                if (isPrime[cost]) continue; // 소수 X

                arr[i].add(new Pos(j, cost));
                arr[j].add(new Pos(i, cost));
            }
        }

        int ans = dijkstra();

        System.out.println(ans);
    }

    private static int dijkstra() {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.dist > dist[now.end]) continue;

            // 도착점이라면 거리 리턴
            if (now.end == n-1) return dist[n-1];

            for (int i = 0; i < arr[now.end].size(); i++) {
                int nextEnd = arr[now.end].get(i).end;
                int nextDist = arr[now.end].get(i).dist;

                if (dist[nextEnd] > dist[now.end] + nextDist) {
                    dist[nextEnd] = dist[now.end] + nextDist;
                    pq.offer(new Pos(nextEnd, dist[nextEnd]));
                }
            }
        }

        return -1;
    }
}