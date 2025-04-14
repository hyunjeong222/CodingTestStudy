import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 모기의 마릿수

        HashMap<Integer, Integer> map = new HashMap<>();

        StringTokenizer st;
        // 모기의 입장 시각, 퇴장 시각
        int sTime, eTime;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            sTime = Integer.parseInt(st.nextToken());
            eTime = Integer.parseInt(st.nextToken());

            map.put(sTime, map.getOrDefault(sTime, 0)+1);
            map.put(eTime, map.getOrDefault(eTime, 0)-1);
        }

        // 모기가 있는 시각별 모기의 수를 누적해서 합해야 함
        List<Integer> keyList = new ArrayList<>(map.keySet());
        keyList.sort((o1, o2) -> o1 - o2);

        int sAns = 0, eAns = 0; // 모기가 가장 많이 있는 시간대
        int max = 0; // 해당 시간대에 최대 모기 마릿수
        int sum = 0;
        boolean flag = false; // 최대 구간이 열렸는지

        for (int key : keyList) {
            sum += map.get(key);

            if (sum > max) { // 최댓값 나오면 갱신
                max = sum;
                sAns = key;
                flag = true;
            } else if (sum < max && flag) { // 최대 구간에서 값이 바뀌는 지점이면
                eAns = key;
                flag = false; // 구간 닫음
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n");
        sb.append(sAns).append(" ").append(eAns).append("\n");

        System.out.println(sb.toString());
    }
}