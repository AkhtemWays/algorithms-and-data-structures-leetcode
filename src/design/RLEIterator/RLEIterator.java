package design.RLEIterator;

public class RLEIterator {

    public static void main(String[] args) {
        int[] ints = {3, 8, 0, 9, 2, 5};
        RLEIterator rLEIterator = new RLEIterator(ints); // This maps to the sequence [8,8,8,5,5].
        System.out.println(rLEIterator.next(2)); // exhausts 2 terms of the sequence, returning 8. The remaining sequence is now [8, 5, 5].
        System.out.println(rLEIterator.next(1)); // exhausts 1 term of the sequence, returning 8. The remaining sequence is now [5, 5].
        System.out.println(rLEIterator.next(1)); // exhausts 1 term of the sequence, returning 5. The remaining sequence is now [5].
        System.out.println(rLEIterator.next(2)); // exhausts 2 terms, returning -1. This is because the first term exhausted was 5,
    }
    private final int[] encoding;
    int iterator;
    public RLEIterator(int[] encoding) {
        int countZeros = 0;
        for (int i = 0; i < encoding.length; i+=2) {
            if (encoding[i] == 0) countZeros++;
        }
        this.encoding = new int[encoding.length - 2 * countZeros];
        for (int i = 0, k = 0; i < encoding.length; i+=2) {
            if (encoding[i] != 0) {
                this.encoding[k] = encoding[i];
                this.encoding[k+1] = encoding[i+1];
                k += 2;
            }
        }
        iterator = 0;
    }

    public int next(int n) {
        while (n > 0) {
            if (iterator >= encoding.length) {
                break;
            }
            else if (n > encoding[iterator]) {
                n -= encoding[iterator];
                encoding[iterator] = 0;
                iterator += 2;
            }
            else if (n == encoding[iterator]) {
                encoding[iterator] = 0;
                iterator += 2;
                return encoding[iterator-1];
            } else {
                encoding[iterator] -= n;
                return encoding[iterator+1];
            }
        }
        return -1;
    }
}
