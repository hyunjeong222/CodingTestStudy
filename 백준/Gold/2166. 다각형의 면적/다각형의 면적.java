import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] vertex = new long[n+1][2];
        StringTokenizer st;
        // 다각형의 넓이는 신발끈 공식을 이용하면 해결할 수 있음
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            vertex[i][0] = Integer.parseInt(st.nextToken());
            vertex[i][1] = Integer.parseInt(st.nextToken());
        }
        vertex[n][0] = vertex[0][0];
        vertex[n][1] = vertex[0][1];

        double sum1 = 0, sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += vertex[i][0] * vertex[i+1][1];
            sum2 += vertex[i][1] * vertex[i+1][0];
        }

        System.out.println(String.format("%.1f", Math.abs(sum1-sum2)/2));
        br.close();
    }
}