package movieticketbookingsystem;
import java.io.*;
import java.util.List;
public class BookingFileManager {
 private final String bookingPath,customerPath;
 public BookingFileManager(String bookingPath,String customerPath){this.bookingPath=bookingPath;this.customerPath=customerPath;}
 public void saveBookings(List<Ticket> tickets){
  try(PrintWriter out=new PrintWriter(new FileWriter(bookingPath))){for(Ticket t:tickets)out.println(t);System.out.println("Bookings saved");}
  catch(IOException e){System.out.println("Save failed: "+e.getMessage());}
 }
 public void loadBookings(){printFile(bookingPath);}
 public void saveCustomers(List<Customer> customers){
  try(PrintWriter out=new PrintWriter(new FileWriter(customerPath))){for(Customer c:customers)out.println(c.getId()+","+c.getName()+","+c.getEmail());System.out.println("Customers saved");}
  catch(IOException e){System.out.println("Save failed: "+e.getMessage());}
 }
 public void loadCustomers(){printFile(customerPath);} 
 private void printFile(String path){
  try(BufferedReader in=new BufferedReader(new FileReader(path))){String line;while((line=in.readLine())!=null)System.out.println(line);}
  catch(IOException e){System.out.println("Cannot read "+path+": "+e.getMessage());}
 }
}
