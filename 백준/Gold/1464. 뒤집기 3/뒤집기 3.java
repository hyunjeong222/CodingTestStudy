import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        Deque<Character> deque = new ArrayDeque<>();

        deque.offer(s[0]);
        for (int i = 1; i < s.length; i++) {
            if (deque.peekLast() < s[i]) {
                deque.offerFirst(s[i]);
            } else deque.offerLast(s[i]);
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Character> iterator = deque.descendingIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        System.out.println(sb);
    }
}