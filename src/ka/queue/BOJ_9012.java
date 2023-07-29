package ka.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문제 이름(난이도) : 괄호(SIL4)
 * 시간 : 144 ms
 * 메모리 : 14924 MB
 * 링크 : https://www.acmicpc.net/problem/9012
 */
public class BOJ_9012 {

    public static Stack<String> stack = new Stack<>();

    public static boolean isVps(String vps) {
        // 스택 초기화
        stack.clear();

        // 입력 받은 문자열을 배열로 분할
        String[] split = vps.split("");

        // 배열을 탐색하면서 VPS 검증
        for (String s : split) {

            // 문자열이 여는 괄호일 경우 스택에 저장
            if (s.equals("(")) {
                stack.push(s);
            }
            // 다음 작업(pop)을 하기 전, 스택이 비어있는지 확인
            // 만약 스택이 비어 있는 상태에서 다음 작업(pop)을 할 경우 EmptyStackException 발생
            else if (stack.isEmpty()) {
                return false;
            }
            // 비어있지 않고, 닫는 괄호일 경우 pop
            else {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
    
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 버퍼 리더
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 라인 수
        int num = Integer.parseInt(br.readLine());

        // 입력을 받아 VPS인지 확인하는 반복문
        for (int i = 0; i < num; i++) {
            String vps = br.readLine();

            // 입력 받은 문자열의 길이가 홀수일 경우 NO
            // 그렇지 않을 경우 VPS 확인 진행
            if (vps.length() % 2 != 0) {
                System.out.println("NO");
            } else {
                if (isVps(vps)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}
