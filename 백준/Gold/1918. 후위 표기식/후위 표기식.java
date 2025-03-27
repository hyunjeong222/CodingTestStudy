import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);

            switch (now) {
                // +, -, *, / 모두 같은 동작 수행
                case '+' :
                case '-' :
                case '*' :
                case '/' :
                    while (!stack.isEmpty() && priority(stack.peek()) >= priority(now)) {
                        sb.append(stack.pop());
                    }
                    stack.add(now);
                    break;
                case '(' :
                    stack.add(now);
                    break;
                case ')' :
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default : sb.append(now); break;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int priority(char operator) {
        if (operator == '(' || operator == ')') return 0;
        else if (operator == '+' || operator == '-') return 1;
        else if (operator == '*' || operator == '/') return 2;

        return -1;
    }
}