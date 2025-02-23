import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            que.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (que.size() != 1) {
            sb.append(que.poll()).append(" ");
            que.offer(que.poll());
        }

        sb.append(que.poll());
        
        System.out.println(sb.toString());
    }
}