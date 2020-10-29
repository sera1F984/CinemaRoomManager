import java.util.Scanner;

public class UI {
    private Scanner scan;
    private Room cinemaRoom;

    public UI(Scanner scan, Room cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
        this.scan = scan;
    }

    public void run() {
        cinemaRoom.initialCinemaRoom(); // maak daadwerkelijk de room; hier worden de boxValues 'S'

        while (true) {
            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

            int input = scan.nextInt();

            if (input == 0) {
                break;
            } else {
                switch (input) {
                    case 1:
                        cinemaRoom.printCinemaRoom();
                        continue;
                    case 2:
                        cinemaRoom.reserveSeat();
                        cinemaRoom.updatedCinemaRoom();
                        continue;
                    case 3:
                        cinemaRoom.getStatistics();
                        continue;
                    default:
                        System.out.println("Unknown command. Try again!");
                }
            }
        }
    }
}
