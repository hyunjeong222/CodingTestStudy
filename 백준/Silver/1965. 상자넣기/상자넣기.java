import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] size, memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 상자의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        size = new int[n]; // 각 상자의 크기
        for (int i = 0; i < n; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }

        memo = new int[n+1];
        int len = 0, idx;
        for (int i = 0; i < n; i++) {
            if(size[i] > memo[len]) {
                memo[++len] = size[i];
            } else {
                idx = binarySearch(0, len, size[i]);
                memo[idx] = size[i];
            }
        }

        System.out.println(len);

        br.close();
    }

    private static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = left+(right-left)/2;

            if (memo[mid] < target) left = mid+1;
            else  right = mid;
        }

        return right;
    }
}