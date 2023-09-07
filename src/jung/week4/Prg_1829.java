package src.jung.week4;

import java.util.*;

/**
 * 문제 이름(난이도) : 카카오프렌즈 컬러링북(LV2)
 * bfs
 * 시간 : 17.02ms
 * 메모리 : 94.9MB
 * dfs
 * 시간 : 8.73ms
 * 메모리 : 91.1MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/1829
 * */
public class Prg_1829 {
    /* bfs
    // 상하좌우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    // 좌표 저장
    static public class pos {
        int x;
        int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean[][] checked;
    public static int[] solution(int m, int n, int[][] picture) {
        // 배열 초기화
        checked = new boolean[m][n];

        int area = 0; // 영역 개수
        int max = 0; // 가장 큰 영역

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !checked[i][j]) {
                    area++; // 영역 개수 증가
                    max = Math.max(max, bfs(i, j, m, n, picture)); // 가장 큰 영역 비교
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = area;
        answer[1] = max;
        return answer;
    }

    private static int bfs(int x, int y, int m, int n, int[][] picture) {
        int count = 1; // 지나온 칸의 개수
        checked[x][y] = true;

        Queue<pos> queue = new LinkedList<pos>();
        queue.offer(new pos(x, y));

        while (!queue.isEmpty()) {
            pos p_q = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p_q.x + dx[i];
                int ny = p_q.y + dy[i];
                if (nx < m && nx >= 0 && ny < n && ny >= 0) {
                    if (picture[nx][ny] == picture[x][y] && !checked[nx][ny]) { // 같은 영역이라면
                        queue.offer(new pos(nx, ny));
                        checked[nx][ny] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println(Arrays.toString(solution(m, n, picture)));
    }
    */

    // dfs
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int tmp_cnt; // 한 영역의 수
    static boolean[][] checked;
    public static int[] solution(int m, int n, int[][] picture) {
        // 배열 초기화
        checked = new boolean[m][n];

        int area = 0; // 영역 개수
        int max = 0; // 가장 큰 영역

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !checked[i][j]) { // 방문하지 않은 색칠된 영역
                    tmp_cnt = 0;
                    dfs(i, j, m, n, picture);
                    area++; // 영역 개수 증가

                    if (tmp_cnt > max) max = tmp_cnt;
                }
            }
        }
        // 출력
        int[] answer = new int[2];
        answer[0] = area;
        answer[1] = max;
        return answer;
    }

    private static void dfs(int x, int y, int m, int n, int[][] picture) {
        checked[x][y] = true;
        tmp_cnt++;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 > nx || nx >= m || 0 > ny || ny >= n || checked[nx][ny]) continue;

            if (picture[x][y] == picture[nx][ny]) {
                dfs(nx, ny, m, n, picture);
            }
        }
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println(Arrays.toString(solution(m, n, picture)));
    }
}
