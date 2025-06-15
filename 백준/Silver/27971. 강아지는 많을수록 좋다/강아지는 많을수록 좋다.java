import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, a, b;
    static public class Pos {
        int dogCnt; int moveCnt;
        public Pos (int dogCnt, int moveCnt) {
            this.dogCnt = dogCnt; this.moveCnt = moveCnt;
        }
    }
    static int[][] arr;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 원하는 강아지 수
        m = Integer.parseInt(st.nextToken()); // 닫힌 구간 개수
        a = Integer.parseInt(st.nextToken()); // a 생성 마법 - a마리 강아지 생성
        b = Integer.parseInt(st.nextToken()); // b 생성 마법 - b마리 강아지 생성

        // 닫힌 구간의 양 끝 점
        arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int ans = bfs();

        System.out.println(ans);

        br.close();
    }

    private static int bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(0, 0));

        // 키우기를 원하는 강아지의 수를 더 적은 횟수로
        // 만들 수 있는 경우 저장
        // idx : 마리 수, 행동 횟수
        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            // 최소 횟수를 구하므로 볼 필요 X
            if (dp[now.dogCnt] < now.moveCnt) continue;

            // 정확히 n마리
            if (now.dogCnt == n) return now.moveCnt;

            // 닫힌 구간이라면 다시 0마리
            if (!isAbleCheck(now.dogCnt)) continue;

            // a 마법
            if (now.dogCnt+a <= n && dp[now.dogCnt+a] > dp[now.dogCnt]+1) {
                dp[now.dogCnt+a] = dp[now.dogCnt]+1;
                que.offer(new Pos(now.dogCnt+a, dp[now.dogCnt+a]));
            }
            // b 마법
            if (now.dogCnt+b <= n && dp[now.dogCnt+b] > dp[now.dogCnt]+1) {
                dp[now.dogCnt+b] = dp[now.dogCnt]+1;
                que.offer(new Pos(now.dogCnt+b, dp[now.dogCnt+b]));
            }
        }

        return -1;
    }

    private static boolean isAbleCheck(int dogCnt) {
        for (int i = 0; i < m; i++) {
            if (arr[i][0] <= dogCnt && arr[i][1] >= dogCnt) return false;
        }

        return true;
    }
}