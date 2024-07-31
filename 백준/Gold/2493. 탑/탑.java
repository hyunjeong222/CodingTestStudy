import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static public class Top {
        int num; int height;
        public Top(int num, int height) {
            this.num = num; this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Top> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int h = Integer.parseInt(st.nextToken());
            while (true) {
                if (stack.isEmpty()) {
                    stack.push(new Top(i, h));
                    sb.append(0).append(" ");
                    break;
                }
                Top top = stack.peek();
                if (top.height > h) {
                    sb.append(top.num).append(" ");
                    stack.push(new Top(i, h));
                    break;
                } else stack.pop();
            }
        }
        System.out.println(sb.toString());
    }
}