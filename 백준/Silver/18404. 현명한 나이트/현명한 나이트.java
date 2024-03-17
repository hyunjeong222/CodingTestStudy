import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static Queue<Pos> knight;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos(int x, int y){
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        knight = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        knight.offer(new Pos(x, y));

        map = new int[n+1][n+1];
        bfs();

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            sb.append(map[x][y]).append(" ");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        while (!knight.isEmpty()) {
            Pos now = knight.poll();
            for (int i = 0; i < 8; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx > 0 && nx <= n && ny > 0 && ny <= n) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = map[now.x][now.y]+1;
                        knight.offer(new Pos(nx, ny));
                    }
                }
            }
        }
    }
}