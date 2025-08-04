import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean isReverseOrder = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 전체 아이스크림의 개수
        int m = Integer.parseInt(st.nextToken()); // 지호가 먹을 아이스크림의 개수

        // TreeSet<Integer>는 인덱스를 자동으로 오름차순 정렬
        // 어떤 양을 가진 아이스크림이 여러 개라면
        // 가장 왼쪽에 있는, 즉 가장 작은 인덱스를 가진 아이스크림을 먹어야 함
        TreeMap<Integer, TreeSet<Integer>> iceCreams = new TreeMap<>(); // 아이스크림의 양, 그 양을 가진 아이스크림의 인덱스들
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int amount = Integer.parseInt(st.nextToken());
            // 해당 키가 존재하지 않으면 value를 삽입하고
            // 이미 존재하면 아무것도 안 함
            iceCreams.putIfAbsent(amount, new TreeSet<>());
            iceCreams.get(amount).add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (m --> 0) {
            TreeSet<Integer> group = iceCreams.lastEntry().getValue(); // 가장 양 많은 그룹

            if (isReverseOrder) {
                sb.append(group.pollLast()).append("\n");
            } else {
                sb.append(group.pollFirst()).append("\n");
            }

            if (iceCreams.lastKey() % 7 == 0) { // 방향 뒤집기
                isReverseOrder = !isReverseOrder;
            }

            if (group.isEmpty()) {
                iceCreams.pollLastEntry(); // 먹은 그룹 다 비우면 삭제
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}