import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] alpha;
    static Stack<Character> stack = new Stack<>();
    static PriorityQueue<String> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String str;
        StringBuilder sb = new StringBuilder();
        while (n --> 0) {
            str = br.readLine();
            alpha = new int[26];
            for (int i = 0; i < str.length(); i++) {
                alpha[str.charAt(i) - 'a']++;
            }
            dfs(str.length());
            while (!pq.isEmpty()) {
                sb.append(pq.poll()).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void dfs(int len) {
        if (len == stack.size()) {
            StringBuilder sb = new StringBuilder();
            for (char now : stack) {
                sb.append(now);
            }
            pq.offer(sb.toString());
        }

        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] > 0) {
                alpha[i]--;
                stack.push((char)(i+'a'));
                dfs(len);
                stack.pop();
                alpha[i]++;
            }
        }
    }
}