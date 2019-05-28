package guizilla.sol.client

import javafx.scene.layout.VBox
import javafx.scene.control.Label
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

/**
 * A link element of an HTML page
 *
 * @param href - The URL of the link
 * @param text - The text to be rendered
 */
case class Link(href: String, text: PageText) extends HTMLElement with Clickable {

  /**
   * An overriden method toString that outputs the text of the page
   */
  //  override def toString = {
  //    text.text
  //  }

  override def render(box: VBox) = {
//    val label = new Label
//    label.setText(text.text)
//    box.getChildren.add(label)
    val b: Button = new Button(text.text)
    val event = new EventHandler[ActionEvent]() {
      override def handle(a: ActionEvent) {
        Browser.browser.archivePage()
        val url = Browser.browser.currentPage.host
        Browser.browser.currentPage = new ClientPage
        Browser.browser.currentPage.host = url
        Browser.browser.request(href, Browser.browser.formatGetReq(href))
        Browser.browser.renderPage()
      }
    }
    b.setOnAction(event)
    box.getChildren.add(b)
  }

  /**
   * The method click that archives the current page,
   * gets the new page, sends the request to the new page
   * and then renders the new page
   */
  override def click() = {
    //    Browser.browser.archivePage()
    //    val url = Browser.browser.currentPage.host
    //    Browser.browser.currentPage = new ClientPage
    //    Browser.browser.currentPage.host = url
    //    Browser.browser.request(href, Browser.browser.formatGetReq(href))
    //    Browser.browser.renderPage()
  }
}
