package movieticketbookingsystem;
public abstract class Movie {
 private final String id,name,genre,language; private final double duration;
 public Movie(String id,String name,String genre,String language,double duration){
  if(duration<=0) throw new IllegalArgumentException("Duration must be positive");
  this.id=id;this.name=name;this.genre=genre;this.language=language;this.duration=duration;
 }
 public String getMovieId(){return id;} public String getMovieName(){return name;}
 public String getGenre(){return genre;} public String getLanguage(){return language;}
 public double getDuration(){return duration;}
 public abstract double getPrice();
 public void displayDetails(){System.out.println(toString()+" | Price: "+getPrice()+" BDT");}
 @Override public String toString(){return "["+id+"] "+name+" | "+genre+" | "+language+" | "+duration+" min";}
}
