import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {
    static Scanner scan  = new Scanner(System.in);

    public static void main(String[] args) {
        UI uiCinemaRoom = new UI(scan, setRoom());
        uiCinemaRoom.run();
    }

    static Room setRoom() {
        System.out.println("Enter the number of rows:");
        int row =  scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scan.nextInt();

        if (row <= 0 || seats <= 0) { // uitproberen met throw new, dit stopt wel programma
            throw new InputMismatchException("Create a valid cinema hall by using positive numbers :D");
        } else {
            return new Room(scan, row, seats);
        }
    }
}