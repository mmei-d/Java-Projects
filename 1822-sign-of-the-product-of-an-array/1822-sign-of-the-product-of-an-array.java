class Solution {
    public int arraySign(int[] nums) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return 0;
            }
            if (nums[i] < 0) {
                counter++;
            }
        }
        int result = counter % 2;
        if(result == 1){
            return -1;
        }else{
            return 1;
        }
    }
}