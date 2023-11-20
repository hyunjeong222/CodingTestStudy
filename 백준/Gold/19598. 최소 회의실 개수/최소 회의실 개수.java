import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class pos implements Comparable<pos>{
        int start; int end;
        public pos(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(pos o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 배열의 크기
        pos[] arr = new pos[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new pos(start, end);
        }
        Arrays.sort(arr);

        PriorityQueue<Integer> meeting = new PriorityQueue<>();
        meeting.offer(arr[0].end);

        for (int i = 1; i < n; i++) {
            if (meeting.peek() <= arr[i].start) {
                meeting.poll();
            }
            meeting.offer(arr[i].end);
        }
        bw.append(meeting.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}