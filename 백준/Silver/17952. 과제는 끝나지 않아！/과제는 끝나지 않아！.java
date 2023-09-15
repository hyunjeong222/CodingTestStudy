import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 이번 학기가 몇 분인지
        StringTokenizer st;
        Stack<Homework> stack = new Stack<>();
        int total = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().charAt(0) == '1') {
                int a = Integer.parseInt(st.nextToken()); // 과제의 만점
                int t = Integer.parseInt(st.nextToken()); // 해결하는데 걸리는 시간
                if (t == 1) {
                    total += a;
                } else {
                    stack.push(new Homework(a, t-1));
                }
            } else if(!stack.isEmpty()) {
                Homework tmp = stack.pop();
                tmp.minute--;
                if (tmp.minute == 0) {
                    total += tmp.score;
                } else {
                    stack.push(tmp);
                }
            }
        }
        bw.append(total + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Homework {
    int score;
    int minute;
    public Homework(int score, int minute) {
        this.score = score;
        this.minute = minute;
    }
}
