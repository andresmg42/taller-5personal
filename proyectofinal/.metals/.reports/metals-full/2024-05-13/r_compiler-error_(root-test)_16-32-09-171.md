file://<WORKSPACE>/src/test/scala/pruebas.worksheet.sc
### file%3A%2F%2F%2Fhome%2Fandresuv%2FcuartoSemestre%2FPFC%2Fproyecto%2Fproyectocode%2Fproyectofinal%2Fsrc%2Ftest%2Fscala%2Fpruebas.worksheet.sc:10: error: illegal character '\u201d'
Aeropuerto ( ”MDE , 200 , 600 , −500) , // Medellin
             ^

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.12
Classpath:
/modules [exists ], <WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/test-classes-Metals-qPj7FbbbTpKZKNc25hcy7Q== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.9.9/semanticdb-javac-0.9.9.jar [exists ], <WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-qPj7FbbbTpKZKNc25hcy7Q== [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest_2.13/3.2.17/scalatest_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-core_2.13/3.2.17/scalatest-core_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-featurespec_2.13/3.2.17/scalatest-featurespec_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-flatspec_2.13/3.2.17/scalatest-flatspec_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-freespec_2.13/3.2.17/scalatest-freespec_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-funsuite_2.13/3.2.17/scalatest-funsuite_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-funspec_2.13/3.2.17/scalatest-funspec_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-propspec_2.13/3.2.17/scalatest-propspec_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-refspec_2.13/3.2.17/scalatest-refspec_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-wordspec_2.13/3.2.17/scalatest-wordspec_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-diagrams_2.13/3.2.17/scalatest-diagrams_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-matchers-core_2.13/3.2.17/scalatest-matchers-core_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-shouldmatchers_2.13/3.2.17/scalatest-shouldmatchers_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-mustmatchers_2.13/3.2.17/scalatest-mustmatchers_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalatest/scalatest-compatible/3.2.17/scalatest-compatible-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalactic/scalactic_2.13/3.2.17/scalactic_2.13-3.2.17.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_2.13/2.1.0/scala-xml_2.13-2.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalameta/mdoc-runtime_2.13/2.5.2/mdoc-runtime_2.13-2.5.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/fansi_2.13/0.4.0/fansi_2.13-0.4.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/pprint_2.13/0.8.1/pprint_2.13-0.8.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scalameta/mdoc-interfaces/2.5.2/mdoc-interfaces-2.5.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/geirsson/metaconfig-pprint_2.13/0.12.0/metaconfig-pprint_2.13-0.12.0.jar [exists ]
Options:
-Yrangepos -Xplugin-require:semanticdb


action parameters:
uri: file://<WORKSPACE>/src/test/scala/pruebas.worksheet.sc
text:
```scala
case class Aeropuerto(cod:String,x:Int,y:Int,GMT:Int)

case class Vuelo(Aln: String,Num:Int,Org:String,HS:Int,MS:Int,Dst:String,HL:Int,ML:Int,Esc:Int)

type Itinerario=List[Vuelo]

val  aeropuertosCurso = List (
Ae aeropuerto ( "CLO", 100 , 200 , −500) , // C a l i
Aeropuerto ( "BOG" , 300 , 500 , −500) , // Bogotá
Aeropuerto ( ”MDE , 200 , 600 , −500) , // Medellin
Aeropuerto ( ”BAQ” , 350 , 850 , −500) , // Barranquilla
Aeropuerto ( ”SMR” , 400 , 950 , −500) , // Santa Marta
Aeropuerto ( ”CTG” , 300 , 800 , −500) , // Cartagena
Aeropuerto ( ”PTY” , 400 , 1000 , −500) , // Ciudad de Panamá
Aeropuerto ( ”JFK” , 2000 , 2000 , −400) , // Nueva York
Aeropuerto ( ”MIA” , 1000 , 2000 , −500) , // Miami
Aeropuerto ( ”MEX” , 1000 , 1000 , −600) , // Ciudad de México
Aeropuerto ( ”MAD” , 5000 , 5000 , 1 0 0 ) , // Madrid
Aeropuerto ( ”SVCS” , 400 , 1000 , −600) , // Caracas
Aeropuerto ( ”MID” , 500 , 100 , −600) , // Merida
Aeropuerto ( ”AUA” , 500 , 2000 , −400) , // Aruba
Aeropuerto ( ”IST” , 9000 , 9000 , 3 0 0 ) , // Estambul
Aeropuerto ( ”HND” , 10000 , 12000 , 9 0 0 ) , // Tokio
Aeropuerto ( ”DXB” , 9500 , 11500 , 4 0 0 ) , // Dubai
Aeropuerto ( ”SVO” , 12500 , 12500 , 300 ) // Moscú
)
val  vuelosCurso = List (
Vuelo ( ”AIRVZLA” , 601 , ”MID” , 5 , 0 , ”SVCS” , 6 , 0 , 0 ) ,
Vuelo ( ”AIRVZLA” , 602 , ”SVCS” , 6 , 30 , ”MID” , 7 , 30 , 0 ) ,
Vuelo ( ”AVA” , 9432 , ”CLO” , 7 , 0 , ”SVO” , 2 , 20 , 4 ) ,
Vuelo ( ”AVA” , 9432 , ”CLO” , 7 , 0 , ”BOG” , 8 , 0 , 0 ) ,
Vuelo ( ”IBERIA” , 505 , ”BOG” , 18 , 0 , ”MAD” , 12 , 0 , 0 ) ,
Vuelo ( ”IBERIA” , 506 , ”MAD” , 14 , 0 , ”SVO” , 23 , 20 , 0 ) ,
Vuelo ( ”IBERIA” , 507 , ”MAD” , 16 , 0 , ”SVO” , 1 , 20 , 0 ) ,
Vuelo ( ”LATAM” , 787 , ”BOG” , 17 , 0 , ”MEX” , 19 , 0 , 0 ) ,
Vuelo ( ”VIVA” , 756 , ”BOG” , 9 , 0 , ”MDE” , 10 , 0 , 0 ) ,
Vuelo ( ”VIVA” , 769 , ”MDE” , 11 , 0 , ”BAQ” , 12 , 0 , 0 ) ,
Vuelo ( ”AVA” , 5643 , ”BAQ” , 14 , 0 , ”MEX” , 16 , 0 , 0 ) ,
Vuelo ( ”COPA” , 1234 , ”CTG” , 10 , 0 , ”PTY” , 11 , 30 , 0 ) ,
Vuelo ( ”AVA” , 4321 , ”CTG” , 9 , 30 , ”SMR” , 10 , 0 , 0 ) ,
Vuelo ( ”COPA” , 7631 , ”SMR” , 10 , 50 , ”PTY” , 11 , 50 , 0 ) ,
Vuelo ( ”TURKISH” , 7799 , ”CLO” , 7 , 0 , ”IST” , 14 , 0 , 3 ) ,
Vuelo ( ”QATAR” , 5566 , ”IST” , 23 , 0 , ”SVO” , 2 , 0 , 0 ) ,
)


def itinerarios((vuelos:Lit[Vuelo], areopuerto:List[Aeropuerto])):(String,String)=>List[Itinerario]={
  def aux(cod1:String,cod2:String):List[Itinerario]={
    val vuelos_cod2=for{
    vuelo<-vuelos
    if (vuelo.Dst==cod2) 
    }yield vuelo
  }
aux
}

itinerarios(vuelosCurso,aeropuertosCurso)(”SVCS”,”SVO”)
```



#### Error stacktrace:

```
scala.meta.internal.tokenizers.Reporter.syntaxError(Reporter.scala:23)
	scala.meta.internal.tokenizers.Reporter.syntaxError$(Reporter.scala:23)
	scala.meta.internal.tokenizers.Reporter$$anon$1.syntaxError(Reporter.scala:33)
	scala.meta.internal.tokenizers.Reporter.syntaxError(Reporter.scala:25)
	scala.meta.internal.tokenizers.Reporter.syntaxError$(Reporter.scala:25)
	scala.meta.internal.tokenizers.Reporter$$anon$1.syntaxError(Reporter.scala:33)
	scala.meta.internal.tokenizers.LegacyScanner.fetchOther$1(LegacyScanner.scala:476)
	scala.meta.internal.tokenizers.LegacyScanner.fetchToken(LegacyScanner.scala:481)
	scala.meta.internal.tokenizers.LegacyScanner.nextToken(LegacyScanner.scala:214)
	scala.meta.internal.tokenizers.LegacyScanner.foreach(LegacyScanner.scala:982)
	scala.meta.internal.tokenizers.ScalametaTokenizer.uncachedTokenize(ScalametaTokenizer.scala:23)
	scala.meta.internal.tokenizers.ScalametaTokenizer.$anonfun$tokenize$1(ScalametaTokenizer.scala:16)
	scala.collection.concurrent.TrieMap.getOrElseUpdate(TrieMap.scala:962)
	scala.meta.internal.tokenizers.ScalametaTokenizer.tokenize(ScalametaTokenizer.scala:16)
	scala.meta.internal.tokenizers.ScalametaTokenizer$$anon$2.apply(ScalametaTokenizer.scala:331)
	scala.meta.tokenizers.Api$XtensionTokenizeDialectInput.tokenize(Api.scala:25)
	scala.meta.tokenizers.Api$XtensionTokenizeInputLike.tokenize(Api.scala:14)
	scala.meta.internal.parsers.ScannerTokens$.apply(ScannerTokens.scala:994)
	scala.meta.internal.parsers.ScalametaParser.<init>(ScalametaParser.scala:33)
	scala.meta.parsers.Parse$$anon$1.apply(Parse.scala:35)
	scala.meta.parsers.Api$XtensionParseDialectInput.parse(Api.scala:25)
	scala.meta.internal.semanticdb.scalac.ParseOps$XtensionCompilationUnitSource.toSource(ParseOps.scala:17)
	scala.meta.internal.semanticdb.scalac.TextDocumentOps$XtensionCompilationUnitDocument.toTextDocument(TextDocumentOps.scala:206)
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:54)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticdbTextDocument$1(ScalaPresentationCompiler.scala:400)
```
#### Short summary: 

file%3A%2F%2F%2Fhome%2Fandresuv%2FcuartoSemestre%2FPFC%2Fproyecto%2Fproyectocode%2Fproyectofinal%2Fsrc%2Ftest%2Fscala%2Fpruebas.worksheet.sc:10: error: illegal character '\u201d'
Aeropuerto ( ”MDE , 200 , 600 , −500) , // Medellin
             ^