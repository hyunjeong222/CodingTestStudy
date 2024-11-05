import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static boolean[] checked;
    static public class Pos {
        int x; int time;
        public Pos (int x, int time) {
            this.x = x; this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        k = Integer.parseInt(st.nextToken()); // 동생의 위치

        checked = new boolean[100001];

        if (n >= k) { // 수빈이가 동생보다 앞에 있다면 뒤로 가는 방법만 존재
            System.out.println(n-k);
            System.exit(0);
        }

        int ans = bfs(n);
        System.out.println(ans);
    }

    private static int bfs(int n) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(n, 0));
        checked[n] = true;

        int next, time;
        while (!que.isEmpty()) {
            Pos now = que.poll();
            
            if (now.x == k) {
                return now.time;
            }

            for (int i = 0; i < 3; i++) {
                if (i == 0) { // 연산 순서 중요 *
                    next = now.x*2;
                    time = now.time;
                } else if (i == 1) {
                    next = now.x-1;
                    time = now.time+1;
                } else {
                    next = now.x+1;
                    time = now.time+1;
                }

                if (rangeCheck(next) || checked[next]) continue;
                checked[next] = true;
                que.offer(new Pos(next, time));
            }
        }
        
        return -1;
    }

    private static boolean rangeCheck(int next) {
        return next < 0 || next >= 100001;
    }
}