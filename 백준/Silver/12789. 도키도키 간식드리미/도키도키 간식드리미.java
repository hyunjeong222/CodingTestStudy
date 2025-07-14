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

        Stack<Integer> stack = new Stack<>(); // 한 명씩만 설 수 있는 공간
        int now = 1; // 간식을 받을 수 있는 현재 순서
        for (int i = 0; i < n; i++) {
            if (arr[i] == now) { // 간식을 받는 순서라면
                now++;
                // 한 명씩만 설 수 있는 공간에서 순서대로 받을 수 있는지 계속 체크
                while (!stack.isEmpty()) {
                    if (stack.peek() == now) {
                        stack.pop();
                        now++;
                    } else break; // 순서대로가 아니면 더 이상 체크 X
                }
            } else { // 간식을 받을 수 있는 순서가 아니라면
                stack.push(arr[i]); // 스택에서 대기
            }
        }

        // 스택이 비어있다면 순서대로 간식을 받을 수 있음
        System.out.println(stack.isEmpty() ? "Nice" : "Sad");

        br.close();
    }
}