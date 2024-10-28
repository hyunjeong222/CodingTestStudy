import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 얼음 양동이의 개수
        int k = Integer.parseInt(st.nextToken())*2+1; //

        int[] ice = new int[1000001];
        int g, x;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            g = Integer.parseInt(st.nextToken()); // 얼음의 양
            x = Integer.parseInt(st.nextToken()); // 양동이 좌표
            ice[x] = g;
        }

        int sum = 0; int max = 0;
        for (int i = 0; i < 1000001; i ++) {
            if (i-k >= 0) sum -= ice[i-k];
            sum += ice[i];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
