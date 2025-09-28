import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        int max = 0;
        String str = "";
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            map.put(name, map.getOrDefault(name, 0)+1);

            if (map.get(name) > max) {
                max = map.get(name);
                str = name;
            } else if (max == map.get(name) && str.compareTo(name) < 0) {
                str = name;
            }
        }

        sb.append(str).append(" ").append(max);

        System.out.println(sb.toString());

        br.close();
    }
}