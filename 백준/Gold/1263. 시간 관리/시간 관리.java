import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static public class Time implements Comparable<Time> {
        int processTime; int end;
        public Time(int processTime, int end) {
            this.processTime = processTime; this.end = end;
        }
        @Override
        public int compareTo(Time o) {
            // 종료 시간이 가장 늦은 일을 가장 뒤에 배치
            return o.end - this.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Time> works = new ArrayList<>();
        int t, s; // 일 처리 시간, 제한 시간
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            works.add(new Time(t, s));
        }
        Collections.sort(works);

        int time = works.get(0).end; // 최댓값
        for (int i = 0; i < n; i++) {
            time = Math.min(works.get(i).end, time) -  works.get(i).processTime;
            if (time < 0) {
                time = -1;
                break;
            }
        }

        System.out.println(time);

        br.close();
    }
}