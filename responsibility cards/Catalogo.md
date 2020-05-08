# Tarjeta de Responsabilidades para Catalogo

## Clase: Catalogo

### Atributos

| Identificador | Descripción |
| -- | --|
| catalogo | Colección de discos. Cada disco es un objeto. |  

| Para el catálogo: |  |
| --| -- |
| numDiscos | Número de discos en el catálogo.|
| fechas | Colección de fechas de transmisión *para cada disco* registrado. Cada registro tiene la fecha de inicio.|

| Para llevar registro: |  |
|--|--|
|historico|Colección *para cada disco* de parejas de fechas que guarda el inicio y final de una transmisión.|
|numHist|Colección que guarda el número de registros históricos para cada disco.|

> Clase: **Catalogo**
>> Atributos

Acceso | Modif(s) | Identificador | Tipo | Nombre
--|--|--|--|--
private ||catalogo|Disco[]| Colección de discos del catálogo
private ||numDiscos|int| cantidad de discos del catálogo
private ||fechas|Gregorian Calendar[][]| fechas transmisiones activas de cada disco
private ||historico|Gregorian Calendar[][][]| Historico fechas inicio y final de cada transmision de cada disco
private ||numHist|int[]| Por cada disco número de posiciones ocupadas en historico, es decir, cantidad de parejas de fechas de inicio y final de la transmisión.

### Responsabilidades ( Métodos )

|Identificador|Salida|Entradas|Descripción|
|--|--|--|--|
|addCatalogo|boolean|Disco|Agrega un disco al catalogo si es que hay lugar. Dice si lo agregó o no|
|daTransmision|boolean|posición del disco|Registra la transmisión del elegido a una cierta hora. Avisa si pudo o no|
|terminaTrans|boolean|posición del disco y consola|Pregunta, de este disco, cual es la transmisión a eliminar. Transcribe el inicio y final al histórico. Avisa si pudo o no|
|mstraCatalogo|Cadena con lista de discos|encabezado|Muestra la lista de discos dados de alta|
|mstraActivos|Cadena con la lista de discos en transmisión|encabezado|Da una lista de los discos en transmisión junto con la lista de hora de inicio.|
|mstraActivas|Cadena con la lista de transmisiones activas|posición del disco|Da una lista de las transmisiones activas del disco en esa posición del catálogo|
|muestraHistorico|Cadena con el hístorico del disco|posición del disco|Muestra la lista de transmisiones iniciadas y finalizadas para ese disco|
|muestraHstrs|Cadena con el histórico de todos los discos|(nada)|Muestra la lista de transmisiones iniciadas y finalizadas para todos los discos|
|conectaCatlgo|(nada)|(nada)|Es el encargado de interaccionar con el usuario y el catálogo|

|Constructores:||||
|--|--|--|--|
|Catalogo|catálogo|(nada)|Construye un catálogo con el máximo número de posiciones y todos los arreglos asociados|
|Catalogo|catálogo|numDscs|Construye un catálogo con numDscs posiciones y todos los arreglos asociados|
|Catalogo|catálogo|numDscs, arreglo inicial de discos|Construye un catálogo con numDscs posiciones, nuevos como contenido inicial y todos los arreglos asociados|
