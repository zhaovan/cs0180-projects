package guizilla.sol.server.pages
import java.io.IOException
import java.net.Socket
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.BufferedReader
import java.io.InputStreamReader
import guizilla.src.Page
import scala.collection.immutable.HashMap
import guizilla.sol.server.Server

/**
 * Class which lets the user search using the ta search server and
 * has methods to display results and pages
 */
class Searcher extends Page {

  /**
   * Generates a unique id tp use as the key in sessionId Map.
   * Prob should be in page src file but not allowed to touch that
   */
  def getUniqueID(): Int = {
    var id = scala.util.Random.nextInt()
    while (Server.server.SessionIDMap.contains(id.toString())) {
      id = scala.util.Random.nextInt()
    }
    id
  }

  var query: String = ""
  var resultIDMap: HashMap[Int, String] = new HashMap[Int, String]

  /**
   * Client class from lab10 bc I need to connect to the search server
   */
  @throws(classOf[IOException])
  class Client(port: Int) {

    /**
     * Function for getting results of a search on the search server
     * with the query text taken in as input
     */
    @throws(classOf[IOException])
    def getResults(query: String): String = {
      var socket = new Socket("eckert", port)
      try {

        val oStream = socket.getOutputStream
        val bWrite = new BufferedWriter(new OutputStreamWriter(oStream))
        val iStream = socket.getInputStream
        val bRead = new BufferedReader(new InputStreamReader(iStream))

        val req = ("REQUEST\t" + query + "\n")
        for (char <- req.toCharArray()) {
          bWrite.write(char)
          bWrite.flush()
        }
        socket.shutdownOutput()
        var response = ""
        var fromServer = bRead.read()
        while (fromServer != -1) {
          response += fromServer.toChar
          fromServer = bRead.read()
        }
        response
      } finally {
        socket.shutdownInput()
        socket.close()

      }
    }
    /**
     * Function for getting the text of a document if given the title
     * 
     * @param query - A string representing the query we want
     */
    def getPage(query: String): String = {
      var socket = new Socket("eckert", port)
      try {

        val oStream = socket.getOutputStream
        val bWrite = new BufferedWriter(new OutputStreamWriter(oStream))
        val iStream = socket.getInputStream
        val bRead = new BufferedReader(new InputStreamReader(iStream))

        val req = query
        for (char <- req.toCharArray()) {
          bWrite.write(char)
          bWrite.flush()
        }
        socket.shutdownOutput()
        var response = ""
        var fromServer = bRead.read()
        while (fromServer != -1) {
          response += fromServer.toChar
          fromServer = bRead.read()
        }
        response
      } finally {
        socket.shutdownInput()
        socket.close()

      }
    }
  }

  /**
   * The function for cloning
   */
  override def clone(): Searcher = {
    val thing = super.clone().asInstanceOf[Searcher]
    thing.query = new String(this.query)
    //    var map = this.resultIDMap.updated(-1, "r")
    //    thing.resultIDMap = map.-(-1)
    thing
  }
  
  /**
   * A function that overrides defaultHandler in the Page Class
   */
  override def defaultHandler(inputs: Map[String, String], sessionID: String): String = {

    val sessionID = getUniqueID()

    Server.server.SessionIDMap.+=((sessionID.toString(), this.clone))

    s"""<html><body><p>Welcome to the search page. Wanna search? Then do it.</p><form method="post" action="/id:"$sessionID"/searchIt"><p>Search for a document</p><input type="text" name="searchq"/><input type="submit" value="submit"/></form><p> <a href="/Index">Go Home </a></p></body></html>"""

  }
  /**
   * All of the below functions do the same thing. I need so many
   * bc of the limitations of reflection. We talked about this during
   * the design check.
   * Anyway. lookupx is the function that is called to display the page
   * when you click on result x of the search
   */
  def lookup1(input: Map[String, String]): String = {
    val q = this.resultIDMap(1)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup2(input: Map[String, String]): String = {
    val q = this.resultIDMap(2)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup3(input: Map[String, String]): String = {
    val q = this.resultIDMap(3)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup4(input: Map[String, String]): String = {
    val q = this.resultIDMap(4)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup5(input: Map[String, String]): String = {
    val q = this.resultIDMap(5)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup6(input: Map[String, String]): String = {
    val q = this.resultIDMap(6)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup7(input: Map[String, String]): String = {
    val q = this.resultIDMap(7)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup8(input: Map[String, String]): String = {
    val q = this.resultIDMap(8)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup9(input: Map[String, String]): String = {
    val q = this.resultIDMap(9)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }
  
  def lookup10(input: Map[String, String]): String = {
    val q = this.resultIDMap(10)
    var cli: Client = new Client(8082)
    var results = cli.getPage(q)
    s"""<html><body><p>"$results</p></body></html>"""
  }

  /**
   * method that returns the top min(numResults,10) pages found by searching the ta
   * server for the query that the user submitted.
   * Presents them as clickable links which when clicked will display the page
   * that the title refers to
   */
  def searchIt(input: Map[String, String]): String = {
    val sessionID = getUniqueID()

    this.query = input.getOrElse("searchq", "evil")

    var cli: Client = new Client(8081)
    var results = cli.getResults(this.query).stripLineEnd.split("\t")

    var response = """<html><body>"""
    var currentfxn = ""
    var currentName = ""
    for (i <- 1 until results.length) {

      currentfxn = s"""/id:"$sessionID"""" + """/lookup""" + i.toString()
      currentName = results(i)
      this.resultIDMap = this.resultIDMap.updated(i, currentName.drop(i.toString().length() + 1))

      response += s"""<p><a href="$currentfxn">$currentName</a></p>"""
    }
    Server.server.SessionIDMap.+=((sessionID.toString(), this.clone))
    response += """</body></html>"""

    response
  }

}