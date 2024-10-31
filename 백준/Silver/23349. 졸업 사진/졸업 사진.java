import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static public class PlaceTime implements Comparable<PlaceTime> {
        String place;
        int start;

        public PlaceTime(String place, int start) {
            this.place = place;
            this.start = start;
        }

        @Override
        public int compareTo(PlaceTime o) {
            if (!this.place.equals(o.place)) {
                return this.place.compareTo(o.place); // 알파벳 정렬
            }
            return this.start - o.start; // 빠른 시간
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 제출 수
        Map<String, int[]> map = new HashMap<>(); // 장소에 대한 시간대
        Set<String> set = new HashSet<>(); // 같은 학생이 여러 번 제출했다면, 첫 번째 제출 이외의 제출들은 모두 무시
        StringTokenizer st;
        int max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken(); // 학생 이름
            String place = st.nextToken(); // 장소
            int start = Integer.parseInt(st.nextToken()); // 시작 시간
            int end = Integer.parseInt(st.nextToken()); // 종료 시간

            if (set.contains(name)) continue;
            set.add(name);

            int[] time;
            if (map.containsKey(place)) { // 이미 장소가 존재하면
                time = map.get(place); // 배열 가져오기
            } else {
                time = new int[50001];
                map.put(place, time);
            }

            for (int idx = start; idx < end; idx++) {
                time[idx]++;
                max = Math.max(max, time[idx]);
            }
        }

        List<PlaceTime> list = new ArrayList<>();
        for (String place : map.keySet()) {
            int[] time = map.get(place);

            // System.out.println(place);
            // System.out.println(Arrays.toString(time));

            for (int i = 0; i < time.length; i++) {
                if (time[i] == max) {
                    list.add(new PlaceTime(place, i));
                    break;
                }
            }
        }
        Collections.sort(list);

        PlaceTime cur = list.get(0);
        int curEnd = findEnd(map.get(cur.place), cur.start, max);

        StringBuilder sb = new StringBuilder();
        sb.append(cur.place).append(" ").append(cur.start).append(" ").append(curEnd);

        System.out.println(sb);
        // System.out.println(max);
    }

    private static int findEnd(int[] time, int start, int max) {
        int end = start;
        while (end <= time.length && time[end] == max) {
            end++;
        }
        return end;
    }
}