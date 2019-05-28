package sparkzilla.sol

import sparkzilla.sol.Page

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
  override def toString = {
     text.text
  }
  
  /**
   * The method click that archives the current page, 
   * gets the new page, sends the request to the new page
   * and then renders the new page
   */
  override def click() = {
    Browser.browser.archivePage()
    val url = Browser.browser.currentPage.host
    Browser.browser.currentPage = new Page
    Browser.browser.currentPage.host = url
    Browser.browser.request(href, Browser.browser.formatGetReq(href))
    Browser.browser.renderPage()
  }
}
