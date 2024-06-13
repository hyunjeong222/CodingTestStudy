import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, l;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 자르는 횟수가 담긴 목록의 길이
        m = Integer.parseInt(st.nextToken()); // 자를 수 있는 지점의 개수
        l = Integer.parseInt(st.nextToken()); // 롤케이크의 길이

        int[] arr = new int[m+2]; // 자를 수 있는 지점
        arr[0] = 0;
        for (int i = 1; i <= m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[m+1] = l;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(binarySearch(arr, q)).append("\n");
        }
        System.out.println(sb);

    }

    private static int binarySearch(int[] arr, int q) {
        int ans = 0;
        int left = 0; int right = l;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 롤 케이크를 잘랐을 때 가장 작은 길이
            int prev = arr[0];
            int cutNum = 0;

            for (int i = 1; i <= m+1; i++) {
                if (arr[i] - prev >= mid) {
                    prev = arr[i];
                    cutNum++;
                }
            }

            if (cutNum > q) {
                left = mid+1;
                ans = Math.max(ans, mid);
            } else right = mid-1;
        }
        return ans;
    }
}