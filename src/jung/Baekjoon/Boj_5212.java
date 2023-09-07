package src.jung.Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 지구 온난화(SIL2)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://www.acmicpc.net/problem/5212
 */
public class Boj_5212 {
    static char[][] map;
    static int r, c; // 행 열
    static int[] dx = {-1, 1, 0, 0}; // 상하, 행
    static int[] dy = {0, 0, -1, 1}; // 좌우, 열
    static class Pos {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Pos> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열

        map = new char[r][c];

        for (int i = 0; i < r; i++) { // 지도입력
            char[] str = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = str[j];
                if (map[i][j] == 'X') { // 땅이면
                    que.add(new Pos(i, j));
                }
            }
        }
        int size = que.size();
        for (int i = 0; i < size; i++) {
            Pos p_q = que.poll(); // 현재위치
            int cnt = 0; // 바다 수

            for (int j = 0; j < 4; j++) { // 상하좌우 탐색
                int nx = p_q.x + dx[j];
                int ny = p_q.y + dy[j];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c) { // 범위를 벗어나지 않고
                    if (map[nx][ny] == '.') { // 현재위치가 바다
                        cnt++;
                    }
                } else { // 지도 범위 밖은 모두 바다
                    cnt++;
                }
            }
            if (cnt >= 3) { // 인접한 바다가 세칸 이상이라면 땅은 잠김
                map[p_q.x][p_q.y] = '*';
            } else {
                que.add(new Pos(p_q.x, p_q.y));
            }
        }

        List<Pos> list = new ArrayList<>(que);
        int minR = r, maxR = 0, minC = c, maxC = 0;
        // int minR = 10, maxR = 0, minC = 10, maxC = 0;
        for (int i = 0; i < list.size(); i++) {
            Pos p_q = list.get(i);
            minR = Math.min(p_q.x, minR);
            maxR = Math.max(p_q.x, maxR);
            minC = Math.min(p_q.y, minC);
            maxC = Math.max(p_q.y, maxC);
        }

        for (int i = minR; i <= maxR; i++) {
            for (int j = minC; j <= maxC; j++) {
                char c = map[i][j];
                if (c == '*') { // 가라앉은 땅 -> 바다로 출력
                    bw.append('.');
                } else {
                    bw.append(c);
                }
            }
            bw.append("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
