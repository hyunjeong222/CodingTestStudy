import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine()); // 명령어의 수
        Queue<Integer> que = new LinkedList<>();
        StringTokenizer st;
        int back = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            if (str.equals("push")) {
                back = Integer.parseInt(st.nextToken());
                que.offer(back);
            } else if (str.equals("pop")) {
                if (que.isEmpty()) sb.append(-1).append("\n");
                else sb.append(que.poll()).append("\n");
            } else if (str.equals("size")) {
                sb.append(que.size()).append("\n");
            } else if (str.equals("empty")) {
                if (que.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else if (str.equals("front")) {
                if (que.isEmpty()) sb.append(-1).append("\n");
                else sb.append(que.peek()).append("\n");
            } else if (str.equals("back")) {
                if (que.isEmpty()) sb.append(-1).append("\n");
                else sb.append(back).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}