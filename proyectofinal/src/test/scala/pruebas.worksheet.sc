case class Aeropuerto(cod: String, x: Int, y: Int, GMT: Int)

case class Vuelo(
    Aln: String,
    Num: Int,
    Org: String,
    HS: Int,
    MS: Int,
    Dst: String,
    HL: Int,
    ML: Int,
    Esc: Int
)

type Itinerario = List[Vuelo]

val aeropuertosCurso = List(
  Aeropuerto("CLO", 100, 200, -500), // Cali
  Aeropuerto("BOG", 300, 500, -500), // Bogotá
  Aeropuerto("MDE", 200, 600, -500), // Medellin
  Aeropuerto("BAQ", 350, 850, -500), // Barranquilla
  Aeropuerto("SMR", 400, 950, -500), // Santa Marta
  Aeropuerto("CTG", 300, 800, -500), // Cartagena
  Aeropuerto("PTY", 400, 1000, -500), // Ciudad de Panamá
  Aeropuerto("JFK", 2000, 2000, -400), // Nueva York
  Aeropuerto("MIA", 1000, 2000, -500), // Miami
  Aeropuerto("MEX", 1000, 1000, -600), // Ciudad de México
  Aeropuerto("MAD", 5000, 5000, 100), // Madrid
  Aeropuerto("SVCS", 400, 1000, -600), // Caracas
  Aeropuerto("MID", 500, 100, -600), // Merida
  Aeropuerto("AUA", 500, 2000, -400), // Aruba
  Aeropuerto("IST", 9000, 9000, 300), // Estambul
  Aeropuerto("HND", 10000, 12000, 900), // Tokio
  Aeropuerto("DXB", 9500, 11500, 400), // Dubai
  Aeropuerto("SVO", 12500, 12500, 300) // Moscú
)

val vuelosCurso = List(
  Vuelo("AIRVZLA", 601, "MID", 5, 0, "SVCS", 6, 0, 0),
  Vuelo("AIRVZLA", 602, "SVCS", 6, 30, "MID", 7, 30, 0),
  Vuelo("AVA", 9432, "CLO", 7, 0, "SVO", 2, 20, 4),
  Vuelo("AVA", 9432, "CLO", 7, 0, "BOG", 8, 0, 0),
  Vuelo("IBERIA", 505, "BOG", 18, 0, "MAD", 12, 0, 0),
  Vuelo("IBERIA", 506, "MAD", 14, 0, "SVO", 23, 20, 0),
  Vuelo("IBERIA", 507, "MAD", 16, 0, "SVO", 1, 20, 0),
  Vuelo("LATAM", 787, "BOG", 17, 0, "MEX", 19, 0, 0),
  Vuelo("VIVA", 756, "BOG", 9, 0, "MDE", 10, 0, 0),
  Vuelo("VIVA", 769, "MDE", 11, 0, "BAQ", 12, 0, 0),
  Vuelo("AVA", 5643, "BAQ", 14, 0, "MEX", 16, 0, 0),
  Vuelo("COPA", 1234, "CTG", 10, 0, "PTY", 11, 30, 0),
  Vuelo("AVA", 4321, "CTG", 9, 30, "SMR", 10, 0, 0),
  Vuelo("COPA", 7631, "SMR", 10, 50, "PTY", 11, 50, 0),
  Vuelo("TURKISH", 7799, "CLO", 7, 0, "IST", 14, 0, 3),
  Vuelo("QATAR", 5566, "IST", 23, 0, "SVO", 2, 0, 0)
)
/*def formarIt(vuelo: Vuelo, vuelos: List[Vuelo], cod1: String):Itinerario ={
  def aux(vuelos: List[Vuelo]): Itinerario = {
    if (vuelos.isEmpty) List(vuelo):Itinerario
    else if (vuelos.head.Dst == vuelo.Org && vuelos.head.Org != vuelo.Dst) {
      vuelos.head :: aux(vuelos.tail)
    } else {
      aux(vuelos.tail)
    }
  }
  val resultado=aux(vuelos) match{
  case l=> if(l.head.Dst==cod1) l
  case _=>Nil
  }

}*/
def formarIt(vuelo: Vuelo, vuelos: List[Vuelo]): Itinerario = {
  if (vuelos.isEmpty) List(vuelo)
  else if (vuelos.head.Dst == vuelo.Org && vuelos.head.Org != vuelo.Dst) {
    vuelos.head :: formarIt(vuelo, vuelos.tail)
  } else {
    formarIt(vuelo, vuelos.tail)
  }
}

