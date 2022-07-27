class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        
        boolean[] numbers = new boolean[n];
        // consider all possible multiples of all prime numbers below n
        for (int p = 2; p <= (int)Math.sqrt(n); p++) {
            // if the number isn't marked as a multiple yet
            if (numbers[p] == false) {
                for (int j = p*p; j < n; j += p) {
                    numbers[j] = true;
                }
            }
        }
        
        // count all numbers not marked as multiples (i.e. prime numbers)
        int numberOfPrimes = 0;
        for (int i = 2; i < n; i++) {
            if (numbers[i] == false) {
                numberOfPrimes++;
            }
        }
        
        return numberOfPrimes;
    }
}