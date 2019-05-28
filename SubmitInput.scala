package sparkzilla.sol

import sparkzilla.sol.Page

/**
 * A submit button for a form
 *
 * @param form - The form that contains this submit button
 */
case class SubmitInput(form: Form) extends HTMLElement with Clickable {
  val link = form.url

  /**
   * A string that just prints the submit button
   */
  override def toString() = {
    "[Submit]"
  }

  /**
   * The method click that archives the current page, 
   * gets the new page, sends the request to the new page
   * and then renders the new page. This posts instead of doing get
   */
  override def click() = {
    Browser.browser.archivePage()
    val url = Browser.browser.currentPage.host
    Browser.browser.currentPage = new Page
    Browser.browser.currentPage.host = url
    Browser.browser.request(link, Browser.browser.postRequest(form))
    Browser.browser.renderPage()
  }
}