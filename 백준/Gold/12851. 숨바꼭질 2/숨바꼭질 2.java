import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] checked;
    static int ans = Integer.MAX_VALUE, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        k = Integer.parseInt(st.nextToken()); // 동생의 위치

        checked = new int[100001];

        StringBuilder sb = new StringBuilder();
        if (n >= k) {
            sb.append(n-k).append("\n").append(1);
            System.out.println(sb.toString());
            return;
        }

        bfs(n);
        sb.append(ans).append("\n").append(cnt);
        System.out.println(sb.toString());
    }

    private static void bfs(int n) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(n);
        checked[n] = 1;

        int next;
        while (!que.isEmpty()) {
            int now = que.poll();
            
            // 더 오래걸린다면 비교 X
            if (ans < checked[now]) continue;

            for (int i = 0; i < 3; i++) {
                if (i == 0) next = now-1;
                else if (i == 1) next = now+1;
                else next = now*2;

                if (next == k) {
                    ans = checked[now];
                    cnt++;
                }

                if (rangeCheck(next)) continue;
                // 첫 방문이거나
                // 이미 방문한 곳이라도 같은 시간에 방문했다면
                // 경우의 수 추가 가능성 ㅇ
                if (checked[next] == 0 || checked[next] == checked[now]+1) {
                    checked[next] = checked[now]+1;
                    que.offer(next);
                }
            }
        }
    }

    private static boolean rangeCheck(int next) {
        return next < 0 || next >= 100001;
    }
}