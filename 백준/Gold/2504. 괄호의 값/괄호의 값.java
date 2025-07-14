import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        int tmp = 1;
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);

            if (now == '(') {
                stack.push(now);
                tmp *= 2;
            } else if (now == '[') {
                stack.push(now);
                tmp *= 3;
            } else if (now == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    ans = 0;
                    break;
                } else if (str.charAt(i-1) == '(') {
                    ans += tmp;
                }
                stack.pop();
                tmp /= 2;
            } else { // ]
                if (stack.isEmpty() || stack.peek() != '[') {
                    ans = 0;
                    break;
                } else if (str.charAt(i-1) == '[') {
                    ans += tmp;
                }
                stack.pop();
                tmp /= 3;
            }
        }

        System.out.println(!stack.isEmpty() ? 0 : ans);

        br.close();
    }
}