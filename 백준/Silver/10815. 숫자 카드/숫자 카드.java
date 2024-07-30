import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] find;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        m = Integer.parseInt(br.readLine());
        find = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            find[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            boolean flag = binarySearch(find[i]);
            if (flag) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);
    }

    private static boolean binarySearch(int x) {
        int left = 0;
        int right = n-1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == x) return true;
            else if (arr[mid] < x) left = mid+1;
            else right = mid-1;
        }
        return false;
    }
}