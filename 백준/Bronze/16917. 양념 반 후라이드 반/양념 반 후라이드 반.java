import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A, B, C, X, Y;
        // 양념 치킨, 후라이드 치킨, 반반 치킨 금액
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 양념 치킨 최소 X마리, 후라이드 치킨 최소 Y마리를 구매
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        int ans = 0;
        int cnt = Math.min(X, Y);

        // 반반 치킨을 두 마리 구입해 양념 치킨 하나와 후라이드 치킨 하나를 만드는 방법도 가능
        // 반반 두 마리를 사는게 한 마리를 사는 것보다 저렴한 경우
        if (2*C < A+B) ans = 2*C*cnt;
        else ans = (A+B)*cnt;

        if (cnt == X) { // 양념은 다 구매했지만 후라이드를 더 사야하는 경우
            cnt = Y - cnt;
            ans += Math.min(cnt*B, 2*C*cnt);
        } else { // 후라이드는 다 구매했지만 양념을 더 사야하는 경우
            cnt = X - cnt;
            ans += Math.min(cnt*A, 2*C*cnt);
        }

        System.out.println(ans);

        br.close();
    }
}