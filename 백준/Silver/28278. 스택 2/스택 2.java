import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 명령의 수

        StringTokenizer st;
        String type, x;
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            type = st.nextToken();

            if ("1".equals(type)) {
                x = st.nextToken();
                stack.push(x);
            } else if ("2".equals(type)) {
                if (stack.isEmpty()) sb.append(-1).append("\n");
                else sb.append(stack.pop()).append("\n");
            } else if ("3".equals(type)) {
                sb.append(stack.size()).append("\n");
            } else if ("4".equals(type)) {
                if (stack.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else {
                if (stack.isEmpty()) sb.append(-1).append("\n");
                else sb.append(stack.peek()).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}