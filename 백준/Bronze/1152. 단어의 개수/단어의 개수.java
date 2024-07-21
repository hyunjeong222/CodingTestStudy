import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int cnt = 0;
        for (String s : str) {
            if (!s.equals("")) cnt++;
        }
        System.out.println(cnt);
    }
}