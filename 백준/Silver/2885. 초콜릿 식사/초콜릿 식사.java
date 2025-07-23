import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine()); // 상근이가 먹어야 하는 초콜릿 갯수

        int chocoSize = 1;
        while(chocoSize < k) {
            chocoSize *= 2; // 초콜릿 크기는 항상 2의 제곱의 형태
        }

        StringBuilder sb = new StringBuilder();
        // 현재 조각과 상근이가 먹어야 하는 초콜릿 갯수가 일치
        if (chocoSize == k) {
            sb.append(chocoSize).append(" ").append(0);
            System.out.println(sb.toString());
            return;
        }

        int cuttingCnt = 0; // 쪼개는 횟수
        int piece = chocoSize;
        while (k > 0) { // 상근이가 모든 초콜릿 조각을 모을 때까지
            piece /= 2; // 항상 가운데로만 쪼개짐
            cuttingCnt++;

            if (piece <= k) { // 상근이가 현재 조각을 가져갈 수 있으면
                k -= piece; // 가져감
            }
        }

        sb.append(chocoSize).append(" ").append(cuttingCnt);
        System.out.println(sb.toString());

        br.close();
    }
}