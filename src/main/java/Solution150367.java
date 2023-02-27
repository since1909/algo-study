public class Solution150367 {
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String bin = Long.toBinaryString(numbers[i]);
            double depth = Math.ceil(baseLog(bin.length() + 1 , 2));
            int cnt = ((int) Math.pow(2, depth) - 1) - bin.length();

            StringBuilder sb  = new StringBuilder();
            for (int j = 0; j < cnt; j++) {
                sb.append('0');
            }

            String fullBin = sb.toString() + bin;

            int center = (int)(fullBin.length() / 2);
            if (check(fullBin, fullBin.substring(center, center + 1))) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    public static boolean check(String bin, String parent) {
        String[] binArray = bin.split("");
        if ("0".equals(parent)) {
            for(String b : binArray) {
                if ("1".equals(b)) {
                    return false;
                }
            }
        }
        if (bin.length() == 1)
            return true;
        int center = (int) (bin.length() / 2);
        return check(bin.substring(0, center), binArray[center]) & check(bin.substring(center + 1), binArray[center]);
    }

    public  static double baseLog(double x, double base) {
        return Math.log(x) / Math.log(base);
    }

    public static void main(String[] args) {
        int[] ans = solution(new long[] {7, 42, 5});
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
