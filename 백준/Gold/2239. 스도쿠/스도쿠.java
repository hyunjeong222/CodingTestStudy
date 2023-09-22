import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map; // 스도쿠 판
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new int[9][9];

        // 스도쿠 판 입력
        for (int i = 0; i < 9; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < s.length; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        sudoku(0, 0); // 행과 열의 담아 시작
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
            // 출력하고 종료
            System.out.println(sb);
            System.exit(0);
        }

        if (map[row][col] == 0) { // 빈칸이라면
            for (int i = 1; i <= 9; i++) {
                if (numCheck(row, col, i)) { // i가 들어갈 수 있는 숫자인지 체크
                    map[row][col] = i;
                    sudoku(row, col+1);
                }
            }
            map[row][col] = 0;
            return;
        } else { // 빈칸 아니라면
            sudoku(row, col+1); // 다음 탐색
        }
    }

    private static boolean numCheck(int row, int col, int val) {
        // 들어갈 수 없는 숫자라면 fasle 리턴
        // 행 체크
        for (int i = 0; i < 9; i++) {
            if (map[row][i] == val) return false;
        }

        // 열 체크
        for (int i = 0; i < 9; i++) {
            if (map[i][col] == val) return false;
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