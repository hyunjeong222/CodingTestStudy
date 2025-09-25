import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        Deque<Integer> hands = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*n; i++) {
            hands.offer(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        while (t --> 0) {
            int num = Integer.parseInt(st.nextToken());
            while (num --> 1) hands.offer(hands.poll());
            sb.append(hands.peek()).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }
}