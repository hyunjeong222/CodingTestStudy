import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, q;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int total, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken()); // 파이어스톰을 총 Q번 시전

        n = (int) Math.pow(2, n);
        map = new int[n][n]; // 2N × 2N인 격자
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] l = new int[q]; // 마법사 상어가 시전한 단계
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            l[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < q; i++) {
            map = divide(l[i]); // 부분 격자 나누기
            map = melt(); // 인접한 얼음 칸이 3개 미만인 칸 찾아 얼음 녹이기
        }

        // 남아있는 얼음의 합, 가장 큰 덩어리가 차지하는 칸의 개수
        total = max = 0;
        biggest();

        StringBuilder sb = new StringBuilder();
        sb.append(total).append("\n").append(max);

        System.out.println(sb.toString());

        br.close();
    }

    private static int[][] divide(int l) {
        int[][] tmp = new int[n][n];
        l = (int) Math.pow(2, l);
        for (int i = 0; i < n; i+=l) {
            for (int j = 0; j < n; j+=l) {
                rotate(i, j, l, tmp); // 시계 방향으로 90도 회전
            }
        }

        return tmp;
    }

    private static void rotate(int x, int y, int l, int[][] tmp) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                tmp[i+x][j+y] = map[x+l-1-j][y+i];
            }
        }
    }
    
    private static int[][] melt() {
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            tmp[i] = Arrays.copyOf(map[i], n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0; // 인접한 얼음이 있는 칸의 개수
                if (map[i][j] == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (map[nx][ny] > 0) cnt++;
                    }
                }

                if (cnt < 3) tmp[i][j]--; // 3개 미만이라면 얼음 녹이기
            }
        }

        return tmp;
    }
    
    private static void biggest() {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] checked = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                total += map[i][j]; // 남아있는 얼음의 합
                if (map[i][j] > 0 && !checked[i][j]) {
                    que.offer(new int[]{i, j});
                    checked[i][j] = true;
                    int cnt = 1; // 현재 영역의 칸의 개수

                    while (!que.isEmpty()) {
                        int[] now = que.poll();

                        for (int d = 0; d < 4; d++) {
                            int nx = now[0] + dx[d];
                            int ny = now[1] + dy[d];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                                if (map[nx][ny] > 0 && !checked[nx][ny]) {
                                    que.offer(new int[]{nx, ny});
                                    checked[nx][ny] = true;
                                    cnt++;
                                }
                            }
                        }
                    }
                    
                    max = Math.max(max, cnt);
                }
            }
        }
    }
}