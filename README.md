# Java-Coursera-Discos - Introducción a java (mooc unam / coursera)

## Proyecto Discos 

Automatizar una empresa de streaming con un amplio número de CD, DVD y Bluray.  
Para cada uno de ellos, existe un determinado número de transmisiones permitidas simultáneas y transmisiones activas.  
Los discos pertenecerán a un catálogo.

### Paso 1: Breve descripción del problema:

Empresa de streaming con amplio número de CD, DVD y
Bluray y para cada uno de ellos un cierto número de
transmisiones permitidas simultáneas. En un momento dado
cuántas están activas.
- - -
>***Por el momento***:  
>>*cómo implementar cada disco*
- - -
### Paso 2: Describir la salida
Proporcionar al usuario:
1. Valor de la información individual de cada disco
    * tipo de disco (CD, DVD o Bluray)
    * nombre (cantante, película o serie, respectivamente)
    * año de grabación
    * transmisiones permitidas
    * transmisiones activas
2. Descripción de cada disco completo
3. Respuesta a solicitudes de transmisión
4. Terminar una transmisión
5. Duplicación de un disco

### Paso 3: Describir entradas del usuario

El usuario deberá proporcionar:
1. Los discos
2. Las solicitudes de mostrar un disco en particular
3. Las solicitudes de transmisión
4. Las solicitudes para finalizar una transmisión
5. Las solicitudes de copiar discos

### Paso 4: Identificar objetos y responsabilidades

A partir de las descripciones dadas:

|||||
|--| -- |--| --|
|>|Identificar a los objetos: ||**sustantivos**  |  
|>|Identificar responsabilidades:||**verbos**  |  
|>|Agruparlos:||*clasificarlos*  |

|||||
|--| -- |--| --|
||mismo tipo de información y comportamiento|⇒ misma clase|

>**Objetos** (sustantivos):
>
> * **disco** <- clase principal
>   * tipos de disco
>   * transmisiones permitidas
>   * fecha de grabación
>   * nombre del disco
>   * transmisiones activas
>
> * **usuario** <-clase de prueba
>   * Objetos
>   * Variables diversas  
>
> **Responsabilidades** (verbos):
>
>* Mostrar el contenido de un disco  
>* Iniciar transmisión
>* Terminar transmisión
>* Duplicar disco
>* “Comprar” o construir un disco

### Paso 5: Elaborar tarjetas de responsabilidades

Determinar atributos (campos) y acceso, una tarjeta por cada clase  
**acceso**: Quién puede usar (conocer) a la clase, sus campos y
funciones  
Reglas de acceso:
Acceso | en Java | Descripción
--|--|--
público|public|Toda clase u objeto en la red lo pueden ver
privado|private|Sólo lo pueden ver objetos de la clase en la que estén declarados
protegido|protected|Sólo lo pueden ver objetos de la clase en la que están declarados y clases que extienden a ésta (herencia)
paquete|(por omisión)|Sólo lo pueden ver lo declarado en el mismo paquete o subdirectorio

Las tarjetas de cada clase se encuentran en *resposibility cards*
