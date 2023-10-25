public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        boolean[] checked = new boolean[10001];
        for (int i = 1; i < 10001; i++) {
            int n = d(i);

            if (n < 10001) {
                checked[n] = true;
            }
        }

        for (int i = 1; i < 10001; i++) {
            if (!checked[i]) sb.append(i + "\n");
        }

        System.out.println(sb);
    }

    private static int d(int num) {
        int sum = num;
        while (num != 0) {
            sum = sum + (num % 10);
            num = num / 10;
        }
        return sum;
    }
}