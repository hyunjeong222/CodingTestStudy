import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int sum = 0;
            while (n <= m) { // n이 m보다 작거나 같을 때 까지
                String num = Integer.toString(n);
                int flag = 0;

                for (int i = 0; i < num.length()-1; i++) {
                    for (int j = i+1; j < num.length(); j++) {
                        if (num.charAt(i) == num.charAt(j)) {
                            flag++;
                            break;
                        }
                    }
                }

                if (flag == 0) sum++;

                n++;
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());

        // br.close();
    }
}