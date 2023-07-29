package ka.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문제 이름(난이도) : 균형잡힌 세상(SIL4)
 * 시간 : 312 ms
 * 메모리 : 20128 MB
 * 링크 : https://www.acmicpc.net/problem/4949
 */
public class BOJ_4949 {
    static Stack<Character> stack = new Stack<>();

    // spilt을 통해 배열로 만들 경우 메모리 2배 이상 차이남
    public static boolean isBalanceString(String str) {
        // 스택 초기화
        stack.clear();
        // 입력 받은 문자열을 문자 배열로 변환
        char[] chars = str.toCharArray();

        // 문자 배열을 돌면서 균형이 잡혀있는지 확인
        for (char c : chars) {
            // 여는 괄호는 스택에 저장
            if (c == '(' || c == '[') {
                stack.push(c);
            }
            // 현재 입력된 문자열이 소괄호인지 대괄호인지 확인
            // 스택이 비어있을 경우 확인할 수 없기 때문에 종료
            else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        // 버퍼리더를 통해 입력 받음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 무한 반복 진행
        while (true) {
            // 입력 받은 문자열이 온점 하나면 종료
            String s = br.readLine();
            if (s.equals(".")) {
                break;
            }
            // 입력된 문자열이 균형잡혀있는지 확인
            if (isBalanceString(s)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}
