import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 끊어진 기타줄의 개수
        int m = Integer.parseInt(st.nextToken()); // 기타줄 브랜드

        int pack = Integer.MAX_VALUE;
        int piece = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            pack = Math.min(pack, Integer.parseInt(st.nextToken())); // 6줄 패키지 중 가장 저렴한 가격
            piece = Math.min(piece, Integer.parseInt(st.nextToken())); // 낱개 중 가장 저렴한 가격
        }

        int ans = 0;
        if (piece*6 <= pack) { // 낱개로 6개 사는게 패키지로 사는 것보다 싸다면
            ans += piece*n;
        } else {
            ans = (n/6)*pack; // 패키지로 가능한만큼 구매

            // 나머지 낱개로 살지 패키지로 살지 결정
            if ((n%6)*piece <= pack) {
                ans += (n%6)*piece;
            } else {
                ans += pack;
            }
        }

        System.out.println(ans);
        
        br.close();
    }
}