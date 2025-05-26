import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long ans = 0;
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                int lower = binarySearch(arr[i]+arr[j], j+1, 0);
                int upper = binarySearch(arr[i]+arr[j], j+1, 1);

                ans += upper-lower;

            }
        }

        System.out.println(ans);

        br.close();
    }

    private static int binarySearch(int target, int left, int type) {
        int right = n;

        while (left < right) {
            int mid = (left+right)/2;

            if (type == 0 && arr[mid]+target >= 0) right = mid;
            else if (type == 1 && arr[mid]+target > 0) right = mid;
            else left = mid+1;
        }

        return left;
    }
}