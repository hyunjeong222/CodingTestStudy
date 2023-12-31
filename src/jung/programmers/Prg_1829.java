package src.jung.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 문제 이름(난이도) : 카카오프렌즈 컬러링북(LV2)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/1829
 * */
public class Prg_1829 {
    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}
                , {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println(Arrays.toString(solution(m, n, picture)));
    }

    static int[] dx = {-1,1,0,0}; // 행
    static int[] dy = {0,0,-1,1}; // 열
    // 좌표 저장
    static public class pos {
        int x;
        int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int count = 0;
    static int[][] map;
    static boolean[][] checked;
    static int M; static int N;
    public static int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        map = new int[M][N];
        checked = new boolean[M][N];
        for (int i = 0; i < M; i ++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = picture[i][j];
            }
        }
        int area = 0; // 영역의 수
        int max = 0; // 가장 큰 영역의 칸 수
        for (int i = 0; i < M; i ++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !checked[i][j]) { // 빈 칸 X 방문 X
                    area++;
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = area;
        answer[1] = max;
        return answer;
    }
    public static int bfs(int x, int y) {
        Queue<pos> queue = new LinkedList<pos>();
        queue.offer(new pos(x, y));
        checked[x][y] = true;
        count = 1;
        while (!queue.isEmpty()) {
            pos p_q = queue.poll();
            for (int i = 0; i < 4; i++) {
                // 다음 위치
                int nx = p_q.x + dx[i];
                int ny = p_q.y + dy[i];
                if (nx < M && nx >= 0 && ny < N && ny >= 0) { // 범위 안에 값이 있고
                    if (map[nx][ny] == map[x][y] && !checked[nx][ny]) { // 같은 영역, 방문 X
                        queue.offer(new pos(nx, ny));
                        checked[nx][ny] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
