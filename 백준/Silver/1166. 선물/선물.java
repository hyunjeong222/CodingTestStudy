import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long l = Long.parseLong(st.nextToken());
        long w = Long.parseLong(st.nextToken());
        long h = Long.parseLong(st.nextToken());

        double left = 0; double right = Math.max(l, Math.max(w, h));
        while (left < right) {
            double mid = (left+right)/2.0;

            if (left == mid || right == mid) break;

            if ((long)(l/mid)*(long)(w/mid)*(long)(h/mid) < n) right = mid;
            else left = mid;
        }

        System.out.println(left);

        br.close();
    }
}