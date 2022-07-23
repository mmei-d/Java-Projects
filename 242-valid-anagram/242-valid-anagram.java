class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char curLetter = s.charAt(i);
            if(map.containsKey(curLetter) == false){
                map.put(curLetter, 1);
            }else{
                int count = map.get(curLetter);
                map.replace(curLetter, ++count);
            }
        }
        
        if(s.length() != t.length()) return false;
        
        for(int i = 0; i < t.length(); i++){
            char curLetter = t.charAt(i);
            if(map.containsKey(curLetter) == false){
                return false;
            }else{
                int count = map.get(curLetter);
                map.replace(curLetter, --count);
                if(map.get(curLetter) == 0){
                    map.remove(curLetter);
                }
            }
        }
        return true;
    }
}