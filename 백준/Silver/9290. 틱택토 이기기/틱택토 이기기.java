import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 수열의 크기
        for (int t = 1; t <= n; t++) {
            board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                String str = br.readLine();
                for (int j = 0; j < 3; j++) {
                    board[i][j] = str.charAt(j);
                }
            }
            // System.out.println(Arrays.deepToString(board));
            char now = br.readLine().charAt(0);

            ticTacToe(now);

            sb.append("Case ").append(t).append(":").append("\n");
            for (char[] line : board) {
                sb.append(line).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void ticTacToe(char now) {
        int cnt = 0;
        // 가로
        for (int i = 0; i < 3; i++) {
            cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == now) cnt++;
            }
            if (cnt >= 2) {
                board[i][0] = now;
                board[i][1] = now;
                board[i][2] = now;
                return;
            }
        }

        // 세로
        for (int i = 0; i < 3; i++) {
            cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (board[j][i] == now) cnt++;
            }
            if (cnt >= 2) {
                board[0][i] = now;
                board[1][i] = now;
                board[2][i] = now;
                return;
            }
        }

        // 왼쪽 아래 대각선
        cnt = 0; int j = 2;
        for (int i = 0; i < 3; i++) {
            if (board[i][j] == now) cnt++;

            if (cnt >= 2) {
                board[0][2] = now;
                board[1][1] = now;
                board[2][0] = now;
                return;
            }
            j--;
        }

        // 오른쪽 아래 대각선
        cnt = 0;
        for (int i = 0; i < 3; i++) {
            j = i;
            if (board[i][j] == now) cnt++;

            if (cnt >= 2) {
                board[0][0] = now;
                board[1][1] = now;
                board[2][2] = now;
                return;
            }
            j--;
        }
    }
}