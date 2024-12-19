import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken()); // 고정구간

        StringBuilder sb = new StringBuilder();
        Deque<int[]> deque = new ArrayDeque<>(); // num, idx
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            while (!deque.isEmpty() && deque.peekLast()[0] > num) {
                deque.removeLast();
            }
            deque.offer(new int[] {num, i});
            if (deque.peek()[1] < i - (l-1)) {
                deque.remove();
            }
            sb.append(deque.peek()[0]).append(" ");
        }

        System.out.println(sb.toString());
    }
}