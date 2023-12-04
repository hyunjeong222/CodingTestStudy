import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int title = 666;
        int cnt = 0;

        while (n!=cnt) {
            String cur = Integer.toString(title);
            if (cur.contains("666")) {
                cnt++;
            }
            title++;
        }
        System.out.println(title-1);
    }
}