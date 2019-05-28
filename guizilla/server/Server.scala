package guizilla.sol.server

import java.io.IOException
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
import java.io.BufferedReader
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder
import scala.collection.immutable.HashMap
import scala.collection.mutable.HashMap
import java.lang.reflect.InvocationTargetException

/**
 * Class which has methods for running a functional server
 */
class Server {
  
  /**
   * custom exception class bc we're #stylish
   * (the name really says it all here)
   */
  class SessionIDNotFoundExcpetion() extends Exception {}
  /**
   * custom exception class bc we're #stylish
   * (the name really says it all here)
   * 
   * @param s - String representing the exception
   */
  class BadRequestException(s: String) extends Exception {}

  /**
   * Custom implementation of the built in require that throws my custom
   * exception rather than using the less descriptive
   * illegalArgumentException
   * 
   * @param cond - A boolean
   */
  def requireCond(cond: Boolean) {
    if (!cond) throw new BadRequestException("bad request")
  }

  val goodResponseStem = """HTTP/1.0 200 OK
Server: Sparkserver/1.0
Connection: close
Connection: close
Content-Type: text/html

"""
  val badReqResponse = """HTTP/1.0 400 Bad Request
Server: Winning
Content-Type: text/html
Connection: close
Content-Length: 58

<html><body><p>GUIZILLA Server Error: Bad Request</p></body></html>"""

  val FNF404Response = """HTTP/1.0 404 Not Found
Server: Winning
Content-Type: text/html
Connection: close
Content-Length: 62

<html><body><p>GUIZILLA Server Error: Page Not Found</p></body></html>"""


  val ISE500Response = """HTTP/1.0 500 NOTOK
Server: Sparkserver/1.0
Connection: close
Connection: close
Content-Type: text/html

<html><body><p>GUIZILLA Server Error: Internal Error</p></body></html>"""

  var SessionIDMap: scala.collection.mutable.HashMap[String, Object] = 
    new scala.collection.mutable.HashMap[String, Object]()

  /**
   * function that calls the page that the user requests using reflection 
   * #nifty
   * 
   * @param classOrIdName - A string representing the class or ID name
   * @param methodName - A string representing the method name being called
   * @param input1 - our map that stores our sessionid to the corresponding object
   * 
   * @return a string representing the reflected object/item
   */
  def reflectAndInvoke(classOrIdName: String, methodName: String, input1: Map[String, String]): String = {
    
    val reflectedObj = if (classOrIdName.startsWith("id:")) {
 
      SessionIDMap.getOrElse(classOrIdName.drop(4).dropRight(1), throw new SessionIDNotFoundExcpetion())
    } else {
      val reflectedClass = Class.forName("guizilla.sol.server.pages." + classOrIdName)
      reflectedClass.newInstance()
    }

    if (methodName == "") {
      val reflectedMeth = reflectedObj.getClass().getMethod("defaultHandler", classOf[Map[String, String]], classOf[String])

      reflectedMeth.invoke(reflectedObj, input1, "").asInstanceOf[String]
    } else {
      val reflectedMeth = reflectedObj.getClass().getMethod(methodName, classOf[Map[String, String]])

      reflectedMeth.invoke(reflectedObj, input1).asInstanceOf[String]
    }

  }
  /**
   * Function that returns a map of the form data from a post request
   * 
   * @param reader - A buffered Reader to get the form data
   * 
   * @return Map[String, String] representing the map of our session 
   * id to object
   */
  def getFormData(reader: BufferedReader): Map[String, String] = {

    var nextLineIsFormData = false
    var formDataLength = -1
    var amDone = false
    var req = ""
    var message = ""

    while (amDone == false && req != null && req != -1) {
      if (!nextLineIsFormData) {

        req = reader.readLine()

        if (req.split(":").length != 0 && req.split(":")(0).trim() == "Content-Length") {
          formDataLength = req.split(":")(1).trim().toInt
          requireCond(!(formDataLength < 0))
        } else if (req == """\r\n""" || req == """\n""" || req.stripLineEnd.length() == 0) {
          nextLineIsFormData = true
          requireCond(!(formDataLength < 0))
        }
      } else {
        requireCond(!(formDataLength < 0))
        amDone = true
        for (i <- 0 until formDataLength) {
          message += reader.read().toChar
        }
      }
    }
    requireCond(!(formDataLength < 0))
    var retMap = scala.collection.mutable.HashMap[String, String]()
    for (kvp <- URLDecoder.decode(message, "UTF-8").split("&")) {
      retMap.+=((kvp.split("=")(0), if (kvp.split("=").length == 1) "None Entered" else kvp.split("=")(1)))
    }
    val hash = retMap.toMap
    hash

  }
  /**
   * Main function that actually runs the server
   */
  def run() = {
    var socket: ServerSocket = null
    var client: Socket = null
    socket = new ServerSocket(8080)
    while (true) {

      client = socket.accept()

      try {
        val iStream = client.getInputStream
        val bRead = new BufferedReader(new InputStreamReader(iStream))

        var req = ""
        var response = ""

        req += bRead.readLine()
        var line1WordArr = req.split(" ")
        try {
          if (line1WordArr.length != 3 ||
            line1WordArr.last.trim() != """HTTP/1.0""" && line1WordArr.last.trim() != """HTTP/1.1""") {
            response = badReqResponse
          } else if (line1WordArr(0).trim() == "GET") {
            line1WordArr(1).split("/").length match {
              case 0 => response = FNF404Response
              case 1 => response = badReqResponse
              case 2 => response = goodResponseStem + reflectAndInvoke(line1WordArr(1).split("/")(1), "", null)
              case 3 => response = goodResponseStem + reflectAndInvoke(line1WordArr(1).split("/")(1), line1WordArr(1).split("/")(2), null)
              case _ => response = FNF404Response
            }

          } else if (line1WordArr(0).trim() == "POST") {

            var args: Map[String, String] = getFormData(bRead)

            line1WordArr(1).split("/").length match {
              case 0 => response = FNF404Response
              case 1 => response = badReqResponse
              case 2 => response = goodResponseStem + reflectAndInvoke(line1WordArr(1).split("/")(1), "", args)
              case 3 => response = goodResponseStem + reflectAndInvoke(line1WordArr(1).split("/")(1), line1WordArr(1).split("/")(2), args)
              case _ => response = FNF404Response
            }
          } else response = badReqResponse
        } catch {
          case iae: IllegalArgumentException    => response = badReqResponse
          case bre: BadRequestException         => response = badReqResponse
          case ite: InvocationTargetException   => response = ISE500Response
          case sinf: SessionIDNotFoundExcpetion => response = ISE500Response
          case nfe: NumberFormatException       => response = badReqResponse
          case npe: NullPointerException        => response = badReqResponse
          case cnf: ClassNotFoundException      => response = FNF404Response
          case nsm: NoSuchMethodException       => response = FNF404Response
          case ncd: NoClassDefFoundError        => response = FNF404Response
        }

        val oStream = client.getOutputStream
        val bWrite = new BufferedWriter(new OutputStreamWriter(oStream))

        for (char <- response.toCharArray()) {
          bWrite.write(char)
          bWrite.flush()
        }
        client.shutdownOutput()

      } finally {
        client.shutdownInput()
        client.close()
      }
    }
  }

}
/**
 * Companion object used to run the server
 */
object Server extends App {
  val server: Server = new Server()
  try {
    server.run
  } catch {
    case e: IOException => println(e.getMessage)
  }

}
