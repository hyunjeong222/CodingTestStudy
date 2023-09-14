package src.jung.week8;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 감시(GOL4)
 * 시간 : 284ms
 * 메모리 : 56072KB
 * 링크 : https://www.acmicpc.net/problem/15683
 */
public class Boj_15683 {
    static int n,m;
    static int[][] map;
    static int min = Integer.MAX_VALUE; // 사각지대의 최소 크기
    static ArrayList<pos> cctvList = new ArrayList<>(); // 각 cctv 위치 x, y, type을 담을 list
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    // 0 위, 1 아래, 2 왼쪽, 3 오른쪽
    // cctv 감시 방향
    static int[][][] dir = {
            {}, // 0번 cctv 없음
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 3}, {3, 1}, {2, 1}, {2, 0}},
            {{2, 0, 3}, {0, 3, 1}, {2, 1, 3}, {1, 2, 0}},
            {{0, 1, 2, 3}}
    };
    static public class pos {
        int x, y, type;

        public pos (int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type; // cctv 번호
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        map = new int[n][m]; // 사무실

        // 사무실 각 칸 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 0 빈 칸, 6 벽, 1~5 cctv 번호
                // 감시할 수 있는 영역 -1 변경
                if (map[i][j] != 0 && map[i][j] != 6) { // cctv 있는 위치라면
                    // cctv 위치, 번호 저장
                    cctvList.add(new pos(i, j, map[i][j]));
                }
            }
        }
        // System.out.println(Arrays.deepToString(map));

        dfs(0, cctvList.size(), map);
        bw.append(min + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int size, int[][] map) {
        if (depth == size) {
            int count = check(map); // 사각지대 체크
            min = Math.min(min, count);
            return;
        }

        int cctvType = cctvList.get(depth).type;
        int x = cctvList.get(depth).x;
        int y = cctvList.get(depth).y;

        // 감시 방향 탐색
        for (int i = 0; i < dir[cctvType].length; i++) {
            // 배열 복사
            int[][] copyMap = new int[n][m];
            for (int k = 0; k < n; k++) {
                for (int l = 0; l < m; l++) {
                    copyMap[k][l] = map[k][l];
                }
            }

            for (int j = 0; j < dir[cctvType][i].length; j++) {
                int direction = dir[cctvType][i][j];
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                // 범위를 벗어나지 않고, 벽이 아닐때
                while (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 6) {
                    copyMap[nx][ny] = -1; // 감시할 수 있는 영역으로 변경
                    nx += dx[direction];
                    ny += dy[direction];
                }
            }
            dfs(depth+1, size, copyMap);
        }
    }

    // 사각지대 구하기
    private static int check(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
