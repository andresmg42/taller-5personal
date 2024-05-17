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






//chat gpt ---------------------------------------------------------------------------------------------------------------------------------------------

/*def itinerarios(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
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
}*/

// Uso de la función itinerarios
val obtenerItinerarios = itinerarios(vuelosCurso, aeropuertosCurso)
val obtenerItinerarios3 = itinerariosInversos(vuelosCurso, aeropuertosCurso)
//val itinerariosPosibles = obtenerItinerarios("CLO", "SVO")
//itinerariosPosibles.foreach(println)

/*En esta implementación, primero creamos un mapa de aeropuertos para facilitar el acceso a los datos. Luego, definimos una función interna generarItinerarios que realiza la recursión para encontrar todos los itinerarios posibles. Utilizamos un conjunto (Set) para llevar un registro de los aeropuertos visitados y evitar ciclos infinitos.

Finalmente, devolvemos una función que toma los códigos de dos aeropuertos y devuelve una lista de itinerarios posibles. Si alguno de los aeropuertos no existe en la lista proporcionada, la función devolverá una lista vacía.*/

obtenerItinerarios("MID", "SVCS") == obtenerItinerarios3("MID", "SVCS")

obtenerItinerarios("CLO", "SVCS") == obtenerItinerarios3("CLO", "SVCS")

obtenerItinerarios("CLO", "SVO") == obtenerItinerarios3("CLO", "SVO")

obtenerItinerarios("CLO", "MEX") == obtenerItinerarios3("CLO", "MEX")

obtenerItinerarios("CTG", "PTY") == obtenerItinerarios3("CTG", "PTY")

obtenerItinerarios3("CLO", "SVO")

obtenerItinerarios("CLO", "SVO")
//version 1.0------------------------------------------------------------------------------------------------------------------------

def itinerarios(
    vuelos: List[Vuelo],
    aeropuertos: List[Aeropuerto]
): (String, String) => List[Itinerario] = {
  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap
  def formarItinerarios(
      cod1: String,
      cod2: String,
      visitados: Set[String]
  ): List[Itinerario] = {
    if (cod1 == cod2) List(Nil)
    else {
      val vuelosDesdeCod1 = vuelos.filter(_.Org == cod1)
      for {
        v <- vuelosDesdeCod1
        if !visitados(v.Dst)
        itRestante <- formarItinerarios(v.Dst, cod2, visitados + v.Dst)

      } yield v :: itRestante

    }
  }

  (cod1: String, cod2: String) => {
    val aeropuerto1 = aeropuertosMap.get(cod1)
    val aeropuerto2 = aeropuertosMap.get(cod2)
    (aeropuerto1, aeropuerto2) match {
      case (Some(airport1), Some(airport2)) =>
        formarItinerarios(cod1, cod2, Set(cod1))
      case _ =>
        Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
    }
  }
}

/*def itinerariosInversos(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap

  def formarItinerarios(cod1: String, cod2: String, visitados: Set[String]): List[Itinerario] = {
    if (cod1 == cod2)
      List(Nil)
    else {
      val vuelosHastaCod2 = vuelos.filter(_.Dst == cod2)
      vuelosHastaCod2.flatMap { v =>
        if (!visitados(v.Org)) {
          formarItinerarios(cod1, v.Org, visitados + v.Org).map(_:+v)
        } else {
          Nil
        }
      }
    }
  }

  (cod1: String, cod2: String) => {
    val aeropuerto1 = aeropuertosMap.get(cod1)
    val aeropuerto2 = aeropuertosMap.get(cod2)
    (aeropuerto1, aeropuerto2) match {
      case (Some(airport1), Some(airport2)) =>
        formarItinerarios(cod1, cod2, Set(cod2)) // Empezamos desde el aeropuerto de destino (cod2)
      case _ => Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
    }
  }
}
 */

def itinerariosInversos(
    vuelos: List[Vuelo],
    aeropuertos: List[Aeropuerto]
): (String, String) => List[Itinerario] = {
  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap

  def formarItinerarios(
      cod1: String,
      cod2: String,
      visitados: Set[String]
  ): List[Itinerario] = {
    if (cod1 == cod2)
      List(Nil)
    else {
      val vuelosHastaCod2 = vuelos.filter(_.Dst == cod2)

      for {
        v <- vuelosHastaCod2
        if !visitados(v.Org)
        itRestante <- formarItinerarios(cod1, v.Org, visitados + v.Org)
      } yield itRestante :+ v

    }
  }

  (cod1: String, cod2: String) => {
    val aeropuerto1 = aeropuertosMap.get(cod1)
    val aeropuerto2 = aeropuertosMap.get(cod2)
    (aeropuerto1, aeropuerto2) match {
      case (Some(airport1), Some(airport2)) =>
        formarItinerarios(
          cod1,
          cod2,
          Set(cod2)
        ) // Empezamos desde el aeropuerto de destino (cod2)
      case _ =>
        Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
    }
  }
}

