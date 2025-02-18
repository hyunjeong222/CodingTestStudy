import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cards = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        int idx;
        for (int i = 0; i < n; i++) {
            if (list.size() == 0 || list.get(list.size()-1) < cards[i]) {
                list.add(cards[i]);
            } else {
                idx = binarySearch(0, list.size()-1, cards[i]);
                list.set(idx, cards[i]);
            }
        }

        System.out.println(list.size());
    }

    private static int binarySearch(int start, int end, int target) {
        int mid;
        while (start <= end) {
            mid = start + (end-start) / 2;

            if (list.get(mid) < target) start = mid+1;
            else end = mid-1;
        }

        return start;
    }
}