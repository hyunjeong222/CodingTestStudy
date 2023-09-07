package src.jung.programmers;

import java.util.Arrays;

/**
 * 문제 이름(난이도) : 로또의 최고 순위와 최저 순위(LV1)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/77484
 * */
public class Prg_77484 {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        System.out.println(Arrays.toString(solution(lottos, win_nums)));
    }

    public static int[] solution(int[] lottos, int[] win_nums) {
        int zero_cnt = 0; // 알 수 없는 숫자 개수
        int equal_cnt = 0; // 알아볼 수 있는 숫자 중 당첨 번호와 맞는 번호의 개수
        for (int i = 0 ; i < lottos.length; i++) {
            if (lottos[i] == 0) zero_cnt++;
            for (int j = 0; j < win_nums.length; j++) {
                if (lottos[i] == win_nums[j]) equal_cnt++;
            }
        }
        int min = equal_cnt; // 알 수 없는 숫자 제외, 당첨 번호만 맞았을 때
        int max = equal_cnt + zero_cnt; // 알 수 없는 숫자와 당첨 번호 모두 맞았을 때
        int[] answer = {Math.min(7-max, 6), Math.min(7-min, 6)}; // 최고, 최저
        return answer;
    }
}
