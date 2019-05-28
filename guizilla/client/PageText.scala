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
   * A render method that just prints the text of the page
   * and returns an empty list
   */
  override def render(box: VBox) = {
    val label = new Label
    label.setText(text)
    box.getChildren.add(label)
  }
}
