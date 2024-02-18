import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] map;
    static boolean[][] checked;
    static String move;
    static int [] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int [] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static Queue<Pos> jongsu;
    static Queue<Pos> aduino;
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        checked = new boolean[r][c];
        jongsu = new LinkedList<>();
        aduino = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'I') jongsu.offer(new Pos(i, j));
                else if (map[i][j] == 'R') aduino.offer(new Pos(i, j));
            }
        }
        boolean flag = true;
        int cnt = 1;
        move = br.readLine();
        for (int i = 0; i < move.length(); i++) {
            if (!jongsuMove(move.charAt(i)-'0')) {
                flag = false; break;
            }
            if (!aduinoMove()) {
                flag = false; break;
            }
            map();
            cnt++;
        }
        StringBuilder sb = new StringBuilder();
        if (!flag) System.out.println("kraj " + cnt);
        else {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void map() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = '.';
            }
        }
        Pos js = jongsu.peek();
        map[js.x][js.y] = 'I';
        for (int i = 0; i < aduino.size(); i++) {
            Pos ad = aduino.poll();
            map[ad.x][ad.y] = 'R';
            aduino.offer(new Pos(ad.x, ad.y));
        }
    }

    private static boolean aduinoMove() {
        Pos jsNow = jongsu.peek();
        int jsX = jsNow.x;
        int jsY = jsNow.y;
        int[][] aduinoCnt = new int[r][c];

        while (!aduino.isEmpty()) {
            Pos adNow = aduino.poll();
            int d = 0;
            int len = Integer.MAX_VALUE;
            for (int i = 1; i < 10; i++) {
                if (i == 5) continue;
                int nx = dx[i] + adNow.x;
                int ny = dy[i] + adNow.y;
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                int distance = Math.abs(jsX-nx) + Math.abs(jsY-ny);
                if (len > distance) {
                    len = distance;
                    d = i;
                }
            }
            int adX = adNow.x + dx[d];
            int adY = adNow.y + dy[d];

            if (map[adX][adY] == 'I') return false;

            aduinoCnt[adX][adY] += 1;
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (aduinoCnt[i][j] == 1) {
                    aduino.offer(new Pos(i, j));
                }
            }
        }
        return true;
    }

    private static boolean jongsuMove(int d) {
        Pos now = jongsu.poll();
        int nx = now.x + dx[d];
        int ny = now.y + dy[d];
        if (map[nx][ny] == 'R') return false;
        else {
            jongsu.offer(new Pos(nx, ny));
            if (d != 5) {
                map[nx][ny] = map[now.x][now.y];
                map[now.x][now.y] = '.';
            }
            return true;
        }
    }
}