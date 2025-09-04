import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); // A의 원소 개수
        int b = Integer.parseInt(st.nextToken()); // B의 원소 개수

        st = new StringTokenizer(br.readLine());
        Set<Integer> setA = new HashSet<>();
        for (int i = 0; i < a; i++) {
            setA.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        Set<Integer> setB = new HashSet<>();
        for (int i = 0; i < b; i++) {
            setB.add(Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        for (int num : setA) {
            if (!setB.contains(num)) {
                ans += 1;
            }
        }

        for (int num : setB) {
            if (!setA.contains(num)) {
                ans += 1;
            }
        }

        System.out.println(ans);

        br.close();
    }
}