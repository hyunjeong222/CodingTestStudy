import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = "";
        while ((s = br.readLine()) != null) {
            BigInteger n = new BigInteger(s);
            BigInteger base = BigInteger.ZERO;

            while (true) {
                base = base.multiply(BigInteger.TEN).add(BigInteger.ONE);
                if (base.mod(n).equals(BigInteger.ZERO)) {
                    sb.append(base.toString().length()).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}