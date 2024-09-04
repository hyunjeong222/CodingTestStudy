import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 현재 휴게소의 개수
        int m = Integer.parseInt(st.nextToken()); // 더 지으려는 휴게소의 개수
        int l = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        int[] road = new int[n+2]; // 고속도로 시작과 끝을 포함
        road[0] = 0; road[n+1] = l;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(road);

        // System.out.println(Arrays.toString(road));

        int left = 1;
        int right = l-1;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            int cnt = getRest(road, mid);

            if (cnt > m) {
                left = mid+1;
            }else { // cnt <= m
                right = mid-1;
                ans = mid;
            }
        }

        System.out.println(ans);
    }

    private static int getRest(int[] road, int mid) {
        int cnt = 0;
        for (int i = 0; i <= road.length-2; i++) {
            int tmpDist = road[i+1] - road[i];
            cnt += tmpDist / mid;

            if (tmpDist % mid == 0) cnt--;
        }

        return cnt;
    }
}