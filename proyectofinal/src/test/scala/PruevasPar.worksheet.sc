import common._

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

def itinerarios(
    vuelos: List[Vuelo],
    aeropuertos: List[Aeropuerto]
): (String, String) => List[Itinerario] = {
  // Crear un mapa de aeropuertos para un acceso más rápido
  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap

  def generarItinerarios(
      cod1: String,
      cod2: String,
      visitados: Set[String]
  ): List[Itinerario] = {
    if (cod1 == cod2) // Si se llega al destino, devolver una lista vacía
      List(Nil)
    else {
      val vuelosDesdeCod1 =
        vuelos.filter(_.Org == cod1) // Filtrar vuelos que salen desde cod1
      vuelosDesdeCod1.flatMap { vuelo =>
        if (!visitados(vuelo.Dst)) {
          val itinerariosRestantes =
            generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
          itinerariosRestantes.map(vuelo :: _)
        } else {
          Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
        }
      }
    }
  }

  // Función final que devuelve los itinerarios dados los códigos de los aeropuertos
  (cod1: String, cod2: String) => {
    // verificar si los aeropuertos existen
    if (aeropuertosMap.contains(cod1) && aeropuertosMap.contains(cod2))
      generarItinerarios(cod1, cod2, Set(cod1))
    else Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
  }
}

def itinerariosPar1(
    vuelos: List[Vuelo],
    aeropuertos: List[Aeropuerto]
): (String, String) => List[Itinerario] = {
  // Crear un mapa de aeropuertos para un acceso más rápido
  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap

  def generarItinerarios(
      cod1: String,
      cod2: String,
      visitados: Set[String]
  ): List[Itinerario] = {
    if (cod1 == cod2) // Si se llega al destino, devolver una lista vacía
      List(Nil)
    else {
      val vuelosDesdeCod1 = vuelos.filter(_.Org == cod1)
      val vuelosDesdeCod1A =
        vuelosDesdeCod1.slice(0, vuelosDesdeCod1.length / 2)
      val vuelosDesdeCod1B = vuelosDesdeCod1.slice(
        vuelosDesdeCod1.length / 2,
        vuelosDesdeCod1.length
      )

      // Filtrar vuelos que salen desde cod1
      val (vuelosCod1A, vuelosCod1B) = parallel(
        vuelosDesdeCod1A.flatMap { vuelo =>
          if (!visitados(vuelo.Dst)) {
            val itinerariosRestantes =
              generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
            itinerariosRestantes.map(vuelo :: _)
          } else {
            Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
          }
        },
        vuelosDesdeCod1B.flatMap { vuelo =>
          if (!visitados(vuelo.Dst)) {
            val itinerariosRestantes =
              generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
            itinerariosRestantes.map(vuelo :: _)
          } else {
            Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
          }
        }
      )

      vuelosCod1A ++ vuelosCod1B
    }
  }

  // Función final que devuelve los itinerarios dados los códigos de los aeropuertos
  (cod1: String, cod2: String) => {
    // verificar si los aeropuertos existen
    if (aeropuertosMap.contains(cod1) && aeropuertosMap.contains(cod2))
      generarItinerarios(cod1, cod2, Set(cod1))
    else Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
  }
}



