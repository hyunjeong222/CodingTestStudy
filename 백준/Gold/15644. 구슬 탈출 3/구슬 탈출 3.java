import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][][][] checked;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static String[] dir = {"L", "R", "U", "D"};
    static public class Pos {
        int redX; int redY;
        int blueX; int blueY;
        int cnt; String dir;
        public Pos(int redX, int redY, int blueX, int blueY, int cnt, String dir) {
            this.redX = redX; this.redY = redY;
            this.blueX = blueX; this.blueY = blueY;
            this.cnt = cnt; this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        int redI = 0; int redJ = 0;
        int blueI = 0; int blueJ = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    redI = i; redJ = j;
                } else if (map[i][j] == 'B') {
                    blueI = i; blueJ = j;
                }
            }
        }
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(redI, redJ, blueI, blueJ, 0, ""));
        checked = new boolean[n][m][n][m];
        checked[redI][redJ][blueI][blueJ] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.cnt > 10) {
                System.out.println(-1);
                System.exit(0);
            }

            if (map[now.blueX][now.blueY] == 'O') continue;

            if (map[now.redX][now.redY] == 'O' && map[now.blueX][now.blueY] != 'O') {
                System.out.println(now.cnt);
                System.out.println(now.dir);
                System.exit(0);
            }

            for (int i = 0; i < 4; i++) {
                int rnx = now.redX;
                int rny = now.redY;

                while (true) {
                    if (rnx+dx[i] >= 0 && rny+dy[i] >= 0) {
                        rnx += dx[i];
                        rny += dy[i];
                        if (map[rnx][rny] == '#' || map[rnx][rny] == 'O') break;
                    }
                }

                if (map[rnx][rny] == '#') {
                    rnx -= dx[i];
                    rny -= dy[i];
                }

                int bnx = now.blueX;
                int bny = now.blueY;

                while (true) {
                    if (bnx+dx[i] >= 0 && bny+dy[i] >= 0) {
                        bnx += dx[i];
                        bny += dy[i];
                        if (map[bnx][bny] == '#' || map[bnx][bny] == 'O') break;
                    }
                }

                if (map[bnx][bny] == '#') {
                    bnx -= dx[i];
                    bny -= dy[i];
                }

                if (rnx == bnx && rny == bny && map[rnx][rny] != 'O') {
                    int rDir = Math.abs(rnx - now.redX) + Math.abs(rny - now.redY);
                    int bDir = Math.abs(bnx - now.blueX) + Math.abs(bny - now.blueY);

                    if (rDir > bDir) {
                        rnx -= dx[i];
                        rny -= dy[i];
                    } else {
                        bnx -= dx[i];
                        bny -= dy[i];
                    }
                }

                if (!checked[rnx][rny][bnx][bny]) {
                    checked[rnx][rny][bnx][bny] = true;
                    que.offer(new Pos(rnx, rny, bnx, bny, now.cnt+1, now.dir+dir[i]));
                }
            } // for
        }
        System.out.println(-1);
    }
}