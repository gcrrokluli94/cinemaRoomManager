
class Main {
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        int a = -1;
        int b = -1;
        boolean secNumZero = false;
        boolean isOrdered = true;
        a = in.nextInt();
        b = in.nextInt();
        if (b == 0) {
            secNumZero = true;
        }
        boolean ascStarted = false;
        boolean descStarted = false;
        while (!secNumZero) {
            if (b == a) {
                a = b;
                b = in.nextInt();
                if (b == 0) {
                    break;
                }
            }
            if (b > a) {
                if (descStarted) {
                    isOrdered = false;
                    break;
                }
                ascStarted = true;
                a = b;
                b = in.nextInt();
            } else if (b < a && !ascStarted) {
                descStarted = true;
                a = b;
                b = in.nextInt();
            } else if (b < a && ascStarted) {
                isOrdered = false;
                break;
            }
            if (b == 0) {
                break;
            }
        }
        System.out.println(isOrdered);
    }
}