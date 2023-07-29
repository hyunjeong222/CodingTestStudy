package ka.collection;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 문제 이름(난이도) : 프로세스(LV2)
 * 시간 : 0,55 ms
 * 메모리 : 72 MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42587
 */
public class PRG_42587 {
    Queue<Process> queue = new LinkedList<>();

    public static class Process {
        int number;
        char alphabet;
        boolean isTarget;

        public Process(int number, char alphabet, boolean isTarget) {
            this.number = number;
            this.alphabet = alphabet;
            this.isTarget = isTarget;
        }

        @Override
        public String toString() {
            return "Process{" +
                    "number=" + number +
                    ", alphabet=" + alphabet +
                    ", isTarget=" + isTarget +
                    '}';
        }
    }

    public boolean hasBigNumber(int num) {
        for (Process process : queue) {
            if (process.number > num) {
                return true;
            }
        }
        return false;
    }
    public int solution(int[] priorities, int location) {
        queue = new LinkedList<>();
        int charCtr = 65, answer = 0, idx = 0;

        // 향상된 for문을 통해 프로세스를 큐에 저장
        for (int priority : priorities) {
            queue.add(new Process(priority, (char) charCtr++, idx == location));
            idx++;
        }

        while (!queue.isEmpty()) {
            // 1. 실행 대기 큐(Queue)에서 대기중인 프로세스 하나를 꺼냅니다.
            Process p = queue.poll();

            // 2. 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 다시 큐에 넣습니다.
            if (hasBigNumber(p.number)) {
                queue.offer(p);
            } else {
                // 3. 프로세스가 실행될 경우 실행되는 순서(answer)를 증가시킵니다.
                answer++;

                // 4. 만약 해당 프로세스가 타겟일 경우 반복문을 종료하고 나갑니다.
                if (p.isTarget) {
                    break;
                }
            }
        }

        return answer;
    }

    @Test
    void solutionTest1() {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        int result = solution(priorities, location);
        System.out.println("result = " + result);
        assertTrue(result == 1);
    }

    @Test
    void solutionTest2() {
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        int result = solution(priorities, location);
        System.out.println("result = " + result);
        assertTrue(result == 5);
    }
}
