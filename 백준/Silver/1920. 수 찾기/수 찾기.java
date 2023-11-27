import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (binarySearch(arr, num) >= 0) {
                sb.append(1).append("\n");
            } else sb.append(0).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int binarySearch(int[] arr, int num) {
        int low = 0;
        int high = arr.length-1;

        while (low <= high) {
            int mid = (low+high) / 2;

            if (num < arr[mid]) {
                high = mid-1;
            } else if (num > arr[mid]) {
                low = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
