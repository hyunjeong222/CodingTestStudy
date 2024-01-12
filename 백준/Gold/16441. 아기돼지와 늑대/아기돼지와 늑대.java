import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][][] checked;
    static ArrayList<Pos> list;
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
        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        map = new char[n][m];
        checked = new boolean[n][m][4];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'W') list.add(new Pos(i, j, 0)); // 늑대 위치 저장
            }
        }
        
        bfs(list);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '.') {
                    if (check(i, j)) {
                        sb.append('P');
                    } else sb.append('.');
                } else sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (checked[x][y][i]) return false;
        }
        return true;
    }

    private static void bfs(ArrayList<Pos> list) {
        Queue<Pos> que = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            checked[list.get(i).x][list.get(i).y][list.get(i).dir] = true;
            que.offer(new Pos(list.get(i).x, list.get(i).y, list.get(i).dir));
        }

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny][i] && map[nx][ny] != '#') {
                    checked[nx][ny][i] = true;
                    if (map[nx][ny] == '+') {
                        Pos next = sliding(nx, ny, i);
                        que.offer(new Pos(next.x, next.y, next.dir));
                    } else {
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

            if (map[x][y] == '#') {
                return new Pos(x - dx[d], y - dy[d], d);
            } else if (map[x][y] == '.') {
                return new Pos(x, y, d);
            }
        }
    }
}