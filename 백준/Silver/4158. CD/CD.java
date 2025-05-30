import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            arr = new int[N];
            answer = 0;

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            for (int i = 0; i < M; i++) {
                int key = Integer.parseInt(br.readLine());
                if (binarySearch(key)) {
                    answer++;
                }
            }

            System.out.println(answer);
        }

        br.close();
    }

    static boolean binarySearch(int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (key == arr[mid]) {
                return true;
            } else if (key < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}