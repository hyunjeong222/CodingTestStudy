import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine()); // 1m2의 넓이에 자라는 참외의 개수

        int[] arr = new int[6];
        int d;
        int maxWidth = 0, maxWidthIdx = 0, maxHeight = 0, maxHeightIdx = 0;
        StringTokenizer st;
        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            arr[i] = Integer.parseInt(st.nextToken());
            if ((d == 1 || d == 2) && maxWidth < arr[i]) { // 가장 긴 가로 길이와 위치
                maxWidth = arr[i];
                maxWidthIdx = i;
            } else if ((d == 3 || d == 4) && maxHeight < arr[i]) { // 가장 긴 세로 길이와 위치
                maxHeight = arr[i];
                maxHeightIdx = i;
            }
        }

        // System.out.println(Arrays.toString(arr));
        // System.out.println(maxWidthIdx);
        // System.out.println(maxHeightIdx);

        int minWidth = 0, minHeight = 0, left = 0, right = 0;

        // 빈 사각형 세로 길이
        if (maxWidthIdx+1 == 6) right = 0;
        else right = maxWidthIdx+1;

        if (maxWidthIdx-1 == -1) left = 5;
        else left = maxWidthIdx-1;

        minHeight = Math.abs(arr[right]-arr[left]);

        // 빈 사각형 가로 길이
        if (maxHeightIdx+1 == 6) right = 0;
        else right = maxHeightIdx+1;

        if (maxHeightIdx-1 == -1) left = 5;
        else left = maxHeightIdx-1;

        minWidth = Math.abs(arr[right]-arr[left]);

        System.out.println(((maxWidth*maxHeight)-(minWidth*minHeight))*k);

        br.close();
    }
}