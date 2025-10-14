import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, t, g;
    static public class Pos {
        int num; int cnt;
        public Pos (int num, int cnt) {
            this.num = num; this.cnt = cnt;
        }
    }
    static final int INF = 100_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 다섯 자리 십진수
        t = Integer.parseInt(st.nextToken()); // 누를 수 있는 최대 버튼 횟수
        g = Integer.parseInt(st.nextToken()); // 같게 만들어야 하는 수

        int ans = fintCnt(); // 최소 버튼 횟수 찾기

        if (ans == -1) System.out.println("ANG");
        else System.out.println(ans);

        br.close();
    }

    private static int fintCnt() {
        boolean[] checked = new boolean[INF];
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(n, 0));

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (checked[now.num]) continue;
            checked[now.num] = true;

            // 누를 수 있는 횟수 초과
            if (now.cnt > t) return -1;

            // 목적지 도착
            if (now.num == g) return now.cnt;

            // 값이 99999면 더 이상 버튼 누를 수 X
            if (now.num == 99999) continue;
            if (!checked[now.num+1]) que.offer(new Pos(now.num+1, now.cnt+1));

            // 값이 0이면 2배 버튼 눌러도 0
            if (now.num == 0) continue;
            now.num *= 2;
            // 곱하기 버튼을 눌렀을 때 99999 넘어가면 안됨
            if (now.num > 99999) continue;
            // 앞자리 -1 해주기
            String str = String.valueOf(now.num);
            char[] tmp = str.toCharArray();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] == '0') continue;
                else {
                    int num = tmp[i]-'0';
                    tmp[i] = String.valueOf(num-1).charAt(0);
                    str = new String(tmp);
                    int result = Integer.parseInt(str);
                    if (!checked[result]) que.offer(new Pos(result, now.cnt+1));
                    break;
                }
            }
        }

        return -1;
    }
}