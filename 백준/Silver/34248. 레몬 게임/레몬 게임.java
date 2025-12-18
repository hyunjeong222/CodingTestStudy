import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static public String[] gradeList = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F"};
    static public double[] scoreList = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            stack.push(x);

            while (true) {
                int size = stack.size();

                if (size >= 2) {
                    int a = stack.get(size-1);
                    int b = stack.get(size-2);

                    if (a+b == 3) {
                        stack.pop();
                        stack.pop();
                        continue;
                    }
                }

                if (size >= 3) {
                    int a = stack.get(size-1);
                    int b = stack.get(size-2);
                    int c = stack.get(size-3);

                    if (a+b+c == 3) {
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        continue;
                    }
                }

                break;
            }
        }

        System.out.println(stack.isEmpty() ? "Yes" : "No");

        br.close();
    }
}