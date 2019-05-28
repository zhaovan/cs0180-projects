package guizilla.sol.server.pages

import scala.collection.mutable.HashMap
import guizilla.sol.server.Server
import guizilla.src.Page

/**
 * Class which contains functions that represent pages. This is a
 * dynamic page with a built in back button which highlights the need for
 * server side sessionID mapping and cloning.
 */
class MultiplyTwo extends Page {

  /**
   * generates a unique id tp use as the key in sessionId Map.
   *  Prob should be in page src file but not allowed to touch that
   */
  def getUniqueID(): Int = {
    var id = scala.util.Random.nextInt()
    while (Server.server.SessionIDMap.contains(id.toString())) {
      id = scala.util.Random.nextInt()
    }
    id
  }

  private var num1 = 0
  private var num2 = 0

  override def clone(): MultiplyTwo = {
    val newBoi = super.clone.asInstanceOf[MultiplyTwo]
    newBoi.num1 = this.num1 + 0
    newBoi.num2 = this.num2 + 0
    newBoi
  }

  override def defaultHandler(inputs: Map[String, String], sessionID: String): String = multiplyTwoNumbers(inputs)

  /**
   * first page in dynamic series. is default page. returns a string that represents a page in html
   */
  def multiplyTwoNumbers(inputs: Map[String, String]): String = {
    val sessionId = getUniqueID()
    Server.server.SessionIDMap.+=((sessionId.toString(), this.clone))

    s"""<html><body><p>Multiply Two Numbers</p><form method="post" action="/id:"$sessionId"/secondNumber"><p> Please enter the first number you would like to multiply: </p><input type="text" name="num1" /><input type="submit" value="submit" /></form></body></html>"""
  }

  /**
   * first page in dynamic series for simple multiplication.
   *  returns a string that represents a page in html
   */
  def secondNumber(inputs: Map[String, String]): String =
    if (inputs == null) {
      "<html><body><p>Sneaky sneaky user. You thought I would let you go through MY SITE out of order? Nice Try Punk" +
        "<a href=\"/Index\">Return to the Index</a></p></body></html>"
    } else {
      inputs.get("num1") match {
        case Some(num) =>
          try {
            val sessionId = getUniqueID()
            num1 = num.toInt
            Server.server.SessionIDMap.+=((sessionId.toString(), this.clone))
            s"""<html><body><p>The first number entered was"$num1"</p><form method="post" action="/id:"$sessionId"/displayResult"><p>Please enter the second number you would like to multiply: </p><input type="text" name="num2"/><input type="submit" value="submit"/></form><p><a href="/MultiplyTwo">Change first number!</a></p></body></html>"""
          } catch {
            case _: NumberFormatException =>
              "<html><body><p>I'm sorry you did not input a valid number." +
                "<a href=\"/Index\">Return to the Index</a></p></body></html>"
          }
        case None => "<html><body><p>I'm sorry, there was an error retrieving your input." +
          "<a href=\"/Index\">Return to the Index</a></p></body></html>"
      }
    }

  /**
   * final page in dynamic series. Displays the result of the
   * multiplication. Returns a string that represents a page in html
   */
  def displayResult(inputs: Map[String, String]): String =
    if (inputs == null) {
      "<html><body><p>Sneaky sneaky user. You thought I would let you go through MY SITE out of order? Nice Try Punk" +
        "<a href=\"/Index\">Return to the Index</a></p></body></html>"
    } else {
      inputs.get("num2") match {
        case Some(num) =>
          try {
            val num2 = num.toInt
            "<html><body>" + "<p>" + num1 + " * " + num2 + "=" + (num1 * num2) + "</p>" + """<p><a href="/Index">Go to Index!</a></p>""" + "</body></html>"
          } catch {
            case _: NumberFormatException => "<html><body><p>I'm sorry you did not input a valid number." +
              "<a href=\"/Index\">Return to the Index</a></p></body></html>"
          }
        case None => "<html><body><p>I'm sorry, there was an error retrieving your input." +
          "<a href= \"/Index\">Return to the Index</a></p></body></html>"
      }
    }
}