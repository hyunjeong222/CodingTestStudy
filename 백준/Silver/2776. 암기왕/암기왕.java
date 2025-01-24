import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] note1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            int n = Integer.parseInt(br.readLine()); // 수첩 1 정수의 개수
            note1 = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                note1[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(note1);

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                boolean flag = binarySearch(num);

                if (flag) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean binarySearch(int num) {
        int start = 0;
        int end = note1.length-1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (note1[mid] == num) return true;

            if (note1[mid] < num) start = mid+1;
            else end = mid-1;
        }

        return false;
    }
}