package cinema;

import java.util.Scanner;

import static java.lang.System.out;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        out.println("Enter the number of rows:");

        int rows = sc.nextInt();
        out.println("Enter the number of seats in each row:");
        int seatsPerRow = sc.nextInt();

        int rowNum = 0, seatNum = 0, counter = 0;
        char[][] cinema = getArray(rows, seatsPerRow);
        Menu(cinema, rows, seatsPerRow, rowNum, seatNum, counter);
        //System.exit(0);
    }
    public static void Menu(char[][] cinema,int rows, int seatsPerRow, int rowNum, int seatNum, int counter) {
        Scanner sc = new Scanner(System.in);
        do {
            out.println("1. Show the seats");
            out.println("2. Buy a ticket");
            out.println("3. Statistics");
            out.println("0. Exit");
            switch (sc.nextInt()) {
                case 1: display(cinema,rowNum,seatNum);
                Menu(cinema, rows, seatsPerRow, rowNum, seatNum, counter);
                    break;
                case 2:
                    out.println("Enter a row number:");
                    rowNum = sc.nextInt();
                    out.println("Enter a seat number in that row:");
                    seatNum = sc.nextInt();
                    if (seatNum > cinema[0].length - 1 || rowNum > cinema.length - 1) {
                        do{
                            out.println("Wrong input!");
                            out.println("Enter a row number:");
                            rowNum = sc.nextInt();
                            out.println("Enter a seat number in that row:");
                            seatNum = sc.nextInt();
                        } while (seatNum > cinema[0].length - 1 || rowNum > cinema.length - 1);
                    } else {
                        if (alreadyPurchased(cinema, rowNum, seatNum)) {
                            do{
                                out.println("That ticket has already been purchased!");
                                out.println("Enter a row number:");
                                rowNum = sc.nextInt();
                                out.println("Enter a seat number in that row:");
                                seatNum = sc.nextInt();
                            } while (alreadyPurchased(cinema, rowNum, seatNum));
                        }
                        char[][] booked = booking(cinema,rowNum,seatNum);
                        out.println("Ticket price: $" + calculateTicketPrice(rows,seatsPerRow,rowNum));
                        counter++;
                        Menu(booked, rows, seatsPerRow, rowNum ,seatNum, counter);
                    }
                    break;
                case 3:
                    Statistics(rows, seatsPerRow, rowNum, counter);
                    Menu(cinema, rows, seatsPerRow, rowNum, seatNum, counter);
                    break;
                case 0:
                    return;
            }
        } while (sc.nextInt() != 0);
    }
    public static char[][] getArray(int rows, int seatsPerRow) {
        int row, col;
        row = rows;
        col = seatsPerRow;
        row++;
        col++;
        char[][] cinema = new char[row][col];
        return cinema;
    }
    public static void display(char[][] cinema, int rowNum, int seatNum) {
        char[][] booked = booking(cinema, rowNum, seatNum);
            out.println("Cinema:");
            for (int i = 0; i < booked.length; i++) {
                for (int j = 0; j < booked[i].length; j++) {
                    out.print(booked[i][j] + " ");
                }
                out.println();
            }
        }
    public static char[][] booking(char[][] cinema, int rowNum, int seatNum) {
        char numCharI = 48;//Unicode --> char '0'
        char numCharJ = 48;

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[0][0] = ' ';
                if (rowNum > 0 && seatNum > 0) {
                    cinema[rowNum][seatNum] = 'B';
                }
                if (cinema[i][j] == cinema[0][j]) {
                    cinema[i][j] = numCharI++;
                }
                if (cinema[i][j] == cinema[i][0]) {
                    cinema[i][j] = numCharJ++;
                } else if (cinema[i][j] != cinema[0][j] && cinema[i][j] != cinema[i][0]
                        && cinema[i][j] != cinema[rowNum][seatNum]) {
                    cinema[i][j] = 'S';
                }
            }
        }
        return cinema;
    }
    public static boolean alreadyPurchased(char[][] cinema, int rowNum, int seatNum) {
        boolean isAlreadyPurchased = false;

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length && !isAlreadyPurchased; j++) {
                if (cinema[rowNum][seatNum] == 'B') {
                    isAlreadyPurchased = true;
                    break;
                }
            }
        }
        return isAlreadyPurchased;
    }
    public static int calculateTicketPrice(int rows, int seatsPerRow, int rowNum) {
        int frontHalf = 0, ticketPrice = 0;

        if (rows % 2 != 0 && rows * seatsPerRow > 60) {
            frontHalf = rows / 2;
            if (rowNum > frontHalf) {
                return ticketPrice = 8;
            } else {
                return ticketPrice = 10;
            }
        } else if (rows % 2 == 0 && rows * seatsPerRow > 60) {
            frontHalf = rows / 2;
            if (rowNum > frontHalf) {
                return ticketPrice = 8;
            } else {
                return ticketPrice = 10;
            }
        } else if (rows * seatsPerRow <= 60) {
            return ticketPrice = 10;
        }
        return ticketPrice;
    }
    public static int calculateTotalIncome(int rows, int seatsPerRow) {
        int ticketPrice = 0;
        int totalIncome = 0;
        int frontHalf = 0;
        int backHalf = 0;

        if (rows % 2 != 0 && rows * seatsPerRow > 60) {
            frontHalf = rows / 2;
            backHalf = (rows % 2) + frontHalf;
            return totalIncome = (frontHalf * seatsPerRow * 10) + (backHalf * seatsPerRow * 8);
        } else if (rows % 2 == 0 && rows * seatsPerRow > 60) {
            frontHalf = rows / 2;
            backHalf = frontHalf;
            return totalIncome = (frontHalf * seatsPerRow * 10) + (backHalf * seatsPerRow * 8);
        } else if (rows * seatsPerRow <= 60) {
            ticketPrice = 10;
            return totalIncome = (rows * seatsPerRow) * ticketPrice;
        }
        return totalIncome;
    }
    public static void Statistics(int rows, int seatsPerRow, int rowNum, int counter) {
        final double PERCENT = 100.0;
        int numberOfPurchasedTickets = counter;

        int ticketPrice = calculateTicketPrice(rows,seatsPerRow,rowNum);
        int totalIncome = calculateTotalIncome(rows, seatsPerRow);
        double percentage = (numberOfPurchasedTickets / (double) (rows * seatsPerRow)) * PERCENT;

        int currentIncome = numberOfPurchasedTickets * ticketPrice;

        out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        out.printf("Percentage: %.2f%%", percentage);
        out.println("\nCurrent income: $" + currentIncome);
        out.println("Total income: $" + totalIncome);
    }
}

