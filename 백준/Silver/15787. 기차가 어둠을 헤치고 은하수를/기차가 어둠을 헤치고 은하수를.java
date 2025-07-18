import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] trains;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 기차 개수
        int m = Integer.parseInt(st.nextToken()); // 명령의 개수

        // 기차 내부 상태 배열
        trains = new int[n+1];
        for (int i = 0; i < m; i++) {
            excute(br.readLine());
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(trains[i]);
        }

        System.out.println(set.size());

        br.close();
    }

    private static void excute(String command) {
        StringTokenizer st = new StringTokenizer(command);

        int t = Integer.parseInt(st.nextToken()); // 명령 번호
        int i = Integer.parseInt(st.nextToken()); // 기차 번호
        int x;

        switch (t) {
            case 1 :
                x = Integer.parseInt(st.nextToken())-1;
                trains[i] |= (1 << x); // 원소 추가
                break;
            case 2 :
                x = Integer.parseInt(st.nextToken())-1;
                trains[i] &= ~(1 << x); // 원소 삭제
                break;
            case 3 :
                trains[i] = (trains[i] << 1) & ((1 << 20) - 1);
                break;
            case 4 :
                trains[i] = trains[i] >> 1;
                break;
        }
    }
}