import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int tmp = n-(m*k);
        tmp = tmp < 0 ? 0 : tmp;

        sb.append(tmp).append(" ").append(n-(m*k)+m-1).append("\n");

        System.out.println(sb.toString());

        br.close();
    }
}