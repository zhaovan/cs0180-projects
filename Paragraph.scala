package sparkzilla.sol

/**
  * A paragraph element of an HTML page
  *
  * @param elements - The HTML elements of the paragraph
  */
case class Paragraph(elements: List[HTMLElement]) extends HTMLElement {
  var actionLst : List[HTMLElement] = List[HTMLElement]()
  
  /**
   * A function called render that takes in the elements
   * in the Paragraph and outputs the list of all potential
   * clickable elements in the list
   */
  override def render(i: Int) : List[Clickable] = {  
    var lstClickable = List[Clickable]()
    var locClickable = 0
    for (ele <- elements){
      lstClickable = lstClickable ::: ele.render(i + locClickable) 
      if(lstClickable.length > locClickable){
        locClickable +=1
        }
      }
    lstClickable
    }
    
  }
