import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int count = 0;
        int ans = 0;
        for (int i = 1; i <= b; i++) {
            for (int j = 1; j <= i; j++) {
                count++;

                if (a <= count && count <= b) {
                    ans += i;
                }
            }
        }

        System.out.println(ans);

        br.close();
    }
}