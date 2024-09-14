import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[3];
        arr[0] = 1;
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int x, y;
        int tmp;
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken())-1;
            y = Integer.parseInt(st.nextToken())-1;

            tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        int cnt = 0;
        for (int a : arr) {
            cnt++;
            if (a == 1) break;
        }

        if (cnt == 0) {
            System.out.println(-1);
        } else {
            System.out.println(cnt);
        }
    }
}