/*def itinerariosOpt(vuelos:List[Vuelo],aeropuertos:List[Aeropuerto]):(String,String)=>List[Itinerario]={
val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap
  def formarItinerarios(v:Vuelo,cod1:String,cod2:String,visitados:Set[String]):List[Itinerario]={
    if(cod1==cod2) List(List(v))
    else{
      val vuelosDesdeCod1=vuelos.filter(_.Org==cod1)
      for{
        v<-vuelosDesdeCod1
        if!visitados(v.Dst)
        itRestante<-formarItinerarios(v,v.Dst,cod2,visitados + v.Dst)

     } yield v::itRestante



    }
  }

  (cod1: String, cod2: String) => {
    val aeropuerto1 = aeropuertosMap.get(cod1)
    val aeropuerto2 = aeropuertosMap.get(cod2)
    (aeropuerto1, aeropuerto2) match {
      case (Some(airport1), Some(airport2)) =>
        formarItinerarios(cod1, cod2, Set(cod1))
      case _ => Nil // Si alguno de los aeropuertos no existe, devolver una lista vacía
    }
  }
}*/

/*def sumarHoras(h1:Int,m1:Int,h2:Int,m2:Int,op:String):Int={

val hora_num1= m1 match{
  case 0=>(h1.toString+m1.toString+"0").toInt
  case _=>(h1.toString + m1.toString).toInt
}
val hora_num2= m2 match{
  case 0=>(h2.toString+m2.toString+"0").toInt
  case _=>(h2.toString + m2.toString).toInt
}
val result= op match{
  case"+"=>(hora_num1 + hora_num2) match{ case }
  case "-"=>(hora_num1-hora_num2)
}

}*/

def sumarHoras(h1: Int, m1: Int, h2: Int, m2: Int, op: Char): (Int, Int) = {
  val H1 = h1 * 60 + m1
  val H2 = h2 * 60 + m2
  val suma = op match {
    case '+' => H1 + H2
    case '-' => H1 - H2
  }
  if (suma < 0) {
    val result = suma + 1440

    (result / 60, result % 60)
  } else (suma / 60, suma % 60)
}

def sumarHoras2(h1: Int, m1: Int, h2: Int, m2: Int): (Int, Int) = {
  val H1 = h1 * 60 + m1
  val H2 = h2 * 60 + m2
  val suma = H1 + H2
  if (suma < 0) {
    val result = suma + 1440

    (result / 60, result % 60)
  } else (suma / 60, suma % 60)
}

val aeropuertosMap =
  aeropuertosCurso.map(airport => airport.cod -> airport).toMap

def convertirHorasGMT(h: Int, m: Int, gmt: Int): (Int, Int) =
  if (gmt < 0) sumarHoras(h, m, -gmt, 0, '+') else sumarHoras(h, m, gmt, 0, '-')

obtenerItinerarios("CLO", "SVO")(1)
  .map(vuelo => {
    val gmtO = aeropuertosMap(vuelo.Org).GMT / 100
    val gmtD = aeropuertosMap(vuelo.Dst).GMT / 100
    val HGMTO = convertirHorasGMT(vuelo.HS, vuelo.MS, gmtO)
    val HGMTD = convertirHorasGMT(vuelo.HL, vuelo.ML, gmtD)
    vuelo.Num -> sumarHoras(HGMTD._1, HGMTD._2, HGMTO._1, HGMTO._2, '-')

  })
  .toMap

val l = obtenerItinerarios("CLO", "SVO")(1)
  .map(vuelo => {
    val gmtO = aeropuertosMap(vuelo.Org).GMT / 100
    val gmtD = aeropuertosMap(vuelo.Dst).GMT / 100
    val HGMTO = convertirHorasGMT(vuelo.HS, vuelo.MS, gmtO)
    val HGMTD = convertirHorasGMT(vuelo.HL, vuelo.ML, gmtD)
    sumarHoras(HGMTD._1, HGMTD._2, HGMTO._1, HGMTO._2, '-')

  })

val it=obtenerItinerarios("CLO", "SVO")(1)

//l.reduceLeft(sumarHoras())

"esta funcion calcula el tiempo total de vuelo pero lo devuelve en horas y utiliza recursion"
def tiempoVueloIt(itinerario: Itinerario): (Int, Int) = {
  val horasViajeItinerario = itinerario.map(vuelo => {
    val gmtO = aeropuertosMap(vuelo.Org).GMT / 100
    val gmtD = aeropuertosMap(vuelo.Dst).GMT / 100
    val HGMTO = convertirHorasGMT(vuelo.HS, vuelo.MS, gmtO)
    val HGMTD = convertirHorasGMT(vuelo.HL, vuelo.ML, gmtD)
    sumarHoras(HGMTD._1, HGMTD._2, HGMTO._1, HGMTO._2, '-')

  })
  def sumaTiempo(horasVIt: List[Tuple2[Int, Int]]): (Int, Int) = {
    if (horasVIt.isEmpty) (0, 0)
    else {
      val h1 = horasVIt.head
      val h2 = sumaTiempo(horasVIt.tail)
      sumarHoras(h1._1, h1._2, h2._1, h2._2, '+')
    }
  }
  sumaTiempo(horasViajeItinerario)

}

