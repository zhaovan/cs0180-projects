package guizilla.sol.client

import javafx.scene.layout.VBox
import javafx.scene.control.Label

/**
 * A text element of an HTML page
 *
 * @param text - The text
 */
case class PageText(val text: String) extends HTMLElement {

   /**
   * A method render that renders the elements of the page
   * 
   * @param box - the VBox
   * @param b - The Browser
   */
  override def render(box: VBox, b : GUIBrowser) = {
    val label = new Label
    label.setText(text)
    box.getChildren.add(label)
  }
}
