package discos;

import java.util.GregorianCalendar;

/**
 * Simula el uso de un catalogo de discos.
 * En cada sesión puede construir su base de discos, 
 * agregar discos, consultar el catalogo, iniciar y
 * transmisiones y terminar transmisiones, registrando
 * todas las actividades en el catalogo.
 * 
 * @author: Elisa Viso. Adaptación Jesús Triano
 * @version: 2017. Adaptada 2020.
 */
public class Catalogo {
	
	/**************************************************************************************
	 ******  CONSTANTES SIMBOLICAS DE CLASE  **********************************************
	 **************************************************************************************/
	
	/** Numero máximo de discos en cualquier catalogo. */
	public static final int MAX_DISCOS = 50;
	/** Menú del catálogo */
	public static final String[] MENU_CATALOGO = {
			"Salir", 								// 0
			"Agregar disco", 						// 1
			"Mostrar discos", 						// 2
			"Mostrar discos activos", 				// 3
			"Pedir transmisión", 					// 4
			"Terminar transmisión", 				// 5
			"Mostrar disco", 						// 6
			"Mostrar historico de un disco",		// 7
			"Mostrar historico de todos los discos" // 8
	};
	/*  Declaramos constantes simbólicas de clase para cada entrada de menú
	 *  para mejorar la legibilidad del código y además poder en un futuro 
	 *  modificar el orden tan sólo modificando la declaración o el número 
	 *  de elementos del menú sin que provoque errores en nuestro código.
	 */
	/** Acción para salir del menú. */
	public final static int SALIR = 0,
	/** Acción para agregar un disco. */
							AGREGA_DSCO = 1,
	/** Acción de mostrar el catálogo. */
							MOSTRAR_DISCOS = 2,
	/** Acción de mostrar el catálogo de discos activos. */
							MOSTRAR_DISCOS_ACTIVOS = 3,
	/** Acción de pedir una transmisión. */
							PIDE_TRANSMISION = 4,
	/** Acción de terminar una transmisión. */
							TERMINA_TRANSMISION = 5,
	/** Acción de mostrar un disco. */
							MOSTRAR_UN_DISCO = 6,
	/** Acción de mostrar un histórico de un disco. */
							MOSTRAR_HISTORICO = 7,
	/** Acción de mostrar los históricos de todos los discos.  */
							MOSTRAR_HISTORICOS = 8;
	
	/** Dimensión histórico inicio/final de cada transmisión */
	public static final int INICIO = 0,
							FINAL =1;
	/** Tamaño del histórico cantidad por cada permitida */
	public static final int SIZE_HSTCO = 2;

	/**************************************************************************************
	 ******  ATRIBUTOS VARIABLES DEL OBJETO  **********************************************
	 **************************************************************************************/
	
	// catálogo de discos
	private Disco[] catalogo;
	// número de discos en el catálogo
	private int numDiscos = 0;
	// fechas de inicio de cada transmisión
	private GregorianCalendar[][] fechas;// [disco][transmision]
				/*	Un renglón / fila por cada disco.
				 *  Una columna por cada transmisión.
				 *  Cada fila tiene una cantidad de 
				 *  columnas dada por el disco de esa
				 *  fila.
				 *  El número de fechas registradas
				 *  está dado por el atributo activas
				 *  del disco.
				 */
	// historico de fechas
	private GregorianCalendar[][][] historico;//[disco][inicio/fin][fecha]
				/* Pareja de renglones por cada disco */
	// numHist nos valdrá como indice "lógico máximo" para la 3D de histórico
	private int[] numHist; /* Da, para cada disco, el número de
							* transmisiones iniciadas y terminadas
	 						* para ese disco 
	 						* 
	 						* Como un arreglo se tiene que construir con el 
	 						* máximo de posiciones que pudieran ocuparse, no
	 						* siempre están todas ocupadas. */
	
	/**************************************************************************************
	 **************  CONSTRUCTORES  *******************************************************
	 **************************************************************************************/
	
