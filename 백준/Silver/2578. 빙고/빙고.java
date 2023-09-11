import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int bingo_cnt = 0;
    static int turn = 1;
    static boolean bingo = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        loop : for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                bingo(Integer.parseInt(st.nextToken()));
                col(); // 열
                row(); // 행
                diagonal1(); // 왼쪽 아래 -> 오른쪽 위
                diagonal2(); // 왼쪽 위 -> 오른쪽 아래

                if (bingo_cnt >= 3) {
                    bw.append(turn + "\n");
                    break loop;
                }
                bingo_cnt = 0;
                turn++;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void diagonal2() {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] == 0) cnt++;
            if (cnt == 5) bingo_cnt++;
        }
    }

    private static void diagonal1() {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][4-i] == 0) cnt++;
            if (cnt == 5) bingo_cnt++;
        }
    }

    private static void row() {
        for (int i = 0; i < 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 0) cnt++;
                if (cnt == 5) bingo_cnt++;
            }
        }
    }

    private static void col() {
        for (int i = 0; i < 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (map[j][i] == 0) cnt++;
                if (cnt == 5) bingo_cnt++;
            }
        }
    }

    // 사회자가 부른 숫자 지우기
    private static void bingo(int n) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == n) {
                    map[i][j] = 0;
                }
            }
        }
    }
}
