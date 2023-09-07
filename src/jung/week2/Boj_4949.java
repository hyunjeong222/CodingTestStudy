package src.jung.week2;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 균형잡힌 세상(SIL4)
 * 시간 : 252ms
 * 메모리 : 19616KB
 * 링크 : https://www.acmicpc.net/problem/4949
 * */
public class Boj_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;

        while(true) {
            s = br.readLine();
            // . 종료조건
            if (s.equals(".")) {
                break;
            }

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[') { // 여는 괄호 stack 바로 넣기
                    stack.push(c);
                } else if (c ==')' && !stack.isEmpty() && stack.peek() == '(') { // 닫는 괄호면서 스택의 가장 위 값이 열리는 괄호여서 짝이 맞는다면 pop
                    stack.pop();
                } else if (c ==']' && !stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else if (c ==')' || c==']') { // 해당 없는 닫는 괄호
                    stack.push(c);
                }
            }

            if (!stack.isEmpty()) { // stack 비어있지 않으면 짝 안맞음
                bw.append("no" + "\n");
            } else {
                bw.append("yes" + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}