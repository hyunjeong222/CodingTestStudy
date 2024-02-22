import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String n = br.readLine();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n.length(); i++) {
            list.add(n.charAt(i)-'0');
        }
        Collections.sort(list, Collections.reverseOrder());
        for (int i : list) {
            sb.append(i);
        }
        System.out.println(sb);
    }
}