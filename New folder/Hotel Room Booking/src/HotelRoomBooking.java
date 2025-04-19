import java.util.*;

abstract class HotelEntity {
    private int id;

    public HotelEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract String displayInfo();
}

class Room extends HotelEntity {
    private boolean isBooked;

    public Room(int number) {
        super(number); // Room number as ID
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }

    @Override
    public String displayInfo() {
        return "Room " + getId() + " - " + (isBooked ? "Booked" : "Available");
    }

    @Override
    public String toString() {
        return displayInfo();
    }
}

class Booking extends HotelEntity {
    private String customerName;

    public Booking(int roomNumber, String customerName) {
        super(roomNumber);
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String displayInfo() {
        return "Room " + getId() + " booked by " + customerName;
    }

    @Override
    public String toString() {
        return displayInfo();
    }
}

public class HotelRoomBooking {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create 5 rooms
        for (int i = 1; i <= 5; i++) {
            rooms.add(new Room(i));
        }

        while (true) {
            System.out.println("\n ::::::: Hotel Booking Menu ::::::::");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Show All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNum = sc.nextInt();
                    bookRoom(roomNum, name);
                    break;
                case 3:
                    System.out.print("Enter room number to cancel: ");
                    int cancelNum = sc.nextInt();
                    cancelBooking(cancelNum);
                    break;
                case 4:
                    showBookings();
                    break;
                case 5:
                    System.out.println("Exiting. Thanks!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void showAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room r : rooms) {
            if (!r.isBooked()) {
                System.out.println(r);
            }
        }
    }

    static void bookRoom(int roomNum, String name) {
        for (Room r : rooms) {
            if (r.getId() == roomNum && !r.isBooked()) {
                r.setBooked(true);
                bookings.add(new Booking(roomNum, name));
                System.out.println("Room booked successfully!");
                return;
            }
        }
        System.out.println("Room is already booked or does not exist.");
    }

    static void cancelBooking(int roomNum) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getId() == roomNum) {
                bookings.remove(i);
                for (Room r : rooms) {
                    if (r.getId() == roomNum) {
                        r.setBooked(false);
                        break;
                    }
                }
                System.out.println("Booking cancelled.");
                return;
            }
        }
        System.out.println("No booking found for that room.");
    }

    static void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            System.out.println("Current Bookings:");
            for (Booking b : bookings) {
                System.out.println(b);
            }
        }
    }
}  