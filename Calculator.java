public class Calculator 
{
    //number extraction: whole number
    public static String extractWholeNumber(String number)
    {
        int length = number.length();
        int dot = 0;
        boolean decimal = false;
        //check for decimal and find the dot
        for (int i = 0; i < length; i++)
        {
            if (number.charAt(i) == '.')
            {
                dot = i;
                decimal = true;
            }
        }
        //return the number after extraction
        if (decimal && dot != 0)
        {
            return number.substring(0, dot);
        }
        else if (decimal && dot == 0)
        {
            return "";
        }
        else
        {
            return number;
        }
    }

    //number extraction: decimals
    public static String extractDecimal(String number)
    {
        int length = number.length();
        int dot = 0;
        boolean decimal = false;
        //check for decimal and find the dot
        for (int i = 0; i < length; i++)
        {
            if (number.charAt(i) == '.')
            {
                dot = i;
                decimal = true;
            }
        }
        if (decimal && dot != (length-1))
        {
            return number.substring(dot+1);
        }
        else
        {
            return "";
        }
    }

    //alignment and formatting: prepend zeros
    public static String prependZeros(String number, int numZeros)
    {
        if (numZeros < 0)
        {
            return number;
        }
        else
        {
            for (int i = 0; i < numZeros; i++)
            {
                number = "0" + number;
            }
            return number;
        }
    }

    //alignment and formatting: append zeros
    public static String appendZeros(String number, int numZeros)
    {
        if (numZeros < 0)
        {
            return number;
        }
        else
        {
            for (int i = 0; i < numZeros; i++)
            {
                number = number + "0";
            }
            return number;
        }
    }

    //alignment and formatting: format result
    public static String formatResult(String number)
    {
        int length = number.length();
        int dot = -1;
        boolean decimal = false;
        //check for decimal and find the dot
        for (int i = 0; i < length; i++)
        {
            if (number.charAt(i) == '.')
            {
                dot = i;
                decimal = true;
                break;
            }
        }
        //add .0 for whole numbers
        if (!decimal)
        {
            number = number + ".0";
            dot = number.length() - 2;
        }
        //add 0 before . if applicable
        if (dot == 0)
        {
            number = "0" + number;
            dot = 1;
        }
        //removing leading zeros
        int index1 = 0;
        while (index1 < dot - 1 && number.charAt(index1) == '0')
        {
            index1++;
        }
        number = number.substring(index1);
        dot = number.indexOf('.');
        //removing trailing zeros
        int index2 = number.length() - 1;
        while (index2 > dot + 1 && number.charAt(index2) == '0')
        {
            index2--;
        }
        number = number.substring(0, index2 + 1);
        //add a zero for numbers ending with .
        if (number.charAt(number.length() - 1) == '.')
        {
            number = number + "0";
        }
        return number;
    }

    //single digit adder add digits
    public static char addDigits(char firstDigit, char secondDigit, boolean carryIn)
    {
        //convert char to int
        int a = firstDigit - '0';
        int b = secondDigit - '0';
        int result;
        if (carryIn)
        {
            result = a + b + 1;
        }
        else
        {
            result = a + b;
        }
        //convert back to char
        return (char)(result % 10 + '0');
    }

    //single digit adder carry out
    public static boolean carryOut(char firstDigit, char secondDigit, boolean carryIn)
    {
        //convert char to int
        int a = firstDigit - '0';
        int b = secondDigit - '0';
        int result;
        if (carryIn)
        {
            result = a + b + 1;
        }
        else
        {
            result = a + b;
        }
        //check carryin
        if (result == result % 10)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //calculation: add
    public static String add(String firstNumber, String secondNumber)
    {
        //format the numbers first
        firstNumber = formatResult(firstNumber);
        secondNumber = formatResult(secondNumber);
        //find the location of dots
        int dot1 = firstNumber.indexOf(".");
        int dot2 = secondNumber.indexOf(".");
        //add zeros before dots
        while (dot1 < dot2)
        {
            firstNumber = "0" + firstNumber;
            dot1++;
        }
        while (dot1 > dot2)
        {
            secondNumber = "0" + secondNumber;
            dot2++;
        }
        //add zeros after dots
        int decimal1 = firstNumber.length() - dot1 - 1;
        int decimal2 = secondNumber.length() - dot2 - 1;
        while (decimal1 < decimal2)
        {
            firstNumber = firstNumber + "0";
            decimal1++;
        }
        while (decimal1 > decimal2)
        {
            secondNumber = secondNumber + "0";
            decimal2++;
        }
        StringBuilder result = new StringBuilder();
        boolean carry = false;
        for (int i = firstNumber.length() - 1; i >= 0; i--) 
        {
            char a = firstNumber.charAt(i);
            char b = secondNumber.charAt(i);
            if (a == '.') 
            {
                result.insert(0, '.');
                continue;
            }
            char digit = addDigits(a, b, carry);
            carry = carryOut(a, b, carry);
            result.insert(0, digit);
        }
        if (carry)
        {
            result.insert(0, '1');
        }
        //return final result
        return formatResult(result.toString());
    }

    //calculation: multiply
    public static String multiply(String number, int numTimes)
    {
        if (numTimes <= 0)
        {
            return "0.0";
        }
        String result = formatResult(number);
        //multiply - adding numTimes times
        for (int i = 1; i < numTimes; i++) 
        {
            result = add(result, number);
        }
        return result;
    }

    /*
    public static void main(String[] args) 
    {
        System.out.println(Calculator.multiply("10", -1));
        System.out.println(Calculator.add("4.02", "0.0050")); // should print 4.025
        System.out.println(Calculator.add("4.02", ".005"));   // should print 4.025
        System.out.println(Calculator.add("100", "200"));     // should print 300.0
        System.out.println(Calculator.multiply("100", 3));    // should print 300.0
    }
    */
}
