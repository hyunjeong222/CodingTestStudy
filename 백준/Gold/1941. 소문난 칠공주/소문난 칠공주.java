import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static char[][] map;
    static boolean[] checked;
    static int[] selected = new int[7];
    static int[] result = new int[7];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        // 7자리 선택
        backtracking(0, 0, 0);
        System.out.println(ans);
    }

    private static void backtracking(int depth, int start, int yNum) {
        // 이다솜파(S) 학생이 적어도 4명 이상 반드시 포함
        if (yNum >= 4) return;

        if (depth == 7) {
            checked = new boolean[7];
            bfs();
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[depth] = i;
            if (map[i / 5][i % 5] == 'Y') backtracking(depth+1, i+1, yNum+1);
            else backtracking(depth+1, i+1, yNum);
        }
    }

    private static void bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(selected[0]/5, selected[0]%5));
        checked[0] = true;
        int cnt = 1;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                int ni = nx*5 + ny;

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                for (int j = 0; j < 7; j++) {
                    if (!checked[j] && selected[j] == ni) {
                        que.offer(new Pos(nx, ny));
                        checked[j] = true;
                        cnt++;
                    }
                }
            }
        }

        if (cnt == 7) ans++;
    }
}