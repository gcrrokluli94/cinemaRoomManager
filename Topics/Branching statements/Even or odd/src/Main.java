class Main {
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        int number;
        do {
            number = in.nextInt();
            if (number == 0) {
                break;
            }
            if (number % 2 == 0) {
                System.out.println("even");
            } else
                System.out.println("odd");
        } while (number != 0);

    }
}