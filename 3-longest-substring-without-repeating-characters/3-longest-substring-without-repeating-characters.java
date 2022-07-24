class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int curLength = 0;
        for(int i = 0; i < s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), i);
                curLength++;
            }else{
                i = map.get(s.charAt(i)) + 1;
                map.clear();
                map.put(s.charAt(i), i);
                curLength = 1;
            }
            maxLength = Math.max(curLength, maxLength);
        }
        return maxLength;
    }
}