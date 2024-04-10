import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dist;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        checked = new boolean[n+1];

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            dist[i] = -1;
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

        dist[r] = 0; // 시작점 0
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x) {
        checked[x] = true;

        for (int next : list.get(x)) {
            if (!checked[next] && dist[next] == -1) {
                checked[next] = true;
                dist[next] = dist[x]+1;
                dfs(next);
            }
        }
    }
}