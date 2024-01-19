import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][][] checked;
    static ArrayList<Pos> list;
    static Queue<Pos> que;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y; int dir;
        public Pos (int x, int y, int dir) {
            this.x = x; this.y = y; this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'W') list.add(new Pos(i, j, 0)); // 늑대의 위치, 방향 저장
            }
        }
        checked = new boolean[n][m][4];
        bfs(list);
        // 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '.') { // 초원에서
                    if (check(i, j)) { // 늑대가 방문하지 않은 안전한 곳인지 체크
                        sb.append('P'); // 안전한 곳이라면 P
                    } else sb.append(map[i][j]);
                } else sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        br.close();
        System.out.println(sb);
    }

    private static boolean check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            // true라면 늑대가 방문했던 곳으로 안전한 곳 X
            if (checked[x][y][i]) return false;
        }
        return true;
    }

    private static void bfs(ArrayList<Pos> list) {
        que = new LinkedList<>();
        // 늑대의 위치 큐에 담고, 방문체크
        for (int i = 0; i < list.size(); i++) {
            checked[list.get(i).x][list.get(i).y][list.get(i).dir] = true;
            que.offer(new Pos(list.get(i).x, list.get(i).y, list.get(i).dir));
        }
        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 범위 안에 있고, 방문했던 위치, 방향이 아니고, 산이 아닌경우
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny][i] && map[nx][ny] != '#') {
                    checked[nx][ny][i] = true;
                    if (map[nx][ny] == '+') { // 빙판
                        Pos next = sliding(nx, ny, i); // 산이나 초원을 만날때까지 미끄러짐
                        que.offer(new Pos(next.x, next.y, next.dir));
                    } else { // 초원
                        que.offer(new Pos(nx, ny, i));
                    }
                }
            }
        }
    }

    private static Pos sliding(int x, int y, int d) {
        while (true) {
            x += dx[d];
            y += dy[d];

            checked[x][y][d] = true;

            if (map[x][y] == '#') { // 산을 만났다면
                return new Pos(x-dx[d], y-dy[d], d); // 멈취서 다른 방향으로 이동 가능
            } else if (map[x][y] == '.') { // 초원이라면
                return new Pos(x, y, d);
            }
        }
    }
}