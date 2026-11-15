package movieticketbookingsystem;
public class RegularMovie extends Movie {
 public enum AgeRating {G,PG,PG13,R,NC17}
 private final AgeRating rating;
 public RegularMovie(String id,String name,String genre,String language,double duration,AgeRating rating){
  super(id,name,genre,language,duration);if(rating==null)throw new IllegalArgumentException("Rating required");this.rating=rating;
 }
 @Override public double getPrice(){return 450;}
 @Override public String toString(){return super.toString()+" | Rated "+rating;}
}
