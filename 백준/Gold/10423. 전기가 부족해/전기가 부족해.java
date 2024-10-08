import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static PriorityQueue<Pos> pq;
    static int[] parent;
    static public class Pos implements Comparable<Pos> {
        int u; int v; int w;
        public Pos(int u, int v, int w) {
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
        n = Integer.parseInt(st.nextToken()); // 도시 개수
        m = Integer.parseInt(st.nextToken()); // 설치 가능한 케이블 수
        k = Integer.parseInt(st.nextToken()); // 발전소 개수

        parent = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            parent[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        int idx;
        for (int i = 0; i < k; i++) {
            idx = Integer.parseInt(st.nextToken());
            // 발전소가 있는 도시는 다른 도시와 연결하지 않아도 되므로 부모노드 -1 설정
            parent[idx] = -1;
        }

        pq = new PriorityQueue<>();
        int u, v, w;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            pq.offer(new Pos(u, v, w));
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            // 부모도느가 다를때 (사이클 X)
            if (find(now.u) != find(now.v)) {
                union(now.u, now.v);
                ans += now.w;

                // 모든 도시가 발전소에 의해 전기를 공급받고 있다면 종료
                if (isAll()) {
                    break;
                }
            }
        }

        System.out.println(ans);
        br.close();
    }

    private static boolean isAll() {
        for (int i = 1; i <= n; i++) {
            if (parent[i] != -1) {
                return false;
            }
        }

        return true;
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u != v) {
            if (u == -1) {
                parent[v] = -1;
            } else if (v == -1) {
                parent[u] = -1;
            } else {
                if (u > v) {
                    parent[u] = v;
                } else {
                    parent[v] = u;
                }
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == -1) return -1;
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}