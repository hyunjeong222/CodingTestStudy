import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dist;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        StringTokenizer st;
        int a, b;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        bfs(1);
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            if (dist[i] > 0 && dist[i] <= 2) ans++;
        }
        System.out.println(ans);
    }

    private static void bfs(int x) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        checked = new boolean[n+1];
        dist = new int[n+1];
        checked[x] = true;

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int next : list.get(now)) {
                if (!checked[next]) {
                    dist[next] = dist[now]+1;
                    checked[next] = true;
                    que.offer(next);
                }
            }
        }
    }
}