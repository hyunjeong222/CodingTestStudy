import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 빌딩의 개수
        Stack<Integer> stack = new Stack<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }
            stack.push(num);
            ans += stack.size()-1;
        }

        System.out.println(ans);
    }
}