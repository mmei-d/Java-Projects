import java.util.Arrays;

public class DuoDec
{
   public static char[] digit 
            = {'@', '!', '?', '&', '%', '$', '=', '^', '(', ')', '[', ']'};

   /* ==========================================================
      Return the 2's complement binary representation for the
      DuoDec number given in String s
      ========================================================== */
   public static int parseDuoDec(String s)
   {
      /* ------------------------------------------------------------------
         This loop checks if the input contains an illegal (non-DuoDec) digit
         ------------------------------------------------------------------ */
      for (int i = 0 ; i < s.length(); i++)
      {
         int j = 0;
         while ( j < digit.length )
         {
            if ( s.charAt(i) == digit[j] || s.charAt(i) == '-' )
            {
               break;
            }

            j++;
         }

         if ( j >= digit.length )
         {
            System.out.println("Illegal nano digit found in input: "
                                        + s.charAt(i) );
            System.out.println("A DuoDec digit must be one of these: "
                                + Arrays.toString (digit) );
            System.exit(1);
         }
      }

      int sign = 1;
      int pos = 0;
      int length = s.length();
      if(s.charAt(0) == '-'){
         sign = -1;
         pos = 1;
	 length--;
      }

      int[] ASCII = new int[length];

      //gets the DuoDec value for each of character in String s
      length = 0;
      for(int j = pos; j < s.length(); j++){
         int c = -1;
         for(int ind = 0; ind < digit.length; ind++){
            if(digit[ind] == s.charAt(j)){
               c = ind;
            }
	 }
         ASCII[length] = c;
         length++;
      }
      
      //calculates the total value of the string using DuoDec values
      int val = 0;
      for(int k = 0; k < length; k++){
         pos = length - 1 - k;
         int pow = 1;
         while(pos > 0){
            pow *= 12;
            pos--;
         }
         val += ASCII[k]*pow;
      }

      if(sign == -1){
	 val *= sign;
      }

      return val;
   }

   /* ==========================================================
      Return the String of DuoDec digit that represent the value
      of the 2's complement binary number given in 
      the input parameter 'value'
      ========================================================== */
   public static String toString(int value)
   {
      int sign;
      int remainder[] = new int[100];
      char duo[] = new char[100];  // Max 100 digits in number
      String result;

      //check sign of value
      if(value < 0){
         sign = -1;
         value *= -1;    // Absolute value >= 0   
      }else{
         sign = 1;
      }

      //get remainders of divisions of 12
      int k = 0;
      if(value == 0){
         remainder[k] = 0;
         k++;
      }
      while ( value > 0 ){
         remainder[k] = value % 12;
         k++;
         value = value / 12;
      }

      //convert remainder to DuoDec 
      for (int i = 0; i < k; i++){
         duo[i] = digit[remainder[i]];
      }

      //make a string from the DuoDec digits
      result = "";
      for (int i = k-1; i >= 0; i--){
         result += duo[i]; //concatenates the digits backwards
      }

      //adds "-" to string if value is negative
      if (sign == -1){
         result = "-" + result;
      }

      return result;     
   }
}




