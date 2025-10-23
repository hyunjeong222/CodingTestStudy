import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long s = Long.parseLong(br.readLine());

        long sum = 0;
        int cnt = 0;
        for (int i = 1; ; i++) {
            if (sum > s) break;
            sum += i;
            cnt++;
        }

        System.out.println(cnt-1);

        br.close();
    }
}