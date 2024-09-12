import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static ArrayList<Pos> list;
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
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 설치 가능한 케이블 수
        k = Integer.parseInt(st.nextToken()); // 발전소의 개수

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int idx;
        for (int i = 0; i < k; i++) {
            idx = Integer.parseInt(st.nextToken());
            // 발전소 위치는 부모 노드 -1 설정
            parent[idx] = -1;
        }

        list = new ArrayList<>();
        int u, v, w;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.add(new Pos(u, v, w));
        }

        // 간선 데이터 오름차순 정렬
        Collections.sort(list);

        int ans = 0; // 최소비용
        // 크루스칼 알고리즘 진행
        for (Pos next : list) {
            // 부모 노드가 다를때만 (사이클 X)
            if (find(next.u) != find(next.v)) {
                union(next.u, next.v);
                ans += next.w;

                // 모든 도시가 발전소에 의해 전기 공급을 받고있다면 종료
                if (isAll()) break;
            }
        }

        System.out.println(ans);
        br.close();
    }

    private static boolean isAll() {
        for (int i = 1; i <= n; i++) {
            if (parent[i] != -1) return false;
        }

        return true;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (x == -1) { // x만 발전소에 연결
                parent[y] = x;
            } else if (y == -1) { // y만 발전소에 연결
                parent[x] = y;
            } else {
                if (x > y) parent[x] = y;
                else parent[y] = x;
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == -1) return -1;
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}