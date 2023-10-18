import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int parent, root, remove;
    static ArrayList<Integer>[] tree;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine()); // 트리의 노드의 개수
        tree = new ArrayList[n+1];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parent = Integer.parseInt(st.nextToken()); // 각 노드의 부모

            if (parent == -1) root = i;
            else tree[parent].add(i);
        }
        remove = Integer.parseInt(br.readLine()); // 지울 노드
        dfs(root);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int node) {
        if (node == remove) return;

        if (tree[node].size() == 0) {
            ans++;
            return;
        }

        for (int next : tree[node]) {
            if (next == remove && tree[node].size() == 1) {
                ans++; // 리프노드
                return;
            }
            dfs(next);
        }
    }
}