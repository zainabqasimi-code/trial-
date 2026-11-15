package movieticketbookingsystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BookingSystem {
 private final ArrayList<Movie> movies=new ArrayList<>();
 private final ArrayList<Show> shows=new ArrayList<>();
 private final Map<String,Show> showMap=new HashMap<>();
 private final ArrayList<Customer> customers=new ArrayList<>();
 private final Map<String,Customer> customerMap=new HashMap<>();
 private final ArrayList<Ticket> tickets=new ArrayList<>();
 public boolean addMovie(Movie movie){if(movie==null||findMovieById(movie.getMovieId())!=null)return false;movies.add(movie);return true;}
 public boolean addShow(Show show){if(show==null||showMap.containsKey(show.getShowID()))return false;shows.add(show);showMap.put(show.getShowID(),show);return true;}
 public boolean addCustomer(Customer c){if(c==null||customerMap.containsKey(c.getId()))return false;customers.add(c);customerMap.put(c.getId(),c);return true;}
 public Movie findMovieById(String id){for(Movie m:movies)if(m.getMovieId().equals(id))return m;return null;}
 public Show findShowById(String id){return showMap.get(id);}
 public Customer findCustomerById(String id){return customerMap.get(id);}
 public List<Movie> getMovies(){return new ArrayList<>(movies);}
 public List<Show> getShows(){return new ArrayList<>(shows);}
 public List<Customer> getCustomers(){return new ArrayList<>(customers);}
 public List<Ticket> getAllTickets(){return new ArrayList<>(tickets);}
 public Ticket bookTicket(String showId,String customerId,String seat)throws SeatUnavailableException,ShowFullException{
  Show show=findShowById(showId);Customer customer=findCustomerById(customerId);
  if(show==null||customer==null){System.out.println("Show or customer ID not found");return null;}
  Ticket ticket=show.bookSeat(seat,customer);tickets.add(ticket);return ticket;
 }
 public Ticket cancelTicket(String showId,String seat)throws SeatNotBookedException{
  Show show=findShowById(showId);if(show==null){System.out.println("Show not found");return null;}
  Ticket ticket=show.cancelSeat(seat);tickets.remove(ticket);return ticket;
 }

public boolean deleteMovie(String movieId) {

    Movie movie = findMovieById(movieId);

    if (movie == null) {
        return false;
    }

    // Do not delete a movie that has scheduled shows.
    for (Show show : getShows()) {
        if (show.getMovie().getMovieId().equals(movieId)) {
            throw new IllegalStateException(
                "Cannot delete this movie because it has scheduled shows."
            );
        }
    }

    return getMovies().remove(movie);
}

}
