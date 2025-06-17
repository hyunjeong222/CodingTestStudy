import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int type, num;
        Queue<Integer> que = new LinkedList<>();
        int lineMax = 0, studentMin = n;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                num = Integer.parseInt(st.nextToken());
                que.offer(num);
                if (que.size() > lineMax) {
                    lineMax = que.size();
                    studentMin = num;
                } else if (lineMax == que.size()) { // 대기하는 학생 수가 최대인 경우가 여러 번이라면
                    studentMin = (studentMin > num) ? num : studentMin;
                }
            } else {
                que.poll();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lineMax).append(" ").append(studentMin).append("\n");
        System.out.println(sb.toString());

        br.close();
    }
}