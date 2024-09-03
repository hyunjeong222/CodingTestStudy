import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 우주의 개수
        int n = Integer.parseInt(st.nextToken()); // 각 우주에 있는 행성의 개수

        int[][] spaces = new int[m][n];
        // 중복제거
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                spaces[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] space : spaces) {
            int[] clone = space.clone();
            // 이분탐색을 위한 중복제거와 정렬 작업 - 좌표압축
            int[] arr = Arrays.stream(clone).sorted().distinct().toArray();
            for (int i = 0; i < n; i++) {
                space[i] = binarySearch(arr, space[i]);
            }
        }
        // System.out.println(Arrays.deepToString(spaces));

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
                if (Arrays.equals(spaces[i], spaces[j])) ans++;
            }
        }

        System.out.println(ans);
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0; int right = arr.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) right = mid;
            else left = mid+1;
        }

        return left;
    }
}