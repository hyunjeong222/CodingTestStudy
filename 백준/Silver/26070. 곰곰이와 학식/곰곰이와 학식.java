import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken()); // 치킨 먹고싶은 곰곰이 수
        long b = Long.parseLong(st.nextToken()); // 피자
        long c = Long.parseLong(st.nextToken()); // 햄버거

        // 치킨 식권 3장 -> 피자 식권 1장
        // 피자 식권 3장 -> 햄버거 식권 1장
        // 햄버거 식권 3장 -> 치킨 식권 1장
        st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken()); // 치킨 식권 장수
        long y = Long.parseLong(st.nextToken()); // 피자 식권 장수
        long z = Long.parseLong(st.nextToken()); // 햄버거 식권 장수

        long ans = 0;
        for (int i = 0; i < 3; i++) {
            // 치킨 교환
            long chicken = Math.min(a, x);
            ans += chicken;
            a -= chicken;
            x -= chicken;

            // 피자 교환
            long pizza = Math.min(b, y);
            ans += pizza;
            b -= pizza;
            y -= pizza;

            // 햄버거 교환
            long burger = Math.min(c, z);
            ans += burger;
            c -= burger;
            z -= burger;

            // 식권 교환
            long newY = x/3;
            long newZ = y/3;
            long newX = z/3;

            x = newX;
            y = newY;
            z = newZ;
        }

        System.out.println(ans);

        br.close();
    }
}