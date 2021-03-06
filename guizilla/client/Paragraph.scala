package guizilla.sol.client

import javafx.scene.layout.VBox

/**
 * A paragraph element of an HTML page
 *
 * @param elements - The HTML elements of the paragraph
 */
case class Paragraph(elements: List[HTMLElement]) extends HTMLElement {

  /**
   * A function called render that takes in the elements
   * in the Paragraph and outputs the list of all potential
   * clickable elements in the list
   *
   * @param box - the VBox
   * @param b - The Browser
   */
  override def render(box: VBox, b: GUIBrowser) = {
    for (ele <- elements) {
      ele.render(box, b)
    }
  }
}
