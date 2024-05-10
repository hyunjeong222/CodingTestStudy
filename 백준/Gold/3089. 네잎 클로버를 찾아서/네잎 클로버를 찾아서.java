import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 네잎 클로버 개수
        int m = Integer.parseInt(st.nextToken()); // 명령의 수

        // 네잎 클로버 위치 저장
        // 각 x에 대한 y의 값, 각 y에 대한 x의 값 저장
        // 범위 -100,000 ~ 100,000
        TreeSet<Integer>[] treeX = new TreeSet[200000];
        TreeSet<Integer>[] treeY = new TreeSet[200000];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작 포인트가 -100,000이므로 + 100,000
            int x = Integer.parseInt(st.nextToken()) + 100000;
            int y = Integer.parseInt(st.nextToken()) + 100000;

            if (treeX[x] == null) treeX[x] = new TreeSet<>();
            if (treeY[y] == null) treeY[y] = new TreeSet<>();

            treeX[x].add(y);
            treeY[y].add(x);
        }

        String order = br.readLine(); // 명령
        // 현재 위치, 시작점 0, 0
        int nowX = 100000;
        int nowY = 100000;
        for (int i = 0; i < m; i++) {
            char d = order.charAt(i);

            switch (d) {
                // 왼쪽 : y 같고, x 작은것
                case 'L' : nowX = treeY[nowY].lower(nowX);
                           break;
                // 오른쪽 : y 같고, x 큰것
                case 'R' : nowX = treeY[nowY].higher(nowX);
                           break;
                // 위 : x 같고, y 큰것
                case 'U' : nowY = treeX[nowX].higher(nowY);
                           break;
                // 아래 : x같고, y 작은것
                case 'D' : nowY = treeX[nowX].lower(nowY);
                           break;
            }
        }
        nowX -= 100000;
        nowY -= 100000;

        System.out.println(nowX + " " + nowY);
    }
}