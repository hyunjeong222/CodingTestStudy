import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v, e;
    static int[] parents;
    static PriorityQueue<Pos> pq;
    static public class Pos implements Comparable<Pos> {
        int a; int b; int c;
        public Pos(int a, int b, int c) {
            this.a = a; this.b = b; this.c = c;
        }
        @Override
        public int compareTo(Pos o) {
            return this.c - o.c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        parents = new int[v+1];
        for (int i = 1; i <= v; i++) {
            parents[i] = i;
        }
        
        pq = new PriorityQueue<>();
        
        int a, b, c;
        while (e --> 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            pq.offer(new Pos(a, b, c));
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (find(now.a) != find(now.b)) {
                union(now.a, now.b);
                sum += now.c;
            }
        }
        System.out.println(sum);
    }

    private static void union(int a, int b) {
        a = find(a); b = find(b);

        if (a != b) {
            if (a < b) parents[b] = a;
            else parents[a] = b;
        }
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
}