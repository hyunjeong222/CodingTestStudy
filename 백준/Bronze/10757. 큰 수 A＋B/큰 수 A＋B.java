import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigDecimal A = new BigDecimal(st.nextToken());
        BigDecimal B = new BigDecimal(st.nextToken());

        System.out.println(String.valueOf(A.add(B)));

        br.close();
    }
}