package discos;

/**
 * Disco digital para su uso por una companhia de
 * "streaming". Se usa como ejemplo en algunos
 * aspectos introductorios a Java
 * 
 * @author Elisa Viso
 * @version 1.0 01/03/17
 */

// Interfaz del proyecto Discos
public interface ServiciosDisco {

// Métodos de consulta o acceso
  
  /** 
   * Proporciona el numero de transmisiones activas
   * 
   * @return numero de transmisiones activas.
   */
  public int getActivas();
  
  /**
   * Proporciona el anho en que fue grabado el disco.
   * 
   * @return fecha de grabación.
   */
  public int getANHO();
  
  /**
   * Proporciona el nombre que tenga asociado el disco. Puede ser el 
   * musico si se trata de un CD o la pelicula si se trata de un DVD
   * o Bluray.
   * 
   * @return nombre del musico si se trata de un CD o de la pelicula
   * si se trata de un DVD o Bluray.
   */
  public String getNOMBRE();
  
  /**
   * Proporciona el numero de transmisiones permitidas
   * 
   * @return numero de transmisiones permitidas.
   */
  public int getPermitidas();
  
  /**
   * Proporciona el tipo de disco.
   * 
   * @return el tipo de disco entre 1 y 3.
   */
  public short getTIPO_DISCO();
  
//  Métodos mutantes o de actualización
  
  /**
   * Modifica el valor de transmisiones activas
   * 
   * @param newActivas valor que se desea establecer.
   */
  public void setActivas(int newActivas);
  
  /**
   * Modifica el valor de transmisiones permitidas.
   * 
   * @param permisos valor que se desea establecer
   */
  public void setPermitidas(int permisos);

  
// Métodos de implementación
  
  /**
   * Duplica a este disco, construyendo otro objeto con los mismos
   * valores, pero con identidad distinta.
   * 
   * @return un nuevo disco identico al que se le pide.
   */
  public ServiciosDisco copiaDisco();
  
  /**
   * Otorga una transmision, contestando con la fecha y hora en que
   * la esta dando. Si no la puede dar, responde negativamente.
   * Actualiza el numero de transmisiones activas.
   * 
   * @return un mensaje diciendo si pudo o no otorgar la transmision
   */
  public String daTransmision ( ); /* Verifica si tiene o no transmisiones. */
  
  /**
   * Muestra de forma estetica el contenido de este disco.
   * 
   * @param encabezado para encabezar lo que se imprima.
   * 
   * @return una cadena con la información y que contiene saltos de
   *          linea.
   */
  public String muestraDisco (String encabezado);
  
  /**
   * Actualiza el numero de transmisiones activas.
   * 
   * @return si pudo (true) o no (false) terminar una transmision.
   */
  public boolean terminaTransmision();
  
  /**
   * Proporciona una cade con los distintos campos ocupando un 
   * lugar definido.
   * 
   * @return La informacion del disco linealizado en forma de 
   *     cadena, todos los discos con la misma informacion.
   */
  public String toString();



}