import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        long num;
        while (n > 0) {
            while (st.hasMoreTokens()) {
                num = Long.parseLong(st.nextToken());
                arr[--n] = getReverse(num);
            }
            if (n > 0) {
                st = new StringTokenizer(br.readLine());
            }
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (long i : arr) {
            sb.append(i).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static long getReverse(long num) {
        long reverse = 0;
        while (num != 0) {
            long dight = num % 10;
            reverse = reverse * 10 + dight;
            num /= 10;
        }
        return reverse;
    }
}