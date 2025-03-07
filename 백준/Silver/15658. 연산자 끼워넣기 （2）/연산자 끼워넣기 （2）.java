import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    // 더하기, 빼기, 곱하기, 나누기 연산 갯수
    static int plusOperation, minusOperation, multiplyOperation,  divideOperation;
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        plusOperation = Integer.parseInt(st.nextToken());
        minusOperation = Integer.parseInt(st.nextToken());
        multiplyOperation = Integer.parseInt(st.nextToken());
        divideOperation = Integer.parseInt(st.nextToken());

        dfs(arr[0], 1);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n").append(min).append("\n");

        System.out.println(sb.toString());
    }

    private static void dfs(int sum, int depth) {
        if (depth == n) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);

            return;
        }

        if (plusOperation > 0) {
            plusOperation -= 1;
            dfs(sum+arr[depth], depth+1);
            plusOperation += 1;
        }
        if (minusOperation > 0) {
            minusOperation -= 1;
            dfs(sum-arr[depth], depth+1);
            minusOperation += 1;
        }
        if (multiplyOperation > 0) {
            multiplyOperation -= 1;
            dfs(sum*arr[depth], depth+1);
            multiplyOperation += 1;
        }
        if (divideOperation > 0) {
            divideOperation -= 1;
            dfs(sum/arr[depth], depth+1);
            divideOperation += 1;
        }
    }
}