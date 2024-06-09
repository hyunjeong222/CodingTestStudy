import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[3];
        // 피타고라스 정리 : 각 변이 a, b, c일 때 c가 빗변이라 하면 c^2 = a^2 + b^2
        while (true) {
            st = new StringTokenizer(br.readLine());
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);

            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) break;

            if (Math.pow(arr[0], 2) + Math.pow(arr[1], 2) == Math.pow(arr[2], 2)) sb.append("right").append("\n");
            else sb.append("wrong").append("\n");
        }

        System.out.println(sb);
    }
}