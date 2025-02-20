import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer> list = new ArrayList<>();
    static Pos[] node;
    static public class Pos {
        int num; int idx;
        public Pos (int num, int idx) {
            this.num = num; this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>(); // 값, idx
        int[] switches = new int[n];
        for (int i = 0; i < n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
            map.put(switches[i], i);
        }
        int[] lights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lights[i] = map.get(Integer.parseInt(st.nextToken()));
        }

        node = new Pos[n];
        for (int i = 0; i < n; i++) {
            if (list.size() == 0 || list.get(list.size()-1) < lights[i]) {
                list.add(lights[i]);
                node[i] = new Pos(lights[i], list.size()-1);
            } else {
                int idx = binarySearch(lights[i]);
                list.set(idx, lights[i]);
                node[i] = new Pos(lights[i], idx);
            }
        }

        int idx = list.size()-1;
        for (int i = n-1; i >= 0; i--) {
            if (node[i].idx == idx) {
                list.set(idx--, switches[node[i].num]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");

        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static int binarySearch(int target) {
        int start = 0;
        int end = list.size()-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (list.get(mid) < target) start = mid+1;
            else end = mid-1;
        }
        return start;
    }
}