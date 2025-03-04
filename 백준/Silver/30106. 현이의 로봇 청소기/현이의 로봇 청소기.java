import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] map;
    static int[][] visited;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //세로 길이
        m = Integer.parseInt(st.nextToken()); //가로 길이
        k = Integer.parseInt(st.nextToken()); //극복 가능 차이...

        visited = new int[n][m];
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //입력 끝

        int operations = 0; //작동 횟수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) { //방문하지 않았으면
                    operations++;
                    bfs_30106(i, j, operations); //좌표 + 방문 처리(작동 횟수로 처리)`
                }
            }
        }

        System.out.println(operations);
    }

    private static void bfs_30106(int x, int y, int operations) {
        Queue<Point> q = new ArrayDeque();
        q.offer(new Point(x, y));
        visited[x][y] = operations; //방문 처리

        while (!q.isEmpty()) {
            Point p = q.poll();
            int curHeight = map[p.x][p.y];

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (nx>=0 && nx<n && ny>=0 && ny<m && visited[nx][ny] == 0
                        && Math.abs(curHeight-map[nx][ny]) <= k) {
                    visited[nx][ny] = operations;
                    q.offer(new Point(nx, ny));
                }
            }
        }
        //
    }
}