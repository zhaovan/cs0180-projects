package sparkzilla.sol

/**
 * A form element of an HTMLPage
 *
 * @param url - The URL to send the form data
 * @param elements - The HTML elements contained in the form
 */
case class Form(val url: String, private var elements: List[HTMLElement]) extends HTMLElement {
  var lstTextUI: List[TextInput] = List[TextInput]()
  
  /**
   * A form that takes in a set of elements and sets the elements in hte
   */
  def setElements(lst: List[HTMLElement]) = {
    elements = lst
  }

  /**
   * An overriden function render that takes in an integer
   * and counts the number of clickable elements. it then keeps track of all 
   * the text inputs and stores that as an item in the list
   */
  override def render(i: Int) = {
    var locCount = 0
    var locLst = List[Clickable]()
    for (ele <- elements) {
      locLst = locLst ::: ele.render(i + locCount)
      if (locLst.length > locCount) {
        locCount += 1
      }
    }
     lstTextUI =  locLst.filter {
      x =>
        x match {
          case z: TextInput => true
          case _            => false
        }
    }.asInstanceOf[List[TextInput]]
    locLst
  }
}
