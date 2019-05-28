package guizilla.sol.server.pages

import scala.collection.mutable.HashMap
import guizilla.sol.server.Server
import guizilla.src.Page
/**
 * Class which contains methods that represent pages on the server.
 * Is a dynamic class for ordering t-shirts
 */
class Dyna extends Page {

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

  var size: String = ""
  var sponsor: String = ""

  /**
   * Function that clones the page
   */
  override def clone: Dyna = {
    val thingy = super.clone.asInstanceOf[Dyna]
    thingy.sponsor = new String(this.sponsor)
    thingy.size = new String(this.size)
    thingy
  }

  override def defaultHandler(inputs: Map[String, String], sessionId: String): String = default(inputs)

  /**
   * default page to display.  returns a string that represents a page in html
   */
  def default(mapmap: Map[String, String]): String = {
    var sessionID: Int = getUniqueID()

    Server.server.SessionIDMap.+=((sessionID.toString(), this.clone))

    s"""<html><body><p>Welcome to the hackathon T-Shirt Booth!</p><form method="post" action="/id:"$sessionID"/chooseSponsor"><p>What size T-Shirt do you want? (Small, Medium, Large)</p><input type="text" name="size"/><input type="submit" value="submit"/></form><p> <a href="/Index">Go Home </a></p></body></html>"""

  }
  /**
   * second page in dynamic series. returns a string that represents a page in html
   * 
   * @param mapmap - The hashmap
   */
  def chooseSponsor(mapmap: Map[String, String]): String = {
    val size = mapmap.getOrElse("size", "NONE ENTERED")
    this.size = size
    var sessionID: Int = getUniqueID()
    Server.server.SessionIDMap.+=((sessionID.toString(), this.clone()))

    s"""<html><body><p>Welcome to the hackathon T-Shirt Booth!</p><form method="post" action="/id:"$sessionID"/reviewDetails"><p>You chose to order a "$size" T-Shirt. Which company do you want to shill for? (GS, Jane Street, Other) </p><input type="text" name="sponsor"/><input type="submit" value="submit"/></form><p> <a href="/Index">Go Home </a></p></body></html>"""
  }
  
  /**
   * final page in series. returns a string that represents a page in html
   * 
   * @param mapmap - The hashmap used for corresponding sessionid to object.
   */
  def reviewDetails(mapmap: Map[String, String]): String = {
    val sponsor = mapmap.getOrElse("sponsor", "NONE ENTERED")
    this.sponsor = sponsor
    val size = this.size
    var sessionID: Int = getUniqueID()
    Server.server.SessionIDMap.+=((sessionID.toString(), this.clone()))
    s"""<html><body><p>Welcome to the hackathon T-Shirt Booth!</p><form method="post" action="/Index"><p>You chose to order a "$size" T-Shirt, representing the fine company of "$sponsor". Enter your address and submit this form so we can send you back to the Index with a fancy new shirt </p><input type="text" name="Address"/><input type="submit" value="submit"/></form><p> <a href="/Index">Go Home </a></p></body></html>"""
  }
}