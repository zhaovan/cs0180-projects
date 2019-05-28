package guizilla.sol.server.pages

import scala.collection.mutable.HashMap
import guizilla.src.Page


/**
 * Class which contains a function that represents a page in html.
 *  DogSpotting links back to home so you can infinitely cycle by spamming the 4 key in text.
 *  
 */
class DogSpotting extends Page {

  override def clone(): DogSpotting = {
    this
  }

  override def defaultHandler(inputs: Map[String, String], sessionID: String): String = default(inputs)
  
/**
 * default page to display
 * @param mapmap - the hashmap of the cloned things
 */
  def default(mapmap: Map[String, String]): String = {
    """<html><body><p>WELCOME TO DOGSPOTTING HOW ARE YOU???
     DO YOU NEED SOME DOGS? BC WE HAVE THEM.
     WE HAVE BIG DOGS, LITTLE DOGS, RED DOGS, BLUE DOGS.
     WE HAVE DOGS IN HATS,DOGS IN SUITS, DOGS IN ORCHESTRAS PLAYING FLUTES SO LMK WHAT YOU NEED WE HAVE IT.</p><p><a href="/Index">Go to Index!</a></p></body></html>"""
  }
}