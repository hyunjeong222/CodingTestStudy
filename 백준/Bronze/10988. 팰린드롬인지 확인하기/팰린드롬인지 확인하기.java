import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        boolean flag = getCheck(str);
        System.out.println(flag ? 1 : 0);
    }

    private static boolean getCheck(String str) {
        Deque<Character> dq = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            dq.offer(str.charAt(i));
        }
        while (!dq.isEmpty() && dq.size() > 1) {
            if (dq.pollFirst() != dq.pollLast()) return false;
        }

        return true;
    }
}