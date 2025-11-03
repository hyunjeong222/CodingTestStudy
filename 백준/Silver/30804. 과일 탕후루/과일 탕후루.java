import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] fruits = new int[n];
        for (int i = 0; i < n; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }

        // 두 종류 과일만 사용
        HashMap<Integer, Integer> fruitsCnt = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            fruitsCnt.put(fruits[right], fruitsCnt.getOrDefault(fruits[right], 0)+1);

            // 과일을 두 종류 이하로 사용한 탕후루
            while (fruitsCnt.size() > 2) {
                fruitsCnt.put(fruits[left], fruitsCnt.get(fruits[left])-1);

                if (fruitsCnt.get(fruits[left])== 0) {
                    fruitsCnt.remove(fruits[left]);
                }

                left++;
            }

            maxLen = Math.max(maxLen, right-left+1);
        }

        System.out.println(maxLen);


        br.close();
    }
}