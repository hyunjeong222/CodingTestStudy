import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n = 4;
    static int[][] play;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            play = new int[6][3];
            ans = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    play[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // System.out.println(Arrays.deepToString(play));

            // 경기 수가 5가 아닌 국가가 있을 경우 올바른 결과 불가
            boolean flag = false;
            for (int i = 0; i < 6; i++) {
                if (play[i][0]+play[i][1]+play[i][2] != 5) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                dfs(0, 1);
            }

            sb.append(ans).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int depth, int next) {
        if (ans == 1) { // 해당 결과가 올바르다면 재귀 종료
            return;
        }

        if (depth == 5) { // 모든 국가가 올바른 경기 진행
            ans = 1;
            return;
        }

        // 현재 국가가 상대 국가를 이겼을 때
        if (play[depth][0] > 0 && play[next][2] > 0) {
            play[depth][0]--;
            play[next][2]--;

            if (next == 5) { // 현재 국가 탐색 완료
                dfs(depth+1, depth+2);
            } else {
                dfs(depth, next + 1);
            }
            play[depth][0]++;
            play[next][2]++;
        }

        // 현재 국가가 상대 국가와 무승부일 때
        if (play[depth][1] > 0 && play[next][1] > 0) {
            play[depth][1]--;
            play[next][1]--;

            if (next == 5) {
                dfs(depth+1, depth+2);
            } else {
                dfs(depth, next + 1);
            }
            play[depth][1]++;
            play[next][1]++;
        }

        // 현재 국가가 상대 국가한테 졌을 때
        if (play[depth][2] > 0 && play[next][0] > 0) {
            play[depth][2]--;
            play[next][0]--;

            if (next == 5) {
                dfs(depth+1, depth+2);
            } else {
                dfs(depth, next + 1);
            }
            play[depth][2]++;
            play[next][0]++;
        }
    }
}