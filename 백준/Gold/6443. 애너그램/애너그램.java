import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] alpha;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<String> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String str;
        while (n --> 0) {
            str = br.readLine();
            alpha = new int[26];
            for (int i = 0; i < str.length(); i++) {
                alpha[str.charAt(i) - 'a']++;
            }
            dfs(0, str.length(), new StringBuilder());
            while (!pq.isEmpty()) {
                sb.append(pq.poll()).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void dfs(int depth, int len, StringBuilder now) {
        if (depth == len) {
            pq.offer(now.toString());
            return;
        }

        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] < 1) continue;
            alpha[i]--;
            now.append((char)(i+'a'));
            dfs(depth+1, len, now);
            now.deleteCharAt(now.length()-1);
            alpha[i]++;
        }
    }
}