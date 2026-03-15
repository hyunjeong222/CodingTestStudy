import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        boolean flag = n+2 >= 8 ? true : false;

        if (flag) sb.append("Success!").append("\n");
        else sb.append("Oh My God!").append("\n");

        System.out.println(sb.toString());

        br.close();
    }
}