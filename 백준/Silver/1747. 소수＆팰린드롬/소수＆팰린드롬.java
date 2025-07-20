import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] arr = new boolean[10000001];
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i < 10000001; i++) {
            if (i >= n && !arr[i]) {
                list.add(i);
            }

            for (int j = i+i; j < 10000001; j+=i) {
                arr[j] = true;
            }
        }

        for (int i : list) {
            String num = String.valueOf(i);
            String reversed = new StringBuilder(num).reverse().toString();

            if (num.equals(reversed)) {
                System.out.println(num);
                return;
            }
        }

        br.close();
    }
}