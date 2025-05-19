import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n , k;
    static int[][] map;
    static boolean[][] checked;
    static public class Pos {
        int x; int y; int time;
        public Pos (int x, int y, int time) {
            this.x = x; this.y = y; this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[2][n];
        checked = new boolean[2][n];

        for (int i = 0; i < 2; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }
        // System.out.println(Arrays.deepToString(map));

        boolean flag = bfs(0, 0);

        System.out.println(flag ? 1 : 0);

        br.close();
    }

    private static boolean bfs(int x, int y) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y, 0));
        checked[x][y] = true;

        int[] dy = {-1, 1, k};
        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int d = 0; d < 3; d++) {
                int ny = now.y + dy[d];
                int nx = now.x;
                int time = now.time;

                // 점프 시 행을 전환
                if (d == 2) {
                    if (now.x == 1) nx = 0;
                    else nx = 1;
                }

                // N번 칸보다 더 큰 칸으로 이동하는 경우
                // 게임 클리어
                if (ny >= n) return true;

                // 0 : 위험한 칸, 1 : 안전한 칸
                if (ny <= time) continue; // 뒤로 점프하거나 이미 방문한 위치로 점프할 수 없음
                if (map[nx][ny] == 0 || checked[nx][ny]) continue;
                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny, time+1));
            }
        }

        return false;
    }
}