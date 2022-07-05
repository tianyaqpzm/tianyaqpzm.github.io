import java.util.ArrayList;
import java.util.List;

/**
 * 水仙花 dfs
 */
public class ShuiXianHua {

    public static void main(String[] args) {
        String str = "f3@dfaa";
        int res = 0;
        // 方法1:
        boolean[][] isShui = new boolean[str.length()][str.length()];
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isShui(arr, j, i)) {
                    isShui[i][j] = true;
                    isShui[j][i] = true;
                }
            }
        }
        ArrayList<List<String>> ans = new ArrayList<>();
        dfs(str, 0, ans, new ArrayList<String>(), isShui);

        // 方法2: split
        split(str, 0);
        if (times == 0) {
            System.out.println(0);
        } else {
            System.out.println(times == 1 ? spliCount : -1);
        }
    }

    private static void dfs(String str, int start, List<List<String>> ans, ArrayList<String> tempList,
            boolean[][] isShui) {
        if (start == str.length()) {
            ans.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < str.length(); i++) {
            if (isShui[start][i]) {
                tempList.add(str.substring(start, i + 1));
                dfs(str, i + 1, ans, tempList, isShui);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private static boolean isShui(char[] arr, int start, int end) {
        if (start == end) {
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            num += arr[i];
        }

        int bak = num;
        int sum = 0;
        while (num > 0) {
            int i = num % 10;
            num = num / 10;
            num += Math.pow(i, 3);
        }
        return bak == sum;
    }

    private static int times = 0;
    private static int spliCount = 0;

    private static void split(String str, int count) {
        if (times > 1) {
            return;
        }
        int sum = 0;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            sum += (int) ch;
            if (isShui(sum)) {
                if (i == charArray.length - 1) {
                    times++;
                    spliCount = count + 1;
                    return;
                }
                split(str.substring(i + 1), count + 1);
            }

        }
    }

    private static boolean isShui(int num) {
        int bak = num;
        int sum = 0;
        while (num > 0) {
            int i = num % 10;
            num = num / 10;
            num += Math.pow(i, 3);
        }
        return bak == sum;
    }

}