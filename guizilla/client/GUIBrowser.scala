package guizilla.sol.client

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.control.TextField
import javafx.stage.Stage

/**
 * Class responsible for handling browser navigation.
 * TODO: extend Browser class that was written for Sparkzilla, i.e.
 * where your code dealt with networking and communicating with server.
 */
class GUIBrowser extends Browser {

  @FXML protected var gPane: GridPane = null
  @FXML protected var urlBar: TextField = null
  @FXML var box: VBox = null

  private var stage: Stage = null
  private var urlText: String = null

  /**
    * Handles the pressing of the submit button on the main GUI page.
    */

  @FXML def handleQuitButtonAction(event: ActionEvent) {
    stage.close()
  }

  /**
    * Handles the pressing of the back button on the main GUI page.
    */
  @FXML def handleBackButtonAction(event: ActionEvent) {
    // TODO: handle back button
    
    stage.close()
    stage.show()

  }

  /**
   * Handles submitting URL button action.
   */
  @FXML def handleSubmitButtonAction(event: ActionEvent) {
    urlText = urlBar.getText
    // TODO: handle submitting URL action given urlText
  }

  /**
    * Sets the stage field of the controller to the given stage.
    *
    * @param stage The stage
    */
  def setStage(stage: Stage) {
    this.stage = stage
  }

  override def renderPage() {
    for (el <- currentPage.HTMLEleLst){
      el.render(box)
    }
  }
  
  // TODO: implement a rendering method.
  // HINT: If you want to add elements to your box and update the stage, what
  // fields might you want the rendering process to have access to?

}
