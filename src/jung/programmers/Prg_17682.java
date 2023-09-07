package src.jung.programmers;

/**
 * 문제 이름(난이도) : [1차] 다트 게임(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/17682
 */
public class Prg_17682 {
    public static void main(String[] args) {
        String dartResult = "1D2S#10S";
        System.out.println(solution(dartResult));
    }

    public static int solution(String dartResult) {
        int answer = 0;
        int[] score = new int[3]; // 3번의 점수를 담을 배열
        String s_num = "";
        int i_num = 0;
        int idx = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (c >= '0' && c <= '9') {
                s_num += String.valueOf(c); // 1 - 10까지의 점수
            } else if (c == 'S' || c == 'D' || c == 'T') {
                i_num = Integer.parseInt(s_num);
                if (c == 'S') {
                    i_num = (int)Math.pow(i_num, 1); // Math.pow(a,b) a의 b제곱, double 형
                } else if (c == 'D') {
                    i_num = (int)Math.pow(i_num, 2);
                } else {
                    i_num = (int) Math.pow(i_num, 3);
                }
                score[idx++] = i_num;
                s_num = ""; // 초기화
            } else { // 옵션
                if (c == '#') {
                    score[idx-1] *= -1; // 현재 점수 마이너스
                } else {
                    score[idx-1] *= 2; // 해당 점수 2배
                    if (idx-2 >= 0) { // 바로 전에 얻은 점수 2배
                        score[idx-2] *= 2;
                    }
                }
            }
        }
        for (int i = 0; i < score.length; i++) {
            answer += score[i];
        }
        return answer;
    }
}
