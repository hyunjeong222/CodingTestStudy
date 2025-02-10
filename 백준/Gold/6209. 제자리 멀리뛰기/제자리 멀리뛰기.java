import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken()); // 돌섬에서 탈출구까지 거리
        int n = Integer.parseInt(st.nextToken()); // 작은 돌섬의 수
        int m = Integer.parseInt(st.nextToken()); // 제거할 수 있는 작은 돌섬의 수

        int[] distance = new int[n+1];
        for (int i = 1; i <= n; i++) {
            distance[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(distance);

        int start = 0;
        int end = d;
        int mid;
        int ans = 0;
        while (start <= end) {
            mid = start + (end-start) / 2; // 돌 다리 사이의 최소거리

            int prev = 0; int cnt = 0;
            for (int i = 1; i <= n; i++) { // 제거할 돌 찾기
                if (mid <= distance[i]-distance[prev]) { // 최소 거리 mid의 최댓값이 될 수 있음
                    prev = i;
                } else {
                    cnt++; // 돌 제거해서 점프 거리 늘리기

                }
            }

            if (cnt <= m) {
                ans = mid;
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

        System.out.println(ans);
    }
}