package src.jung.week4;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 안전 영역(SIL1)
 * 시간 : 264ms, 228ms
 * 메모리 : 19496KB, 16864KB
 * 링크 : https://www.acmicpc.net/problem/2468
 * */
public class Boj_2468 {
    static int[][] map;
    static boolean[][] checked;
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n]; // 지역

        int max_height = 0; // 지역의 최고 높이
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] =Integer.parseInt(st.nextToken());
                max_height = Math.max(max_height, map[i][j]);
            }
        }

        int max = 0; // 안전한 영역의 최대 개수
        for (int h = 0; h < max_height; h++) {
            int cnt = 0; // 안전한 영역의 개수
            checked = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 방문하지 않고, 높이보다 커야 잠기지 않음
                    if (!checked[i][j] && map[i][j] > h) {
                        cnt++;
                        dfs(h, i, j);
                    }
                }
            }
            max = Math.max(max, cnt);
        }
        bw.append(max + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int height, int x, int y) {
        checked[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 > nx || nx >= n || 0 > ny || ny >= n || checked[nx][ny]) continue;

            if (!checked[nx][ny] && map[nx][ny] > height) {
                checked[nx][ny] = true;
                dfs(height, nx, ny);
            }
        }
    }
}
