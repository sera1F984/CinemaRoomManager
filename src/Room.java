import java.util.Scanner;

public class Room {
    private Scanner scan;
    private String[][] cinema;
    private int row;
    private int seats;
    private int totalSeats;

    private int reserveRow;
    private int reserveSeat;

    private int ticketCount;
    private int currentIncome;
    private int totalIncome;

    public Room(Scanner scan, int row, int seats) {
        this.scan = scan;
        cinema = new String[row][seats];
        this.row = row;
        this.seats = seats;
        totalSeats = row * seats;

        reserveRow = 0;
        reserveSeat = 0;

        ticketCount = 0;
        currentIncome = 0;
        totalIncome = 0;
    }

    protected void initialCinemaRoom() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    protected void updatedCinemaRoom() {
        cinema[reserveRow - 1][reserveSeat - 1] = "B"; // -1 want een array begint bij 0
    }

    public void printCinemaRoom() {
        System.out.println("Cinema: ");
        System.out.print("  ");
        for (int i = 0; i < seats; i++) {
            System.out.print(i + 1 + " "); // nummering bovenaan
        }

        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i + 1 + " "); // nummering links
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    protected int totalIncome() {
        int frontRows = row / 2; // decimalen achter de komma worden er af gechopt, er wordt niet speciaal afgerond als ie 'int' gedeclareerd is
        int backRows = row - frontRows;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = (frontRows * seats * 10) + (backRows * seats * 8);
        }
        return totalIncome;
    }

    protected void reserveSeat() {
        try {
            System.out.println("Enter a row number:");
            this.reserveRow = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            this.reserveSeat = scan.nextInt();

            if (isBooked()) {
                System.out.println("That ticket has already been purchased!");
                reserveSeat();
            } else {
                Ticket ticket = new Ticket();
                ticket.getReservedSeatPrice();
                currentIncome += ticket.getTicketPrice();
                ticketCount++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input. Try existing row and seat numbers.");
            reserveSeat();
        }
    }

    private boolean isBooked() { return cinema[reserveRow - 1][reserveSeat - 1].equals("B"); }

    private double getPercentage() {
        return (double) currentIncome / (double) totalIncome() * 100;
    }

    protected void getStatistics() {
        System.out.println("Number of purchased tickets: " + ticketCount);
        System.out.println("Percentage: " + String.format("%.2f", getPercentage()) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome());
    }

    class Ticket {
        private int ticketPrice;

        Ticket() {
            ticketPrice = 0;
        }

        public int getTicketPrice() {
            return ticketPrice;
        }

        public void getReservedSeatPrice() {
            if (totalSeats <= 60) {
                ticketPrice = 10;
                System.out.println("Ticket price: $10");
            } else {
                if (reserveRow <= (row / 2)) { // row/2 rond naar beneden af!
                    ticketPrice = 10;
                    System.out.println("Ticket price: $10");
                } else {
                    ticketPrice = 8;
                    System.out.println("Ticket price: $8");
                }
            }
        }
    }
}
