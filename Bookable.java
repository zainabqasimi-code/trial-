
package movieticketbookingsystem;

public interface Bookable {

    Ticket bookSeat(String seat, Customer customer)
            throws SeatUnavailableException, ShowFullException;

    Ticket cancelSeat(String seat)
            throws SeatNotBookedException;

    void displayAvailableSeats();
}

