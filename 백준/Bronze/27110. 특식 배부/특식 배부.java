import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            int people = Integer.parseInt(st.nextToken());
            if (people >= n) {
                sum += n;
            } else sum += people;
        }

        System.out.println(sum);

        br.close();
    }
}