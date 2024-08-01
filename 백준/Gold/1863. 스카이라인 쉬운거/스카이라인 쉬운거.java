import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        // 스카이 라인이 낮아졌다는 의미는
        // 어떤 빌딩이 그 지점에서 끝났다는 것
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            while (!stack.empty() && stack.peek() > y) {
                ans++;
                stack.pop(); // 스카이 라인이 낮아지므로 그 건물은 끝남
            }
            // 같은 높이는 같은 건물
            if (!stack.isEmpty() && stack.peek() == y) {
                continue;
            }
            stack.push(y);
        }
        // 스택이 비지 않았다면 남은 건물이 있는 것
        while (!stack.isEmpty()) {
            if (stack.peek() > 0) {
                ans++;
            }
            stack.pop();
        }

        System.out.println(ans);
    }
}