import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack;
        int idx = 0;
        while (true) {
            String str = br.readLine();
            stack = new Stack<>();
            int cnt = 0; // 변경 횟수
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == '{') {
                    stack.push(c);
                } else { // }
                    if (stack.isEmpty()) {
                        cnt++;
                        stack.push('{');
                    } else stack.pop();
                }
            }
            if (str.contains("-")) break;
            int restul = cnt + (stack.size()/2);
            ++idx;
            bw.append(idx + ". " + restul + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}