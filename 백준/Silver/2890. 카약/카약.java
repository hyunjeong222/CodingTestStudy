import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c =  Integer.parseInt(st.nextToken());

        int[] distance = new int[10];
        Arrays.fill(distance, -1);
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char ch = str.charAt(j);
                if (ch >= '1' && ch <= '9') {
                    distance[ch-'0'] = c-1-j;
                }
            }
        }
        // System.out.println(Arrays.toString(distance));

        int[] sorted = distance.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int d : sorted) {
            if (d!=-1 && !rankMap.containsKey(d)) {
                rankMap.put(d, rank);
                rank++;
            }
        }

        for (int i = 1; i <= 9; i++) {
            sb.append(rankMap.get(distance[i])).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}