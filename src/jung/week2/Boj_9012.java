package src.jung.week2;

import java.io.*;
import java.util.*;

/**
 * 문제 이름(난이도) : 괄호(SIL4)
 * 시간 : 128ms
 * 메모리: 14348KB
 * 링크 : https://www.acmicpc.net/problem/9012
 * */
public class Boj_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '(') { // 여는 괄호 stack 바로 넣기
                    stack.push(c);
                } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') { // 닫는 괄호면서 스택의 가장 위 값이 열리는 괄호여서 짝이 맞는다면 pop
                    stack.pop();
                } else if (c == ')') { // 해당 없는 닫는 괄호
                    stack.push(c);
                }
            }
            if (!stack.isEmpty()) { // stack 비어있지 않으면 짝 안맞음
                bw.append("NO" + "\n");
            } else {
                bw.append("YES" + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
