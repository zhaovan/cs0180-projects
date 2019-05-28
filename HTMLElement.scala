package sparkzilla.sol

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
  def render(i: Int) : List[Clickable]
  
}


