//package search.sol
//
//import search.src.PorterStemmer
//import scala.xml.Node
//import scala.xml.NodeSeq
//import scala.util.matching.Regex
//import java.io.BufferedWriter
//import java.io.FileWriter
//import search.src.StopWords
//import java.io.IOException
//import java.io.FileNotFoundException
//import scala.collection.mutable.HashMap
//
//
///**
// * A class called Index which takes in 4 files and basically runs all of the
// * functions that we would want for preprocessing such as cutting, stemming, 
// * tokenizing, getting the links, page rank, tf, idf, all that good stuff
// * 
// * @param fileName - A string representing the corpus
// * @param titleFile - A string representing the file that we're printing the titles 
// * and ID's too
// * @param indexFile - A string representing the index file that has the tf/idf 
// * scores 
// * and the corresponding words and ID's
// * @param prFile - A string representing the file with the page rank scores.
// */
//
//class Index(fileName: String, titleFile: String, indexFile: String, prFile: String) {
//  // Preprocessing for parsing
//  val mainNode : Array[Node] = (xml.XML.loadFile(fileName) \ "page").toArray
//  val numDocs = mainNode.length
//  val titles: Array[String] = Array.fill(numDocs)(null)
//  val idsArray: Array[Int] = Array.fill(numDocs)(0)
//  for (i <- 0 to mainNode.length - 1) {
//    titles(i) = (mainNode(i) \ "title").text.trim
//    idsArray(i) = (mainNode(i) \ "id").text.trim.toInt
//  }
//  val regex = new Regex("""[^\W_]+'[^\W_]+|[^\W_]+""")
//  val linkRegex = new Regex("""\[\[[^\[]+?\]\]""")
//
//  /**
//   * Once preprocessing has finished, we start our function pageRank which 
//   * essentially does the pageRanking algorithm and scoring for the entire 
//   * corpus. We have epislon value coded at 0.15 and utilize various hash
//   * maps.
//   */
//  private def pageRank() = {
//    val epsilon: Double = 0.15  
//    val linksMap = HashMap[Int, Array[String]]()
//    val r : Array[Double] = Array.fill(numDocs)(0.0)
//    val rP : Array[Double] = Array.fill(numDocs)(1.0/numDocs.toDouble) 
//    
//    for (i <- 0 to numDocs - 1) {
//      val lst = linkRegex.findAllMatchIn(mainNode(i).text.trim
//        .replaceAll("""\n""", "")).toArray
//        .map {aMatch => aMatch.matched}
//        .map(word => word.replaceAll("""\|[\w+\s+\(+\)+]+\]\]|\[\[|\]\]""", ""))
//        .filter(word => titles.contains(word))
//      linksMap.+=((idsArray(i), lst))
//    }
//    
//    /**
//     * A function that implements the Euclidean distance algorithm
//     */
//    def distance(a1: Array[Double], a2: Array[Double]): Double = {
//      var sum = 0.0
//      for (i <- 0 to a1.length - 1) {
//        sum = sum + Math.pow((a1(i) - a2(i)), 2.0).toDouble
//      }
//      Math.sqrt(sum)
//    }
//    
//    while (distance(r, rP) > 0.001){
//      for (i <- 0 to numDocs - 1){
//        r(i) = rP(i)
//      }
//      for (j <- 0 to numDocs - 1){
//        rP(j) = 0.0
//        for (k <- 0 to numDocs - 1){
//          val weight: Double = {
//            if (linksMap(idsArray(k)).contains(titles(j))) {
//              (epsilon/numDocs) + 
//              (1.0 - epsilon)*(1.0/(linksMap(idsArray(k)).length.toDouble))
//            } else {
//              epsilon/numDocs
//            }
//          }
//          rP(j) = rP(j) + weight * r(k)
//        }
//      }  
//    }
//    
//    /**
//     * Start writing to the file for the pageRank File
//     */
//    var bw : BufferedWriter = null
//    try {
//      bw = new BufferedWriter(new FileWriter(prFile))
//      for (i <- 0 to numDocs - 1) {
//        bw.write(idsArray(i).toString)
//        bw.write("\n")
//        bw.write(r(i).toString)
//        bw.write("\n")
//      }        
//    } catch {
//      case e : IOException => println(e)
//      case f : FileNotFoundException => println(f)
//    } finally {
//      try {
//        if (bw != null) bw.close
//      } catch {
//        case e : IOException => println(e)
//      }
//    }
//  }
//
//  /**
//   * A function scoring that does the tf/idf calculations and stores this in a 
//   * nested hashmap.
//   */
//  private def scoring() = {
//    val scoresMap = HashMap[Int, HashMap[String, Double]]()
//    val idfMap = HashMap[String, Double]()
//    for (i <- 0 to numDocs - 1) {
//      val pg = regex.findAllMatchIn(mainNode(i).text
//        .replaceAll("""\[\[[\w+\s+\(+\)+]\||\[\[|\]\]""", "")
//        .replaceAll(""":""", " ")
//        .toLowerCase).toArray.map{aMatch => aMatch.matched}
//        .filter(x => !StopWords.isStopWord(x))
//        .map(word => PorterStemmer.stem(word))
//      val wordToOccurences = HashMap[String, Double]()
//      var max = 1.0
//      for (word <- pg) {
//        var bool = false
//        wordToOccurences.get(word) match {
//          case Some(num) => {
//            wordToOccurences.update(word, num + 1.0)
//            if (num + 1 > max) {
//              max = num + 1.0
//            }
//            bool = true
//          }
//          case None => wordToOccurences.+=((word, 1))
//        }
//        if (!bool) {
//          idfMap.get(word) match {
//            case Some(num) => idfMap.update(word, num + 1.0)
//            case None => idfMap.+=((word, 1))
//          }
//        }
//      }
//      
//      for (word <- pg.distinct) {
//        wordToOccurences.get(word) match {
//          case Some(num) => wordToOccurences.update(word, num/max)
//          case None => throw new RuntimeException("wordToOccurences did not have " + word)
//          
//        }
//      }
//      scoresMap.+=((idsArray(i), wordToOccurences))
//      mainNode(i) = null
//      titles(i) = null
//    }
//    for(pair <- idfMap) {
//      idfMap.update(pair._1, Math.log(numDocs.toDouble/pair._2))
//    }
//    for(pair <- scoresMap) {
//      for(kvPair <- pair._2) {
//        pair._2.update(kvPair._1, kvPair._2 * idfMap(kvPair._1))
//      }
//    }
//    /**
//     * Begins writing to the index file that prints out the tf/idf scores
//     */
//    var bw : BufferedWriter = null
//    try {
//      bw = new BufferedWriter(new FileWriter(indexFile))
//      for (pair <- scoresMap) {
//        bw.write(pair._1.toString)
//        bw.write("\n")
//        for (wordScorePair <- pair._2) {
//          bw.write(wordScorePair._1)
//          bw.write("\n")
//          bw.write(wordScorePair._2.toDouble.toString)
//          bw.write("\n")
//        }
//        bw.write("\n")
//      }     
//    } catch {
//      case e : IOException => println(e)
//      case f : FileNotFoundException => println(f)
//    } finally {
//      try {
//        if (bw != null) bw.close
//      } catch {
//        case e : IOException => println(e)
//      }
//    }
//  }
//  
//  /**
//   * A function that does exactly what its called. It writes
//   * the titles and ID's to a file provided by the user.
//   */
//  def writeTitlesAndIds() = {
//    var bw : BufferedWriter = null
//    try {
//      bw = new BufferedWriter(new FileWriter(titleFile))
//      for (i <- 0 to numDocs - 1){
//        bw.write(idsArray(i).toString)
//        bw.write("\n")
//        bw.write(titles(i))
//        bw.write("\n")
//      }
//    } catch {
//      case e : IOException => println(e)
//      case f : FileNotFoundException => println(f)
//    } finally {
//      try {
//        if (bw != null) bw.close
//      } catch {
//        case e : IOException => println(e)
//      }
//    }
//  }
//  
//  /**
//   * A basic function method that calls the three functions that we built in 
//   * our index class.
//   */
//  def index() = {
//    pageRank()
//    writeTitlesAndIds()
//    scoring() 
//  }
//}
//
///**
// * An object index that runs our index class and also checks that
// * the number of arguments in the user input is correct
// */
//object Index {
//  def main(args: Array[String]): Unit = {
//    if (args.length != 4){
//      println("4 files are needed as input (corpus, and three txt files)")
//    } else {
//      val index = new Index(args(0), args(1), args(2), args(3))
//      index.index()
//    }
//  }
//}
