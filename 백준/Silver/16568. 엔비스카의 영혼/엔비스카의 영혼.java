import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, a, b;
    static Queue<Integer> que;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        que = new LinkedList<>();
        checked = new boolean[1000001];

        int ans = bfs();
        System.out.println(ans);

        br.close();
    }

    private static int bfs() {
        que.offer(n);
        checked[n] = true;
        int time = 0;

        while (!que.isEmpty()) {
            int size = que.size();

            while (size --> 0) {
                int now = que.poll();

                if (now == 0) return time;

                if (now-1 >= 0 && !checked[now-1]) {
                    que.offer(now-1);
                    checked[now-1] = true;
                }

                if (now-a-1 >= 0 && !checked[now-a-1]) {
                    que.offer(now-a-1);
                    checked[now-a-1] = true;
                }

                if (now-b-1 >= 0 && !checked[now-b-1]) {
                    que.offer(now-b-1);
                    checked[now-b-1] = true;
                }
            }

            time++;
        }

        return -1;
    }
}