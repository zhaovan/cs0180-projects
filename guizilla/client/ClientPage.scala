package guizilla.sol.client

import java.util.ArrayList
import guizilla.sol.client.HTMLElement

/**
 * A Pageclass that just stores some of the data necessary
 * to help render the page
 */
class ClientPage {
  var host: String = ""
  var HTMLEleLst: List[HTMLElement] = List[HTMLElement]()
  var currentFormData: String = ""
}