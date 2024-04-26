import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[] parent;
    static int[] candies;
    static int[] count;
    static ArrayList<Pos> list;
    static public class Pos {
        int c; int v;
        public Pos(int c, int v) {
            this.c = c; this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        candies = new int[n+1];
        count = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            candies[i] = Integer.parseInt(st.nextToken());
            count[i] = 1;
        }

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        for (int i = 1; i <= n; i++) {
            if (parent[i] != i) {
                int p = find(i);
                candies[p] += candies[i];
                count[p] += count[i];
            }
        }

        list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) list.add(new Pos(count[i], candies[i]));
        }

        int[][] dp = new int[list.size()+1][k];
        for (int i = 1; i <= list.size(); i++) {
            for (int j = 0; j < k; j++) {
                if (list.get(i-1).c <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-list.get(i-1).c]+list.get(i-1).v);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        System.out.println(dp[list.size()][k-1]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u < v) parent[v] = u;
        else parent[u] = v;
    }

    private static int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }
}