def validar(vuelo:Vuelo,vuelos:List[Vuelo],acumulado:List[Vuelo],contador:Int):List[Vuelo]={
  if (vuelos(contador).Dst == vuelo.Org && vuelos.head.Org != vuelo.Dst) vuelos.head::acumulado
  else Nil
  
}


/*def formarIt(cod1:String,vueloF:Vuelo,vuelos:List[Vuelo]):Itinerario={
  def aux(vueloF:Vuelo,vuelos:List[Vuelo],acumulado:List[Vuelo]):Itinerario={
  if(acumulado.head.Dst==cod1) acumulado else formarIt()
  }
 
}*/

/*def formarIt(vuelo:Vuelo,vuelos:List[Vuelo]):Itinerario={
  for{
    v<-vuelos
    if (v.Dst == vuelo.Org && vuelos.head.Org != vuelo.Dst)
  }yield 
}*/
def itinerariosC(
    vuelos: List[Vuelo],
    areopuerto: List[Aeropuerto]
): (String, String) => List[Itinerario] = {
  def aux(cod1: String, cod2: String): List[Itinerario] = {

    val vuelos_cod2 = vuelos filter (vuelo => vuelo.Dst == cod2)

    vuelos_cod2.map(vuelo=>formarIt(vuelo,vuelos))
  }

  aux
}


val itsCurso = itinerariosC(vuelosCurso, aeropuertosCurso)
itsCurso("MID", "SVCS")
//formarItinerario(Vuelo("IBERIA", 507, "MAD", 16, 0, "SVO", 1, 20, 0),vuelosCurso)

val its2 = itsCurso("CLO", "SVCS")

val its3= itsCurso("CLO","SVO")

val vuelos_cod2 = vuelosCurso filter (vuelo => vuelo.Dst == "SVO")

val primera= formarIt(vuelos_cod2.tail.tail.head,vuelosCurso)

val segunda=formarIt(primera.head,vuelosCurso)

val tercera=formarIt(segunda.head,vuelosCurso)

formarIt(formarIt(formarIt(vuelos_cod2.tail.tail.head,vuelosCurso).head,vuelosCurso).head,vuelosCurso)



//chat gpt ---------------------------------------------------------------------------------------------------------------------------------------------

def itinerarios(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
  // Crear un mapa de aeropuertos para un acceso más rápido
  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap

  def generarItinerarios(cod1: String, cod2: String, visitados: Set[String]): List[Itinerario] = {
    if (cod1 == cod2) // Si se llega al destino, devolver una lista vacía
      List(Nil)
    else {
      val vuelosDesdeCod1 = vuelos.filter(_.Org == cod1) // Filtrar vuelos que salen desde cod1
      vuelosDesdeCod1.flatMap { vuelo =>
        if (!visitados(vuelo.Dst)) {
          val itinerariosRestantes = generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
          itinerariosRestantes.map(vuelo :: _)
        } else {
          Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
        }
      }
    }
  }

  // Función final que devuelve los itinerarios dados los códigos de los aeropuertos
  (cod1: String, cod2: String) => {
    val aeropuerto1 = aeropuertosMap.get(cod1)
    val aeropuerto2 = aeropuertosMap.get(cod2)
    (aeropuerto1, aeropuerto2) match {
      case (Some(airport1), Some(airport2)) =>
        generarItinerarios(cod1, cod2, Set(cod1))
      case _ => Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
    }
  }
}

// Uso de la función itinerarios
val obtenerItinerarios = itinerarios(vuelosCurso, aeropuertosCurso)
//val itinerariosPosibles = obtenerItinerarios("CLO", "SVO")
//itinerariosPosibles.foreach(println)


/*En esta implementación, primero creamos un mapa de aeropuertos para facilitar el acceso a los datos. Luego, definimos una función interna generarItinerarios que realiza la recursión para encontrar todos los itinerarios posibles. Utilizamos un conjunto (Set) para llevar un registro de los aeropuertos visitados y evitar ciclos infinitos.

Finalmente, devolvemos una función que toma los códigos de dos aeropuertos y devuelve una lista de itinerarios posibles. Si alguno de los aeropuertos no existe en la lista proporcionada, la función devolverá una lista vacía.*/

obtenerItinerarios("MID","SVCS")

obtenerItinerarios("CLO","SVCS")

obtenerItinerarios("CLO","SVO")

obtenerItinerarios("CLO","MEX")

obtenerItinerarios("CTG","PTY")

//version 1.0------------------------------------------------------------------------------------------------------------------------

aeropuertosCurso.map(aeropuerto=>aeropuerto.cod->aeropuerto).toMap
