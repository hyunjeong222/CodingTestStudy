import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int[] exerciseKit;
    static boolean[] checked;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 운동 키트 개수
        k = Integer.parseInt(st.nextToken()); // 하루가 지날 때마다 감소하는 중량 양

        exerciseKit = new int[n]; // 각 운동 키트의 중량 증가량
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            exerciseKit[i] = Integer.parseInt(st.nextToken());
        }

        checked = new boolean[n];
        backTracking(500, 0);

        System.out.println(ans);
    }

    private static void backTracking(int weightSum, int idx) {
        if (idx == n) { // 모든 운동 키트를 사용
            ans++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i] && (weightSum + exerciseKit[i] - k) >= 500) {
                checked[i] = true;
                backTracking(weightSum + exerciseKit[i] - k, idx+1);
                checked[i] = false;
            }
        }
    }
}