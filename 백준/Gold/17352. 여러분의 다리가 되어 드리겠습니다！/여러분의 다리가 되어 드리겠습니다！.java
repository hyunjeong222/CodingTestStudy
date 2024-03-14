import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        StringTokenizer st;
        int u, v;
        for (int i = 0; i < n-2; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            union(u, v);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i == find(i)) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        
        if (u != v) {
            if (u < v) parents[v] = u;
            else parents[u] = v;
        }
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
}