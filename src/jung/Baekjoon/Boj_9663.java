package src.jung.Baekjoon;

import java.io.*;

/**
 * 문제 이름(난이도) : N-Queen(GOL)
 * 시간 : 5864ms
 * 메모리 : 12248KB
 * 링크 : https://www.acmicpc.net/problem/9663
 * 설명 : 퀸 n 개를 서로 공격할 수 없다 -> 퀸 자신을 기준으로 일직선상(가로, 세로)과 대각선 방향에는 아무것도 놓여있으면 안 됨
 */
public class Boj_9663 {
    static int n; // 체스판 크기
    static int[] arr; // 한 열에서 퀸이 있는 인덱스를 담을 배열
    static int count = 0; // 퀸을 놓는 방법의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        nQueen(0); // 첫번째 열을 담아 시작
        bw.append(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void nQueen(int depth) {
        if (depth == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[depth] = i;
            if (Possiblity(depth)) {
                nQueen(depth+1);
            }
        }
    }

    // 해당 열의 i번째 행에 퀸을 놓을 수 있는지 검사
    private static boolean Possiblity(int col) {
        // 퀸을 놓을 수 없는 경우, false 리턴
        for (int i = 0; i < col; i++) {
            // 일직선 탐색
            if (arr[col] == arr[i]) return false;
            // 대각선 탐색
            // 열의 차와 행의 차가 같은 경우 대각선에 놓여있음
            else if (Math.abs(arr[col]-arr[i]) == Math.abs(col-i)) return false;
        }

        // 퀸을 놓을 수 있다면 true 리턴
        return  true;
    }
}
