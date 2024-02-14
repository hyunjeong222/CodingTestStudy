import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] maxDp = new int[3]; // 최대 점수
        int[] minDp = new int[3]; // 최소 점수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());
            if (i == 0) {
                maxDp[0] = num1;
                maxDp[1] = num2;
                maxDp[2] = num3;

                minDp[0] = num1;
                minDp[1] = num2;
                minDp[2] = num3;
            } else {
                int beforeMax1 = maxDp[0]; int beforeMax2 = maxDp[1];
                maxDp[0] = Math.max(maxDp[0], maxDp[1]) + num1;
                maxDp[1] = Math.max(beforeMax1, Math.max(maxDp[1], maxDp[2])) + num2;
                maxDp[2] = Math.max(beforeMax2, maxDp[2]) + num3;

                int beforeMin1 = minDp[0]; int beforeMin2 = minDp[1];
                minDp[0] = Math.min(minDp[0], minDp[1]) + num1;
                minDp[1] = Math.min(beforeMin1, Math.min(minDp[1], minDp[2])) + num2;
                minDp[2] = Math.min(beforeMin2, minDp[2]) + num3;
            }
        }
        sb.append(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]))).append(" ")
                .append(Math.min(minDp[0], Math.min(minDp[1], minDp[2])));
        System.out.println(sb);
    }
}