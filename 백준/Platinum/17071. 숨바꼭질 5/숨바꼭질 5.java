import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static boolean[][] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        k = Integer.parseInt(st.nextToken()); // 동생의 위치

        checked = new boolean[2][500001]; // 짝수 초에 방문, 홀수 초에 방문 체크

        int ans = bfs(n);
        System.out.println(ans);
    }

    private static int bfs(int n) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(n);
        checked[0][n] = true; // 0초 방문 체크
        
        if (n == k) return 0;

        int size, flag, time = 0; // 큐 사이즈, 홀수 초인지 짝수 초인지, 동생의 가속 시간
        int next;
        while (!que.isEmpty()) {
            time++;
            flag = time % 2; // 짝수 0, 홀수 1
            size = que.size();

            k += time; // 동생 이동
            if (k > 500000) return -1; // 찾는 위치가 500,000 넘음

            for (int i = 0; i < size; i++) {
                int now = que.poll();

                for (int j = 0; j < 3; j++) {
                    if (j == 0) next = now * 2;
                    else if (j == 1) next = now - 1;
                    else next = now + 1;

                    if (rangeCheck(next) || checked[flag][next]) continue;
                    que.offer(next);
                    checked[flag][next] = true;
                }
            }

            if (checked[flag][k]) return time;
        }

        return -1;
    }

    private static boolean rangeCheck(int next) {
        return next < 0 || next >= 500001;
    }
}
