class Solution {
    public boolean containsDuplicate(int[] nums) {
        // Map<Integer, Integer> map = new HashMap<>(nums.length);
        // for(int i = 0; i < nums.length; i++){
        //     if(map.containsValue(nums[i])){
        //         return true;
        //     }
        //     map.put(i, nums[i]);
        // }
        // return false;
        
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}