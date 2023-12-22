import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int h, w, k, p, startX, startY, endX, endY;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x; int y; int power;
        public pos(int x, int y, int power) {
            this.x = x; this.y = y; this.power = power;
        }
    }
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine()); // 테스트케이스
        StringTokenizer st;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            startX = Integer.parseInt(st.nextToken())-1;
            startY = Integer.parseInt(st.nextToken())-1;
            endX = Integer.parseInt(st.nextToken())-1;
            endY = Integer.parseInt(st.nextToken())-1;
            map = new int[h][w];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken())-1;
                int y = Integer.parseInt(st.nextToken())-1;
                int l = Integer.parseInt(st.nextToken());
                map[x][y] = l;
            }
            checked = new boolean[h][w];
            bfs(startX, startY);
            if (flag) bw.append("잘했어!!" + "\n");
            else bw.append("인성 문제있어??" + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bfs(int x, int y) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(x, y, p));
        checked[x][y] = true;
        flag = false;

        while (!que.isEmpty()) {
            pos now = que.poll();

            if (now.power <= 0) continue;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w || checked[nx][ny]) continue;

                if (now.power < map[nx][ny] - map[now.x][now.y]) continue;

                if (nx == endX && ny == endY) flag = true;

                checked[nx][ny] = true;
                que.offer(new pos(nx, ny, now.power-1));
            }
        }
        return flag;
    }
}