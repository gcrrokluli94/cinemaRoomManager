class Main {
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        int len = in.nextInt();
        int iCounter = 1;
        int jCounter = 0;
        boolean stopped = false;
        for (int i = 0; i < len && !stopped; i++) {
            for (int j = 0; j <= i; j++) {
                if (jCounter == len) {
                    stopped = true;
                    break;
                }
                System.out.print(iCounter + " ");
                jCounter++;
            }
            iCounter++;
        }
    }
}