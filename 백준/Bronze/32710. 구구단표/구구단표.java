import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean flag = false;
        for (int i = 1; i <= 9; i++) {
            if (n % i == 0 && n / i <= 9) {
                flag = true;
                break;
            }
        }

        System.out.println(flag ? 1 : 0);
    }
}