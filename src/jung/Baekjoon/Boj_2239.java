package src.jung.Baekjoon;

import java.io.*;

/**
 * 문제 이름(난이도) : 스도쿠(GOL)
 * 시간 : 560ms
 * 메모리 : 17528KB
 * 링크 : https://www.acmicpc.net/problem/2239
 */
public class Boj_2239 {
    static int[][] map; // 스도쿠 판
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];

        // 스도쿠 판 입력
        for (int i = 0; i < 9; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < s.length; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        sudoku(0, 0); // 첫번째 행과 열 담아 시작
    }

    private static void sudoku(int row, int col) {
        if (col == 9) { // 열 다 탐색했다면
            sudoku(row+1, 0); // 다음 행 탐색
            return;
        }

        if (row == 9) { // 스도쿠 판 다 탐색했다면
            // 출력
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }


        if (map[row][col] == 0) { // 빈칸이라면
            for (int i = 1; i <= 9; i++) { // 빈칸에 1-9까지 들어갈 수 있는 숫자 탐색
                if (possibility(row, col, i)) { // i가 들어갈 수 있다면
                    map[row][col] = i;
                    sudoku(row, col+1); // 다음 열 탐색
                }
            }
            map[row][col] = 0;
            return;
        } else { // 빈칸이 아니라면
            sudoku(row, col+1); // 다음 열 탐색
        }
    }

    private static boolean possibility(int row, int col, int val) {
        // 들어갈 수 없는 숫자라면 false 리턴
        for (int i = 0; i < 9; i++) {
            // 행, 열 체크
            if (map[row][i] == val) return false;
            else if (map[i][col] == val) return false;
        }

        // 3*3 체크
        int threeRow = (row/3)*3;
        int threeCol = (col/3)*3;
        for (int i = threeRow; i < threeRow+3; i++) {
            for (int j = threeCol; j < threeCol+3; j++) {
                if (map[i][j] == val) return false;
            }
        }

        // 들어갈 수 있는 숫자라면 true 리턴
        return true;
    }
}