import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static HashMap<String, ArrayList<String>> folders;
    static HashMap<String, ArrayList<String>> files;
    static HashMap<String, Count> ans;
    static public class Count {
        int type; int cnt;
        public Count(int type, int cnt) {
            this.type = type; this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 폴더의 총 개수
        m = Integer.parseInt(st.nextToken()); // 파일의 총 개수

        folders = new HashMap<>();
        files = new HashMap<>();

        for (int i = 0; i < n+m; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String child = st.nextToken();
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) { // 폴더
                if (!folders.containsKey(parent)) {
                    folders.put(parent, new ArrayList<>());
                }
                folders.get(parent).add(child);
            } else { // 파일
                if (!files.containsKey(parent)) {
                    files.put(parent, new ArrayList<>());
                }
                files.get(parent).add(child);
            }
        }

        ans = new HashMap<>();
        dfs("main");

        int q = Integer.parseInt(br.readLine());
        while (q --> 0) {
            String str = br.readLine();
            String[] splitStr = str.split("/");

            String dest = splitStr[splitStr.length-1];
            Count c = ans.get(dest);
            sb.append(c.type).append(" ").append(c.cnt).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static ArrayList<String> dfs(String folderName) {
        ArrayList<String> result = new ArrayList<>();
        if (folders.get(folderName) != null) {
            for (String key : folders.get(folderName)) {
                ArrayList<String> searchFolder = dfs(key); // 하위 폴더 탐색
                result.addAll(searchFolder);
            }
        }

        if (files.get(folderName) != null) {
            result.addAll(files.get(folderName));
        }

        if (!ans.containsKey(folderName)) {
            HashSet<String> set = new HashSet<>(result);
            ans.put(folderName, new Count(set.size(), result.size()));
        }

        return result;
    }
}