package movieticketbookingsystem;
public class PremiumMovie extends Movie {
 public enum ScreenType {IMAX,THREE_D,FOUR_DX,DOLBY_ATMOS}
 private final ScreenType screen;
 public PremiumMovie(String id,String name,String genre,String language,double duration,ScreenType screen){
  super(id,name,genre,language,duration);if(screen==null)throw new IllegalArgumentException("Screen required");this.screen=screen;
 }
 @Override public double getPrice(){return 850;}
 @Override public String toString(){return super.toString()+" | "+screen;}
}
