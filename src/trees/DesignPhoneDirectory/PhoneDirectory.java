package trees.DesignPhoneDirectory;

import java.util.TreeSet;

public class PhoneDirectory {
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory(3);
        System.out.println(phoneDirectory.get());      // It can return any available phone number. Here we assume it returns 0.
        System.out.println(phoneDirectory.get());      // Assume it returns 1.
        System.out.println(phoneDirectory.check(2));   // The number 2 is available, so return true.
        System.out.println(phoneDirectory.get());      // It returns 2, the only number that is left.
        System.out.println(phoneDirectory.check(2));   // The number 2 is no longer available, so return false.
        phoneDirectory.release(2); // Release number 2 back to the pool.
        System.out.println(phoneDirectory.check(2));   // Number 2 is available again, return true.
    }

    TreeSet<Integer> directory;
    public PhoneDirectory(int maxNumbers) {
        this.directory = new TreeSet<>();
        for (int i = 0; i< maxNumbers; i++) {
            directory.add(i);
        }
    }

    public int get() {
        if (this.directory.isEmpty()) return -1;
        int val = this.directory.first();
        this.directory.remove(val);
        return val;
    }

    public boolean check(int number) {
        Integer ceilingVal = this.directory.ceiling(number);
        Integer floorVal = this.directory.floor(number);
        if (ceilingVal != null && ceilingVal == number) return true;
        if (floorVal != null && floorVal == number) return true;
        return false;
    }

    public void release(int number) {
        this.directory.add(number);
    }
}
