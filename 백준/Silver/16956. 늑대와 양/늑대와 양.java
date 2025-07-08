import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> que;
    static boolean flag = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        que = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'W') que.offer(new int[]{i, j}); // 늑대 위치 저장
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        if (flag) {
            sb.append(1).append("\n");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        } else {
            sb.append(0).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (rangeCheck(nx, ny)) continue;

                if (map[nx][ny] == '.') { // 빈 칸
                    map[nx][ny] = 'D';
                }
                if (map[nx][ny] == 'S') { // 양 만나면
                    flag = false;
                    return;
                }
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= r || y < 0 || y >= c;
    }
}