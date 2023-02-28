import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution150366 {
    public static int[] parent;
    public static String[] stringValue;

    public static String[] solution(String[] commands) {
        parent = new int[2501];
        stringValue = new String[2501];

        for (int i = 1; i < 2501 ; i++) {
            parent[i] = i;
            stringValue[i] = "";
        }

        List<String> answer= new ArrayList<>();
        for (int n = 0; n < commands.length; n++) {
            String item = commands[n];
            StringTokenizer st = new StringTokenizer(item);

            String command = st.nextToken();

            if ("UPDATE".equals(command)) {
                if (st.countTokens() == 2) {
                    String before = st.nextToken();
                    String after = st.nextToken();
                    update(before, after);
                }
                else {
                    int r1 = Integer.parseInt(st.nextToken());
                    int c1 = Integer.parseInt(st.nextToken());
                    String after = st.nextToken();
                    update(r1, c1, after);
                }

            } else if ("MERGE".equals(command)) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                merge(r1, c1, r2, c2);
            } else if ("UNMERGE".equals(command)){
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                unmerge(r1, c1);
            } else if ("PRINT".equals(command)) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                answer.add(print(r1, c1));
            }
        }


        return answer.toArray(new String[0]);
    }

    public static void update(String before, String after) {
        for (int i = 1 ; i < 2501 ; i++) {
            if (before.equals(stringValue[i])) {
                stringValue[i] = after;
            }
        }
    }

    public static void update(int r, int c, String after) {
        stringValue[find(getIndexByCor(r, c))] = after;
    }

    public static void merge(int r1, int c1, int r2, int c2) {
        int p1 = find(getIndexByCor(r1, c1));
        int p2 = find(getIndexByCor(r2, c2));

        if (p1 == p2)
            return;

       String mergeValue = "".equals(stringValue[p1]) ? stringValue[p2] : stringValue[p1];
       stringValue[p1] = "";
       stringValue[p2] = "";
       union(p1, p2);
       stringValue[p1] = mergeValue;
    }

    public static void unmerge(int r, int c) {
        int p = find(getIndexByCor(r, c));

        String parentValue = stringValue[p];
        stringValue[p] = "";
        stringValue[getIndexByCor(r, c)] = parentValue;

        List<Integer> deleteList = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            if (find(i) == p)
                deleteList.add(i);
        }
        for (Integer t : deleteList)
            parent[t] = t;
    }

    public static String print(int r, int c) {
        int p = find(getIndexByCor(r, c));

        if ("".equals(stringValue[p])) {
            return "EMPTY";
        }
        else {
            return stringValue[p];
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    public static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static int getIndexByCor(int r, int c) {
        return 50 * (r -1) + c;
    }
}
