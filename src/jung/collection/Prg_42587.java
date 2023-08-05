package src.jung.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

/**
 * 문제 이름(난이도) : 프로세스(LV2)
 * 시간 : 0.48ms
 * 메모리: 78.1MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42587
 * */
public class Prg_42587 {
    public int solution(int[] priorities, int location) {
        int answer = 1; // 인쇄를 요청한 문서의 인쇄 순서
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위가 높은 숫자 순, 내림차순
        for (int i : priorities) { // 프로세스의 중요도
            pq.add(i); // ex) 3, 2, 2, 1
        }
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) { // 우선순위가 가장 높은 프로세스
                    if (i == location) { // 프로세스 위치 일치
                        return answer;
                    }
                    pq.remove(); // 한 번 실행한 프로세스는 그대로 종료
                    answer++; // 인쇄 순서 증가
                }
            }
        }
        return answer;
    }
    @Test
    void solutionTest1() {
        int[] priorities = {2, 1, 3, 2}; // 프로세스의 중요도
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
