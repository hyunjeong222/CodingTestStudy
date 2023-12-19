import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        System.out.println(combi(n, k));
    }

    private static int combi(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        }

        return combi(n-1, r-1) + combi(n-1, r);
    }
}