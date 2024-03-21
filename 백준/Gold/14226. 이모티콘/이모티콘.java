import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int s;
    static boolean[][] checked;
    static Queue<Emoji> que;
    static public class Emoji {
        int display;
        int clipBoard;
        int time;

        public Emoji(int display, int clipBoard, int time) {
            this.display = display;
            this.clipBoard = clipBoard;
            this.time = time;
        }
    }
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());
        checked = new boolean[1001][1001];
        que = new LinkedList<>();
        que.offer(new Emoji(1, 0, 0));
        bfs();
        System.out.println(ans);
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            Emoji now = que.poll();

            if (now.display == s) {
                ans = now.time;
                return;
            }

            if (!checked[now.display][now.display]) {
                checked[now.display][now.display] = true;
                que.offer(new Emoji(now.display, now.display, now.time+1));
            }

            if (now.clipBoard != 0 && now.display+ now.clipBoard < 1001) {
                if (!checked[now.display+ now.clipBoard][now.clipBoard]) {
                    checked[now.display+ now.clipBoard][now.clipBoard] = true;
                    que.offer(new Emoji(now.display+now.clipBoard, now.clipBoard, now.time+1));
                }
            }

            if (now.display != 0) {
                if (!checked[now.display-1][now.clipBoard]) {
                    checked[now.display-1][now.clipBoard] = true;
                    que.offer(new Emoji(now.display-1,now.clipBoard, now.time+1));
                }
            }
        }
    }
}