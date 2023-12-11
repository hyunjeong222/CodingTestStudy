import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] map;
    static boolean[] checked;
    static public class pos {
        int x;
        int cnt;
        public pos(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[101];
        for (int i = 1; i < map.length; i++) {
            map[i] = i;
        }

        while (n+m --> 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u] = v;
        }

        checked = new boolean[101];
        int ans = bfs();
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(1, 0));
        checked[1] = true;

        while (!que.isEmpty()) {
            pos cur = que.poll();

            if (cur.x == 100) return cur.cnt;

            for (int i = 1; i < 7; i++) {
                int next = cur.x+i;

                if (next > 100 || checked[next]) continue;

                que.offer(new pos(map[next], cur.cnt+1));
                checked[next] = true;
            }
        }

        return -1;
    }
}