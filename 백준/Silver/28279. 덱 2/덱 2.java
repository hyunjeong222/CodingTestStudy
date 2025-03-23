import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 풍선의 개수
        StringTokenizer st;
        Deque<Integer> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (n --> 0) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if (order == 1) {
                int x = Integer.parseInt(st.nextToken());
                dq.offerFirst(x);
            } else if (order == 2) {
                int x = Integer.parseInt(st.nextToken());
                dq.offerLast(x);
            } else if (order == 3) {
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.pollFirst()).append("\n");
            } else if (order == 4) {
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.pollLast()).append("\n");
            } else if (order == 5) {
                sb.append(dq.size()).append("\n");
            } else if (order == 6) {
                if (dq.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else if (order == 7) {
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.peekFirst()).append("\n");
            } else { // order == 8
                if (dq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(dq.peekLast()).append("\n");
            }
        }
        
        System.out.println(sb.toString());

        br.close();
    }
}