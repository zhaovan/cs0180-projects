package guizilla.sol.client

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.net.ConnectException
import javafx.scene.control.Label

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
  private var linkLst: List[String] = List()
  
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
    if (pagesCacheList.tail.isEmpty) {
      currentPage = new ClientPage
      urlBar.setText("")
      this.currentPage.host = ""
      box.getChildren.clear()
      renderHomePage()
    } else {
      box.getChildren.clear()
      currentPage = pagesCacheList.head
      pagesCacheList = pagesCacheList.tail
      linkLst = linkLst.tail
      renderPage()
      if (linkLst.isEmpty) {
        urlBar.setText("")
      } else {
        urlBar.setText("http://" + currentPage.host + linkLst.head)
      }
    }
  }

  /**
   * Handles submitting URL button action.
   */
  @FXML def handleSubmitButtonAction(event: ActionEvent) {
    try {
      urlText = urlBar.getText
      currentPage.host = urlText.replaceAll("""http:(//)""", "").takeWhile(x => x != '/')
      //      this.currentPage.host = currentPage.host
      val path = urlText.replaceAll("""http:(//)\w*""", "")
      val getReq = super.formatGetReq(path)
      super.request(path, getReq)
      box.getChildren.clear()
      renderPage()
      super.archivePage()
      linkLst = path :: linkLst
    } catch {
      case e: (ConnectException) =>
        println("Connect Exception")
        renderPage()
      case rte: (RuntimeException) =>
        println("Incorrect Host")
        renderPage()
    }
  }

  /**
   * Sets the stage field of the controller to the given stage.
   *
   * @param stage - The stage
   */
  def setStage(stage: Stage) {
    this.stage = stage
  }

  /**
   * A method called renderPage that overrides the original
   * renderPage
   */
  override def renderPage() {
    println("Rendering Page...")
    box.getChildren.clear()
    if (currentPage.HTMLEleLst.isEmpty) {
      renderHomePage()
    } else {
      for (el <- currentPage.HTMLEleLst) {
        el.render(box, this)
      }
    }
  }
  
  /**
   * A method that fills the URL bar with the corresponding page
   * that we're currently at
   *
   * @param link - A string representing the pathway of the link
   */
  def fillURLBar(link: String) = {
    urlBar.setText("http://" + currentPage.host + link)
    linkLst = link :: linkLst
  }

  /**
   * A method called renderHomePage that prints the welcome message to the
   * first page that we see when the page is opened
   */
  def renderHomePage() {
    val label = new Label
    label.setText("Hello! Welcome to Guizilla!")
    box.getChildren.add(label)
  }
}


