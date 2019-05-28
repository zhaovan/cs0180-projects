package guizilla.sol.client

import javafx.scene.layout.VBox
import javafx.scene.control.Label
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Hyperlink
import javafx.scene.input.MouseEvent

/**
 * A link element of an HTML page
 *
 * @param href - The URL of the link
 * @param text - The text to be rendered
 */
case class Link(href: String, text: PageText) extends HTMLElement {

  /**
   * A method render that renders the elements of the page
   * 
   * @param box - the VBox
   * @param b - The Browser
   */
  override def render(box: VBox, b: GUIBrowser) = {
    val link = new Hyperlink()
    link.setText(text.text)
    val event = new EventHandler[MouseEvent]() {
      override def handle(a: MouseEvent) = {
        click(b)
      }
    }
    link.setOnMouseClicked(event)
    box.getChildren.add(link)
  }

  /**
   * The method click that archives the current page,
   * gets the new page, sends the request to the new page
   * and then renders the new page
   * 
   * @param b - the guiBrowser that we pass into the method
   */
  def click(b: GUIBrowser) = {
    b.archivePage()
    val url = b.currentPage.host
    b.currentPage = new ClientPage
    b.currentPage.host = url
    println(url)
    b.request(href, b.formatGetReq(href))
    b.renderPage()
    b.fillURLBar(href)
  }
}
