import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = br.readLine();
        }

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // A와 B의 길이가 다르면, 짧은 것이 먼저
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                }

                int sum1 = 0, sum2 = 0;
                for (int i = 0; i < o1.length(); i++) {
                    if(o1.charAt(i) >= '0' && o1.charAt(i) <= '9'){
                        sum1 += o1.charAt(i) - '0';
                    }
                }
                for (int i = 0; i < o2.length(); i++) {
                    if(o2.charAt(i) >= '0' && o2.charAt(i) <= '9'){
                        sum2 += o2.charAt(i) - '0';
                    }
                }
                if(sum1 != sum2) return sum1 - sum2;

                // 사전순
                for (int i = 0; i < o1.length(); i++) {
                    if(o1.charAt(i) != o2.charAt(i)) {
                        return o1.charAt(i) - o2.charAt(i);
                    }
                }

                return 0;
            }
        });

        for (String str : strs) {
            sb.append(str).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}