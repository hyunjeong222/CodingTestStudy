import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 농장의 층 수
        int m = Integer.parseInt(st.nextToken()); // 비가 오는 횟수
        int k = Integer.parseInt(st.nextToken()); // 각 층이 버틸 수  있는 빗물의 양

        long firstFloor = 0;
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (flag) {
                firstFloor += r;

                if (firstFloor > k) {
                    sb.append(i+1).append(" ").append(1).append("\n");
                    flag = false;
                }
            }
        }

        if (flag) sb.append(-1).append("\n");

        System.out.println(sb.toString());

        br.close();
    }
}