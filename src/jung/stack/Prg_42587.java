package src.jung.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

/**
 * 문제 이름(난이도) : 프로세스(LV2)
 * 시간 : ms
 * 메모리: MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42587
 * */
public class Prg_42587 {
    public int solution(int[] priorities, int location) {
        int answer = 1; // 내가 인쇄를 요청한 문서의 인쇄 순서
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위가 높은 숫자 순
        for (int i : priorities) {
            pq.add(i);
        }
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) { // 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열의 i번째 값과 우선순위 큐의 첫번째 값이 같다면
                    if (i == location) { // 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열의 인덱스 i와 내가 인쇄를 요청한 문서의 위치가 같다면
                        return answer; // 순서 리턴
                    }
                    pq.remove();
                    answer++;
                }
            }
        }
        return answer;
    }
    @Test
    void solutionTest1() {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        int answer = solution(priorities, location);
        System.out.println(answer);
    }

    @Test
    void solutionTest2() {
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        int answer = solution(priorities, location);
        System.out.println(answer);
    }
}