tiempoVueloIt(obtenerItinerarios("CLO", "SVO")(1))

val h1 = sumarHoras(1, 0, 12, 0, '+')
sumarHoras(h1._1, h1._2, 7, 20, '+')

"esta funcion caclula el tiempo total de vuelo pero lo devuelve en minutos y no utiliza recursion"
def tiempoVueloIt2(itinerario: Itinerario): Int = {
  val horasViajeItinerario = itinerario.map(vuelo => {
    val gmtO = aeropuertosMap(vuelo.Org).GMT / 100
    val gmtD = aeropuertosMap(vuelo.Dst).GMT / 100
    val HGMTO = convertirHorasGMT(vuelo.HS, vuelo.MS, gmtO)
    val HGMTD = convertirHorasGMT(vuelo.HL, vuelo.ML, gmtD)
    sumarHoras(HGMTD._1, HGMTD._2, HGMTO._1, HGMTO._2, '-')

  })

  horasViajeItinerario.map(hora => hora._1 * 60 + hora._2).sum

}

/*def tiempoViaje(itinerario: Itinerario): Int = {


def sumaT(itinerario:Itinerario):(Int,Int)={
  val aeropuertosMap = aeropuertosCurso.map(airport => airport.cod -> airport).toMap
  if(itinerario.isEmpty) (0,0)
  else{

    val h1=itinerario.head.HS
    val m1=itinerario.head.MS
    val gmt1 = aeropuertosMap(itinerario.head.Org).GMT / 100
    val (h2,m2)=sumaT(itinerario.tail)
    val (hg1,mg2)=convertirHorasGMT(h1,m1,gmt1)

    sumarHoras(hg1,mg2,h2,m2,'+')
  }
}
val tH=sumaT(itinerario)
tH._1*60+tH._2

}*/



/*tiempoEsperaIt(it)

val tiempos=it.map(vuelo=>convertirHorasGMT(vuelo.HS,vuelo.MS,aeropuertosMap(vuelo.Org).GMT/100)):+convertirHorasGMT(it.last.HL,it.last.ML,aeropuertosMap(it.last.Dst).GMT/100)
val tiempos_diff=tiempos.zip(tiempos.tail).map{case (a,b)=>sumarHoras(b._1,b._2,a._1,a._2,'-')}
val tiempos_viaje=tiempos_diff.map(t=>t._1*60+t._2).sum*/



"esta funcion calcula el tiempo total de vuelo de un itinerario"

def tiempoViaje(it:Itinerario):Int={
  if(it.isEmpty) 0
  else{
  val t_entre_Org=it.map(v=>convertirHorasGMT(v.HS,v.MS,aeropuertosMap(v.Org).GMT/100))
  val t_total=t_entre_Org:+convertirHorasGMT(it.last.HL,it.last.ML,aeropuertosMap(it.last.Dst).GMT/100)
  val tiempos_diff=t_total.zip(t_total.tail).map{case(a,b)=>sumarHoras(b._1,b._2,a._1,a._2,'-')}
  tiempos_diff.map(t=>t._1*60+t._2).sum

  }
  
}


tiempoViaje(it)-tiempoVueloIt2(it)



"esta funcion cula el tiempo de espera en minutos sumando el tiempo que transcurre entre la llegada de un vuelo y el comienzo del siguiente.!VALORES VARIABLES"
def tiempoEsperaIt(itinerario: Itinerario): Int = {
  if (itinerario.isEmpty) 0
  else {
    val result = for {
      i <- 0 until itinerario.length-1
      v = itinerario(i)
      vNext = itinerario(i + 1)

    } yield sumarHoras(vNext.HS, vNext.MS, v.HL, v.ML, '-')
    result.toList
    result.map(hora => hora._1 * 60 + hora._2).sum

  }

}







//L i s t ( Vuelo (AVA, 9 4 3 2 ,CLO, 7 , 0 ,BOG, 8 , 0 , 0 ) , Vuelo ( IBERIA , 5 0 5 ,BOG, 1 8 , 0 ,MAD, 1 2 , 0 , 0 ) , Vuelo ( IBERIA , 5 0 7 ,MAD, 1 6 , 0 ,SVO, 1 , 2 0 , 0 ) )

val gmtS=convertirHorasGMT(7,0,-5)
val gmtL=convertirHorasGMT(18,0,-5)

sumarHoras(gmtL._1,gmtL._2,gmtS._1,gmtS._2,'-')


val gmtS1=convertirHorasGMT(18,0,-5)
val gmtL1=convertirHorasGMT(14,0,1)

sumarHoras(gmtL1._1,gmtL1._2,gmtS1._1,gmtS1._2,'-')

val gmtS2=convertirHorasGMT(14,0,1)
val gmtL2=convertirHorasGMT(23,20,3)

sumarHoras(gmtL2._1,gmtL2._2,gmtS2._1,gmtS2._2,'-')




val lq=List(1)

lq.last