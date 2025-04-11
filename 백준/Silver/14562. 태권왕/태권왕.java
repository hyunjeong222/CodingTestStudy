import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int s, t;
    static public class Pos {
        int sScore; int tScore;
        int cnt;
        public Pos (int sScore, int tScore, int cnt) {
            this.sScore = sScore; this.tScore = tScore;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (c --> 0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            int ans = bfs(s, t);
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int s, int t) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(s, t, 0));

        int sScore, tScore, cnt;
        while (!que.isEmpty()) {
            Pos now = que.poll();
            sScore = now.sScore;
            tScore = now.tScore;
            cnt = now.cnt;

            if (sScore == tScore) return cnt;

            if (sScore > tScore) continue;

            que.offer(new Pos(sScore+1, tScore, cnt+1));
            que.offer(new Pos(sScore*2, tScore+3, cnt+1));
        }

        return -1;
    }
}