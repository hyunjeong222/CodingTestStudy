package src.jung.array;

import java.util.Arrays;

/**
 * 문제 이름(난이도) : 삼각 달팽이(LV2)
 * 시간 : 14.03ms
 * 메모리: 123MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/68645
 * */
public class Prg_68645 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(Arrays.toString(solution(n)));
    }
    public static int[] solution(int n) {
        int[][] board = new int[n][n];
        int h = -1;
        int v = 0;
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) { // 아래 arr[i+1][j]
                    h++;
                } else if (i % 3 == 1) { // 오른쪽 arr[i][j+1]
                    v++;
                } else { // 대각선 arr[i-1][j-1]
                    h--;
                    v--;
                }
                board[h][v] = num++;
            }
        }
        int[] answer = new int[num-1];
        // int[] answer = new int[(n*(n+1))/2]; 등차수열의 합의 일반항, 마지막으로 채워지는 숫자
        int idx = 0;
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) break;
                answer[idx++] = board[i][j];
            }
        }
        return answer;
    }
}