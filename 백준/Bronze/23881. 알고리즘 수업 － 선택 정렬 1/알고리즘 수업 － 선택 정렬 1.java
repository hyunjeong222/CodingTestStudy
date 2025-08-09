import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int maxIndex = 0, count = 0;
        for (int i = n-1; i > 0; i--) {
            maxIndex = i;
            for (int j = i-1; j >= 0; j--) {
                if (A[j] > A[maxIndex]) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                int tmp = A[maxIndex];
                A[maxIndex] = A[i];
                A[i] = tmp;
                count++;
                if (count == k) {
                    System.out.println(A[maxIndex] + " " + A[i]);
                    return;
                }
            }
        }

        System.out.println(-1);

        br.close();
    }
}