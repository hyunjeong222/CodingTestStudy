import java.io.*;

public class Main {
    static int i = 666;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(searchN(n));
    }

    private static int searchN(int n) {
        if (Integer.toString(i).contains("666")) cnt++;
        if (cnt == n) return i;
        i++;
        return searchN(n);
    }
}