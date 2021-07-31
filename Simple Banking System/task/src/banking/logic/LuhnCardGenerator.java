package banking.logic;

import java.util.Arrays;
import java.util.Random;

public class LuhnCardGenerator {

    private StringBuilder cardNumber;
    private StringBuilder pinCode;
    private Random random;

    public LuhnCardGenerator() {
        this.random = new Random();
    }

    private static int luhnSum(StringBuilder cardNumber)  {
        String[] digits = cardNumber.toString().split("");
        int[] integerDigits  = new int[15];
        for (int i = 0; i < integerDigits.length; i++) {
            if (i % 2 == 0) {
                integerDigits[i] = (Integer.parseInt(digits[i]) * 2);
                if (integerDigits[i] > 9) {
                    integerDigits[i] = (Integer.parseInt(digits[i]) * 2) - 9;
                }
            } else {
                integerDigits[i] = Integer.parseInt(digits[i]);
            }
        }

        return Arrays.stream(integerDigits).sum();
    }
    public static boolean luhnNumberVerify(StringBuilder cardNumber) {
        if ( cardNumber.length() != 16) {
            return false;
        }
        int sum = luhnSum(cardNumber);
        int lastDigit = Integer.parseInt(String.valueOf(cardNumber.charAt(15)));
        return (sum + lastDigit) % 10 == 0;
    }

    public StringBuilder generateCardNumber() {
        this.cardNumber = new StringBuilder("400000");
        randomNumberGenerator(this.cardNumber, 15);
        int sum = luhnSum(this.cardNumber);

        int lastDigit = 0;
        while (true) {
            if ((sum + lastDigit) % 10 == 0) {
                this.cardNumber.append(lastDigit);
                break;
            } else {
                lastDigit++;
            }
        }

        return this.cardNumber;
    }

    public StringBuilder generatePinCode() {
        this.pinCode = new StringBuilder("");
        this.pinCode.append(this.random.nextInt(9) + 1);
        randomNumberGenerator(this.pinCode, 4);
        return this.pinCode;
    }

    public void randomNumberGenerator(StringBuilder string, int length) {
        while(string.length() < length) {
            int randomDigit = this.random.nextInt(10);
            string.append(randomDigit);
        }
    }
}
