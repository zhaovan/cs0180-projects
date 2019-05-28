package guizilla.sol.client

import java.io.BufferedReader
import guizilla.src.parser.HTMLParser
import guizilla.src.HTMLTokenizer
import guizilla.src.parser.ParseException
import java.io.InputStreamReader
import java.io.IOException
import java.util.Arrays.ArrayList
import java.util.ArrayList
import java.net.Socket
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import scala.util.matching.Regex
import java.net.URLEncoder
import guizilla.src._

/**
 * A text-based browser class
 */
class Browser() {
  val standardOptions = "Action (1) Back, (2) New URL, (3) Quit:"
  var userInput = 0
  var reader: BufferedReader = new BufferedReader(new InputStreamReader(System.in));
  var pagesCacheList: List[ClientPage] = List[ClientPage]()
  var currentPage: ClientPage = new ClientPage

  /**
   * A function that does the request (get or post depending on the input)
   * and gets the correct HTML Doc as a result
   *
   * @param host - A string representing the hostname
   */
  @throws(classOf[IOException])
  def request(path: String, req: String) = {
    val port = 8080
    val socket = new Socket(currentPage.host, port)
    val iStream = socket.getInputStream
    val oStream = socket.getOutputStream
    try {
      val socket = new Socket(currentPage.host, port)
      val bRead = new BufferedReader(new InputStreamReader(iStream))
      val bWrite = new BufferedWriter(new OutputStreamWriter(oStream))
      bWrite.write(req)
      bWrite.flush()
      socket.shutdownOutput()
      println("\n" + "Connecting to " + currentPage.host + ":" + port)
      var line: String = bRead.readLine()
      if (line == "HTTP/1.0 200 OK") {
        println("Connected")
        line = bRead.readLine()
        println(line)
        bRead.readLine()
        bRead.readLine()
        bRead.readLine()
        currentPage.HTMLEleLst = getHTMLElementList(bRead)
      }
      println("Parsing page...")
      System.out.flush()
      socket.shutdownInput()
    } catch {
      case rte: RuntimeException => Unit
    } finally {
      socket.close()
    }
  }

  /**
   * A class that builds the post string which we then send
   * into the request function
   *
   * @param form - A form representing the HTML element form
   * in the document
   * @return a String representing the post path
   */
  def postRequest(form: Form): String = {
    val path = form.url
    var encStr = ""
    form.fillLst()
    for (field <- form.lstTextUI) {
      encStr += URLEncoder.encode(field.name, "UTF-8")
      encStr += "="
      encStr += URLEncoder.encode(field.getVal().getOrElse(""), "UTF-8") + "&"
    }
    print(encStr.toString)
    "POST " + path + " HTTP/1.0\r\n" +
      "Connection: close\r\n" +
      "User-Agent: SparkZilla/1.0\r\n" +
      "Content-Type: application/x-www-form-urlencoded\r\n" +
      "Content-Length: " + encStr.length().toString + "\r\n" + "\r\n" + encStr
  }

  /**
   * A function that gets the correct string for GET requests
   * and outputs it
   *
   * @param path - the corresponding path for the get request that we want
   * @returns the string representing the get request
   */
  def formatGetReq(path: String): String = {
    "GET " + path + " HTTP/1.0\r\n" +
      "Connection: close\r\n" +
      "User-Agent: SparkZilla/1.0\r\n" + "\n" + "\r\n"
  }

  /**
   * A function that displays the original home page that
   * sparkzilla has when it starts up
   */
  private def displayHomePage() {
    println("-----------------------" + "\n")
    println("Welcome to Sparkzilla!")
    println(standardOptions)
  }

  /**
   * Archives the current page into the pagesCacheList which we
   * use to run our back button essentially
   *
   */
  def archivePage() {
    pagesCacheList = currentPage :: pagesCacheList
  }

  /**
   * Loads the most recent cached element.
   */
  private def loadMostRecentCached() {
    if (pagesCacheList.isEmpty) {
      println("Rendering page...")
      displayHomePage()
    } else {
      currentPage = pagesCacheList.head
      pagesCacheList = pagesCacheList.tail
      renderPage()
    }
  }

  /**
   * A function that takes in the HTML elements of the page
   * and renders them.
   */
  def renderPage() {
    println("Rendering Page...")
    println("-------------" + "\n")
  }

  /**
   * Parses the input from the server into a list of HTMLElements.
   * @param inputFromServer- BufferedReader containing HTML from server
   * @returns hierarchical list of the HTMLElements. See the documentation
   * 	and view the sol code for the specific composition of each HTMLElement
   *   within the list.
   */
  private def getHTMLElementList(inputFromServer: BufferedReader): List[HTMLElement] = {
    val parser = new HTMLParser(new HTMLTokenizer(inputFromServer))
    return parser.parse().toHTML
  }
}

/**
 * The main object Browser that essentially does all the running.
 * It checks all of the user input and displays the corresponding inputs as such.
 *
 */
object Browser {
  val browser: Browser = new Browser()

}
