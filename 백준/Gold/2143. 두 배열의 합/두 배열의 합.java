import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long t = Long.parseLong(br.readLine()); // target

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int aSize = n*(n+1)/2;
        int bSize = m*(m+1)/2;
        long[] aSum = new long[aSize];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int av = 0;
            for (int j = i; j < n; j++) {
                av += A[j];
                aSum[idx++] = av;
            }
        }
        // System.out.println(Arrays.toString(A));
        // System.out.println(Arrays.toString(aSum));

        long[] bSum = new long[bSize];
        idx = 0;
        for (int i = 0; i < m; i++) {
            int bv = 0;
            for (int j = i; j < m; j++) {
                bv += B[j];
                bSum[idx++] = bv;
            }
        }

        Arrays.sort(aSum);
        Arrays.sort(bSum);

        long ans = 0;
        for (int i = 0; i < aSize; i++) {
            long bv = t - aSum[i];

            long value = upperBound(bSum, bv) - lowerBound(bSum, bv);

            ans += value;
        }

        System.out.println(ans);

        br.close();
    }

    private static long upperBound(long[] arr, long target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left+right)/2;

            if (arr[mid] <= target) left = mid+1;
            else right = mid;
        }
        return right;
    }

    private static long lowerBound(long[] arr, long target) {
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