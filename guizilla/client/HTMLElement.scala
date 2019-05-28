package guizilla.sol.client

import javafx.scene.layout.VBox

/**
  * An element of a HTML Page
  */
abstract class HTMLElement {
  
  /**
   * A method render that renders the elements of the page
   * 
   * @param box - the VBox
   * @param b - The Browser
   */
  def render(box: VBox, b: GUIBrowser)
  
}


