import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;
    static int[] state;
    static boolean[][] checked;
    static public class Info {
        int type, l, r;
        public Info (int type, int l, int r) {
            this.type = type; this.l = l; this.r = r;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 약을 먹어야 하는 날짜

        String str = br.readLine(); // 약의 상태
        state = new int[n*3+1];
        checked = new boolean[n*3+1][n*3+1];

        char tmp;
        for (int i = 0; i < str.length(); i++) {
            tmp = str.charAt(i);
            if (tmp == 'B') state[i] = 0; // 아침
            else if (tmp == 'L') state[i] = 1; // 점심
            else state[i] = 2; // 저녁
        }

        int ans = bfs(0, n*3-1);

        System.out.println(ans);

        br.close();
    }

    private static int bfs(int l, int r) {
        int cnt = 0; // 윤호가 먹을 수 있는 약의 최대 개수
        Queue<Info> que = new LinkedList<>();

        // 양 끝에 아침 약이 있으면 먹을 수 있음
        if (state[l] == 0) {
            que.offer(new Info(0, l+1, r));
            checked[l+1][r] = true;
        }
        if (state[r] == 0) {
            que.offer(new Info(0, l, r-1));
            checked[l][r-1] = true;
        }

        while (!que.isEmpty()) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                Info now = que.poll();
                l = now.l;
                r = now.r;

                if (l > r) continue; // 범위 벗어남

                int nextType = (now.type + 1) % 3;

                if (l <= r && state[l] == nextType && !checked[l+1][r]) {
                    que.offer(new Info(nextType, l+1, r));
                    checked[l+1][r] = true;
                }

                if (r >= l && state[r] == nextType && !checked[l][r-1]) {
                    que.offer(new Info(nextType, l, r-1));
                    checked[l][r-1] = true;
                }
            }

            cnt++;
        }

        return cnt;
    }
}