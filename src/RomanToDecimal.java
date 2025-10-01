import java.util.Objects;
import java.util.Scanner;

/**
 * Converts between Roman numerals and decimal numbers, and validates Roman numeral input.
 * @version 09.30.2025
 * @author @toanshdesai
 * Flint: <a href="https://app.flintk12.com/chats/82500c27-d6a8-4f03-b216-587d3d1fa538">...</a>
 */
public class RomanToDecimal {
    public static int romanToDecimal(String roman) {
        int sum=0;
        roman=roman.toUpperCase();
        for (int i = 0; i < roman.length(); i++) {
            // Need "" because charAt returns '', which cannot be stored as a string
            String ch = "" + roman.charAt(i);

            // https://youtube.com/Om3qzMoaIUo?si=pr5B1JXBYIYUpQQv
            switch (ch) {
                case "I" : sum += 1;
                break;
                case "V" : sum += 5;
                break;
                case "X" : sum += 10;
                break;
                case "L" : sum += 50;
                break;
                case "C" : sum += 100;
                break;
                case "D" : sum += 500;
                break;
                case "M" : sum += 1000;
                break;

                default : {
                    return -1;
                }
            }
        }

        if (roman.contains("IV")) sum -= 2;
        if (roman.contains("IX")) sum -= 2;
        if (roman.contains("XL")) sum -= 20;
        if (roman.contains("XC")) sum -= 20;
        if (roman.contains("CD")) sum -= 200;
        if (roman.contains("CM")) sum -= 200;

        return sum;
    }

    /**
     * Calculates the value of a specific digit in an integer, if greater than 999 then it returns the number of thousands
     * @param num the input decimal value
     * @param position the digit from right to left
     * @return the decimal value of a specific digit of num
     */
    public static int valueOfPosition(int num, int position){
        if (position>3) {
            return num / 1000;
        }
        else{
            int divisor = (int) Math.pow(10, (position - 1));
            return (num / divisor) % 10;
        }
    }

    /**
     * Assigns a roman numeral to the value of thousands(e.g. the value of thousands in 11000 is 11)
     * @param num the input decimal number
     * @return Roman numeral representation of anything greater than or equal to the thousands place
     */
    private static String thousandsOfNum(int num){
        String thousands="";
        int len=0;
        for (int i = 1; i < num; i++) {
            len+=1;
        }
        if(len>=4) {
            int numOfThousands = valueOfPosition(num, 5);
            for (int i = 1; i <= numOfThousands; i++) {
                thousands = thousands + "M";
            }
        }
        return thousands;
    }

    /**
     * Assigns a Roman numeral to the value of the hundreds place digit
     * @param num the input decimal number
     * @return Roman numeral for the value of the hundreds place
     */
    private static String hundredsOfNum(int num){
        String hundred;
        if (valueOfPosition(num,3)==1) hundred="C";
        else if (valueOfPosition(num,3)==2) hundred="CC";
        else if (valueOfPosition(num,3)==3) hundred="CCC";
        else if (valueOfPosition(num,3)==4) hundred="CD";
        else if (valueOfPosition(num,3)==5) hundred="D";
        else if (valueOfPosition(num,3)==6) hundred="DC";
        else if (valueOfPosition(num,3)==7) hundred="DCC";
        else if (valueOfPosition(num,3)==8) hundred="DCCC";
        else if (valueOfPosition(num,3)==9) hundred="CM";
        else hundred="";
        return hundred;
    }

    /**
     * Assigns a Roman numeral to the value of the tens place digit
     * @param num the input decimal number
     * @return Roman numeral for the value of the tens place
     */
    private static String tensOfNum(int num){
        String tens;
        if (valueOfPosition(num,2)==1) tens="X";
        else if (valueOfPosition(num,2)==2) tens="XX";
        else if (valueOfPosition(num,2)==3) tens="XXX";
        else if (valueOfPosition(num,2)==4) tens="XL";
        else if (valueOfPosition(num,2)==5) tens="L";
        else if (valueOfPosition(num,2)==6) tens="LX";
        else if (valueOfPosition(num,2)==7) tens="LXX";
        else if (valueOfPosition(num,2)==8) tens="LXXX";
        else if (valueOfPosition(num,2)==9) tens="XC";
        else tens="";
        return tens;
    }

