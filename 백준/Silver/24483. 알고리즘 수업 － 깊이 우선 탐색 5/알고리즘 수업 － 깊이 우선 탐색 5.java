import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    static long[] dist;
    static long[] depth;
    static int cnt = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        checked = new boolean[n+1];
        dist = new long[n+1];
        depth = new long[n+1];

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            depth[i] = -1;
        }

        int u, v;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            list.get(u).add(v); list.get(v).add(u);
        }

        for (ArrayList<Integer> l : list) {
            Collections.sort(l);
        }

        depth[r] = 0;
        dfs(r);

        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (dist[i]*depth[i]);
        }

        System.out.println(sum);
    }

    private static void dfs(int r) {
        checked[r] = true;
        dist[r] = cnt++;

        for (int next : list.get(r)) {
            if (!checked[next] && depth[next] == -1) {
                checked[next] = true;
                depth[next] = depth[r]+1;
                dfs(next);
            }
        }
    }
}