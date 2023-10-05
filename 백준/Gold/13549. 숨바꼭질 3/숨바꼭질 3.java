import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int size = 100001;
    static boolean[] checked;
    static int ans = Integer.MAX_VALUE;
    static public class pos {
        int idx;
        int time;
        public pos(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        checked = new boolean[size];

        bfs(n);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int x) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(x, 0)); // 수빈이의 현재 위치, time
        checked[x] = true;

        while (!que.isEmpty()) {
            pos now = que.poll();

            if (now.idx == k) ans = Math.min(ans, now.time);

            if (now.idx*2 < size && !checked[now.idx*2]) {
                que.offer(new pos(now.idx*2, now.time));
                checked[now.idx*2] = true;
            }
            if (now.idx-1 >= 0 && !checked[now.idx-1]) {
                que.offer(new pos(now.idx-1, now.time+1));
                checked[now.idx-1] = true;
            }
            if (now.idx+1 < size && !checked[now.idx+1]) {
                que.offer(new pos(now.idx+1, now.time+1));
                checked[now.idx+1] = true;
            }
        }
    }
}