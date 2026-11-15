package movieticketbookingsystem;
import java.util.Scanner;
public class main {
 private static final Scanner input=new Scanner(System.in);
 private static final BookingSystem system=new BookingSystem();
 public static BookingSystem getSystem() {
	    return system;
	}

	public static void loadStarterData() {
	    if (system.getMovies().isEmpty()) {
	        starterData();
	    }
	}
 private static final BookingFileManager files=new BookingFileManager("bookings.txt","customers.txt");
 private static final Admin admin=new Admin("A01","Front Desk","admin@cinema.com","admin123");
 public static void main(String[] args){
  starterData();
  while(true){
   System.out.println("\n1. Customer  2. Admin  3. Exit");int role=number("Choose: ");
   if(role==3){System.out.println("Goodbye!");return;}
   if(role!=1&&role!=2){System.out.println("Invalid role");continue;}
   boolean isAdmin=role==2;
   if(isAdmin&&!admin.checkPassword(read("Admin password: "))){System.out.println("Wrong password");continue;}
   while(true){
    menu(isAdmin);int choice=number("Choose: ");
    if(choice==(isAdmin?17:9)){System.out.println("Logged out");break;}
    if(choice<1||choice>(isAdmin?17:9)){System.out.println("Invalid choice");continue;}
    try{action(choice,isAdmin);}catch(SeatUnavailableException|ShowFullException|SeatNotBookedException|IllegalArgumentException e){System.out.println("Error: "+e.getMessage());}
   }
  }
 }
 private static String read(String prompt){System.out.print(prompt);return input.nextLine().trim();}
 private static int number(String prompt){try{return Integer.parseInt(read(prompt));}catch(NumberFormatException e){System.out.println("Enter a whole number");return -1;}}
 private static double decimal(String prompt){try{return Double.parseDouble(read(prompt));}catch(NumberFormatException e){System.out.println("Enter a number");return -1;}}
 private static void menu(boolean a){
  System.out.println("\n=== MOVIE TICKET BOOKING ===");
  System.out.println("1.View Movies  2.View Shows  3.Book Ticket  4.Cancel Ticket");
  System.out.println("5.Search Movie  6.Check Seats  7.Booking History  8.Register Customer");
  if(a)System.out.println("9.Add Movie  10.Add Show  11.Total Movies  12.Total Bookings\n13.Save Bookings  14.Read Saved Bookings  15.Save Customers  16.Read Saved Customers  17.Log Out");
  else System.out.println("9.Log Out");
 }
 private static void action(int choice,boolean a)throws SeatUnavailableException,ShowFullException,SeatNotBookedException{
  switch(choice){
   case 1:for(Movie m:system.getMovies())m.displayDetails();break;
   case 2:for(Show s:system.getShows())System.out.println(s);break;
   case 3:{String show=read("Show ID: "),customer=read("Customer ID: "),seat=read("Seat number: ");Ticket t=system.bookTicket(show,customer,seat);if(t!=null)System.out.println("Booked: "+t);break;}
   case 4:{Ticket t=system.cancelTicket(read("Show ID: "),read("Seat number: "));if(t!=null)System.out.println("Cancelled: "+t);break;}
   case 5:{String name=read("Movie name: ").toLowerCase();boolean found=false;for(Movie m:system.getMovies())if(m.getMovieName().toLowerCase().contains(name)){m.displayDetails();found=true;}if(!found)System.out.println("Not found");break;}
   case 6:{Show s=system.findShowById(read("Show ID: "));if(s==null)System.out.println("Show not found");else s.displayAvailableSeats();break;}
   case 7:{Customer c=system.findCustomerById(read("Customer ID: "));if(c==null)System.out.println("Customer not found");else if(c.getBookingHistory().isEmpty())System.out.println("No bookings");else for(Ticket t:c.getBookingHistory())System.out.println(t);break;}
   case 8:{Customer c=new Customer(read("Customer ID: "),read("Name: "),read("Email: "));System.out.println(system.addCustomer(c)?"Customer added":"Customer ID already exists");break;}
   case 9:if(a)addMovie();break;
   case 10:if(a)addShow();break;
   case 11:if(a)System.out.println("Total movies: "+system.getMovies().size());break;
   case 12:if(a)System.out.println("Total bookings: "+system.getAllTickets().size());break;
   case 13:if(a)files.saveBookings(system.getAllTickets());break;
   case 14:if(a)files.loadBookings();break;
   case 15:if(a)files.saveCustomers(system.getCustomers());break;
   case 16:if(a)files.loadCustomers();break;
   default:System.out.println("Invalid choice");
  }
 }
 private static void addMovie(){
  String id=read("Movie ID: "),name=read("Name: "),genre=read("Genre: "),language=read("Language: ");
  double duration=decimal("Duration in minutes: ");if(duration<=0){System.out.println("Invalid duration");return;}
  int type=number("1.Regular  2.Premium: ");Movie movie;
  if(type==1){RegularMovie.AgeRating[] values=RegularMovie.AgeRating.values();for(int i=0;i<values.length;i++)System.out.println((i+1)+"."+values[i]);int n=number("Rating: ");if(n<1||n>values.length){System.out.println("Invalid rating");return;}movie=new RegularMovie(id,name,genre,language,duration,values[n-1]);}
  else if(type==2){PremiumMovie.ScreenType[] values=PremiumMovie.ScreenType.values();for(int i=0;i<values.length;i++)System.out.println((i+1)+"."+values[i]);int n=number("Screen: ");if(n<1||n>values.length){System.out.println("Invalid screen");return;}movie=new PremiumMovie(id,name,genre,language,duration,values[n-1]);}
  else{System.out.println("Invalid type");return;}
  System.out.println(system.addMovie(movie)?"Movie added":"Movie ID already exists");
 }
 private static void addShow(){
  Movie m=system.findMovieById(read("Movie ID: "));if(m==null){System.out.println("Movie not found");return;}
  String id=read("Show ID: "),time=read("Time: "),hall=read("Hall: ");int seats=number("Number of seats: ");
  if(seats<=0){System.out.println("Invalid seat count");return;}
  System.out.println(system.addShow(new Show(id,m,time,hall,seats))?"Show added":"Show ID already exists");
 }
 private static void starterData(){
  Movie a=new RegularMovie("M01","The Silent Horizon","Sci-Fi","English",128,RegularMovie.AgeRating.PG13);
  Movie b=new PremiumMovie("M02","Ironclad: New Dawn","Action","English",132,PremiumMovie.ScreenType.IMAX);
  Movie c=new RegularMovie("M03","Autumn Letters","Drama","English",105,RegularMovie.AgeRating.PG);
  Movie d=new PremiumMovie("M04","Midnight Circuit","Thriller","English",118,PremiumMovie.ScreenType.DOLBY_ATMOS);
  Movie e=new RegularMovie("M05","Comet's Edge","Sci-Fi","English",132,RegularMovie.AgeRating.PG13);
  Movie f=new PremiumMovie("M06","The Wanderer's Voyage","Adventure","English",155,PremiumMovie.ScreenType.IMAX);
  Movie g=new RegularMovie("M07","Crimson Alley","Crime","English",121,RegularMovie.AgeRating.R);
  Movie h=new PremiumMovie("M08","Skyweb Guardian","Superhero","English",140,PremiumMovie.ScreenType.FOUR_DX);
  Movie i=new RegularMovie("M09","Paper Moons","Animation","English",98,RegularMovie.AgeRating.G);
  Movie j=new RegularMovie("M10","The Last Ember","Fantasy","English",142,RegularMovie.AgeRating.PG13);
  for(Movie m:new Movie[]{a,b,c,d,e,f,g,h,i,j})system.addMovie(m);
  system.addShow(new Show("S01",a,"6:00 PM","Hall A",5));system.addShow(new Show("S02",b,"9:00 PM","Hall B",3));
  system.addShow(new Show("S03",c,"5:30 PM","Hall A",4));system.addShow(new Show("S04",d,"8:00 PM","Hall C",3));
  system.addShow(new Show("S05",e,"7:15 PM","Hall B",5));system.addShow(new Show("S06",f,"6:45 PM","Hall D",6));
  system.addShow(new Show("S07",g,"10:00 PM","Hall C",4));system.addShow(new Show("S08",h,"4:30 PM","Hall D",6));
  system.addShow(new Show("S09",h,"8:30 PM","Hall D",6));system.addShow(new Show("0",i,"3:00 PM","Hall A",5));
  system.addShow(new Show("S11",j,"7:00 PM","Hall B",4));
  system.addCustomer(new Customer("C101","Ayesha Rahman","ayesha@example.com"));
  system.addCustomer(new Customer("C102","Tanvir Hasan","tanvir@example.com"));
  system.addCustomer(new Customer("C103","Nadia Islam","nadia@example.com"));
  system.addCustomer(new Customer("C104","Rafiq Chowdhury","rafiq@example.com"));
  system.addCustomer(new Customer("C105","Mahin Karim","mahin@example.com"));
  system.addCustomer(new Customer("C106","Farah Sultana","farah@example.com"));
  System.out.println("Starter data loaded: 10 movies, 11 shows, 6 customers.");
 }
}
