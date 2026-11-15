package movieticketbookingsystem;
import java.util.ArrayList;
import java.util.List;
public class Customer extends Person {
 private final ArrayList<Ticket> history=new ArrayList<>();
 public Customer(String id,String name,String email){super(id,name,email);}
 @Override public String getRole(){return "Customer";}
 public void addTicket(Ticket t){history.add(t);}public void removeTicket(Ticket t){history.remove(t);}
 public List<Ticket> getBookingHistory(){return new ArrayList<>(history);}
}
