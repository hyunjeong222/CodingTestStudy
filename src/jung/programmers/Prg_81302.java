package src.jung.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 문제 이름(난이도) : 거리두기 확인하기(LV2)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/81302
 * */
public class Prg_81302 {
    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(solution(places)));
    }

    static int[] dx = {-1,1,0,0}; // 행
    static int[] dy = {0,0,-1,1}; // 열
    static boolean[][] check; // 방문체크
    public static class pos { // 좌표저장
        int x; int y;
        public pos (int x, int y) {
            this.x = x;
            this.y =  y;
        }
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length]; // 대기실 수
        for (int i = 0; i < places.length; i++) {
            String[] p = places[i];
            boolean flag = true;
            check = new boolean[p.length][p.length];
            for (int j = 0; j < p.length; j++) { // 대기실 마다 방문체크
                for (int k = 0; k < p.length; k++) {
                    check[j][k] = false;
                }
            }
            for (int j = 0; j < p.length; j++) {
                for (int k = 0; k < p.length; k++) {
                    if (p[j].charAt(k) == 'P' && !bfs(p,j,k)) { // 응시자가 앉아있고, 거리두기가 지켜지지 않았다면
                        flag = false;
                    }
                }
            }
            answer[i] = flag ? 1 : 0;
        }
        return answer;
    }

    public static boolean bfs(String[] p, int x, int y) {
        Queue<pos> queue = new LinkedList<pos>();
        queue.offer(new pos(x, y));
        check[x][y] = true;
        while (!queue.isEmpty()) {
            pos p_q = queue.poll();
            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                // 다음 위치
                int nx = p_q.x + dx[i];
                int ny = p_q.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < p.length && ny < p.length && !check[nx][ny]) { // 범위 안, 다음 위치 방문 X
                    int d = Math.abs(x-nx) + Math.abs(y-ny); // 맨해튼 거리
                    if (p[nx].charAt(ny) == 'P' && d <= 2) { // 다음 위치에 응시자가 있고, 맨해튼 거리가 2 이하라면
                        return false; // 거리두기가 지켜지지 않음
                    } else if (p[nx].charAt(ny) == 'O' && d < 2) { // 다음 탐색 시, 맨해튼 거리가 1 증가하므로 d < 2인 칸에 대해서만 탐색 !
                        queue.offer(new pos(nx, ny));
                        check[nx][ny] = true;
                    }
                }
            }
        }
        return true;
    }
}
