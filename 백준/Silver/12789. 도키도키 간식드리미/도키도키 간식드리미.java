import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Stack<Integer> stack = new Stack<>();
        int now = 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == now) {
                now++;
                while (!stack.isEmpty()) {
                    if (stack.peek() == now) {
                        stack.pop();
                        now++;
                    } else break;
                }
            } else {
                stack.push(arr[i]);
            }
        }

        System.out.println(stack.isEmpty() ? "Nice" : "Sad");

        br.close();
    }
}