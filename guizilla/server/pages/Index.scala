package guizilla.sol.server.pages

import scala.collection.mutable.HashMap
import guizilla.src.Page

/**
 * Class which contains functions that represent pages. Index is the home page
 */
class Index extends Page {

  override def defaultHandler(inputs: Map[String, String], sessionID: String): String = showIndex(inputs)

  /**
   * Home page that has links to the other primary pages
   * Returns a string that represents a page in html
   */
  def showIndex(mapmap: Map[String, String]): String = {

    """<html><body><p>Welcome to the Index of my server.""" + 
      """ We have all things that are nice. Check out some of the other pages on the server by clicking below!</p> """ +
      """<p><a href="/DogSpotting">GO DOGSPOTTING REX DDDDDDDD!</a></p>""" +
      """<p><a href="/Dyna">Do to the T-Shirt store!</a></p>""" +
      """ <p><a href="/MultiplyTwo">Do some multiplication!</a></p>""" +
      """<p><a href="/Searcher">Do some searching!</a></p></body></html>"""
  }

  /**
   * Idk what exactly is up with this. 
   * My friend used to say it when things went wrong. So it's here... 
   * Returns a string that represents a page in html
   */
  def uOTW(mapmap: Map[String, String]): String = {
    """<html><body><p>UH OH TINKY WINKY</p></body></html>"""
  }

  /**
   * Function that throws an error just to use for testing and such
   */
  def internalError(mapmap: Map[String, String]): String = {
    throw new RuntimeException()
    ""
  }

}