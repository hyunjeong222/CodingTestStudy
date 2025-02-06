import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 길이
        int h = Integer.parseInt(st.nextToken()); // 높이

        int[] down = new int[n/2]; // 아래 장애물
        int[] up = new int[n/2]; // 위 장애물
        for (int i = 0; i < n/2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(down);
        Arrays.sort(up);

        int min = n;
        int cnt = 0;
        // 현재 높이에 장애물이 있는지 탐색
        for (int i = 1; i <= h; i++) {
             int total = binarySearch(0, n/2, i, down) + binarySearch(0, n/2, h-i+1, up);
             if (total == min) {
                 cnt++;
             } else if (min > total) {
                 min = total;
                 cnt = 1;
             }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(cnt).append("\n");

        System.out.println(sb.toString());
    }

    private static int binarySearch(int start, int end, int h, int[] arr) {
        while (start < end) {
            int mid = start + (end-start) / 2;

            if (arr[mid] >= h) {
                end = mid;
            } else {
                start = mid+1;
            }
        }

        return arr.length-end;
    }
}