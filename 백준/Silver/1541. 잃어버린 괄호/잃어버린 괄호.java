import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer minus = new StringTokenizer(br.readLine(), "-");
        int sum = Integer.MAX_VALUE;
        while (minus.hasMoreTokens()) {
            int temp = 0;

            StringTokenizer plus = new StringTokenizer(minus.nextToken(), "+");
            while (plus.hasMoreTokens()) {
                temp += Integer.parseInt(plus.nextToken());
            }
            
            if (sum == Integer.MAX_VALUE) {
                sum = temp;
            } else {
                sum -= temp;
            }
        }
        bw.append(sum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}