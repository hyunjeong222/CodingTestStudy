import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] num, operation;
    static char[] cal;
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 수의 개수

        num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        operation = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        cal = new char[n-1];
        dfs(0);

        sb.append(max).append("\n").append(min).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int idx) {
        if (idx == n-1) {
            calculateResult();
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operation[i] == 0) continue;

            cal[idx] = getOperation(i);

            operation[i]--;
            dfs(idx+1);
            operation[i]++;
        }
    }

    private static void calculateResult() {
        Deque<Integer> numDq = new LinkedList<>();
        Deque<Character> operationDq = new LinkedList<>();
        numDq.add(num[0]);

        for (int i = 0; i < cal.length; i++) {
            char operation = cal[i];

            switch (operation) {
                case '+' : case '-' :
                    operationDq.addLast(operation);
                    numDq.addLast(num[i+1]);
                    break;
                case '*' : // 우선순위 높으니 즉시 계산
                    numDq.addLast(numDq.pollLast()*num[i+1]);
                    break;
                case '/' : // 우선순위 높으니 즉시 계산
                    numDq.addLast(numDq.pollLast()/num[i+1]);
                    break;
            }
        }

        int ans = numDq.poll();
        while (!operationDq.isEmpty()) {
            Character operation = operationDq.poll();
            Integer num = numDq.poll();

            if (operation == '+') ans += num;
            if (operation == '-') ans -= num;
        }

        min = Math.min(ans, min);
        max = Math.max(ans, max);
    }

    private static char getOperation(int i) {
        switch (i) {
            case 0 : return '+';
            case 1 : return '-';
            case 2 : return '*';
            case 3 : return '/';
        }

        return 'z';
    }
}