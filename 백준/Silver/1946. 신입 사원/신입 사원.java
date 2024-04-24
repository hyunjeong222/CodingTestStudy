import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n+1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                arr[a] = b;
            }

            int cnt = 1;
            int criteria = arr[1];
            for (int i = 2; i <= n; i++) {
                if (criteria > arr[i]) {
                    criteria = arr[i];
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}