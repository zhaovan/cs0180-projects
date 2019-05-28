package search.sol

import search.src.StopWords
import search.src.PorterStemmer
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader
import java.io.IOException
import java.io.FileNotFoundException
import scala.util.matching.Regex
import collection.mutable.HashMap

/**
 * A class Query that takes in an array of strings that represents the user 
 * inputs and does all the necessary setting up in order to get the documents 
 * to run such as parsing the txt files for the various necessary info and 
 * storing them into the related data structures as necessary 
 * 
 * @param files - an array of strings representing the user input
 */
class Query (val files: Array[String]) {
  private def stopWordsAndStem(words: Array[String]): Array[String] = {
    val filtered : Array[String] = words.filter {x => !StopWords.isStopWord(x)}
    PorterStemmer.stemArray(filtered)
  }
  
  /**
   * A function getScores that takes in the user input and gets the 
   * corresponding score in the hashmap
   * 
   * @param words - an array of string representing the user Input (words
   * that they want to search for)
   */
  def getScores(words: Array[String]): HashMap[Int, Double] = {
    val idsAndScores = new HashMap[Int, Double]
    for (word <- words) {
      val scoresOfWord = wordsMap.get(word)
      scoresOfWord match {
        case Some(x) => {
          for ((id, score) <- x) {
            if (idsAndScores.contains(id)) {
              val oldScore : Double = idsAndScores(id)
              idsAndScores.update(id, oldScore + score)
            } else {
              idsAndScores.+=((id, score))
            }
          }
        }
        case None => Unit
      }
        
    }
    idsAndScores
  }
  
  /**
   * A function that takes the scores from the hashmap and prints
   * the top 10 results that match to the highest values in the 
   * hashmap 
   * 
   * @param map - A Hashmap from ints to doubles
   */
  private def printTopTen(map: HashMap[Int, Double]): Unit = {
    for ((k, v) <- map) {
      if (v == 0) map.remove(k)
    }
    if (map.size == 0) {
      println("No documents found")
    } else {
      var index = 0
      if (map.size < 10) {
        index = map.size
      } else {
        index = 10
      }
      for(i <- 1 to index) {
        val maxID : Int = findMaxID(map)
        idAndTitles.get(maxID) match {
          case Some(title) => println(i + " " + title)
          case None => println("ID did not exist in the map")
        }
        map.remove(maxID)
      }
    }
  }
  
  /**
   * A function that finds id of the maximum score 
   * 
   * @param map - HashMap from int to double
   */
  private def findMaxID(map: HashMap[Int, Double]): Int = {
    var max = Double.NegativeInfinity
    var maxID = 0
    for((id, score) <- map) {
      if (score > max) {
        max = score
        maxID = id
      }
    }
    maxID
  }
  
  var titlesReader : BufferedReader = null
  val idAndTitles = new HashMap[Int, String]()
  var scoreReader : BufferedReader = null
  val wordsMap = new HashMap[String, HashMap[Int, Double]]()
  var pageRankReader : BufferedReader = null
  val pageRankMap = new HashMap[Int, Double]()
  
  /**
   * A function query that essentially does all the reading and stores
   * the necessary info into the various data structures. It reads all
   * three files given. If it doesn't find anything, it should catch a possible
   * file not found or io exception. 
   */
  def query() = {
    try {
      titlesReader = new BufferedReader(new FileReader(files(0)))
      var titleLine : String = titlesReader.readLine()
      while (titleLine != null && titleLine != "") {
        val id : Int = titleLine.toInt
        titleLine = titlesReader.readLine()
        idAndTitles.+= ((id, titleLine))
        titleLine = titlesReader.readLine()
      }
      scoreReader = new BufferedReader(new FileReader(files(1)))
      var scoreLine : String = scoreReader.readLine()
      while(scoreLine != null && scoreLine != "") {
        val id = scoreLine.toInt
        scoreLine = scoreReader.readLine()
        while(scoreLine != null && scoreLine != "") {
          val word : String = scoreLine
          scoreLine = scoreReader.readLine()
          val score : Double = scoreLine.toDouble
          wordsMap.get(word) match {
            case Some(x) => x.+=((id, score))
            case None => {
              val idToDocScore = new HashMap[Int, Double]()
              idToDocScore += ((id, score))
              wordsMap.+= ((word, idToDocScore))
            }
          }
          scoreLine = scoreReader.readLine()
        }
        scoreLine = scoreReader.readLine()
      }
      pageRankReader = new BufferedReader(new FileReader(files(2)))
      var pageRankLine : String = pageRankReader.readLine()
      while(pageRankLine != null && pageRankLine != "") {
        val id : Int = pageRankLine.toInt
        pageRankLine = pageRankReader.readLine()
        val pageRankScore : Double = pageRankLine.toDouble
        pageRankMap += ((id, pageRankScore))
        pageRankLine = pageRankReader.readLine()
      }
    } catch {
      case e : IOException => println(e)
      case f : FileNotFoundException => println(f)
    } finally {
      try {
        if (titlesReader != null) titlesReader.close
        if (scoreReader != null) scoreReader.close
        if (pageRankReader != null) pageRankReader.close
      } catch {
        case e : IOException => println(e)
      }
    }
    
    /**
     * After successfully reading, it should prompt the user for input and
     * ask them for a word. the word is passed into the printTopTen 
     * scores method
     */
    var on = true
    var userReader : BufferedReader = null
      try {
        while(on) {
          print("search > ")
          userReader = new BufferedReader(new InputStreamReader(System.in))         
          val line = userReader.readLine()
          if (line == ":quit" || line == "") {
            on = false
          } else {
            val lowerCase = line.toLowerCase()
            val words = lowerCase.split("""\s+""")
            val filteredWords = stopWordsAndStem(words)
            val scores = getScores(filteredWords)
            printTopTen(scores)
          }
        }
      } catch {
        case e : IOException => println(e)
      } finally {
        try {
          if (userReader != null) userReader.close
        } catch {
          case e : IOException => println(e)
        }
      }
  }
}

/**
 * PageRank as a trait that extends Query which, if used, extends Query so that
 * it takes the scores for pageRank into account when calculating the various 
 * scores
 */
trait PageRank extends Query {
  override def getScores(words: Array[String]): HashMap[Int, Double] = {
    val map : HashMap[Int, Double] = super.getScores(words)
    for ((id, score) <- map) {
      pageRankMap.get(id) match {
        case Some(x) => map.update(id, score + (Math.sqrt(x)))
        case None => println("id not in id and pagerank hashmap")
      }
    }
    map
  }
}

/**
 * An object query that takes in the various files and checks if it's in the 
 * right format. If it is, it'll run, else it'll print out an informative 
 * line that should help the user!
 */
object Query {
  def main(args: Array[String]) = {
    if (args.length == 3 || args.length == 4) {
      var usePageRank : Boolean = false
      if (args(0) == "--pagerank" || args(0) == "--smart")
        usePageRank = true
      val files = args.dropWhile { x => x == "--pagerank" || x == "--smart" }
      if (files.length != 3) {
        System.out.println("Must have three file names")
      } else {
        System.out.println("Search! Type in a query or press :quit to exit.")
        var query : Query = null
        if (usePageRank) {
          query = new Query(files) with PageRank
        } else {
          query = new Query(files)
        }
        query.query()
      }
    } else {
      System.out.println("Must have 3 or 4 arguments")
    }
  }
}
