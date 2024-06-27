import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int sy = parseInt(st.nextToken()) - 1;
        int sx = parseInt(st.nextToken()) - 1;
        int ey = parseInt(st.nextToken()) - 1;
        int ex = parseInt(st.nextToken()) - 1;

        String line;
        char val;
        for (int y = 0; y < N; y++) {
            line = br.readLine();
            for (int x = 0; x < M; x++) {
                val = line.charAt(x);
                if (val == '#' || val == '*')
                    continue;

                map[y][x] = val - '0';
            }
        }

        map[ey][ex] = 1;
        System.out.println(bfs(sx, sy, ex, ey));
        br.close();
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(sx, sy, 0));
        visited[sy][sx] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int nx, ny;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.x == ex && cur.y == ey) return cur.level;

            for (int i = 0; i < dx.length; i++) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;

                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                if (map[ny][nx] == 0) {
                    queue.offerFirst(new Node(nx, ny, cur.level));
                } else {
                    queue.offerLast(new Node(nx, ny, cur.level + 1));
                }
            }
        }

        return -1;
    }

    static class Node {
        int x, y, level;

        public Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }
}