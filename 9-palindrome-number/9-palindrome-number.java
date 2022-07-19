class Solution {
    public boolean isPalindrome(int x) {
        // edge case: negative numbers = false
        if(x < 0) return false;
        
        String s = "" + x;
        String reverse = "";
        for(int i = s.length() - 1; i >= 0; i--){
            reverse += s.charAt(i);
        }
        if(s.compareTo(reverse) == 0) return true;
        return false;
    }
}