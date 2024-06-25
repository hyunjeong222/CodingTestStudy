import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] arr;
    static boolean[] checked;
    static boolean[] selected; // 부분집합
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1]; // 각 구역의 인원수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken()); // 그 구역과 인접한 구역의 수
            for (int j = 0; j < cnt; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph.get(i).add(num);
            }
        }

        selected = new boolean[n+1];
        // 선거구역 나누기
        divide(1);

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void divide(int idx) {
        if (idx == n) {
            // 구역을 두 개의 선거구로 나누기
            ArrayList<Integer> aList = new ArrayList<>();
            ArrayList<Integer> bList = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                if (selected[i]) aList.add(i);
                else bList.add(i);
            }

            if (aList.size() == 0 || bList.size() == 0) return; // 구역을 적어도 하나 포함

            // 한 선거구에 포함되어 있는 구역은 모두 연결
            if (check(aList) && check(bList)) {
                getDiff();
            }
            return;
        }

        // 현재원소 선택
        selected[idx] = true;
        divide(idx+1);
        // 현재원소 비선택
        selected[idx] = false;
        divide(idx+1);
    }

    private static boolean check(ArrayList<Integer> list) {
        Queue<Integer> que = new LinkedList<>();
        checked = new boolean[n+1];
        checked[list.get(0)] = true;
        que.offer(list.get(0));

        int cnt = 1; // 방문한 지역의 수
        while (!que.isEmpty()) {
            int now = que.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);
                if (list.contains(next) && !checked[next]) {
                    que.offer(next);
                    checked[next] = true;
                    cnt++;
                }
            }
        }

        // 선거구에 해당하는 지역수와 방문한 지역수가 같다면 모두 연결되어 있음
        if (list.size() == cnt) return true;
        else return false;
    }

    private static void getDiff() {
        int aPeople = 0;
        int bPeople = 0;

        for (int i = 1; i <= n; i++) {
            if (selected[i]) aPeople += arr[i];
            else bPeople += arr[i];
        }

        int diff = Math.abs(aPeople-bPeople);
        ans = Math.min(ans, diff);
    }
}