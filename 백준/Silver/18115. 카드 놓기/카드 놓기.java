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

        int n = Integer.parseInt(br.readLine()); // 카드 장 수

        Deque<Integer> deque = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(st.nextToken());

            if (x == 1) {
                deque.offerFirst(i);
            } else if (x == 2) {
                int top = deque.pollFirst();
                deque.offerFirst(i);
                deque.offerFirst(top);
            } else {
                deque.offerLast(i);
            }
        }

        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst()).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }
}