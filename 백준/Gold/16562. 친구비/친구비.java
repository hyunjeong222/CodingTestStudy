import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int[] money = new int[n+1];
        for (int i = 1; i <= n; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) union(b, a);
            else union(a ,b);
        }

        int[] min = new int[n+1];
        Arrays.fill(min, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            int root = find(i);
            min[root] = Math.min(min[root], money[i]);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (min[i] != Integer.MAX_VALUE) ans += min[i];
        }

        if (k < ans) System.out.println("Oh no");
        else System.out.println(ans);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) parent[a] = b;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}