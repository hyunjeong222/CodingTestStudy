import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] origin = new int[n];
        int[] compress = new int[n]; // 압축배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            origin[i] = compress[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 압축배열에 대해 오름차순 정렬
        Arrays.sort(compress);

        // 2. 압축배열의 중복 요소 제거
        // 압축배열에서는 idx 값 필요
        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int key : compress) {
            if (!map.containsKey(key)) {
                map.put(key, idx);
                idx++;
            }
        }

        // 3. 인풋배열 요소 == 압축배열 요소일때 탐색
        // 탐색 성공시 해당 인덱스 값 반환
        StringBuilder sb = new StringBuilder();
        for (int key : origin) {
            int ans = map.get(key);
            sb.append(ans).append(" ");
        }
        System.out.println(sb);

    }
}