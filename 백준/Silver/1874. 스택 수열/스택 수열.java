import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (start < num) {
                for (int j = start+1; j <= num; j++) {
                    stack.push(j);
                    sb.append("+").append("\n");
                }
                start = num;
            }

            if (stack.peek() == num) {
                stack.pop();
                sb.append("-").append("\n");
            }
        }

        if (stack.isEmpty()) System.out.println(sb.toString());
        else System.out.println("NO");
    }
}