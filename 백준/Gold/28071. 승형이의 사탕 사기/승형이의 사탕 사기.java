import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m ,k;
    static int maxCandy;
    static int[] candies;
    static boolean[] checked;
    static Queue<Pos> que;
    static public class Pos {
        // 사탕 개수, 챙긴 사탕 상자
        int num; int depth;
        public Pos(int num, int depth) {
            this.num = num; this.depth = depth;
        }
    }
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사탕의 종류
        m = Integer.parseInt(st.nextToken()); // 승형이가 가져갈 수 있는 사탕 상자의 개수
        k = Integer.parseInt(st.nextToken()); // 동생의 수

        candies = new int[n]; // 각 사탕 상자에 담겨있는 사탕의 개수
        maxCandy = 0;
        que = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
            que.offer(new Pos(candies[i], 1));
            maxCandy = Math.max(maxCandy, candies[i]);
        }

        checked = new boolean[maxCandy*m+1];
        bfs();

        System.out.println(max);
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.num % k == 0) { // 남김없이 나눠줄 수 있을때
                max = Math.max(max, now.num);
            }

            if (now.depth == m) continue; // 이미 모든 사탕 상자를 챙겼다면

            for (int i = 0; i < n; i++) {
                if (!checked[now.num+candies[i]]) {
                    que.offer(new Pos(now.num+candies[i], now.depth+1));
                    checked[now.num+candies[i]] = true;
                }
            }
        }
    }
}