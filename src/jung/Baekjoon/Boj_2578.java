package src.jung.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 빙고(SIL4)
 * 시간 : 80ms
 * 메모리 : 11668KB
 * 링크 : https://www.acmicpc.net/problem/2578
 */
public class Boj_2578 {
    static int[][] map;
    static int bingoCount;
    static int turn = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new int[5][5];
        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        loop : for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());

                bingo(num);
                bingoCheck();

                if (bingoCount >= 3) {
                    bw.append(turn + "\n");
                    break loop;
                }

                bingoCount = 0;
                turn++;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // 빙고 체크하기
    private static void bingoCheck() {
        col();
        row();
        diagonal1();
        diagonal2();
    }

    // 대각선 - 왼쪽 위에서 오른쪽 아래
    private static void diagonal2() {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] == 0) cnt++;
            if (cnt == 5) bingoCount++;
        }
    }

    // 대각선 - 왼쪽 아래에서 오른쪽 위로
    private static void diagonal1() {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (map[4-i][i] == 0) cnt++;
            if (cnt == 5) bingoCount++;
        }
    }

    // 행 체크
    private static void row() {
        for (int i = 0; i < 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 0) cnt++;
                if (cnt == 5) bingoCount++;
            }
        }
    }

    // 열 체크
    private static void col() {
        for (int i = 0; i < 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (map[j][i] == 0) cnt++;
                if (cnt == 5) bingoCount++;
            }
        }
    }

    // 사회자가 부른 숫자 지워주기
    private static void bingo(int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == num) map[i][j] = 0;
            }
        }
    }
}
