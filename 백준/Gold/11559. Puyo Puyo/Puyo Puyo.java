import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static char[][] map;
    static boolean[][] checked;
    static int r = 12;
    static int c = 6;
    static ArrayList<int[]> list;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y; char color;
        public Pos(int x, int y, char color) {
            this.x = x; this.y = y; this.color = color;
        }
    }
    static boolean flag;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        while (true) {
            flag = false;
            bfs();
            down();
            if (!flag) break;
            ans++;
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void down() {
        for (int i = 0; i < c; i++) {
            Queue<Pos> que = new LinkedList<>();
            int idx = 11;
            for (int j = idx; j >= 0; j--) {
                if (map[j][i] != '.') {
                    que.offer(new Pos(i, j, map[j][i]));
                    map[j][i] = '.';
                }
            }
            while (!que.isEmpty()) {
                Pos now = que.poll();
                map[idx][i] = now.color;
                idx--;
            }
        }
    }

    private static void bfs() {
        Queue<Pos> que = new LinkedList<>();
        checked = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != '.' && !checked[i][j]) {
                    list = new ArrayList<>();
                    list.add(new int[]{i, j});
                    que.offer(new Pos(i, j, map[i][j]));
                    checked[i][j] = true;

                    while (!que.isEmpty()) {
                        Pos now = que.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = dx[k] + now.x;
                            int ny = dy[k] + now.y;

                            if (nx >= 0 && nx < r && ny >= 0 && ny < c && !checked[nx][ny]) {
                                if (map[nx][ny] == now.color) {
                                    que.offer(new Pos(nx, ny, map[nx][ny]));
                                    list.add(new int[]{nx, ny});
                                    checked[nx][ny] = true;
                                }
                            }
                        }
                    }
                    
                    if (list.size() >= 4) {
                        for (int k = 0; k < list.size(); k++) {
                            int x = list.get(k)[0];
                            int y = list.get(k)[1];

                            map[x][y] = '.';
                            flag = true;
                        }
                    }
                }
            }
        }
    }
}