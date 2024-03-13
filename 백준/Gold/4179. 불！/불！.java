import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Pos> person;
    static Queue<Pos> fire;
    static public class Pos {
        int x; int y; int time;
        public Pos(int x, int y, int time) {
            this.x = x; this.y = y; this.time = time;
        }
    }
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        person = new LinkedList<>();
        fire = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') person.offer(new Pos(i, j, 0));
                else if (map[i][j] == 'F') fire.offer(new Pos(i, j, 0));
            }
        }
        ans = 0;
        bfs();
        if (ans == 0) System.out.println("IMPOSSIBLE");
        else System.out.println(ans);
    }

    private static void bfs() {
        while (!person.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Pos now = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = dx[d] + now.x;
                    int ny = dy[d] + now.y;

                    if (range(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == 'J')) {
                        map[nx][ny] = '*';
                        fire.offer(new Pos(nx, ny, 0));
                    }
                }
            }

            size = person.size();
            for (int i = 0; i < size; i++) {
                Pos now = person.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = dx[d] + now.x;
                    int ny = dy[d] + now.y;

                    if (!range(nx, ny)) {
                        ans = now.time+1;
                        return;
                    }

                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'J';
                        person.offer(new Pos(nx, ny, now.time+1));
                    }
                }
            }
        }
    }

    private static boolean range(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}