import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 진행된 차례 수
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int a, b;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            // 같은 루트 노드를 바라보도록 만들어 주면서
            // 이미 루트 노드가 같다면 같은 집합에 있는 점을 잇는다면 사이클 형성
            if (find(a) == find(b)) {
                ans = i+1;
                break;
            }
            union(a, b);
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