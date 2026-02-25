import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 원소의 개수
        int m = Integer.parseInt(st.nextToken()); // 질문의 개수

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        for (int i = 0; i < m; i++) {
            int d = Integer.parseInt(br.readLine());

            sb.append(binary(arr, d)).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int binary(int[] arr, int target) {
        int ans = -1;

        int left = 0, right = arr.length-1;
        while (left <= right) {
            int mid = (left+right)/2;

            if (arr[mid] == target) {
                ans = mid;
                right = mid-1;
            } else if (arr[mid] < target) {
                left = mid+1;
            } else right = mid-1;
        }

        return ans;
    }
}