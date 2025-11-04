import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        // A보다 크거나 같고, B보다 작거나 같은
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        search(a, b, 0L);

        System.out.println(cnt);

        br.close();
    }

    private static void search(int a, int b, long now) {
        if (now > b) {
            return;
        }

        if (now >= a) {
            cnt++;
        }

        search(a, b, now*10+4);
        search(a, b, now*10+7);
    }
}