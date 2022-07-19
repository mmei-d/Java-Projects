class Solution {
    public int romanToInt(String s) {
        // add all roman numeral values
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        // start at beginning of s and compare two characters at once to see if left character is > or < right character
        int val = 0;
        for(int i = 0; i < s.length(); i++){
            if(i < s.length() - 1){
                // if for example IV where left numeral is < right numeral
                if(map.get(s.charAt(i)) < map.get(s.charAt(i + 1))){
                    val -= map.get(s.charAt(i));
                // if for example VI where left numeral is > right numeral
                }else if(map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))){
                    val += map.get(s.charAt(i));
                }
            }else{
               val += map.get(s.charAt(i)); 
            }
        }
        return val;
    }
}