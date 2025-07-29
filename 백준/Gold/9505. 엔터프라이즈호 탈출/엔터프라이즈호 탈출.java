import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k, w, h;
    static int[][] map;
    static boolean[][] checked;
    static public class Pos implements Comparable<Pos> {
        int x, y, w;
        public Pos (int x, int y, int w) {
            this.x = x; this.y = y; this.w = w;
        }
        @Override
        public int compareTo(Pos o) {
            return this.w - o.w;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // 테스트케이스

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        PriorityQueue<Pos> pq;
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken()); // 클링온 전투선의 클래스 개수
            w = Integer.parseInt(st.nextToken()); // 평면의 폭, 열
            h = Integer.parseInt(st.nextToken()); // 평면의 높이, 행

            int[] cost = new int[26];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0); // 클링온 전투선의 클래스 이름
                int num = Integer.parseInt(st.nextToken()); // 무력화시키는 데에 결리는 시간

                cost[type-'A'] = num;
            }

            map = new int[h][w];
            checked = new boolean[h][w];
            pq = new PriorityQueue<>();
            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    char c = input.charAt(j);
                    if (c == 'E') { // 엔터프라이즈호의 위치
                        pq.offer(new Pos(i, j, 0));
                        checked[i][j] = true;
                    }
                    map[i][j] = c-'A';
                }
            }

            while (!pq.isEmpty()) {
                Pos now = pq.poll();

                if (now.x == h-1 || now.x == 0 || now.y == w-1 || now.y == 0) {
                    sb.append(now.w).append("\n");
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];

                    if (checked[nx][ny]) continue;

                    checked[nx][ny] = true;
                    pq.offer(new Pos(nx, ny, now.w+cost[map[nx][ny]]));
                }
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}