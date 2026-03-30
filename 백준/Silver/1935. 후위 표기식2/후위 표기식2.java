import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 피연산자의 개수

        String str = br.readLine(); // 후위 표기식

        int[] num = new int[n];
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);

            if ('A' <= c && c <= 'Z') {
                double d = num[c-'A'];
                stack.add(d);
            } else {
                double num1 = stack.pop();
                double num2 = stack.pop();
                double num3 = 0.0;

                switch(c) {
                    case '+' :
                        num3 = num2 + num1;
                        break;
                    case '-' :
                        num3 = num2 - num1;
                        break;
                    case '*' :
                        num3 = num2 * num1;
                        break;
                    case '/' :
                        num3 = num2 / num1;
                        break;
                }

                 stack.add(num3);
            }
        }

        System.out.printf("%.2f", stack.pop());

        br.close();
    }
}