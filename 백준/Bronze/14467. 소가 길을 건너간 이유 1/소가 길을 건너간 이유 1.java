import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] cow = new int[11][1];
        for (int i = 1; i <= 10; i++) {
            cow[i][0] = -1;
        }

        StringTokenizer st;
        int ans = 0; // 소가 길을 건너간 최소 횟수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int flag = Integer.parseInt(st.nextToken());

            if (cow[idx][0] == -1) {
                cow[idx][0] = flag;
            } else if (cow[idx][0] != flag) {
                ans++;
                cow[idx][0] = flag;
            }
        }

        System.out.println(ans);
    }
}