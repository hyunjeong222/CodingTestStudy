import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int a, b, n, m;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        checked = new boolean[n+1];

        int u, v;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        int ans = bfs(a);

        System.out.println(ans);
    }

    private static int bfs(int a) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(a);
        checked[a] = true;

        int cnt = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int now = que.poll();

                if (now == b) return cnt;

                for (int next : list.get(now)) {
                    if (!checked[next]) {
                        checked[next] = true;
                        que.offer(next);
                    }
                }
            }

            cnt++;
        }

        return -1;
    }
}