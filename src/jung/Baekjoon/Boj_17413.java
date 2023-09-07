package src.jung.Baekjoon;

import java.io.*;
import java.util.Stack;

/**
 * 문제 이름(난이도) : 단어 뒤집기 2(SIL3)
 * 시간 : 220ms
 * 메모리 : 24676KB
 * 링크 : https://www.acmicpc.net/problem/17413
 */
public class Boj_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        boolean flag = false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                flag = true;
            }else if (str.charAt(i) == '>') {
                sb.append(str.charAt(i));
                flag = false;
                continue;
            }

            if (flag) {
                sb.append(str.charAt(i));
            }else {
                if (str.charAt(i) == ' ') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                    continue;
                } else {
                    stack.push(str.charAt(i));
                }
            }
            if (i == str.length()-1) {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }
        }
        bw.append(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
