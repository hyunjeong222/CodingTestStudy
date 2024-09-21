import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String str;
        String tmp;
        while (n --> 0) {
            str = br.readLine();
            tmp = str.substring(1, str.length());
            str = str.toUpperCase();
            sb.append(str.charAt(0)).append(tmp).append("\n");
        }
        System.out.println(sb.toString());
    }
}