import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken()); // 학교까지 거리
        int n = Integer.parseInt(st.nextToken()); // 킥보드 충전소 개수
        int k = Integer.parseInt(st.nextToken()); // 최대 충전소 방문 횟수

        int[] charges = new int[n+2]; // 충전소 위치
        charges[0] = 0; // 시작
        charges[n+1] = l; // 도착
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            charges[i] = Integer.parseInt(st.nextToken());
        }

        int diff = -1;
        for (int i = 1; i <= n + 1; i++) {
            diff = Math.max(diff, charges[i]-charges[i - 1]);
        }

        int left = diff;
        int right = l;
        int ans = l;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            int cnt = 0;
            int tmp = 0;

            for (int i = 1; i <= n+1; i++) {
                if (charges[i] - tmp > mid) {
                    cnt++;
                    if (charges[i] - tmp == mid) {
                        tmp = charges[i];
                    } else {
                        tmp = charges[i-1];
                    }
                }
            }

            if (cnt > k) {
                left = mid+1;
            } else {
                right = mid-1;
                ans = Math.min(ans, mid);
            }
        }

        System.out.println(ans);
    }
}