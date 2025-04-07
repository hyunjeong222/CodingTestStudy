import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, q;
    static ArrayList<ArrayList<Integer>> list;
    static int[] route, milk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 방의 개수

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int a, b;
        StringTokenizer st;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // 양방향 연결
            list.get(a).add(b);
            list.get(b).add(a);
        }

        route = new int[n+1];
        milk = new int[n+1];

        q = Integer.parseInt(br.readLine()); // 쿼리의 개수
        int t; // 쿼리 번호
        int u, w; // 출발, 도착 방의 번호
        int x; // 지금까지 배달한 우유의 총 개수를 계산해야 하는 방의 번호
        StringBuilder sb = new StringBuilder();
        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());

            if (t == 1) {
                u = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                dfs(u, 0, w, 0);
            } else {
                x = Integer.parseInt(st.nextToken());
                sb.append(milk[x]).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static boolean dfs(int now, int prev, int end, int cnt) {
        route[cnt] = now;

        if (now == end) {
            for (int i = 0; i <= cnt; i++) {
                milk[route[i]] += i;
            }
            return true;
        }

        boolean flag = false;
        for (int next : list.get(now)) {
            if (next == prev) continue;

            if (dfs(next, now, end, cnt+1)) {
                flag = true;
            }
        }

        return flag;
    }
}