import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 문자열 길이

        if (n % 2 != 0) { // O와 X를 보면 ()와 )(로 찢기 때문에 홀수 불가능
            System.out.println(-1);
            return;
        }

        // StringTokenizer st = new StringTokenizer(br.readLine()); // 만들고자 하는 괄호 문자열
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        int ans = -1; // 최소 일 수
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (stack.isEmpty() || stack.peek() == c) {
                stack.push(c);
            } else {
                stack.pop();
            }

            ans = Math.max(ans, stack.size());
        }
        
        if (!stack.isEmpty()) ans = -1;

        System.out.println(ans);

        br.close();
    }
}