def itinerariosPar2(
    vuelos: List[Vuelo],
    aeropuertos: List[Aeropuerto]
): (String, String) => List[Itinerario] = {
  // Crear un mapa de aeropuertos para un acceso más rápido
  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap

  def generarItinerarios(
      cod1: String,
      cod2: String,
      visitados: Set[String]
  ): List[Itinerario] = {
    if (cod1 == cod2) // Si se llega al destino, devolver una lista vacía
      List(Nil)
    else {
      val vuelosDesdeCod1 = vuelos.filter(_.Org == cod1)

      val vuelosDesdeCod1A =vuelosDesdeCod1.slice(0, vuelosDesdeCod1.length / 4)

      val vuelosDesdeCod1B = vuelosDesdeCod1.slice(
        vuelosDesdeCod1.length / 4,
        vuelosDesdeCod1.length / 3
      )
      val vuelosDesdeCod1C = vuelosDesdeCod1.slice(
        vuelosDesdeCod1.length / 3,
        vuelosDesdeCod1.length / 2
      )
      val vuelosDesdeCod1D = vuelosDesdeCod1.slice(
        vuelosDesdeCod1.length / 2,
        vuelosDesdeCod1.length
      )

      // Filtrar vuelos que salen desde cod1
      val (vuelosCod1A, vuelosCod1B,vuelosCod1C,vuelosCod1D) = parallel(
       vuelosDesdeCod1A.flatMap { vuelo =>
          if (!visitados(vuelo.Dst)) {
            val itinerariosRestantes =
              generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
            itinerariosRestantes.map(vuelo :: _)
          } else {
            Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
          }
        },
        vuelosDesdeCod1B.flatMap { vuelo =>
          if (!visitados(vuelo.Dst)) {
            val itinerariosRestantes =
              generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
            itinerariosRestantes.map(vuelo :: _)
          } else {
            Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
          }
        },
        vuelosDesdeCod1C.flatMap { vuelo =>
          if (!visitados(vuelo.Dst)) {
            val itinerariosRestantes =
              generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
            itinerariosRestantes.map(vuelo :: _)
          } else {
            Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
          }
        },
        vuelosDesdeCod1D.flatMap { vuelo =>
          if (!visitados(vuelo.Dst)) {
            val itinerariosRestantes =
              generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
            itinerariosRestantes.map(vuelo :: _)
          } else {
            Nil // Si ya se visitó el aeropuerto de destino, no se incluye este vuelo en el itinerario
          }
        }
      )

      vuelosCod1A ++ vuelosCod1B ++ vuelosCod1C ++ vuelosCod1D
    }
  }

  // Función final que devuelve los itinerarios dados los códigos de los aeropuertos
  (cod1: String, cod2: String) => {
    // verificar si los aeropuertos existen
    if (aeropuertosMap.contains(cod1) && aeropuertosMap.contains(cod2))
      generarItinerarios(cod1, cod2, Set(cod1))
    else Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
  }
}


def itinerariosPar3(
    vuelos: List[Vuelo],
    aeropuertos: List[Aeropuerto]
): (String, String) => List[Itinerario] = {

  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap

  def generarItinerarios(
      cod1: String,
      cod2: String,
      visitados: Set[String]
  ): List[Itinerario] = {
    if (cod1 == cod2) 
      List(Nil)
    else {
      val vuelosDesdeCod1 = vuelos.filter(_.Org == cod1) 
      val result=vuelosDesdeCod1.map { vuelo =>
        task{if (!visitados(vuelo.Dst)) {
          val itinerariosRestantes =
          generarItinerarios(vuelo.Dst, cod2, visitados + vuelo.Dst)
          itinerariosRestantes.map(vuelo :: _)}
          
         else {
          Nil 
        }}
      }

      result.flatMap(_.join())
    }
  }


  (cod1: String, cod2: String) => {
   
    if (aeropuertosMap.contains(cod1) && aeropuertosMap.contains(cod2))
      generarItinerarios(cod1, cod2, Set(cod1))
    else Nil 
  }
}





val obtenerItinerarios = itinerarios(vuelosCurso, aeropuertosCurso)
val obtenerItinerarios3 = itinerariosPar3(vuelosCurso, aeropuertosCurso)
//val itinerariosPosibles = obtenerItinerarios("CLO", "SVO")
//itinerariosPosibles.foreach(println)

/*En esta implementación, primero creamos un mapa de aeropuertos para facilitar el acceso a los datos. Luego, definimos una función interna generarItinerarios que realiza la recursión para encontrar todos los itinerarios posibles. Utilizamos un conjunto (Set) para llevar un registro de los aeropuertos visitados y evitar ciclos infinitos.

Finalmente, devolvemos una función que toma los códigos de dos aeropuertos y devuelve una lista de itinerarios posibles. Si alguno de los aeropuertos no existe en la lista proporcionada, la función devolverá una lista vacía.*/

obtenerItinerarios("MID", "SVCS") == obtenerItinerarios3("MID", "SVCS")

obtenerItinerarios("CLO", "SVCS") == obtenerItinerarios3("CLO", "SVCS")

obtenerItinerarios("CLO", "SVO") == obtenerItinerarios3("CLO", "SVO")

obtenerItinerarios("CLO", "MEX") == obtenerItinerarios3("CLO", "MEX")

obtenerItinerarios("CTG", "PTY") == obtenerItinerarios3("CTG", "PTY")
