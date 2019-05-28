package guizilla.sol.client

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException
import javafx.scene.layout.VBox
import javafx.scene.control.Label


/**
 * A trait called clickable that extends HTMLElement and
 *  is used to determine
 * whether the item is an active element
 */
trait Clickable extends HTMLElement{
  
  /**
   * A render method that override for an active element.
   * It returns the active element in a list and also prints
   * the element with the corresponding number after it
   */
//  override def render(i : Int) : List[Clickable] = {
//    println(this.toString + "(" + (i+1).toString() + ")")
//    val label = new Label
//    label.setText(text)
//    box.getChildren.add(label)
//    List(this)
//  }
  
  /**
   * A function that acts as if the element is clicked
   */
  def click()

}