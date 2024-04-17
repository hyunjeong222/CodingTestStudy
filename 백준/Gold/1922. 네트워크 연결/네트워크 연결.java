import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parents;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int u; int v; int d;
        public Pos(int u, int v, int d) {
            this.u = u; this.v = v; this.d = d;
        }
        @Override
        public int compareTo(Pos o) {
            return this.d - o.d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        pq = new PriorityQueue<>();
        int u, v, d;
        StringTokenizer st;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            pq.offer(new Pos(u, v, d));
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            if (find(now.u) != find(now.v)) {
                union(now.u, now.v);
                sum += now.d;
            }
        }

        System.out.println(sum);
    }

    private static void union(int u, int v) {
        u = find(u); v = find(v);

        if (u != v) {
            if (u < v) parents[v] = u;
            else parents[u] = v;
        }
    }

    private static int find(int u) {
        if (parents[u] == u) return u;
        return parents[u] = find(parents[u]);
    }
}