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
        int truthCnt = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수

        if (truthCnt == 0) { // 진실을 아는 사람이 없다면 모든 파티 참석 가능
            System.out.println(m);
            System.exit(0);
        }

        int[] truthPeople = new int[truthCnt+1];
        for (int i = 1; i <= truthCnt; i++) {
            truthPeople[i] = Integer.parseInt(st.nextToken());
        }
        // 진실을 아는 사람 하나의 집합으로 묶어주기
        for (int i = 1; i < truthCnt; i++) {
            union(truthPeople[i], truthPeople[i+1]);
        }

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(st.nextToken()); // 각 파티에 오는 사람의 수
            int[] partyPeople = new int[partyNum+1];
            for (int j = 1; j <= partyNum; j++) {
                partyPeople[j] = Integer.parseInt(st.nextToken());
                list.get(i).add(partyPeople[j]);
            }

            // 파티에 오는 사람들 하나의 집합으로 묶어주기
            for (int j = 1; j < partyNum; j++) {
                union(partyPeople[j], partyPeople[j+1]);
            }
        }

        int ans = m;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (findSameGroup(truthPeople[1], list.get(i).get(j))) {
                    ans--;
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean findSameGroup(int a, int b) {
        a = find(a);
        b = find(b);

        return a == b;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) parent[a] = b;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}