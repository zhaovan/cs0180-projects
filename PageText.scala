package sparkzilla.sol

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
  override def render(i: Int) = {
    println(text)
    List()
  }
}
