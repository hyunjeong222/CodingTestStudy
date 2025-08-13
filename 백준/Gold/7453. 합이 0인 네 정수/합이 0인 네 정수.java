import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] a, b, c, d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        int[] ab = new int[n*n];
        int[] cd = new int[n*n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[idx] = a[i]+b[j];
                cd[idx++] = c[i]+d[j];
            }
        }

        Arrays.sort(cd);

        long ans = 0;
        for (int i = 0; i < ab.length; i++) {
            ans += (upperBound(cd, -ab[i])-lowerBound(cd, -ab[i]));
        }

        System.out.println(ans);

        br.close();
    }

    private static long upperBound(int[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left+right)/2;

            if (arr[mid] <= target) left = mid+1;
            else right = mid;
        }

        return right;
    }

    private static long lowerBound(int[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left+right)/2;

            if (arr[mid] < target) left = mid+1;
            else right = mid;
        }

        return right;
    }
}