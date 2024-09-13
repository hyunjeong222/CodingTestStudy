import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int v, e;
    static ArrayList<Pos> list;
    static int[] parent;
    static public class Pos implements Comparable<Pos> {
        int u; int v; int w;
        public Pos (int u, int v, int w) {
            this.u = u; this.v = v; this.w = w;
        }
        @Override
        public int compareTo(Pos o) {
            return this.w - o.w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수

        parent = new int[v+1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        list = new ArrayList<>();
        int u, v, w;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.add(new Pos(u, v, w));
        }
        Collections.sort(list);

        int ans = 0;
        for (Pos next : list) {
            if (find(next.u) != find(next.v)) {
                union(next.u, next.v);
                ans += next.w;
            }
        }

        System.out.println(ans);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u != v) {
            if (u > v) parent[u] = v;
            else parent[v] = u;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}