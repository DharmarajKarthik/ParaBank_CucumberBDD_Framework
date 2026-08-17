package Zprac;

public class InterPrac {

    // Reverse a string

    public void Reverword(){

        String word = "Dharmaraj";
        String reversed = "";

        for(int i = word.length()-1; i>=0;i--){
          reversed = reversed + word.charAt(i);
        }
        System.out.println(reversed);

        // Palindrome
        System.out.println(word.equals(reversed));
    }

    // fibonacci series
    public void fibonacci(){

        int n = 10;
        int num1 = 0;
        int num2 = 1;

        for(int i = 2; i<=n; i++){
           int num3 = num1 + num2;
            System.out.println(" "+num3);
            num1 = num2;
            num2 = num3;
        }
    }

    //factorial
    public void factorial(){
        int num = 5;
        int fact = 1;

        for (int i = 1; i<=num; i++ ){
            fact *= i;
        }
        System.out.println(fact);
    }

    // sum of digits of given number
    public void sumofdigits(){
        int sum = 0;
        int num =369963;
        int rem;
        while(num!=0){
            rem = num%10;
            sum = sum + rem;
            num = num/10;
        }
        System.out.println(sum);
    }

    // find duplicate in given string
    public void stringdupe(){

        String string = "Dharmaraj";
        char[] chars = string.toCharArray();

        for(int i=0; i <= chars.length ; i++){
            int count = 1;
            for(int j = i + 1; j < chars.length; j++ ){
               if( chars[i] == chars[j]){
                   count ++;
                   chars[j]=0;
               }
                if (count > 1 && chars[i] != '0') {
                    System.out.println(chars[i] + " -> " + count);
                }
            }
        }
    }

    public static void main(String[] args){

    }
}
