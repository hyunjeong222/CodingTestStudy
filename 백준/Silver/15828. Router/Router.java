import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Queue<Integer> que = new LinkedList<>();
        while (true) {
            int num = Integer.parseInt(br.readLine());

            if (num == -1) break;

            if (num == 0) que.poll();
            else if (n > que.size()) que.offer(num);
        }


        if (que.isEmpty()) {
            sb.append("empty").append("\n");
        } else {
            while (!que.isEmpty()) {
                sb.append(que.poll()).append(" ");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}