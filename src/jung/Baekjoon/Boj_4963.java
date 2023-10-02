package src.jung.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 섬의 개수(SIL)
 * 시간 : 120ms
 * 메모리 : 13692KB
 * 링크 : https://www.acmicpc.net/problem/4963
 */
public class Boj_4963 {
    static int w, h; // 너비, 높이
    static int[][] map;
    static boolean[][] checked;
    static int count; // 섬의 개수
    // 대각선 포함 8방향
    static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
    static int nx, ny;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break; // 종료 조건

            map = new int[h][w];
            checked = new boolean[h][w];

            // 섬 입력, 1 땅, 0 바다
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // System.out.println(Arrays.deepToString(map));
            count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!checked[i][j] && map[i][j] == 1) { // 방문하지 않았고, 땅이라면
                        count++; // 섬의 개수 증가
                        dfs(i, j);
                    }
                }
            }
            bw.append(count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y) {
        checked[x][y] = true;

        for (int i = 0; i < 8; i++) {
            // 다음 방향
            nx = dx[i] + x;
            ny = dy[i] + y;

            // 범위 벗어나지 않고, 방문하지 않았고, 땅이라면
            if (rangeCheck() && !checked[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

    private static boolean rangeCheck() {
        return (nx < h && ny < w && nx >= 0 && ny >= 0);
    }
}
