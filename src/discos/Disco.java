package discos;
// Clase del proyecto y paquete Discos

import java.util.Scanner;
import java.util.GregorianCalendar;

/**
 * Clase Disco 
 * Disco digital para su uso por una companhia de
 * "streaming". Se usa como ejemplo en algunos
 * aspectos introductorios a Java
 * 
 * @author Elisa Viso
 * @version 1.0 01/03/17
 */
public class Disco implements ServiciosDisco {
	
	/**************************************************************************************
	 ******  CONSTANTES SIMBOLICAS DE CLASE  **********************************************
	 **************************************************************************************/
	
	// Tope de transmisiones permitidas
	private final static int MAX_PERMITIDAS = 50;
	// Tipos de discos
	private final static short	CD = 1,
								DVD = 2,
								BR = 3;
	// Primer y último año valido.
	private final static int	PRIMER_ANHO = 1900,
								ULT_ANHO = 2020;
	// Tamaño de cada atributo en la salida del método toString()
	public static final int LUG_TD 			= 1,
							LUG_NOMBRE		= 40,
							LUG_ANHO 		= 4,
							LUG_PERMITIDAS	= 4,
							LUG_ACTIVAS 	= 4;
	// Cadena de espacios utilizada para salida de método toString()
	public final static String ESPACIOS = " ".repeat(LUG_NOMBRE);
	// Cadena nombres días para crear salida método daTransmision()
	public final static String N_DIAS =	  "         "
										+ "domingo  "
										+ "lunes    "
										+ "martes   "
										+ "miércoles"
										+ "jueves   "
										+ "viernes  "
										+ "sábado   ";
	// tamaño de cada bloque de la cadena anterior
	public static final int TAM_DIA = 9;

	/***************************************************************************************
	 ******  ATRIBUTOS CONSTANTES DEL OBJETO  **********************************************
	 ***************************************************************************************/
	
	private final int ANHO; 		// fecha de grabacion
	private final String NOMBRE;	// artista o pelicula
	private final short TIPO_DISCO; // 1 :CD, 2 :DVD, 3 :BRç
	
	/**************************************************************************************
	 ******  ATRIBUTOS VARIABLES DEL OBJETO  **********************************************
	 **************************************************************************************/
	
	private int activas;	// Transmisiones activas
	private int permitidas;	// Maximo transmisiones permitidas
	
	/**************************************************************************************
	 **************  CONSTRUCTORES  *******************************************************
	 **************************************************************************************/
	
	public Disco (short tipo, String nombre, int fecha) {
		ANHO = checkIntRange(PRIMER_ANHO, fecha, ULT_ANHO);
		NOMBRE = checkString(nombre.trim());
		TIPO_DISCO = (short) checkIntRange(CD, tipo, BR);
		
	}
	
	public Disco (short tipo, String nombre, int fecha, int permitidas) {
		ANHO = checkIntRange(PRIMER_ANHO, fecha, ULT_ANHO);
		NOMBRE = checkString(nombre.trim());
		TIPO_DISCO = (short) checkIntRange(CD, tipo, BR);
		this.permitidas = checkIntRange(0, permitidas, MAX_PERMITIDAS);
	}
	
	public Disco () {
		Scanner inStd = new Scanner(System.in);
		System.out.println("Qué deseas grabar:"
				+ "(1) CD, (2) DVD, (3) Bluray");
		System.out.print("Elige tipo de disco (1,2,3), "
				+ "terminando con un [Enter]: ->");
		TIPO_DISCO = (short) checkIntRange(CD, inStd.nextShort(), BR);
		inStd.nextLine(); // clean inputStream to discard Enter key.
		System.out.println("Ahora dame el nombre "
				+ (TIPO_DISCO == CD
						? "del cantante "
						: TIPO_DISCO == DVD
						? " de la película "
						: " de la serie ")
				+ "(terminando con [Enter]): ->");
		NOMBRE = checkString(inStd.nextLine());
		System.out.println("Dame el año en el que fue grabado"
				+ " (entre " + PRIMER_ANHO + " y " + ULT_ANHO
				+ ") terminando con un [Enter]: ->");
		ANHO = checkIntRange(PRIMER_ANHO, inStd.nextInt(), ULT_ANHO);
		inStd.nextLine(); // clean inputStream to discard Enter key.
		System.out.println("Dame ahora el número de transmisiones"
				+ " permitidas, \n(entre 1 y " + MAX_PERMITIDAS + ")"
				+ " terminando con un [Enter]: ->");
		this.permitidas = checkIntRange(1, inStd.nextInt(), MAX_PERMITIDAS);
		inStd.nextLine(); // clean inputStream to discard Enter key.
		System.out.println("Gracias");
		inStd.close();
		
	}	
	
	/**************************************************************************************
	 ************  MÉTODOS DE CONSULTA O ACCESO // GETTERs  *******************************
	 **************************************************************************************/
	
