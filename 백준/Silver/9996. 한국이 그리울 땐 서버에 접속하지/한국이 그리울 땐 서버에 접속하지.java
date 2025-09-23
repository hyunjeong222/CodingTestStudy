import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        String pattern = br.readLine();
        String regex = "^"+pattern.replace("*", ".*")+"$";

        Pattern p = Pattern.compile(regex);

        while (n --> 0) {
            String str = br.readLine();
            if (p.matcher(str).matches()) {
                sb.append("DA").append("\n");
            } else {
                sb.append("NE").append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}