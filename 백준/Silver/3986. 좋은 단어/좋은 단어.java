import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            check(str);
        }

        System.out.println(cnt);

        br.close();
    }

    private static void check(String str) {
        if (str.length()%2 != 0) return; // 문자열의 길이가 홀수라면 짝이 안맞으므로 좋은 단어 X

        Stack<Character> stack = new Stack<>();
        stack.push(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == str.charAt(i)) stack.pop();
            else stack.push(str.charAt(i));
        }

        if (stack.isEmpty()) cnt++;
    }
}