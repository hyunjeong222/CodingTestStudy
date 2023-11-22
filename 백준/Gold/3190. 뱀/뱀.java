import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int l;
    static int[][] map;
    static HashMap<Integer, String> change;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static public class pos {
        int x; int y;
        public pos (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine()); // 보드의 크기
        map = new int[n+1][n+1];
        k = Integer.parseInt(br.readLine()); // 사과의 개수
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = 1;
        }
        l = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
        change = new HashMap<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            change.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        int ans = getTime(1, 1, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getTime(int nx, int ny, int dir) {
        int time =  0;
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(nx, ny));
        map[nx][ny] = 2;

        while (true) {
            time++;

            nx += dx[dir];
            ny += dy[dir];

            if (nx < 1 || nx > n || ny < 1 || ny > n || map[nx][ny] == 2) break;

            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
            } else {
                pos tail = que.poll();
                map[tail.x][tail.y] = 0;
            }

            que.add(new pos(nx, ny));
            map[nx][ny] = 2;

            if (change.containsKey(time)) {
                String cDir = change.get(time);
                if (cDir.equals("L")) dir = dir == 0 ? 3 : dir-1;
                else dir = dir == 3 ? 0 : dir+1;
            }
        }

        return time;
    }
}