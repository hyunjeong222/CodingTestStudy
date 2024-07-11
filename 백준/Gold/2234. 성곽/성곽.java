import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n;
    static String[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0}; // 북 남 서 동
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int roomSize; // 방 넓이
    static int brokenRoomSize; // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
    static HashMap<String, Integer> mergeRoom = new HashMap<>(); // 방 번호, 넓이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 서쪽 1, 북쪽 2, 동쪽 4, 남쪽 8을 더한 값
        // 2진수로 변환하여 벽의 위치 파악
        // 1000: 남쪽에 벽, 0100: 동쪽에 벽, 0010: 북쪽에 벽, 0001: 서쪽에 벽
        map = new String[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int num = Integer.parseInt(st.nextToken());
                // 4자리수만 들어가게 2진수로 변환
                map[i][j] = String.format("%04d", Integer.parseInt(Integer.toBinaryString(num)));
            }
        }
        // System.out.println(Arrays.deepToString(map));

        int roomCnt = 0; // 방의 개수
        int roomMax = 0; // 가장 넓은 방의 넓이
        checked = new boolean[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (!checked[i][j]) {
                    roomCnt++;
                    roomSize = 1; // 방의 넓이
                    bfs(i, j, roomCnt);
                    mergeRoom.put(String.valueOf(roomCnt), roomSize); // 방 번호, 넓이
                    roomMax = Math.max(roomMax, roomSize);
                }
            }
        }
        // System.out.println(Arrays.deepToString(map));

        breakWall(); // 벽 하나를 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기

        StringBuilder sb = new StringBuilder();
        sb.append(roomCnt).append("\n");
        sb.append(roomMax).append("\n");
        sb.append(brokenRoomSize).append("\n");

        System.out.println(sb);
    }

    private static void bfs(int x, int y, int cnt) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            // 현재 위치의 벽 정보 가져오기
            String direction = map[now.x][now.y];
            // 벽을 부쉈을 때 합친 크기를 쉽게 구하기 위해 방 번호 넣어주기
            map[now.x][now.y] = String.valueOf(cnt);

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx < 1 || nx > m || ny < 1 || ny > n) continue;

                // 동쪽으로 이동 가능
                if (direction.charAt(1) == '0' && d == 3 && !checked[nx][ny]) {
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                    roomSize++;
                }
                // 남쪽으로 이동 가능
                if (direction.charAt(0) == '0' && d == 1 && !checked[nx][ny]) {
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                    roomSize++;
                }
                // 북쪽으로 이동 가능
                if (direction.charAt(2) == '0' && d == 0 && !checked[nx][ny]) {
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                    roomSize++;
                }
                // 서쪽으로 이동 가능
                if (direction.charAt(3) == '0' && d == 2 && !checked[nx][ny]) {
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                    roomSize++;
                }
            }
        }
    }
    
    private static void breakWall() {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                String nowRoom = map[i][j];

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx < 1 || nx > m || ny < 1 || ny > n) continue;

                    String nextRoom = map[nx][ny];

                    if (nowRoom.equals(nextRoom)) continue; // 같은 방이면 넘어가기

                    brokenRoomSize = Math.max(brokenRoomSize, mergeRoom.get(nowRoom)+mergeRoom.get(nextRoom));
                }
            }
        }
    }
}