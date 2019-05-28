package sparkzilla.sol

import java.util.ArrayList
import sparkzilla.sol.Clickable
import sparkzilla.sol.HTMLElement

/**
 * A Pageclass that just stores some of the data necessary
 * to help render the page
 */
class Page {
  var host: String = ""
  var HTMLEleLst: List[HTMLElement] = List[HTMLElement]()
  val actionArrayL: ArrayList[Clickable] = new ArrayList[Clickable]
  var currentFormData: String = ""
}