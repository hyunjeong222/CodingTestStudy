import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] checked;
    static int cnt;
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x;
        int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        checked = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !checked[i][j]) {
                    bfs(i, j);
                }
            }
        }

        Collections.sort(list);
        bw.append(list.size() + " \n");
        for (int i = 0; i < list.size(); i++) {
            bw.append(list.get(i) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int x, int y) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(x, y));
        checked[x][y] = true;
        cnt = 0;

        while (!que.isEmpty()) {
            pos now = que.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !checked[nx][ny] && map[nx][ny] == 1) {
                    que.offer(new pos(nx, ny));
                    checked[nx][ny] = true;
                }
            }
        }
        list.add(cnt);
    }
}