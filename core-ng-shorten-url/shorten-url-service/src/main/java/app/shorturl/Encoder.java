package app.shorturl;

import java.text.MessageFormat;

/**
 * @author Kam
 */
public class Encoder {
    public static final int MAX_LENGTH = 3;

    private final char[] availableChars = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public final long maximumNumber;

    public Encoder() {
        this.maximumNumber = (long) Math.pow(availableChars.length, MAX_LENGTH) - 1;
    }

    public String encode(long counter) {
        if (counter > maximumNumber || counter < 0) {
            throw new IllegalArgumentException(MessageFormat.format("number is larger than threshold, maximum number={0}, minimum number=0, num={1}", maximumNumber, counter));
        }

        char[] code = new char[MAX_LENGTH];

        long number = counter;
        for (int i = MAX_LENGTH - 1; i > -1; i--) {
            if (number > 0) {
                int value = (int) (number % availableChars.length);
                number = number / availableChars.length;
                code[i] = availableChars[value];
            } else {
                code[i] = availableChars[0];
            }
        }

        return String.valueOf(code);
    }

}
