public class Application {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        houseRobber3(arr);
    }

    public static int houseRobber3(int[] nums) {
        return Math.max(houseRobber3(nums, 0, nums.length-2), houseRobber3(nums, 1, nums.length-1));
    }

    public static int houseRobber3(int[] nums, int lo, int hi) {
        int preRob = 0, preNotRob = 0, rob = 0, notRob = 0;
        for (int i = lo; i <= hi; i++) {
            rob = preNotRob + nums[i];
            notRob = Math.max(preRob, preNotRob);

            preNotRob = notRob;
            preRob = rob;
        }
        return Math.max(rob, notRob);
    }
}
