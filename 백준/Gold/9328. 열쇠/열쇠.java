import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int h, w;
    static char[][] map;
    static boolean[][] checked;
    static boolean[] key;
    static ArrayList<Pos>[] gate; // 열지 못한 문
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans;
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            ans = 0;

            map = new char[h+2][w+2];
            checked = new boolean[h+2][w+2];
            key = new boolean[26];
            gate = new ArrayList[26];

            for (int i = 0; i < 26; i++) {
                gate[i] = new ArrayList<>();
            }

            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    map[i][j] = '.';
                }
            }

            for (int i = 1; i <= h; i++) {
                String input = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = input.charAt(j-1);
                }
            }

            String keyInput = br.readLine();
            if (!keyInput.equals("0")) {
                for (int i = 0; i < keyInput.length(); i++) {
                    int tmp = keyInput.charAt(i) - 'a';
                    key[tmp] = true;
                }
            }

            // System.out.println(Arrays.toString(key));
            bfs();
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(0, 0));
        checked[0][0] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (rangeCheck(nx, ny) || checked[nx][ny]) continue; // 범위 아웃, 방문 체크
                if (map[nx][ny] == '*') continue; // 벽

                int alpha = map[nx][ny];
                if (alpha - 'A' >= 0 && alpha - 'A' <= 25) { // 문
                    if (key[alpha-'A']) { // 열쇠를 갖고있다면
                        map[nx][ny] = '.';
                        que.offer(new Pos(nx, ny));
                        checked[nx][ny] = true;
                    } else {
                        // 열지 못하는 문의 좌표를 저장
                        gate[alpha - 'A'].add(new Pos(nx, ny));
                    }
                } else if (alpha - 'a' >= 0 && alpha - 'a' <= 25) { // 열쇠
                    key[alpha-'a'] = true;
                    map[nx][ny] = '.';
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;

                    for (Pos g : gate[alpha-'a']) {
                        checked[g.x][g.y] = true;
                        que.offer(new Pos(g.x, g.y));
                    }
                } else if (alpha == '$') { // 문서
                    ans++;
                    map[nx][ny] = '.';
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                } else { // '.' 빈칸
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                }
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= h+2 || y < 0 || y >= w+2;
    }
}