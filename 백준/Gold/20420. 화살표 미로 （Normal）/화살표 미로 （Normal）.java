import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static int[][] map;
    static boolean[][][][] checked;
    // 상우하좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열
        k = Integer.parseInt(st.nextToken()); // 주문서 세트 개수

        map = new int[r][c]; // 지도
        checked = new boolean[r][c][k+1][k+1]; // 행열좌우

        // 화살표 미로 지도 입력
        String str;
        for (int i = 0; i < r; i++) {
            str = br.readLine();
            for (int j = 0; j < c; j++) {
                char tmp = str.charAt(j);

                // 상우하좌 1234
                if (tmp == 'U') map[i][j] = 0;
                else if (tmp == 'D') map[i][j] = 2;
                else if (tmp == 'L') map[i][j] = 3;
                else map[i][j] = 1; // R
            }
        }

        boolean flag = bfs();

        System.out.println(flag ? "Yes" : "No");
    }

    private static boolean bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{0, 0, k, k});
        checked[0][0][k][k] = true;

        int x, y, left, right;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            x = now[0]; y = now[1];
            left = now[2]; right = now[3];

            // 출구 도착
            if (x == r-1 && y == c-1) return true;

            int d = map[x][y]; // 현재 방향
            // 지도 방향대로 이동
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (rangeCheck(nx, ny) && !checked[nx][ny][left][right]) {
                checked[nx][ny][left][right] = true;
                que.offer(new int[]{nx, ny, left, right});
            }

            // 좌회전
            if (left > 0) {
                int nd = d;
                int cnt = Math.min(3, left); // 남은 좌회전 횟수와 최대 3번 중 작은 값 선택

                for (int i = 1; i <= cnt; i++) {
                    nd = (nd+3)%4;
                    nx = x + dx[nd];
                    ny = y + dy[nd];

                    if (rangeCheck(nx, ny) && !checked[nx][ny][left-i][right]) {
                        checked[nx][ny][left-i][right] = true;
                        que.offer(new int[]{nx, ny, left-i, right});
                    }
                }
            }

            // 우회전
            if (right > 0) {
                int nd = d;
                int cnt = Math.min(3, right); // 남은 우회전 횟수와 최대 3번 중 작은 값 선택

                for (int i = 1; i <= cnt; i++) {
                    nd = (nd+1)%4;
                    nx = x + dx[nd];
                    ny = y + dy[nd];

                    if (rangeCheck(nx, ny) && !checked[nx][ny][left][right-i]) {
                        checked[nx][ny][left][right-i] = true;
                        que.offer(new int[]{nx, ny, left, right-i});
                    }
                }
            }
        }

        return false;
    }

    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}