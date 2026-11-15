package movieticketbookingsystem;
import java.util.HashMap;
import java.util.Map;
public class Show implements Bookable {
 private final String id,time,hall;private final Movie movie;private final int totalSeats;
 private final Map<String,Ticket> booked=new HashMap<>();
 public Show(String id,Movie movie,String time,String hall,int totalSeats){
  if(movie==null||totalSeats<=0)throw new IllegalArgumentException("Movie required and seats must be positive");
  this.id=id;this.movie=movie;this.time=time;this.hall=hall;this.totalSeats=totalSeats;
 }
 public String getShowID(){return id;}public Movie getMovie(){return movie;}
 public String getShowTime(){return time;}public String getHall(){return hall;}
 public int getTotalSeats(){return totalSeats;}public int getAvailableSeats(){return totalSeats-booked.size();}
 public Map<String,Ticket> getBookedSeats(){return new HashMap<>(booked);}
 @Override public Ticket bookSeat(String seat,Customer customer)throws SeatUnavailableException,ShowFullException{
  if(customer==null)throw new IllegalArgumentException("Customer required");
  if(seat==null||!seat.matches("[1-9][0-9]*"))throw new SeatUnavailableException("Enter a seat number from 1 to "+totalSeats);
  int number;
  try{number=Integer.parseInt(seat);}catch(NumberFormatException e){throw new SeatUnavailableException("Invalid seat number");}
  if(number>totalSeats)throw new SeatUnavailableException("Seat must be between 1 and "+totalSeats);
  seat=String.valueOf(number);
  if(booked.containsKey(seat))throw new SeatUnavailableException("Seat already booked");
  if(getAvailableSeats()==0)throw new ShowFullException("Show is full");
  Ticket ticket=new Ticket(this,customer,seat,movie.getPrice());booked.put(seat,ticket);customer.addTicket(ticket);return ticket;
 }
 @Override public Ticket cancelSeat(String seat)throws SeatNotBookedException{
  if(seat!=null&&seat.matches("[1-9][0-9]*")){
   try{seat=String.valueOf(Integer.parseInt(seat));}catch(NumberFormatException e){/* invalid seat */}
  }
  Ticket ticket=booked.remove(seat);
  if(ticket==null)throw new SeatNotBookedException("Seat was not booked");
  ticket.getCustomer().removeTicket(ticket);return ticket;
 }
 @Override public void displayAvailableSeats(){
  System.out.println("Available: "+getAvailableSeats()+"/"+totalSeats);
  for(int i=1;i<=totalSeats;i++)System.out.print(booked.containsKey(String.valueOf(i))?"["+i+":Booked] ":"["+i+":Free] ");
  System.out.println();
 }
 @Override public String toString(){return "["+id+"] "+movie.getMovieName()+" | "+time+" | "+hall+" | "+getAvailableSeats()+"/"+totalSeats+" free";}
}
