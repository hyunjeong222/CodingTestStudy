import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {0, -1, 0, 1}; // 서 북 동 남
    static int[] dy = {-1, 0, 1, 0};
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int roomSize;
    static HashMap<Integer, Integer> mergeRoom;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 열
        m = Integer.parseInt(st.nextToken()); // 행
        map = new int[m][n];
        checked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // System.out.println(Arrays.deepToString(map));
        int roomCnt = 0; // 방의 개수
        int maxSize = 0; // 가장 넓은 방의 넓이
        mergeRoom = new HashMap<>(); // 방 번호, 넓이
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!checked[i][j]) {
                    roomCnt++;
                    roomSize = bfs(i, j, roomCnt);
                    mergeRoom.put(roomCnt, roomSize);
                    maxSize = Math.max(maxSize, roomSize);
                }
            }
        }
        // System.out.println(mergeRoom);
        // System.out.println(Arrays.deepToString(map));
        int brokenSize = brokenWall(); // 벽 하나 제거해서 얻을 수 있는 가장 넓은 방의 크기 구하기

        StringBuilder sb = new StringBuilder();
        sb.append(roomCnt).append("\n");
        sb.append(maxSize).append("\n");
        sb.append(brokenSize).append("\n");

        System.out.println(sb);
    }

    private static int brokenWall() {
        int size = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int now = map[i][j]; // 현재 방번호

                for (int d = 0; d < 4; d++) {
                    int nx = dx[d] + i;
                    int ny = dy[d] + j;

                    if (rangeCheck(nx, ny) || map[nx][ny] == now) continue;

                    size = Math.max(size, mergeRoom.get(now) + mergeRoom.get(map[nx][ny]));
                }
            }
        }

        return size;
    }

    private static int bfs(int x, int y, int cnt) {
        checked[x][y] = true;
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));
        roomSize = 0; // 방 넓이

        while (!que.isEmpty()) {
            Pos now = que.poll();
            // 서쪽 1, 북쪽 2, 동쪽 4, 남쪽 8
            // 벽이 있을 때는 이 값을 더한 값이 주어짐
            int wall = map[now.x][now.y];
            map[now.x][now.y] = cnt;
            roomSize++;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                // 범위 아웃, 방문체크
                if (rangeCheck(nx, ny) || checked[nx][ny]) continue;
                // 벽 체크
                // 비트가 1이면 벽이 있음
                if ((wall & (1 << i)) > 0) continue;

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny));
            }
        }

        return roomSize;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}