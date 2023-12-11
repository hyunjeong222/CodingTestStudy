import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] map;
    static int[] count;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[101];
        count = new int[101];
        checked = new boolean[101];
        while (n+m --> 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u] = v;
        }

        bfs();
        br.close();
    }

    private static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        count[1] = 0;
        checked[1] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();

            if (cur == 100) {
                System.out.println(count[cur]);
                System.exit(0);
            }

            for (int i = 1; i < 7; i++) {
                int next = cur+i;

                if (next > 100 || checked[next]) continue;

                if (map[next] != 0) {
                    if (!checked[map[next]]) {
                        checked[map[next]] = true;
                        count[map[next]] = count[cur]+1;
                        que.offer(map[next]);
                    }
                } else {
                    checked[next] = true;
                    count[next] = count[cur]+1;
                    que.offer(next);
                }
            }
        }
    }
}