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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
    Label text = new Label("dfdf");
    text.setFont(new Font(32));
    root.setCenter(text);

    Scene scene = new Scene(root, 400, 500);
    stage.setScene(scene);
    stage.show();

    /*MenuBar menuBar = createMenuBar();
    VBox vbox = new VBox(menuBar, device);
    VBox.setVgrow(device, Priority.ALWAYS);
    stage.setTitle("Things Central Studio");
    Scene scene = new Scene(vbox);
    stage.setScene(scene);

    stage.setWidth(1400);
    stage.setHeight(1000);*/

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

    Menu thingsCentralMenu = new Menu("ThingsCentral");

    MenuItem menuItem = new MenuItem("Open app");
    thingsCentralMenu.getItems().add(menuItem);
    menuItem.setOnAction(openApp());

    menuItem = new MenuItem("/things-central/entities");
    thingsCentralMenu.getItems().add(menuItem);
    menuItem.setOnAction(navigateTo());

    Menu testScreensMenu = new Menu("TestScreens");

    menuItem = new MenuItem("text row in block");
    testScreensMenu.getItems().add(menuItem);
    menuItem.setOnAction(testScreen("trie"));

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(thingsCentralMenu);
    menuBar.getMenus().add(testScreensMenu);
    return menuBar;
  }

  private EventHandler<ActionEvent> openApp() {
    return new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      }
    };
  }

  private EventHandler<ActionEvent> navigateTo() {
    return new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      }
    };
  }

  private EventHandler<ActionEvent> testScreen(final String screenName) {
    return new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      }
    };
  }
}