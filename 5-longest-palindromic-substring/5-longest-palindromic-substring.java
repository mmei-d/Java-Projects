class Solution {
    public String longestPalindrome(String s) {
    
        int left = 0;
        int right = 0;
        String maxPal = "";
        
        while(left < s.length() && right < s.length()){
            if(s.charAt(left) == s.charAt(right)){
                String curStr = s.substring(left, right + 1);
                if(isPalindrome(curStr)){
                    if(curStr.length() > maxPal.length()) maxPal = curStr;
                }else if(right + 1 == s.length()){
                    left++;
                    right = left;
                }
            }else if(left + 1 != s.length() && right + 1 == s.length()){
                left++;
                right = left;
            }
            right++;
        }
        
        return maxPal;
    }
    
    boolean isPalindrome(String str){
        int left = 0;

        for(int i = str.length() - 1; i > left; i--){
            if(str.charAt(left) != str.charAt(i)) return false;
            left++;
        }
        
        return true;
    }
}