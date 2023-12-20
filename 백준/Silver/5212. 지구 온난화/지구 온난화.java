import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        Queue<pos> que = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'X') que.offer(new pos(i, j));
            }
        }

        while (!que.isEmpty()) {
            pos now = que.poll();
            int cnt = 0;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                    if (map[nx][ny] == '.') cnt++;
                } else cnt++;
            }

            if (cnt >= 3) {
                map[now.x][now.y] = '#';
            }
        }
        
        int minR = Integer.MAX_VALUE;
        int maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE;
        int maxC = Integer.MIN_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'X') {
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);
                }
            }
        }
        
        for (int i = minR; i <= maxR; i++) {
            for (int j = minC; j <= maxC; j++) {
                if (map[i][j] == '#') sb.append('.');
                else sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}