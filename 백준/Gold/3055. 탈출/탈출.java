import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> que;
    static Queue<int[]> water;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        que = new LinkedList<>();
        water = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    que.offer(new int[]{i, j, 0});
                } else if (map[i][j] == '*') {
                    water.offer(new int[]{i, j});
                }
            }
        }
        bfs();
        if (ans == Integer.MAX_VALUE) bw.append("KAKTUS" + "\n");
        else bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            int wl = water.size();
            for (int i = 0; i < wl; i++) {
                int[] nowWater = water.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = nowWater[0] + dx[j];
                    int ny = nowWater[1] + dy[j];

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        water.offer(new int[]{nx, ny});
                    }
                }
            }
            
            int sl = que.size();
            for (int i = 0; i < sl; i++) {
                int[] nowQue = que.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = nowQue[0] + dx[j];
                    int ny = nowQue[1] + dy[j];
                    int time = nowQue[2];

                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        if (map[nx][ny] == 'D') {
                            ans = Math.min(ans, time+1);
                            return;
                        } else if (map[nx][ny] == '.') {
                            map[nx][ny] = 'S';
                            que.offer(new int[]{nx, ny, time+1});
                        }
                    }
                }
            }
        }
    }
}