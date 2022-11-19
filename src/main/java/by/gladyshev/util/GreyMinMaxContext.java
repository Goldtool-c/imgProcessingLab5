package by.gladyshev.util;

public class GreyMinMaxContext {
    private static int max;
    private static int min;

    public static int getMax() {
        return max;
    }

    public static void setMax(int max) {
        GreyMinMaxContext.max = max;
    }

    public static int getMin() {
        return min;
    }

    public static void setMin(int min) {
        GreyMinMaxContext.min = min;
    }
}
