class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        String result = "";
        int space = s.length();
        int end = s.length();
        for(int i = s.length() - 1; i >= 0; i--){
            if((s.charAt(i) == ' ' && (i == s.length() - 1 || s.charAt(i+1) != ' ') || i == 0)){
                space = i;
                if(i != 0){
                    while(space + 1 < end){
                        result += s.charAt(space + 1);
                        space++;
                    }
                    result += " ";
                }else{
                    while(space < end){
                        result += s.charAt(space);
                        space++;
                    }
                }
                end = i;
                while(end > 0 && s.charAt(end - 1) == ' '){
                    end--;
                }
            }
        }
        return result;
    }
}