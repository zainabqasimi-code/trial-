package movieticketbookingsystem;


public abstract class Person {
 private final String id,name;private String email;
 public Person(String id,String name,String email){this.id=id;this.name=name;setEmail(email);}
 public String getId(){return id;}public String getName(){return name;}public String getEmail(){return email;}
 public void setEmail(String email){if(email==null||!email.contains("@"))throw new IllegalArgumentException("Invalid email");this.email=email;}
 public abstract String getRole();
 @Override public String toString(){return getRole()+" "+id+" - "+name+" ("+email+")";}
}