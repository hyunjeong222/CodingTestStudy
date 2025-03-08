import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arrX = br.readLine().toCharArray();
        char[] arrY = br.readLine().toCharArray();
        char[] arrZ = br.readLine().toCharArray();

        int k = Integer.parseInt(br.readLine());

        dfs(arrX, new boolean[arrX.length], 0, k);
        dfs(arrY, new boolean[arrY.length], 0, k);
        dfs(arrZ, new boolean[arrZ.length], 0, k);

        ArrayList<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) >= 2) { // 두 번 이상 나타나는
                list.add(key);
            }
        }

        if (list.size() == 0) {
            System.out.println(-1);
            return;
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(char[] arr, boolean[] checked, int depth, int k) {
        // 임의로 k개를 선택하여 문자열 S에서의 순서를 유지하여 만든
        // 모든 부분 문자열을 모아 놓은 집합
        if (k == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (checked[i]) sb.append(arr[i]);
            }

            String key = sb.toString();
            // map 사용해서 두 번 이상 나타나는 부분 문자열 확인
            map.put(key, map.getOrDefault(key, 0)+1);

            return;
        }

        for (int i = depth; i < arr.length; i++) {
            if (!checked[i]) {
                checked[i] = true;
                dfs(arr, checked, i+1, k-1);
                checked[i] = false;
            }
        }
    }
}