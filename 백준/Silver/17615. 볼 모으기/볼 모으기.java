import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String balls = br.readLine();

        int ans = Integer.MAX_VALUE;
        boolean flag = false;
        int cnt = 0;
        // 왼쪽으로 빨간 볼 모으기
        for (int i = 0; i < n; i++) {
            if (flag && balls.charAt(i) == 'R') {
                cnt++;
                continue;
            }
            if (balls.charAt(i) == 'B') flag = true;
        }
        ans = Math.min(ans, cnt);

        // 왼쪽으로 파란 볼 모으기
        flag = false;
        cnt = 0;
        for (int i = 0; i < n; i++) {
            if (flag && balls.charAt(i) == 'B') {
                cnt++;
                continue;
            }
            if (balls.charAt(i) == 'R') flag = true;
        }
        ans = Math.min(ans, cnt);

        // 오른쪽으로 빨간 볼 모으기
        flag = false;
        cnt = 0;
        for (int i = n-1; i >= 0; i--) {
            if (flag && balls.charAt(i) == 'R') {
                cnt++;
                continue;
            }
            if (balls.charAt(i) == 'B') flag = true;
        }
        ans = Math.min(ans, cnt);

        // 오른쪽으로 파란 볼 모으기
        flag = false;
        cnt = 0;
        for (int i = n-1; i >= 0; i--) {
            if (flag && balls.charAt(i) == 'B') {
                cnt++;
                continue;
            }
            if (balls.charAt(i) == 'R') flag = true;
        }
        ans = Math.min(ans, cnt);

        System.out.println(ans);
    }
}