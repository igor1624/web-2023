import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

  public static void main(String[] args) {

    launch();
  }

  @Override
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    Label text = new Label("Studio");
    text.setFont(new Font(32));
    root.setCenter(text);

    Scene scene = new Scene(root, 400, 500);
    stage.setScene(scene);
    stage.show();

    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent event) {
        Platform.exit();
        System.exit(0);
      }
    });

    stage.show();
  }

  private MenuBar createMenuBar() {

    Menu studioMenu = new Menu("Studio");

    MenuItem menuItem = new MenuItem("Open project");
    studioMenu.getItems().add(menuItem);
    menuItem.setOnAction(openProject());

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(studioMenu);
    return menuBar;
  }

  private EventHandler<ActionEvent> openProject() {
    return new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      }
    };
  }
}
