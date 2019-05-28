package guizilla.sol.client

import javafx.scene.layout.VBox

/**
  * An element of a HTML Page
  */
abstract class HTMLElement {
  
  /**
   * A method render that renders teh elements of the page
   * 
   * @param i - Int for the number of clickable items
   * @return a list of clickable items
   */
  // Maybe return a list of clickable still?
  def render(box: VBox)
  
}


