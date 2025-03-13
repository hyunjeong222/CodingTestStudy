import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append((int)(n*0.78)).append(" ")
                .append((int)(n*0.8+n*0.2*0.78)).append("\n");

        System.out.println(sb.toString());
    }
}