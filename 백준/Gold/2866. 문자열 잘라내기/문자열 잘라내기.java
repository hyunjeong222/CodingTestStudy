import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()); // 행
        int c = Integer.parseInt(st.nextToken()); // 열

        String[] arr = new String[c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                if (i == 0) arr[j] = String.valueOf(str.charAt(j));
                else arr[j] += String.valueOf(str.charAt(j));
            }
        }
        // da, od, ba, at, ra, zk

        for (int i = 0; i < r-1; i++) {
            Set<String> set = new HashSet<>();

            for (int j = 0; j < c; j++) {
                String key = arr[j].substring(i+1);
                if (set.contains(key)) {
                    System.out.println(i);
                    System.exit(0);
                } else set.add(key);
            }
        }

        System.out.println(r-1);
    }
}