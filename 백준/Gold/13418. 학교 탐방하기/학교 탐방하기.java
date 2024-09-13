import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<Pos> list;
    static int[] parent;
    static public class Pos implements Comparable<Pos> {
        int x; int y; int w;
        public Pos(int x, int y, int w) {
            this.x = x; this.y = y; this.w = w;
        }
        @Override
        public int compareTo(Pos o) {
            return this.w - o.w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        n++; m++;

        parent = new int[n];
        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }

        list = new ArrayList<>();
        int u, v, w;
        for (int i = 0 ; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.add(new Pos(u, v, w));
        }
        Collections.sort(list);

        // 가중치 0이 오르막길이므로 오름차순 정렬한대로 MTS 구현하면
        // 최악의 경로가 된다.
        int ans1 = 0;
        for (Pos now : list) {
            if (find(now.x) != find(now.y)) {
                union(now.x, now.y);
                if (now.w == 0) ans1++;
            }
        }

        // 오름차순 정렬한것과 반대로 MTS 구현하면
        // 최상의 경로가 된다.
        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }
        int ans2 = 0;
        for (int i = list.size()-1; i >= 0; i--) {
            Pos now = list.get(i);

            if (find(now.x) != find(now.y)) {
                union(now.x, now.y);
                if (now.w == 0) ans2++;
            }
        }

        int ans = (int)(Math.pow(ans1, 2) - Math.pow(ans2, 2));
        System.out.println(ans);
        br.close();
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x > y) parent[x] = y;
            else parent[y] = x;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}