	/**
	 * Construye un catalogo.
	 * Da el maximo numero posible de registros para discos y 
	 * anota que no tiene ningun disco registrado. 
	 * Se construiran los arreglos correspondientes a 
	 * historico y fechas, pero unicamente con su numero de 
	 * renglones (o tablas).
	 * Al registrar a cada disco mediante el menu, se
	 * construiran los arreglos correspondientes a sus fechas
	 * y su historico.
	 */
	public Catalogo() {
		/* Construye un catálogo con el número máximo de posiciones
		 * y todos los arreglos asociados */
		this.catalogo = new Disco[MAX_DISCOS];
		this.numDiscos = 0; // catálogo aún vacío, ningún disco registrado aún
		/* El numero de posiciones disponibles para registrar transmisiones e
		 * historica estara dado por el numero de transmisiones permitidas
		 * del disco de esa posicion, que no se sabra hasta que se
		 * construya el disco correspondiente.
		 * El numero de lugares ocupados esta dado por transmisiones activas
		 *  del disco en esa posicion. */
		this.fechas = new GregorianCalendar[this.catalogo.length][];
		this.historico = new GregorianCalendar[this.catalogo.length][2][];
		/* el numero de lugares ocupados en historico esta dado por
		 * numHist[disco] para cada disco. Para cada disco se requiere
		 * la fecha de inicio y fin de la transmision, por eso el 2. */
		this.numHist = new int[this.catalogo.length];
		// Al ser un arreglo de enteros, por defecto se inicializa a 0 cada elemento
	} // fin constructor sin parametros
	
