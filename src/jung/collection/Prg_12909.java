package src.jung.collection;

import java.util.*;

/**
 * 문제 이름(난이도) : 올바른 괄호(LV2)
 * 시간 : 20.43ms
 * 메모리: 52.5MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12909
 * */
public class Prg_12909 {
    public static void main(String[] args) {
        String s = "(()(";
        System.out.println(solution(s));
    }

    public static boolean solution(String s) {
        Stack<Character> stack = new Stack<>(); // 괄호 담아서 짝 맞춰 볼 stack
        for (char c : s.toCharArray()) {
            if (c == '(') { // 여는 괄호 stack 바로 넣기
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') { // 닫는 괄호면서 스택의 가장 위 값이 열리는 괄호여서 짝이 맞는다면 pop
                stack.pop();
            } else if (c == ')') { // 해당 없는 닫는 괄호
                stack.push(c);
            }
        }
        if (!stack.isEmpty()) { // stack 비어있지 않으면 짝 안맞음
            return false;
        } else {
            return true;
        }
    }
}