package movieticketbookingsystem;
public class Admin extends Person {
 private final String password;
 public Admin(String id,String name,String email,String password){super(id,name,email);this.password=password;}
 public boolean checkPassword(String attempt){return password.equals(attempt);}
 @Override public String getRole(){return "Admin";}
}
