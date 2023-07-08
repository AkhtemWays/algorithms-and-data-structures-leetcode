package math.MirrorReflection;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.mirrorReflection(3, 2));
    }

    public int mirrorReflection(int p, int q) {
        int extensions = 1, reflections = 1;
        while(extensions * p != reflections * q){
            reflections++;
            extensions = reflections * q / p;
        }
        if (extensions % 2 == 0 && reflections % 2 == 1) return 0;
        if (extensions % 2 == 1 && reflections % 2 == 1) return 1;
        if (extensions % 2 == 1 && reflections % 2 == 0) return 2;
        return -1;
    }
}
