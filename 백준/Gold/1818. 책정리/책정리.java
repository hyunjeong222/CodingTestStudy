import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] books;
    static int[] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        books = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            books[i] = Integer.parseInt(st.nextToken());
        }

        memo = new int[n+1];
        int len = 0, idx;
        for (int i = 0; i < n; i++) {
            if (books[i] > memo[len]) {
                len += 1;
                memo[len] = books[i];
            } else {
                idx = binarySearch(0, len, books[i]);
                memo[idx] = books[i];
            }
        }

        System.out.println(n-len);
    }

    private static int binarySearch(int start, int end, int target) {
        int mid;
        while (start < end) {
            mid = start + (end-start) / 2;

            if (memo[mid] < target) start = mid+1;
            else end = mid;
        }

        return end;
    }
}