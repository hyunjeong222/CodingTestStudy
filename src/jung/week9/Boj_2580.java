package src.jung.week9;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 스도쿠(GOL)
 * 시간 : 372ms
 * 메모리 : 18844KB
 * 링크 : https://www.acmicpc.net/problem/2580
 */
public class Boj_2580 {
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9]; // 스도쿠 판

        // 판 입력
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0); // 행 열
    }

    private static void dfs(int row, int col) {
        if (col == 9) { // 한 행을 끝까지 다 체크했으면
            dfs(row+1, 0);
            return;
        }

        if (row == 9) { // 스도쿠 판을 다 탐색했다면
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
            // 출력하고 종료
            System.out.println(sb);
            System.exit(0);
        }

        if (map[row][col] == 0) { // 빈 칸이라면
            for (int i = 1; i <= 9; i++) { // 들어갈 수 있는 수 1-9 탐색
                if (numCheck(row, col, i)) {
                    map[row][col] = i;
                    dfs(row, col+1);
                }
            }
            map[row][col] = 0;
            return;
        } else { // 빈 칸이 아니라면
            dfs(row, col+1); // 다음 숫자 탐색
        }
    }

    private static boolean numCheck(int row, int col, int val) {
        // 들어갈 수 없는 숫자면 false 리턴
        // 행 체크
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == val) return false;
        }

        // 열 체크
        for (int i = 0; i < 9; i++) {
            if (map[i][col] == val) return false;
        }

        // 3*3 정사각형 안에서 체크
        int threeRow = (row/3) * 3;
        int threeCol = (col/3) * 3;
        for (int i = threeRow; i < threeRow+3; i++) {
            for (int j = threeCol; j < threeCol+3; j++) {
                if (map[i][j] == val) return false;
            }
        }

        // 들어갈 수 있는 숫자면 true 리턴
        return true;
    }
}
