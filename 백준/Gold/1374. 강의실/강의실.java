import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static public class Pos implements Comparable<Pos>{
        int num; int start; int end;
        public Pos(int num, int start, int end){
            this.num = num; this.start = start; this.end = end;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Pos[] arr = new Pos[n];
        int num, start, end;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            arr[i] = new Pos(num, start, end);
        }
        Arrays.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(arr[0].end);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start >= pq.peek()) {
                pq.poll();
                pq.offer(arr[i].end);
            } else pq.offer(arr[i].end);
        }
        System.out.println(pq.size());
    }
}