    /**
     * Assigns a Roman numeral to the value of the ones place digit
     * @param num the input decimal number
     * @return Roman numeral for the value of the ones place
     */
    private static String onesOfNum(int num){
        String ones;
        if (valueOfPosition(num,1)==1) ones="I";
        else if (valueOfPosition(num,1)==2) ones="II";
        else if (valueOfPosition(num,1)==3) ones="III";
        else if (valueOfPosition(num,1)==4) ones="IV";
        else if (valueOfPosition(num,1)==5) ones="V";
        else if (valueOfPosition(num,1)==6) ones="VI";
        else if (valueOfPosition(num,1)==7) ones="VII";
        else if (valueOfPosition(num,1)==8) ones="VIII";
        else if (valueOfPosition(num,1)==9) ones="IX";
        else ones="";
        return ones;
    }

    /**
     * Converts any decimal number input into its absolute value Roman numeral
     * @param num the input decimal value
     * @return Complete roman representation of num
     */
    public static String decimalToRoman(int num){
        String roman="";
        num=Math.abs(num);

        roman+=thousandsOfNum(num);
        roman+=hundredsOfNum(num);
        roman+=tensOfNum(num);
        roman+=onesOfNum(num);

        return roman;
    }

    /**
     * Checks if the input Roman numeral is the same as a valid roman numeral for the same decimal value
     * @param roman input Roman numeral
     * @return whether the Roman numeral is valid or not
     */
    public static boolean validRoman(String roman){
        return (Objects.equals(decimalToRoman(romanToDecimal(roman)), roman.toUpperCase()));
    }

    /**
     * Main method for class RomanToDecimal
     * @param args command line arguments
     */
    public static void main(String[] args) {
        for (String s : args) {
            int decimal = romanToDecimal(s);
            // Ternary Operator: (test ? if yes : if no)
            System.out.println("Input: " + s + " => output: " + (validRoman(s) ? decimal : "invalid"));
        }

        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter a Roman numeral or decimal number(or 'end' to end the program): ");
                String input = in.nextLine().trim();

                if (input.equalsIgnoreCase("end")) break;

                try{
                    int decimal = Integer.parseInt(input.replaceAll("\\s+", ""));
                    String roman = decimalToRoman(decimal);
                    System.out.println("Input: " + input + " => output: " +roman);
                }
                catch (Exception e){
                    int decimal = romanToDecimal(input);
                    // Ternary Operator: (test ? if yes : if no)
                    System.out.println("Input: " + input + " => output: " + (validRoman(input) ? decimal : "invalid"));
                }
            }
            catch (Exception e) {break;}
        }
    }


    /*
    public static int digitsOfNum(int num){
        // Returns the amount of digits in the input number
        int count = 0;
        while (num>0){
            num/=10;
            count++;
        }
        return count;
    }

    public static boolean containsInvariant(String roman){
        String[] invariants = {"IV","IX","XL","XC","CD","CM"};
        for (String invariant : invariants){
            if (roman.toUpperCase().contains(invariant)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int I = 1;
        int V = 5;
        int X = 10;
        int L = 50;
        int C = 100;
        int D = 500;
        int M = 1000;
        int sum = 0;
        String roman = "" ;

        for(String temp : args) {
            roman = temp.toUpperCase();
            System.out.println(containsInvariant(roman));
            for (int i = 0; i < roman.length(); i++) {
                String ch = "" + temp.charAt(i);
                if (ch.equals("I")) {sum += I;}
                if (ch.equals("V")) {sum += V;}
                if (ch.equals("X")) {sum += X;}
                if (ch.equals("L")) {sum += L;}
                if (ch.equals("C")) {sum += C;}
                if (ch.equals("D")) {sum += D;}
                if (ch.equals("M")) {sum += M;}
            }
            System.out.println(sum);
            if (roman.contains("IV")){
                sum-=2;
            }
            else if (roman.contains("IX")) {
                sum-=2;
            }
            else if (roman.contains("XL")) {
                sum-=20;
            }
            else if (roman.contains("XC")) {
                sum-=20;
            }
            else if (roman.contains("CD")) {
                sum-=200;
            }
            else if (roman.contains("CM")) {
                sum-=200;
            }
            System.out.println(sum);
        }
    }
    */
}