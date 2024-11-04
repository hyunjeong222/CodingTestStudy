import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n; // 공간의 크기
    // 상어의 초기 위치, 사이즈
    static int s_x, s_y, size = 2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static public class Fish implements Comparable<Fish> {
        int x; int y; int dist;
        public Fish (int x, int y, int dist) {
            this.x = x; this.y = y; this.dist = dist;
        }
        @Override
        public int compareTo(Fish o) {
            if (this.dist == o.dist) { // 거리가 같다면
                if (this.x != o.x) {
                    return this.x - o.x; // 위쪽부터
                }
                return this.y - o.y; // 그러한 물고기가 여러마리라면 가장 왼쪽
            }
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) { // 상어 위치
                    s_x = i; s_y = j;
                    map[i][j] = 0;
                }
            }
        }

        int ans = 0, cnt = 0;
        while (true) {
            // 먹을 수 있는 물고기 있나 탐색
            Fish fish = bfs();

            if (fish == null) {
                break;
            } else { // 먹을 수 있는 물고기 있음
                map[fish.x][fish.y] = 0;
                s_x = fish.x; s_y = fish.y;
                cnt++;
                ans += fish.dist;
                if (cnt == size) {
                    cnt = 0;
                    size++;
                }
            }
        }

        System.out.println(ans);
    }

    private static Fish bfs() {
        PriorityQueue<Fish> pq = new PriorityQueue<>();

        boolean[][] checked = new boolean[n][n];
        Queue<Fish> que  = new LinkedList<>();
        que.add(new Fish(s_x, s_y, 0));
        checked[s_x][s_y] = true;

        while (!que.isEmpty()) {
            Fish fish = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = fish.x + dx[i];
                int ny = fish.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (!checked[nx][ny] && map[nx][ny] <= size) {
                        que.add(new Fish(nx, ny, fish.dist+1));
                        checked[nx][ny] = true;
                        // 자신과 크기가 같은 물고기는 그냥 지나감
                        if (map[nx][ny] != 0 && map[nx][ny] < size) {
                            pq.add(new Fish(nx, ny, fish.dist+1));
                        }
                    }
                }
            }
        }

        if (pq.isEmpty()) {
            return null;
        }
        return pq.poll();
    }
}