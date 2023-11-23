import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 명령의 수
        StringTokenizer st;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String s = st.nextToken();
            if (s.equals("push")) {
                stack.push(Integer.parseInt(st.nextToken()));
            } else if (s.equals("pop")) {
                if (stack.isEmpty()) bw.append(-1 + "\n");
                else bw.append(stack.pop() + "\n");
            } else if (s.equals("size")) {
                bw.append(stack.size() + "\n");
            } else if (s.equals("empty")) {
                if (stack.isEmpty()) bw.append(1 + "\n");
                else bw.append(0 + "\n");
            } else if (s.equals("top")) {
                if (stack.isEmpty()) bw.append(-1 + "\n");
                else bw.append(stack.peek() + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}