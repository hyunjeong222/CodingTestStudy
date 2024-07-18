import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 사람의 수
        int m = Integer.parseInt(st.nextToken()); // 파티의 수

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int truthNum = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
        int[] truthPeople = new int[truthNum];
        if (truthNum == 0) { // 진실을 아는 사람이 없다면 모든 파티에서 과장된 이야기 가능
            System.out.println(m);
            System.exit(0);
        }
        for (int i = 0; i < truthNum; i++) {
            truthPeople[i] = Integer.parseInt(st.nextToken());
        }
        // 진실을 아는 사람들 집합 만들기
        for (int i = 0; i < truthNum-1; i++) {
            union(truthPeople[i], truthPeople[i+1]);
        }

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(st.nextToken());
            int[] partyPeople = new int[partyNum];
            for (int j = 0; j < partyNum; j++) {
                partyPeople[j] = Integer.parseInt(st.nextToken());
                list.get(i).add(partyPeople[j]);
            }
            for (int j = 0; j < partyNum-1; j++) {
                union(partyPeople[j] , partyPeople[j+1]);
            }
        }

        int ans = m;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (findSameGroup(truthPeople[0], list.get(i).get(j))) {
                    ans--;
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean findSameGroup(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) parent[x] = y;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}