import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x, y, k;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dice = {0, 0, 0, 0, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 지도의 크기 nxm
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 처음 주사위 위치
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        // 명령의 개수
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m]; // 지도
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int dir;
        for (int i = 0; i < k; i++) {
            dir = Integer.parseInt(st.nextToken())-1;
            x += dx[dir];
            y += dy[dir];

            if (x < 0 || x >= n || y < 0 || y >= m) {
                x -= dx[dir];
                y -= dy[dir];
                continue;
            }

            switch (dir) {
                case 0 :
                    move_right();
                    break;
                case 1 :
                    move_left();
                    break;
                case 2:
                    move_up();
                    break;
                case 3 :
                    move_down();
                    break;
            }

            if (map[x][y] == 0) {
                map[x][y] = dice[1];
            } else {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }

            sb.append(dice[0]).append("\n");
        }

        System.out.println(sb);
    }

    private static void move_right() {
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[1];
        dice[1] = dice[5];
        dice[5] = tmp;
    }

    private static void move_left() {
        int tmp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[1];
        dice[1] = dice[4];
        dice[4] = tmp;
    }

    private static void move_up() {
        int tmp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[1];
        dice[1] = dice[3];
        dice[3] = tmp;
    }

    private static void move_down() {
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[1];
        dice[1] = dice[2];
        dice[2] = tmp;
    }
}