import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Pair {
        int q, v;
        Pair(int q, int v) {
            this.q = q;
            this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Stack<Integer> stack = new Stack<>();
        Stack<Pair> backStack = new Stack<>();
        while (q --> 0) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int i = Integer.parseInt(st.nextToken());
                backStack.push(new Pair(1, stack.push(i)));
            } else if (cmd == 2) {
                backStack.push(new Pair(2, stack.pop()));
            } else if (cmd == 3) {
                int j = Integer.parseInt(st.nextToken());
                while (j --> 0) {
                    Pair p = backStack.pop();

                    if (p.q == 1) stack.pop();
                    else stack.push(p.v);
                }
            } else if (cmd == 4) {
                sb.append(stack.size()).append("\n");
            } else { // 5
                if (stack.isEmpty()) sb.append(-1).append("\n");
                else sb.append(stack.peek()).append("\n");
            }
        }
        
        System.out.println(sb.toString());

        br.close();
    }
}