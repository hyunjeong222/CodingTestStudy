import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static public class Pos implements Comparable<Pos> {
        int x, y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
        public int compareTo(Pos o) {
            int res = Integer.compare(this.x, o.x);
            if(res==0){
                res = Integer.compare(this.y, o.y);
            }
            return res;
        }
    }

    static int len;
    
    public int solution(int[][] game_board, int[][] table) {
        int ans = 0;

        len = game_board.length; // 보드 길이
        // 빈 칸 : 1, 조각 : 0
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (game_board[i][j] == 1) game_board[i][j] = 0;
                else game_board[i][j] = 1;
            }
        }

        List<List<Pos>> g = new ArrayList<>();
        List<List<Pos>> t = new ArrayList<>();

        boolean[][] checked_game = new boolean[len][len];
        boolean[][] checked_table = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // table 에서 블록 찾기
                if (!checked_table[i][j] && table[i][j] == 1) {
                    bfs(i, j, checked_table, table, t);
                }

                // game_board 에서 빈 공간 찾기
                if (!checked_game[i][j] && game_board[i][j] == 1) {
                    bfs(i, j, checked_game, game_board, g);
                }
            }
        }

        ans = compareBlock(t, g, ans);
        
        return ans;
    }
    
    private int compareBlock(List<List<Pos>> t, List<List<Pos>> g, int ans) {
        int tSize = t.size();
        int gSize = g.size();

        boolean[] checked = new boolean[g.size()];

        for (int i = 0; i < tSize; i++) {
            for (int j = 0; j < gSize; j++) {
                if (checked[j] || t.get(i).size() != g.get(j).size()) continue;
                if (isRotate(t.get(i), g.get(j))) {
                    checked[j] = true;
                    ans += g.get(j).size();
                    break;
                }
            }
        }

        return ans;
    }

    private boolean isRotate(List<Pos> t, List<Pos> g) {
        Collections.sort(g);

        for (int i = 0; i < 4; i++) {
            Collections.sort(t);

            int nx = t.get(0).x;
            int ny = t.get(0).y;

            for (int j = 0; j < t.size(); j++) {
                t.get(j).x -= nx;
                t.get(j).y -= ny;
            }

            boolean flag = true;
            for (int j = 0; j < g.size(); j++) {
                if (g.get(j).x != t.get(j).x || g.get(j).y != t.get(j).y) {
                    flag = false;
                    break;
                }
            }

            if (flag) return true;
            else {
                for (int j = 0; j < t.size(); j++) {
                    int tmp = t.get(j).x;
                    t.get(j).x = t.get(j).y;
                    t.get(j).y = -tmp;
                }
            }
        }

        return false;
    }

    private void bfs(int x, int y, boolean[][] checked, int[][] board, List<List<Pos>> list) {
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