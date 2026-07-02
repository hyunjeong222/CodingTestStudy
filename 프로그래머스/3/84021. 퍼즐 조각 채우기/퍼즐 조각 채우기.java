import java.util.*;

class Solution {
    static int len;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static public class Pos implements Comparable<Pos> {
        int x, y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
        // 오름차순
        public int compareTo(Pos o) {
            int res = Integer.compare(this.x, o.x);
            if (res == 0) { // 같다면
                res = Integer.compare(this.y, o.y);
            }
            return res;
        }
    }
    public int solution(int[][] game_board, int[][] table) {
        int ans = 0; // 채울 수 있는 가장 많은 조각 수

        len = game_board.length;

        // game_board
        // 빈 칸 : 1, 채워진 칸 : 0
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (game_board[i][j] == 1) game_board[i][j] = 0;
                else game_board[i][j] = 1;
            }
        }

        List<List<Pos>> g = new ArrayList<>(); // 빈칸들
        List<List<Pos>> t = new ArrayList<>(); // 조각들

        boolean[][] checked_game = new boolean[len][len];
        boolean[][] checked_table = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!checked_table[i][j] && table[i][j] == 1){
                    bfs(i, j, table, checked_table, t);
                }
                if (!checked_game[i][j] && game_board[i][j] == 1){
                    bfs(i, j, game_board, checked_game, g);
                }
            }
        }

        ans = compareBlock(g, t, ans);

        return ans;
    }

    private static int compareBlock(List<List<Pos>> g, List<List<Pos>> t, int ans) {
        int gSize = g.size(); // 빈칸
        int tSize = t.size(); // 조각

        boolean[] checked = new boolean[gSize]; // 빈칸을 사용했는지

        for (int i = 0; i < tSize; i++) {
            for (int j = 0; j < gSize; j++) {
                // 이미 블록을 배치했거나, 칸 수가 달라 배치 불가능
                if (checked[j] || t.get(i).size() != g.get(j).size()) continue;
                // 회전해서 블록 배치 가능
                if (isRotate(t.get(i), g.get(j))) {
                    checked[j] = true;
                    ans += g.get(j).size();
                    break;
                }
            }
        }

        return ans;
    }

    private static boolean isRotate(List<Pos> t, List<Pos> g) {
        Collections.sort(g);

        for (int i = 0; i < 4; i++) {
            Collections.sort(t);

            // 기준점 재설정
            int nowX = t.get(0).x;
            int nowY = t.get(0).y;

            for (int j = 0; j < t.size(); j++) {
                t.get(j).x -= nowX;
                t.get(j).y -= nowY;
            }

            boolean flag = true;
            for (int j = 0; j < g.size(); j++) {
                if (g.get(j).x != t.get(j).x || g.get(j).y != t.get(j).y) {
                    flag = false; // 돌려도 블록이 안맞으면
                    break;
                }
            }

            if (flag) return true;
            else {
                // 회전
                for (int j = 0; j < t.size(); j++) {
                    int tmp = t.get(j).x;
                    t.get(j).x = t.get(j).y;
                    t.get(j).y = -tmp;
                }
            }
        }

        return false;
    }

    private static void bfs(int x, int y, int[][] board, boolean[][] checked, List<List<Pos>> list) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));

        checked[x][y] = true;

        List<Pos> sub = new ArrayList<>();
        sub.add(new Pos(0, 0));

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= len || ny >= len) continue;
                if (!checked[nx][ny] && board[nx][ny] == 1) {
                    checked[nx][ny] = true;
                    que.offer(new Pos(nx, ny));
                    sub.add(new Pos(nx-x, ny-y)); // (0, 0) 기준
                }
            }
        }

        list.add(sub);
    }
}