	/**
	 * Construye un catalogo con espacio para un numero
	 * definido de discos. Los espacios para discos contienen
	 * una referencia nula. Se construiran los arreglos correspondientes
	 * a historico y fechas, pero unicamente con su numero
	 * de renglones (o tablas). Al registrar a cada disco, se
	 * completaran los arreglos correspondientes a sus fechas
	 * y su historico.
	 * @param numDscs Maximo numero de discos que va a tener
	 */
	public Catalogo(int numDscs) {
		/* Construye un catálogo con el número de posiciones indicadas por el 
		 * parametro numDscs, siempre que esté dentro del rango valido
		 * 1 <= numDscs <= MAX_DISCOS.
		 * y todos los arreglos asociados
		 */
		// verificamos que el argumento este dentro del rango
		//numDscs = (numDscs < 1) ? 1 : (numDscs > MAX_DISCOS) ? MAX_DISCOS : numDscs;
		numDscs = Disco.checkIntRange(1, numDscs, MAX_DISCOS);
		this.catalogo = new Disco[numDscs];
		this.numDiscos = 0; // catálogo aún vacío, ningún disco registrado aún
		/* El numero de posiciones disponibles para registrar transmisiones e
		 * historica estara dado por el numero de transmisiones permitidas
		 * del disco de esa posicion, que no se sabra hasta que se
		 * construya el disco correspondiente.
		 * El numero de lugares ocupados esta dado por transmisiones activas
		 *  del disco en esa posicion. */
		this.fechas = new GregorianCalendar[this.catalogo.length][];
		this.historico = new GregorianCalendar[this.catalogo.length][2][];
		/* el numero de lugares ocupados en historico esta dado por
		 * numHist[disco] para cada disco. Para cada disco se requiere
		 * la fecha de inicio y fin de la transmision, por eso el 2. */
		this.numHist = new int[this.catalogo.length];
		// Al ser un arreglo de enteros, por defecto se inicializa a 0 cada elemento
	} // fin constructor con el tamaño del catalogo
	
	
	/**
	 * Construye una Catalogo con espacio para un numero definido de discos.
	 * El catalogo lo inicializas con el contenido del arreglo que le pasas.
	 * Inicializa el contador relativo al numero de discos. Para aquellos discos
	 * que ya estan construidos, construye las columnas relacionadas en fechas
	 * e historico. Si el numero de lugares solicitados es menor que el de discos
	 * para inicializacion, se usa este ultimo para los tamanhos de arreglos.
	 * @param numDscs Maximo numero de discos que va a tener.
	 * @param nuevos Arreglo de discos con los que inicia
	 * el catalogo.
	 */
	public Catalogo(int numDscs, Disco[] nuevos) {
		/* Construye un catálogo con el número de posiciones indicadas por el 
		 * parametro numDscs, siempre que esté dentro del rango valido
		 * 1 <= numDscs <= MAX_DISCOS.
		 * y todos los arreglos asociados.
		 * Y finalmente agrega los discos del parametro nuevos al catalogo
		 */
		// verificamos que no es una referencia nula antes de asignar el tamaño
		// para evitar posibles errores en ejecución.
		int numNuevos = nuevos == null ? 0 : nuevos.length; 
		// Si el tamaño de catalogo indicado por parametro es menor que el 
		// tamaño de la colección aportada en el 2o. parametros nos quedamos
		// este último.
		numDscs = numDscs < numNuevos ? numNuevos : numDscs;
		// verificamos que el tamaño del catalogo queda dentro del rango permitido
		numDscs = Disco.checkIntRange(1, numDscs, MAX_DISCOS);
		/* El numero de discos a copiar es el minimo entre el tamaño final
		 * del arreglo y el numero de discos nuevos.
		 * Nos aseguramos que numNuevos no sea mayor que numDscs 
		 * que es el máximo de discos que podemos agregar al catalogo */
		numNuevos = numNuevos > numDscs ? numDscs : numNuevos;
		// Construimos el catalogo
		this.catalogo = new Disco[numDscs];
		this.numDiscos = 0; // catálogo aún vacío, se irá modificando cuando agregemos los discos
		// y construimos los arreglos asociados
		/* El numero de posiciones disponibles para registrar transmisiones e
		 * historica estara dado por el numero de transmisiones permitidas
		 * del disco de esa posicion, que no se sabra hasta que se
		 * construya el disco correspondiente.
		 * El numero de lugares ocupados esta dado por transmisiones activas
		 *  del disco en esa posicion. */
		this.fechas = new GregorianCalendar[this.catalogo.length][];
		this.historico = new GregorianCalendar[this.catalogo.length][2][];
		/* el numero de lugares ocupados en historico esta dado por
		 * numHist[disco] para cada disco. Para cada disco se requiere
		 * la fecha de inicio y fin de la transmision, por eso el 2. */
		this.numHist = new int[this.catalogo.length];
		// El arreglo numHist por defecto quedará inicializado con ceros
		/* Copiamos ahora los discos nuevos al catalogo en los primeros
		 * lugares del catalogo */
		int numPer;
		for (int i= 0; i < numNuevos; i++) {
			if (nuevos[i]== null) { // disco no valido => no lo incluimos
				continue; // go to update & new iteration
				// break; go to out for
			}
			// else 
			this.catalogo[this.numDiscos] = nuevos[i];
			numPer = this.catalogo[this.numDiscos].getPermitidas(); // permitidas del disco
			/* número posible de fechas */
			this.fechas[this.numDiscos] = new GregorianCalendar[numPer];
			this.historico[this.numDiscos][Catalogo.INICIO]= new GregorianCalendar[Catalogo.SIZE_HSTCO * numPer];
			this.historico[this.numDiscos][Catalogo.FINAL]= new GregorianCalendar[Catalogo.SIZE_HSTCO * numPer];
			this.numDiscos++;
		} // end for
		
	} // fin constructor con el tamaño del catalogo y coleccion de discos registrados en él
	
	
	/**************************************************************************************
	 ************  MÉTODOS DE CONSULTA O ACCESO // GETTERs  *******************************
	 **************************************************************************************/
	
	/**************************************************************************************
	 ************  MÉTODOS DE ACTUALIZACIÓN O MUTANTES // SETTERs  ************************
	 **************************************************************************************/
	
	/**************************************************************************************
	 ************  MÉTODOS DE IMPLEMENTACIÓN // RESPOSABILIDADES  *************************
	 **************************************************************************************/ 
	
	/**************************************************************************************
	 ************  MÉTODOS AUXILIARES // METHODS AUXILIARIES  *****************************
	 **************************************************************************************/  
	
}






