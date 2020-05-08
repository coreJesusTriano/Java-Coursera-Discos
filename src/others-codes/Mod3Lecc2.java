package discos;

public class Mod3Lecc2 {
	  public static String mensaje (int n)  {
	    String sNumVueltas = "";
	    for( int i=1; i < n; i++)  {
	      i++;
	      sNumVueltas += "\nOtra mas";
	      System.out.println("i =" + i + "\t");
	    } // for
	    return sNumVueltas;
	  }
	  
	  public static void main(String[] args) {
	    System.out.println ("El resultado es:" + mensaje (6) );
	  }// main
	} // class