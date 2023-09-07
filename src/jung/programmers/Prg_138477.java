package src.jung.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 문제 이름(난이도) : 명예의 전당 (1)(LV1)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/138477
 * */
public class Prg_138477 {
    public static void main(String[] args) {
        int k = 4;
        int[] score = {0, 300, 40, 300, 20, 70, 150, 50, 500, 1000};
        System.out.println(Arrays.toString(solution(k, score)));
    }

    public static int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (i < k) { // k일까지는 명예의 전당에 오름
                list.add(score[i]);
                list.sort(Collections.reverseOrder()); // 내림차순
                answer[i] = list.get(list.size()-1); // 최하위 점수
            } else if (i >= k) { // k일 다음부터
                list.add(score[i]);
                list.sort(Collections.reverseOrder());
                answer[i] = list.get(k-1); // 명예의 전당 목록의 점수의 개수만 비교하기 위해
            }
        }
        return answer;
    }
}
