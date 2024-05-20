package object Itinerarios{
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
        Nil 
    }
  }
}

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

def convertirHorasGMT(h: Int, m: Int, gmt: Int): (Int, Int) =if (gmt < 0) sumarHoras(h, m, -gmt, 0, '+') else sumarHoras(h, m, gmt, 0, '-')

"esta funcion caclula el tiempo total de vuelo pero lo devuelve en minutos y no utiliza recursion"
def tiempoVueloIt2(
    itinerario: Itinerario,
    aeropuertos: List[Aeropuerto]
): Int = {

  val aeropuertosMap = aeropuertos.map(airport => airport.cod -> airport).toMap
  val horasViajeItinerario = itinerario.map(vuelo => {
    val gmtO = aeropuertosMap(vuelo.Org).GMT / 100
    val gmtD = aeropuertosMap(vuelo.Dst).GMT / 100
    val HGMTO = convertirHorasGMT(vuelo.HS, vuelo.MS, gmtO)
    val HGMTD = convertirHorasGMT(vuelo.HL, vuelo.ML, gmtD)
    sumarHoras(HGMTD._1, HGMTD._2, HGMTO._1, HGMTO._2, '-')

  })

  horasViajeItinerario.map(hora => hora._1 * 60 + hora._2).sum

}

def tiempoEsperaIt2(itinerario:Itinerario):Int={
  if(itinerario.isEmpty) 0
  else{
    val result=(0 until itinerario.length - 1).map(i =>{
      val v=itinerario(i)
      val vNext=itinerario(i+1)
      sumarHoras(vNext.HS, vNext.MS, v.HL, v.ML, '-')

    }).toList

    result.map(hora => hora._1 * 60 + hora._2).sum
  }
}

def menoresQue_noMenoresQue[T](
    l: List[T],
    v: T,
    comp: (T, T) => Boolean
): (List[T], List[T]) = {
  def aux(l: List[T], l1: List[T], l2: List[T]): (List[T], List[T]) = {
    if (l.isEmpty) (l1, l2)
    else {
      val head = l.head
      val tail = l.tail
      if (comp(head, v)) aux(tail, head :: l1, l2)
      else aux(tail, l1, head :: l2)
    }
  }

  aux(l, List(), List())
}

def quickSort[T](comp: (T, T) => Boolean): List[T] => List[T] = {
  def quick(l: List[T]): List[T] = {

    if (l.isEmpty || l.tail.isEmpty) l
    else {
      val pivot = l.head
      val (less, greater) = menoresQue_noMenoresQue(l.tail, pivot, comp)
      val less1 = quick(less)
      val greater1 = quick(greater)
      less1 ++ (pivot :: greater1)

    }
  }
  quick
}

def itinerariosTiempo2(
    vuelos: List[Vuelo],
    aeropuertos: List[Aeropuerto]
): (String, String) => List[Itinerario] = { (cod1: String, cod2: String) =>
  {
    val it = itinerarios(vuelos, aeropuertos)(cod1, cod2)
    if (it.isEmpty) Nil

    else{
      val tiemposIt = for {
      i <- it

    } yield (tiempoVueloIt2(i,aeropuertos)+tiempoEsperaIt2(i), i)

    val itsTiempo = quickSort[(Int, Itinerario)](
      (a: (Int, Itinerario), b: (Int, Itinerario)) => a._1 < b._1
    )(tiemposIt.toList) map (t => t._2)
      (for {
        i <- 0 until itsTiempo.length
        if i <= 2
      } yield itsTiempo(i)).toList
}
   
    }

}


}