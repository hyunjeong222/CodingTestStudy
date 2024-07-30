import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 집의 개수
        int c = Integer.parseInt(st.nextToken()); // 공유기의 개수
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int start = 1; // 최소 거리가 가질 수 있는 최솟값
        int end = arr[n-1] + arr[0] + 1; // 최소 거리가 가질 수 있는 최댓값
        while (start < end) {
            int mid = start + (end - start) / 2;

            // mid 거리에 대해 설치 가능한 공유기 개수가 c보다 작다면
            // mid 거리를 줄여야 함
            if (install(mid) < c) {
                end = mid;
            } else {
                // 설치 가능한 공유기 개수가 c와 같거나 크다면
                // 거리를 늘리면서 최소거리가 가질 수 있는 최대 거리 찾기
                start = mid+1;
            }
        }
        System.out.println(start-1);
    }

    private static int install(int mid) {
        int cnt = 1; // 첫번째 집은 무조건 설치한다고 가정
        int lastLocation = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int location = arr[i];

            if (location - lastLocation >= mid) {
                cnt++;
                lastLocation = location;
            }
        }

        return cnt;
    }
}