package Targyalo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomController {
    private Office office = new Office();

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MeetingRoomController controller = new MeetingRoomController();
        controller.runMenu();


    }

    private void runMenu() {
        System.out.println("Üdvözöljük");

        int menuItem = 0;
        do {
            printMenu();
           try {
               menuItem = scanner.nextInt();
               scanner.nextLine();
               chooseMenuItem(menuItem);
           }catch(InputMismatchException e){
               System.out.println("Hibás adat");
               scanner.nextLine();
           }
        } while (menuItem != 9);
    }

    private void chooseMenuItem(int menuItem) {
        switch (menuItem) {

            case 1: {
                System.out.println("\nA tárgyalók neve sorrendben: ");
                printNames(office.getMeetingRooms());
                break;
            }
            case 2: {
                System.out.println("\nA tárgyalók neve visszafele sorrendben:");
                printNames(office.getMeetingRoomsinReverseOrder());
                break;
            }
            case 3: {
                System.out.println("\nAdja meg, hogy páratlan vagy páros sorszámú tárgyalókat szeretne lekérni");
                System.out.println("1.Páratlan");
                System.out.println("2.Páros");
                int oddOrEven = scanner.nextInt();
                scanner.nextLine();
                if (oddOrEven == 1) {
                    System.out.println("A páratlan számú tárgyalók:");
                }
                if (oddOrEven == 2) {
                    System.out.println("A páros számú tárgyalók:");
                }
                printNames(office.getEverySecondMeetingRoom(oddOrEven));
                break;
            }
            case 4: {
                System.out.println("\nA rendszerben található tárgyalók adatai:");
                printMeetingRooms(office.getMeetingRooms());
                break;
            }
            case 5: {

                System.out.println("\nKérem a tárgyaló nevét:");
                String name = scanner.nextLine();
                System.out.println("A kért tárgyaló adatai:");
                MeetingRoom found = office.getMeetingRoomGivenName(name);
                if (found != null) {
                    printMeetingRoom(found);
                } else {
                    System.out.println("Nincs ilyen nevű tárgyaló!");
                }
                break;
            }
            case 6: {

                System.out.println("\nKérem a név töredéket:");
                String namePart = scanner.nextLine();
                System.out.println("A kért tárgyalók névtöredék alapján:");
                printMeetingRooms(office.getMeetingRoomsWithGivenNamePart(namePart));
                break;
            }
            case 7: {
                System.out.println("\nAdja meg a területet:");
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.println("A " + number + " -nél nagyobb tárgyalók adatai");
                printMeetingRooms(office.getMeetingRoomsWithGivenAreaLargerThan(number));
                break;
            }
            case 8: {
                try {
                    System.out.println("Kérem adja meg hány tárgyalót szeretne rögzízeni!");
                    int meetingRooms = scanner.nextInt();
                    scanner.nextLine();
                    readMeetingRooms(meetingRooms);

                } catch (Exception e) {
                    System.out.println("Hibás adat");
                    scanner.nextLine();
                }
                break;
            }
            case 9: {
                System.out.println("\nViszontlátásra!");
                break;
            }
            default: {
                System.out.println("\nIsmeretlen menüpont");
            }
        }
    }

    private void printMeetingRooms(List<MeetingRoom> meetingRooms) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            printMeetingRoom(meetingRoom);
        }
    }

    private void printMeetingRoom(MeetingRoom meetingRoom) {
        System.out.println(
                "Név: " + meetingRoom.getName() + ", "
                        + "szélesség " + meetingRoom.getWidth() + " m, "
                        + "hossz: " + meetingRoom.getLength() + " m, "
                        + "terület: " + meetingRoom.getArea() + " nm2"
        );
    }

    private void printNames(List<MeetingRoom> meetingRooms) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.getName());
        }
    }

    private void printMenu() {
        System.out.println("\nKérem válasszon az alábbi menüpontokból:");
        System.out.println("\n1. Tárgyalók sorrendben\n" +
                "2. Tárgyalók visszafele sorrendben\n" +
                "3. Minden második tárgyaló\n" +
                "4. Területek\n" +
                "5. Keresés pontos név alapján\n" +
                "6. Keresés névtöredék alapján\n" +
                "7. Keresés terület alapján\n" +
                "8. Terem felvétele\n" +
                "9. Kilépés");


    }

    private void readMeetingRooms(int meetingRooms) {
        for (int i = 1; i <= meetingRooms; i++) {
           try {
               System.out.println("Adja meg a(z) " + i + ". tárgyaló adatait");
               System.out.println("Név:");
               String name = scanner.nextLine();
               System.out.println("Szélesség:");
               int width = scanner.nextInt();
               scanner.nextLine();
               System.out.println("Hossz:");
               int length = scanner.nextInt();
               scanner.nextLine();
               office.addMeetingRoom(name, width, length);
               System.out.println("A tárgyaló mentése sikeres volt\n");
           }catch (Exception e) {
               System.out.println("❌ Hiba az adatokban, próbáld újra ezt a termet!");
               scanner.nextLine();
               i--;
           }

        }
    }


}
