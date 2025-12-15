import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(br.readLine());
        Deque<Character> left;
        Deque<Character> right;
        while (t --> 0) {
            char[] str = br.readLine().toCharArray();
            left = new LinkedList<>();
            right = new LinkedList<>();

            for (char c : str) {
                switch (c) {
                    case '<':
                        if (!left.isEmpty()) right.addFirst(left.removeLast()); // P
                        break;
                    case '>':
                        if (!right.isEmpty()) left.addLast(right.removeFirst());
                        break;
                    case '-':
                        if (!left.isEmpty()) left.removeLast();
                        break;
                    default:
                        left.addLast(c); // BAPC
                }
            }

            for (char c : left) sb.append(c);
            for (char c : right) sb.append(c);
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}