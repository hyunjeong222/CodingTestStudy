import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int n;
        Deque<Character> deque;
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());

            deque = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                char c = st.nextToken().charAt(0);

                if (deque.isEmpty()) {
                    deque.offer(c);
                } else {
                    if (deque.peekFirst() >= c) {
                        deque.offerFirst(c);
                    } else {
                        deque.offerLast(c);
                    }
                }
            }

            while (!deque.isEmpty()) {
                sb.append(deque.poll());
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}