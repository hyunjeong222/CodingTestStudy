import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 막걸리 주전자 개수
        int k = Integer.parseInt(st.nextToken()); // 은상이를 포함한 친구들의 수

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int start = 1;
        int end = arr[n-1];
        while (start <= end) {
            int mid = start + (end-start) / 2;

            int cnt = 0;
            for (int a : arr) {
                cnt += a/mid;
            }

            if (cnt >= k) start = mid+1;
            else end = mid-1;
        }

        System.out.println(end);
    }
}