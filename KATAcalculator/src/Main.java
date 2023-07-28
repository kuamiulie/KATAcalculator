import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public enum RomanNumerals {
        C(100),XC(90),L(50),XL(40),X(10),IX(9),VIII(8),VI(6), VII(7),V(5),IV(4),III(3),II(2), I(1);

        private final int value;
        private RomanNumerals(int value) {
            this.value = value;
        }
        public int getInt() {
            return this.value;
        }
        public static String getRomeNum(int x) {
            String rnum = null;
            for (RomanNumerals numeral : RomanNumerals.values()) {
                if (numeral.getInt() == x)
                    rnum = String.valueOf(numeral);
            }
            return rnum;
        }
        public static String intToRoman(int num) {
            StringBuilder romanNum = new StringBuilder();
            for(RomanNumerals x :RomanNumerals.values()){
                while(num >= x.getInt()){
                    romanNum.append(x.toString());
                    num -= x.getInt();
                }
            }
            return romanNum.toString();
        }
    }
    public static String calc(String input) throws InvalidException {
        List <String> ArabNum = new ArrayList<>();
        List <String> RomeNum = new ArrayList<>();
        for (int x = 0; x <= 1000; x++) {
            ArabNum.add(String.valueOf(x));
            RomeNum.add(RomanNumerals.intToRoman(x));
        }
        String[] signs = new String[]{"+", "-", "*", "/"};
        String[] data = new String[]{};
        data = input.split("[,\\s]+");
         if (data.length!=3){
             throw new InvalidException("GIVEN EXPRESSION IS INVALID.EITHER TOO MANY CHARACTERS, OR TOO LITTLE OF THEM");
         }
        if (Arrays.asList(signs).contains(data[1])) {
            if (RomeNum.contains(data[0].toUpperCase()) & RomeNum.contains(data[2].toUpperCase())) {
                if (RomeNum.indexOf(data[0].toUpperCase()) > 10 | RomeNum.indexOf(data[2].toUpperCase()) > 10) {
                    throw new InvalidException("GIVEN NUMBER OR NUMBERS ARE GREATER THAN 10. THE NUMBER MUST BE LESS THAN OR EQUAL TO 10");
                } else {
                    String sign = data[1];
                    int firstInt = RomanNumerals.valueOf(data[0].toUpperCase()).getInt();
                    int secInt = RomanNumerals.valueOf(data[2].toUpperCase()).getInt();
                    String result = String.valueOf(RomanArithmeticalActions(firstInt, sign, secInt));
                    return result;
                }
            } else if ((RomeNum.contains(data[0].toUpperCase()) && ArabNum.contains(String.valueOf(Math.round(Float.parseFloat(data[2]))))) || (RomeNum.contains(data[2].toUpperCase()) && ArabNum.contains(String.valueOf(Math.round(Float.parseFloat(data[0])))))) {
                throw new InvalidException("GIVEN NUMBER OR NUMBERS ARE FROM DIFFERENT NUMERAL SYSTEMS");
            } else if (ArabNum.contains(String.valueOf(Math.round(Float.parseFloat(data[0])))) & ArabNum.contains(String.valueOf(Math.round(Float.parseFloat(data[2]))))) {
                if (Math.floor(Float.parseFloat(data[0])) != Float.parseFloat(data[0]) | Math.floor(Float.parseFloat(data[2])) != Float.parseFloat(data[2])) {
                    throw new InvalidException("GIVEN NUMBER OR NUMBERS AREN'T INTEGER");
                }
                int firstInt = Integer.parseInt(data[0]);
                int secInt = Integer.parseInt(data[2]);
                String sign = data[1];
                if (firstInt >10 || secInt > 10) {
                    throw new InvalidException("GIVEN NUMBER OR NUMBERS ARE GREATER THAN 10. THE NUMBER MUST BE LESS THAN OR EQUAL TO 10");
                } else {
                    int result = ArabianArithmeticalActions(firstInt, sign, secInt);
                    return String.valueOf(result);
                }
            } else {
                    throw new InvalidException("INVALID EXPRESSION WAS GIVEN");
                }
        }else {
            throw new InvalidException("INVALID SIGN WAS GIVEN");
        }
    }

    public static int ArabianArithmeticalActions(int firstInt, String sign, int secInt){
        int result =0;
        switch(sign) {
                case "+":
                    result = firstInt + secInt;
                    break;
                case "-":
                    result = firstInt - secInt;
                     break;
                case "/":
                    result = firstInt / secInt;
                    break;
                case "*":
                    result = firstInt * secInt;
                    break;

            }
            return result;
    }
    public static String RomanArithmeticalActions(int firstInt, String sign, int secInt) throws InvalidException {
        int result =0;
        switch(sign) {
            case "+":
                result = firstInt + secInt;
                break;
            case "-":
                result = firstInt - secInt;
                break;
            case "/":
                result = firstInt / secInt;
                break;
            case "*":
                result = firstInt * secInt;
                break;

        }
        if (result >= 1){
            if (result>10){
                return RomanNumerals.intToRoman(result);
            }
            else {
                return RomanNumerals.getRomeNum(result);
            }
        } else {
            throw new InvalidException("THE RESULT IS LESS THAN 1");
        }
    }






    public static void main(String[] args) throws IOException, InvalidException {
        Scanner scanner = new Scanner(System.in);
        String input = new String();
        System.out.println("Enter a mathematical expression for calculations");
        input = scanner.nextLine();
        System.out.println(calc(input));
    }

}

