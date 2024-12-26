import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<Integer> que = new ArrayDeque<>();
        StringTokenizer st;
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if (order.equals("push")) {
                int x = Integer.parseInt(st.nextToken());
                que.offer(x);
            } else if (order.equals("pop")) {
                if (que.isEmpty()) sb.append(-1).append("\n");
                else sb.append(que.poll()).append("\n");
            } else if (order.equals("size")) {
                sb.append(que.size()).append("\n");
            } else if (order.equals("empty")) {
                if (que.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else if (order.equals("front")) {
                if (que.isEmpty()) sb.append(-1).append("\n");
                else sb.append(que.peekFirst()).append("\n");
            } else if (order.equals("back")) {
                if (que.isEmpty()) sb.append(-1).append("\n");
                else sb.append(que.peekLast()).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}