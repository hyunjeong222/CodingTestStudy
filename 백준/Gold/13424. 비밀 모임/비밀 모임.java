import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] ansArr;
    static PriorityQueue<Pos> pq;
    static boolean[] checked;
    static int[] dist;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos(int end, int dist) {
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
        int t = Integer.parseInt(br.readLine());
        while (t --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 방의 개수
            m = Integer.parseInt(st.nextToken()); // 비밀통로의 개수
            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            int a, b, c;
            while (m --> 0) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                list.get(a).add(new Pos(b, c));
                list.get(b).add(new Pos(a, c));
            }
            ansArr = new int[n+1];
            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int num;
            for (int i = 0; i < k; i++) {
                num = Integer.parseInt(st.nextToken());
                Dijkstra(num);
            }
            int min = ansArr[1];
            for (int i = 1; i <= n; i++) {
                min = Math.min(min, ansArr[i]);
            }
            int ans = 1;
            for (int i = 1; i <= n; i++) {
                if (min == ansArr[i]) {
                    ans = i;
                    break;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void Dijkstra(int x) {
        pq = new PriorityQueue<>();
        pq.offer(new Pos(x, 0));
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[x] = 0;
        checked = new boolean[n+1];

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;

            if (checked[end]) continue;
            checked[end] = true;

            for (Pos next : list.get(end)) {
                if (dist[next.end] > dist[end]+next.dist) {
                    dist[next.end] = dist[end]+next.dist;
                    pq.offer(new Pos(next.end, dist[next.end]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            ansArr[i] += dist[i];
        }
    }
}