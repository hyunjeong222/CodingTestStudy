import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long n2 = (long) Math.pow(n, 2);

        System.out.println(n2 > 100_000_000 ? "Time limit exceeded" : "Accepted");

        br.close();
    }
}