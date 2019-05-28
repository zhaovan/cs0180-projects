package sparkzilla.sol

/**
  * A text input element of an HTML page
  *
  * @param name - Name of the text input for communicating with the server
  * @param value - Value of the text input as given by the user
  */
case class TextInput(val name: String, private var value: Option[String]) extends HTMLElement with Clickable{
  
  /**
   * Prints the output of the userInput
   */
  override def toString() = {
    "___" + this.value.getOrElse("") + "___"
  }
  
  /**
   * A function that returns the option of value because the
   * value field is private
   */
  def getVal(): Option[String] = {
    value
  }
  
  /**
   * A click function that asks for user input and then
   * rerenders the page with the corresponding value
   */
  override def click() = {
    println("Enter value for field " + this.name)
    this.value = Some(Browser.browser.getStringInput())
    Browser.browser.renderPage()
  }
}
