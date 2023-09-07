package src.jung.programmers;

import java.util.Stack;

/**
 * 문제 이름(난이도) : 햄버거 만들기(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/133502
 */
public class Prg_133502 {
    public static void main(String[] args) {
        int[] ingredient = {2, 1, 1, 2, 3, 1, 2, 3, 1};
        System.out.println(solution(ingredient));
    }

    public static int solution(int[] ingredient) {
        // stack, 후입선출
        int answer = 0; // 상수가 포장하는 햄버거의 개수
        Stack<Integer> stack = new Stack<>();
        for (int i : ingredient) {
            stack.push(i);

            if (stack.size() >= 4) { // 재료 순서 비교 위해 stack 크기가 4이상 부터
                if (stack.get(stack.size()-4) == 1
                        && stack.get(stack.size()-3) == 2
                        && stack.get(stack.size()-2) == 3
                        && stack.get(stack.size()-1) == 1) {
                    stack.pop(); stack.pop(); stack.pop(); stack.pop();
                    answer++;
                }
            }
        }
        return answer;

        /* 문자열
        int answer = 0; // 상수가 포장하는 햄버거의 개수
        String s = ""; // 재료의 정보를 문자열로 합칠 변수
        for (int i : ingredient) {
            s += String.valueOf(i);
        }
        while (s.contains("1231")) { // 재료 순서가 포함된다면
            int idx = s.indexOf("1231"); // 재료 순서 위치 찾기, 특정 문자열의 시작 위치와 가장 가까운 위치 반환 (2)
            s = s.substring(0, idx) + s.substring(idx+4); // 재료 순서(1231) 제외하고 문자열 이어 붙이기
            answer++;
        }
        return answer;
        */
    }
}
