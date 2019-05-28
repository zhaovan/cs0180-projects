package guizilla.sol.client

import javafx.scene.layout.VBox
import javafx.event.ActionEvent
import javafx.beans.value.ObservableValue
import javafx.beans.value.ChangeListener
import javafx.scene.control.TextField

/**
 * A text input element of an HTML page
 *
 * @param name - Name of the text input for communicating with the server
 * @param value - Value of the text input as given by the user
 */
case class TextInput(val name: String, private var value: Option[String]) extends HTMLElement {

  val textField = new TextField()
  val listener = new ChangeListener[String]() {
    override def changed(ov: ObservableValue[_ <: String], old: String, news: String) = {
      value = Some(textField.getText)
    }
  }
  
  /**
   * A method render that renders the Text fields and then adds it to the
   * box
   * 
   * @param box - the VBox
   * @param b - The Browser
   */
  def render(box: VBox, b : GUIBrowser) = {
    textField.textProperty.addListener(listener)
    box.getChildren.add(textField)
  }
  
  /**
   * A function that returns the option of value because the
   * value field is private
   */
  def getVal(): Option[String] = {
    value
  }
}
