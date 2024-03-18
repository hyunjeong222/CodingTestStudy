import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, r;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int u, v;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            list.get(u).add(v);
            list.get(v).add(u);
        }

        for (ArrayList<Integer> l : list) {
            Collections.sort(l, Collections.reverseOrder());
        }
        bfs(r);
        for (int i = 1; i <= n; i++) {
            System.out.println(count[i]);
        }
    }

    private static void bfs(int x) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        checked = new boolean[n+1];
        checked[x] = true;
        int cnt = 1;
        count = new int[n+1];
        while (!que.isEmpty()) {
            int now = que.poll();
            count[now] = cnt++;
            for (int next : list.get(now)) {
                if (!checked[next]) {
                    que.offer(next);
                    checked[next] = true;
                }
            }
        }
    }
}