import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxDp[0] = n1;
                maxDp[1] = n2;
                maxDp[2] = n3;

                minDp[0] = n1;
                minDp[1] = n2;
                minDp[2] = n3;
            } else {
                int beforeMaxDp0 = maxDp[0]; int beforeMaxDp2 = maxDp[2];
                maxDp[0] = Math.max(maxDp[0], maxDp[1]) + n1;
                maxDp[2] = Math.max(maxDp[1], maxDp[2]) + n3;
                maxDp[1] = Math.max(Math.max(beforeMaxDp0, maxDp[1]), beforeMaxDp2) + n2;

                int beforeMinDp0 = minDp[0]; int beforeMinDp2 = minDp[2];
                minDp[0] = Math.min(minDp[0], minDp[1]) + n1;
                minDp[2] = Math.min(minDp[1], minDp[2]) + n3;
                minDp[1] = Math.min(Math.min(beforeMinDp0, minDp[1]), beforeMinDp2) + n2;
            }
        }
        sb.append(Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2])).append(" ")
                .append(Math.min(Math.min(minDp[0], minDp[1]), minDp[2]));
        System.out.println(sb);
    }
}