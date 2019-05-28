package guizilla.sol.client

import javafx.scene.layout.VBox
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

/**
 * A submit button for a form
 *
 * @param form - The form that contains this submit button
 */
case class SubmitInput(form: Form) extends HTMLElement {
  val link = form.url

  /**
   * A method render that renders the submit button and handles
   * the click parameter
   * 
   * @param box - the VBox
   * @param b - The Browser
   */
  override def render(box: VBox, b : GUIBrowser) = {
    val button: Button = new Button("Submit!")
    val event = new EventHandler[ActionEvent]() {
      override def handle(a: ActionEvent) {
        click(b)
      }
    }
    button.setOnAction(event)
    box.getChildren.add(button)
  }

  /**
   * The method click that archives the current page,
   * gets the new page, sends the request to the new page
   * and then renders the new page. This posts instead of doing get
   * 
   * @param b - passes the GUIBrowser
   */
  def click(b : GUIBrowser) = {
    b.archivePage()
    val url = b.currentPage.host
    b.currentPage = new ClientPage
    b.currentPage.host = url
    b.request(link, b.postRequest(form))
    b.renderPage()
    b.fillURLBar(link)
  }
}