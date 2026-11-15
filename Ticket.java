package movieticketbookingsystem;
import java.time.LocalDateTime;
public class Ticket {
 private static int counter=1000;
 private final String id,seat;private final Show show;private final Customer customer;
 private final double price;private final LocalDateTime bookedAt;
 public Ticket(Show show,Customer customer,String seat,double price){
  id="TCK"+(++counter);this.show=show;this.customer=customer;this.seat=seat;this.price=price;bookedAt=LocalDateTime.now();
 }
 public String getTicketId(){return id;}public Show getShow(){return show;}
 public Customer getCustomer(){return customer;}public String getSeatNumber(){return seat;}
 public double getPrice(){return price;}public LocalDateTime getBookingTime(){return bookedAt;}
 @Override public String toString(){return id+" | "+show.getMovie().getMovieName()+" | Show "+show.getShowID()+" | Seat "+seat+" | "+customer.getName()+" | "+price+" BDT | "+bookedAt;}
}
