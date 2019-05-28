package sparkzilla.sol

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException

/**
 * A trait called clickable that is used to determine
 * whether the item is an active element
 */
trait Clickable extends HTMLElement{
  
  /**
   * A render method that override for an active element.
   * It returns the active element in a list and also prints
   * the element with the corresponding number after it
   */
  override def render(i : Int) : List[Clickable] = {
    println("<" + this.toString + ">" + "(" + (i+1).toString() + ")")
    List(this)
  }
  
  /**
   * A function that acts af if the element is clicked
   */
  def click()
  
}
