import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int a, b, c;
    static ArrayList<ArrayList<Pos>> list;
    static public class Pos implements Comparable<Pos> {
        int end; int dist;
        public Pos(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
        @Override
        public int compareTo(Pos o) {
            return this.dist - o.dist;
        }
    }
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Pos(v, w));
            list.get(v).add(new Pos(u, w));
        }

        int[] aArr = getDijkstra(a);
        int[] bArr = getDijkstra(b);
        int[] cArr = getDijkstra(c);

        int num = getNum(aArr, bArr, cArr);
        System.out.println(num);
    }

    private static int getNum(int[] aArr, int[] bArr, int[] cArr) {
        int num = 0;
        int min;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (i == a || i == b || i == c) continue; // 친구 집 제외

            min = Math.min(aArr[i], Math.min(bArr[i], cArr[i]));
            if (min != INF && min > max) {
                num = i;
                max = min;
            }
        }

        return num;
    }

    private static int[] getDijkstra(int start) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);

        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int end = now.end;
            int len = now.dist;

            if (dist[end] < len) continue;

            int nextLen;
            for (Pos next : list.get(end)) {
                nextLen = len + next.dist;
                if (dist[next.end] > nextLen) {
                    dist[next.end] = nextLen;
                    pq.offer(new Pos(next.end, nextLen));
                }
            }
        }

        return dist;
    }
}