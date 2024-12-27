import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for (Character c : str) {
            left.push(c);
        }

        int m = Integer.parseInt(br.readLine());
        while (m --> 0) {
            String line = br.readLine();
            char order = line.charAt(0);

            switch (order) {
                case 'L' :
                    if (!left.isEmpty()) {
                        right.push(left.pop());
                    }
                    break;
                case 'D' :
                    if (!right.isEmpty()) {
                        left.push(right.pop());
                    }
                    break;
                case 'B' :
                    if (!left.isEmpty()) {
                        left.pop();
                    }
                    break;
                case 'P' :
                    char c = line.charAt(2);
                    left.push(c);
                    break;
                default :
                    break;
            }
        }

        while (!left.isEmpty()) {
            right.push(left.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }

        System.out.println(sb.toString());
    }
}