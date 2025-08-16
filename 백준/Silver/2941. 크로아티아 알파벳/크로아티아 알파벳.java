import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        String[] alph = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        for (int i = 0; i < alph.length; i++) {
            if (str.contains(alph[i])) str = str.replace(alph[i], "#");
        }

        System.out.println(str.length());

        br.close();
    }
}