  /** 
   * Proporciona el numero de transmisiones activas
   * 
   * @return numero de transmisiones activas.
   */
  public int getActivas(){
	  return activas;
  }
  
  /**
   * Proporciona el anho en que fue grabado el disco.
   * 
   * @return fecha de grabación.
   */
  public int getANHO(){
	  return ANHO;
  }
  
  /**
   * Proporciona el nombre que tenga asociado el disco. Puede ser el 
   * musico si se trata de un CD o la pelicula si se trata de un DVD
   * o Bluray.
   * 
   * @return nombre del musico si se trata de un CD o de la pelicula
   * si se trata de un DVD o Bluray.
   */
  public String getNOMBRE(){
	return NOMBRE;
	  
  }
  
  /**
   * Proporciona el numero de transmisiones permitidas
   * 
   * @return numero de transmisiones permitidas.
   */
  public int getPermitidas(){
	return permitidas;
	  
  }
  
  /**
   * Proporciona el tipo de disco.
   * 
   * @return el tipo de disco entre 1 y 3.
   */
  public short getTIPO_DISCO(){
	return TIPO_DISCO;
	  
  }

	/**************************************************************************************
	 ************  MÉTODOS DE ACTUALIZACIÓN O MUTANTES // SETTERs  ************************
	 **************************************************************************************/
  
  /**
   * Modifica el valor de transmisiones activas
   * 
   * @param newActivas valor que se desea establecer.
   */
  public void setActivas(int newActivas){
	  activas = checkIntRange(1, newActivas, permitidas);
  }
  
  /**
   * Modifica el valor de transmisiones permitidas.
   * 
   * @param permisos valor que se desea establecer
   */
  public void setPermitidas(int permisos){
	  permitidas = checkIntRange(1, permisos, MAX_PERMITIDAS);
  }

	/**************************************************************************************
	 ************  MÉTODOS DE IMPLEMENTACIÓN // RESPOSABILIDADES  *************************
	 **************************************************************************************/  
  
  /**
   * Duplica a este disco, construyendo otro objeto con los mismos
   * valores, pero con identidad distinta.
   * 
   * @return un nuevo disco identico al que se le pide.
   */
  public ServiciosDisco copiaDisco(){
	return new Disco(this.TIPO_DISCO, this.NOMBRE, this.ANHO, this.permitidas);
	  
  }
  
  /**
   * Otorga una transmision, contestando con la fecha y hora en que
   * la esta dando. Si no la puede dar, responde negativamente.
   * Actualiza el numero de transmisiones activas.
   * 
   * @return un mensaje diciendo si pudo o no otorgar la transmision.
   */
  public String daTransmision ( ){
	  GregorianCalendar miCal = new GregorianCalendar();
	  boolean siHay = activas < permitidas;
	  activas += siHay ? 1 : 0;
	return siHay ? "Transmision dada a las " 
			  + daHora(miCal)+ " del "
			  + daFecha(miCal)
			  : "No hay transmisiones disponibles";
	  
  } /* Verifica si tiene o no transmisiones. */
  
  /**
   * Otorga una transmision, contestando con la fecha y hora en que
   * la esta dando. Si no la puede dar, responde negativamente.
   * Actualiza el numero de transmisiones activas.
   * 
   * @param miCal La fecha y hora en la que se esta pidiendo
   *          la transmisión.
   * @return un mensaje diciendo si pudo o no otorgar la transmision.
   */
  public String daTransmision (GregorianCalendar miCal){
	  boolean siHay = activas < permitidas;
	  activas += siHay ? 1 : 0;
	return siHay ? "Transmision dada a las " 
			  + daHora(miCal)+ " del "
			  + daFecha(miCal)
			  : "No hay transmisiones disponibles";
	  
  } /* Verifica si tiene o no transmisiones. */

  /**
   * Muestra de forma estetica el contenido de este disco.
   * 
   * @param encabezado para encabezar lo que se imprima.
   * 
   * @return una cadena con la información y que contiene saltos de
   *          linea.
   */
  public String muestraDisco (String encabezado){
	  String salida = checkString(encabezado)
			  + "\n=================================================\n";
	  salida += "Tipo de disco: " + (TIPO_DISCO == CD
			  						? "CD" : TIPO_DISCO == DVD
			  						? "DVD" : "Bluray") + "\n";
	  salida += "Nombre de" + (TIPO_DISCO == CD ? "l cantante"
			  					: TIPO_DISCO == DVD ? " la película"
	  							: " la serie") + ": " + NOMBRE.trim()+ "\n";
	  salida += "Transmisiones permitidas: " + permitidas + "\n";
	  salida += "Transmisiones activas: " + activas + "\n";
	return salida;
	  
  }
  
