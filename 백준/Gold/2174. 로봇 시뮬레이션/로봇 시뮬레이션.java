import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int a, b;
    static int[][] map;
    static Pos[] robot;
    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static public class Pos {
        int x; int y; int d;
        public Pos(int x, int y, int d) {
            this.x = x; this.y = y; this.d = d;
        }
    }
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        map = new int[b+1][a+1];
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        robot = new Pos[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            int d;
            if (dir == 'N') d = 0;
            else if (dir == 'E') d = 1;
            else if (dir == 'S') d = 2;
            else d = 3; // W

            map[y][x] = i;
            robot[i] = new Pos(x, y, d);
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            char type = st.nextToken().charAt(0);
            int iter = Integer.parseInt(st.nextToken());

            move(num, type, iter);

            if (flag) break;
        }

        if (!flag) System.out.println("OK");
    }

    private static void move(int num, char type, int iter) {
        while (iter --> 0) {
            Pos now = robot[num];

            if (type == 'L' || type == 'R') {
                int nextDir = getDir(type, now.d);
                robot[num] = new Pos(now.x, now.y, nextDir);
            } else { // F
                int nx = now.x + dx[now.d];
                int ny = now.y + dy[now.d];

                if (nx < 1 || nx >= a+1 || ny < 1 || ny >= b+1) { // 범위 아웃
                    System.out.println("Robot "+num+" crashes into the wall");
                    flag = true;
                    return;
                }

                if (map[ny][nx] != 0) {
                    System.out.println("Robot "+num+" crashes into robot "+map[ny][nx]);
                    flag = true;
                    return;
                } else {
                    map[now.y][now.x] = 0;
                    map[ny][nx] = num;
                    robot[num] = new Pos(nx, ny, now.d);
                }
            }
        }
    }

    private static int getDir(char type, int d) {
        if (type == 'L') {
            if (d == 0) d = 3;
            else d--;
        } else {
            if (d == 3) d = 0;
            else d++;
        }
        return d;
    }
}