package src.jung.programmers;

import java.util.Stack;

/**
 * 문제 이름(난이도) : 크레인 인형뽑기 게임(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/64061
 */
public class Prg_64061 {
    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}; // 게임화면
        int[] moves = {1,5,3,5,1,2,1,4}; // 작동위치
        System.out.println(solution(board, moves));
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0; // 터진 인형의 개수
        Stack<Integer> basket = new Stack<>(); // 후입선출 : 나중에 들어간 자료가 먼저 나온다.
        basket.push(0); // 초깃값 세팅
        for (int m : moves) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][m-1] != 0) { // 인형이 있고
                    if (board[j][m-1] == basket.peek()) { // 바구니 안의 인형과 같다면
                        basket.pop(); // 터트림
                        answer += 2;
                    } else { // 바구니 안의 인형과 같지 않다면
                        basket.push(board[j][m-1]); // 인형 넣어주기
                    }
                    board[j][m-1] = 0; // 꺼낸 자리 0으로 초기화
                    break;
                }
            }
        }
        return answer;
    }
}