  /**cadena
   * Actualiza el numero de transmisiones activas.
   * 
   * @return si pudo (true) o no (false) terminar una transmision.
   */
  public boolean terminaTransmision(){
	  boolean hayActivas = this.activas > 0;
	  activas -= hayActivas ? 1 : 0;
	return hayActivas;
  }
  
  /**
   * Proporciona una cadena con los distintos campos ocupando un 
   * lugar definido.
   * 
   * @return La informacion del disco linealizado en forma de 
   *     cadena, todos los discos con la misma informacion.
   *     
   * It build a string with the attributes of the object in a 
   * predefined order 
   * 
   * @return a string in a predefined order from attributes of the
   * 		object
   */
  public String toString(){
	  String salida = editNum(TIPO_DISCO, LUG_TD)
			  + editStr(NOMBRE, LUG_NOMBRE)
			  + editNum(ANHO, LUG_ANHO)
			  + editNum(permitidas, LUG_PERMITIDAS)
			  + editNum(activas, LUG_ACTIVAS);
	return salida;
	  
  }

	/**************************************************************************************
	 ************  MÉTODOS AUXILIARES // METHODS AUXILIARIES  *****************************
	 **************************************************************************************/  

  public static final int checkIntRange(int limInf, int arg, int limSup) {
	  return (arg < limInf
			  ? limInf
			  : (arg > limSup
					  ? limSup
					  : arg));
  }
  
  public static final String checkString(String cadena) {
	  return (cadena == null || cadena.length() == 0)
			  	? "No definida"
	  			: cadena;
  }
  
  public static String editNum(int valor, int lugares) {
	  String conEspacios = ESPACIOS + valor;
	  return conEspacios.substring(conEspacios.length() - lugares);
  }
  public static String editStr(String valor, int lugares) {
	  String conEspacios = valor + ESPACIOS;
	  return conEspacios.substring(0, lugares);
  }
  
  public static String daHora(GregorianCalendar cal) {
	  int hora = cal.get(GregorianCalendar.HOUR);
	  int minutos = cal.get(GregorianCalendar.MINUTE);
	  String ampm = cal.get(GregorianCalendar.AM_PM) == GregorianCalendar.AM
			  ? "AM" : "PM";
	  hora = (hora == 0 && cal.get(GregorianCalendar.AM_PM) == GregorianCalendar.PM) 
			  ? 12: hora;
	  return "" + hora + ":" 
			  + (minutos<10 ? "0"+minutos:minutos)
			  + " "+ ampm;
  }
  
  public static String daFecha(GregorianCalendar cal) {
	  int dia = cal.get(GregorianCalendar.DAY_OF_MONTH);
	  int mes = cal.get(GregorianCalendar.MONTH) + 1;
	  int anho = cal.get(GregorianCalendar.YEAR);
	  String diaSemana = nombreDia(cal.get(GregorianCalendar.DAY_OF_WEEK));
	  return diaSemana + " " + dia + "/" + mes + "/" + anho; 
  }
 
  private static String nombreDia(int numDia) {
//	  int desde = numDia * TAM_DIA;
//	  int hasta = desde + TAM_DIA; 
	  return N_DIAS.substring(/*desde*/numDia*TAM_DIA, /*hasta*/ (numDia+1)*TAM_DIA).trim();
  }
  
  public static String cad10(String cad)  { 
	  return cad == null ? "          "  
	  : (cad + "          ").substring(0,10);
	}

  public static String cade10(String cad)  {  
	  cad = cad + "          "; // 10 blancos  
	  return cad == null ? "          "      
	  : cad.substring(0,10);
	}

	/**************************************************************************************
	 ************  MAIN DE PRUEBAS  *******************************************************
	 **************************************************************************************/
  
  public static void main(String[] args) {
	  GregorianCalendar elCal = new GregorianCalendar();
	  System.out.println("Fecha:"+daFecha(elCal));
	  System.out.println("Hora :"+daHora(elCal));
	  Disco elMio = new Disco(DVD, "Ahora los ves, ahora no", 2013, 2);
	  System.out.println(elMio.muestraDisco("- Esto es un Encabezado -"));
	  System.out.println(elMio.daTransmision());
	  System.out.println(elMio.toString());
	  System.out.println(elMio.terminaTransmision());
	  System.out.println(elMio.daTransmision());
	  System.out.println(elMio.muestraDisco(" CABECERA "));
	  System.out.println(elMio.terminaTransmision());
	  System.out.println(elMio.muestraDisco(" CABECERA "));
	  System.out.println(elMio.daTransmision());
	  System.out.println(elMio.daTransmision());
	  System.out.println(elMio.daTransmision());
	  System.out.println(elMio.muestraDisco(" CABECERA "));
	  System.out.println(elMio.terminaTransmision());
	  GregorianCalendar calTry = new GregorianCalendar(2020, 5, 4, 12, 12, 24);
	  System.out.println(elMio.daTransmision(calTry));
	  
  }

}