import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int a, k;
    static boolean[] checked;
    static public class Pos {
        int num; int cnt;
        public  Pos(int num, int cnt) {
            this.num = num; this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();

        br.close();
    }

    private static void bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(a, 0));
        checked = new boolean[k+1];
        checked[a] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.num == k) {
                System.out.println(now.cnt);
                return;
            }

            if (now.num*2 <= k) {
                checked[now.num*2] = true;
                que.offer(new Pos(now.num*2, now.cnt+1));
            }
            if (!checked[now.num+1]) {
                checked[now.num+1] = true;
                que.offer(new Pos(now.num+1, now.cnt+1));
            }
        }
    }
}