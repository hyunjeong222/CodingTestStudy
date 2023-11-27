import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine()); // 명령어의 수
        Deque<Integer> dq = new LinkedList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken(); // 명령어
            if (str.equals("push_front")) {
                dq.addFirst(Integer.parseInt(st.nextToken()));
            } else if (str.equals("push_back")) {
                dq.addLast(Integer.parseInt(st.nextToken()));
            } else if (str.equals("pop_front")) {
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.pollFirst()).append("\n");
            } else if (str.equals("pop_back")) {
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.pollLast()).append("\n");
            } else if (str.equals("size")) {
                sb.append(dq.size()).append("\n");
            } else if (str.equals("empty")) {
                if (dq.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else if (str.equals("front")) {
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.peekFirst()).append("\n");
            } else if (str.equals("back")) {
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.peekLast()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}