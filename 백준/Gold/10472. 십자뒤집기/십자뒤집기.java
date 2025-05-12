import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int N = 3, M = 3;
    static final char BLACK = '*', WHITE = '.';
    static boolean[] checked;
    static public class Board {
        int state; int cnt; // 보드 상태, 뒤집기 횟수
        public Board (int state, int cnt) {
            this.state = state; this.cnt = cnt;
        }
    }
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            int board = 0;
            for (int row = 0; row < N; row++) {
                String str = br.readLine();
                for (int col = 0; col < M; col++) {
                    if (str.charAt(col) == BLACK) {
                        board = changeColor(board, row, col);
                    }
                }
            }

            sb.append(bfs(board)).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int bfs(int board) {
        Queue<Board> que = new LinkedList<>();
        checked = new boolean[1<<9];
        que.offer(new Board(board, 0));
        checked[board] = true;

        while (!que.isEmpty()) {
            Board now = que.poll();

            // 흰 보드가 된 경우 종료
            if (now.state == 0) {
                return now.cnt;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int nextState = getNextState(now.state, i, j);

                    if (checked[nextState]) continue;

                    checked[nextState] = true;
                    que.offer(new Board(nextState, now.cnt+1));
                }
            }
        }

        return -1;
    }

    private static int getNextState(int now, int row, int col) {
        int nextState = now;

        for (int d = 0; d < 5; d++) {
            int nx = row + dx[d];
            int ny = col + dy[d];

            if (rangeCheck(nx, ny)) continue;

            nextState = changeColor(nextState, nx, ny);
        }

        return nextState;
    }

    private static int changeColor(int state, int row, int col) {
        int idx = row * N + col;

        return state ^ (1 << idx);
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}