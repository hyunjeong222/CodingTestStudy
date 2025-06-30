import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(br.readLine()));
        int h;
        for (int i = 1; i < n; i++) {
            h = Integer.parseInt(br.readLine());
            while (true) {
                if (!stack.isEmpty() && stack.peek() <= h) {
                    stack.pop();
                } else {
                    stack.push(h);
                    break;
                }
            }
        }

        System.out.println(stack.size());

        br.close();
    }
}