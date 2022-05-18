package view.internal_window;

import controller.event.internal_window.ArtManagerPageEvents;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.entity.Art;
import view.component.ArtTableView;

public class ArtManagerPage extends InternalWindow{
	private final String WINDOW_TITLE = "Art Manager";
	
	private BorderPane borderPane = null;
	private VBox artTableContainer = null;
	private Text title = null;
	private GridPane gridPane = null;
	private VBox idContainer = null;
	private VBox artistContainer = null;
	private VBox dateContainer = null;
	private VBox titleContainer = null;
	private VBox urlContainer = null;
	private VBox priceContainer = null;
	private VBox descriptionContainer = null;
	private Text idText = null;
	private TextField idField = null;
	private Text artistText = null;
	private TextField artistField = null;
	private Text dateText = null;
	private TextField dateField = null;
	private Text titleText = null;
	private TextField titleField = null;
	private Text urlText = null;
	private TextField urlField = null;
	private Text priceText = null;
	private Spinner<Integer> priceField = null;
	private Text descriptionText = null;
	private TextField descriptionField = null;
	private Button publishButton = null;
	private Button updateButton = null;
	private Button withdrawButton = null;
	private Button clearButton = null;
	private ArtTableView tableView = null;
	
	public ArtManagerPage() {
		super();
		window.setTitle(WINDOW_TITLE);
		
		borderPane = new BorderPane();
		window.setContentPane(borderPane);
		
		// Art Table TOP
		artTableContainer = new VBox();
		artTableContainer.setPadding(new Insets(25, 25, 0, 25));
		borderPane.setTop(artTableContainer);
		
		title = new Text(WINDOW_TITLE);
		title.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		artTableContainer.getChildren().add(title);
		
		tableView = new ArtTableView();
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Art>() {
			@Override
			public void changed(ObservableValue<? extends Art> obs, Art oldSel, Art newSel) {
				ArtManagerPageEvents.tableDataSelected(newSel);
			}
		});
		artTableContainer.getChildren().add(tableView);
		
		// Fields BOTTOM
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		gridPane.setAlignment(Pos.CENTER_LEFT);
		borderPane.setBottom(gridPane);
		
		idContainer = new VBox();
		idContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(idContainer, 0, 0, 1, 1);

		idText = new Text("ID");
		idField = new TextField();
		idField.setDisable(true);
		idContainer.getChildren().add(idText);
		idContainer.getChildren().add(idField);

		artistContainer = new VBox();
		artistContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(artistContainer, 0, 1, 1, 1);

		artistText = new Text("Artist");
		artistField = new TextField();
		artistField.setDisable(true);
		artistContainer.getChildren().add(artistText);
		artistContainer.getChildren().add(artistField);

		dateContainer = new VBox();
		dateContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(dateContainer, 0, 2, 1, 1);

		dateText = new Text("Published Date");
		dateField = new TextField();
		dateField.setDisable(true);
		dateContainer.getChildren().add(dateText);
		dateContainer.getChildren().add(dateField);

		titleContainer = new VBox();
		titleContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(titleContainer, 1, 0, 1, 1);

		titleText = new Text("Title");
		titleField = new TextField();
		titleContainer.getChildren().add(titleText);
		titleContainer.getChildren().add(titleField);

		urlContainer = new VBox();
		urlContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(urlContainer, 1, 1, 1, 1);

		urlText = new Text("URL");
		urlField = new TextField();
		urlContainer.getChildren().add(urlText);
		urlContainer.getChildren().add(urlField);

		priceContainer = new VBox();
		priceContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(priceContainer, 1, 2, 1, 1);

		priceText = new Text("Price");
		priceField = new Spinner<Integer>(0, Integer.MAX_VALUE, 0);
		priceContainer.getChildren().add(priceText);
		priceContainer.getChildren().add(priceField);

		descriptionContainer = new VBox();
		descriptionContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(descriptionContainer, 0, 3, 2, 1);

		descriptionText = new Text("Description");
		descriptionField = new TextField();
		descriptionContainer.getChildren().add(descriptionText);
		descriptionContainer.getChildren().add(descriptionField);	
		
		publishButton = new Button("Publish Art");
		publishButton.setMaxHeight(Double.MAX_VALUE);
		publishButton.setMinWidth(400);
		publishButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArtManagerPageEvents.publishButtonClicked();
			}
		});
		gridPane.add(publishButton, 3, 0, 1, 1);

		updateButton = new Button("Update Art");
		updateButton.setMaxHeight(Double.MAX_VALUE);
		updateButton.setMinWidth(400);
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArtManagerPageEvents.updateButtonClicked();
			}
		});
		gridPane.add(updateButton, 3, 1, 1, 1);

		withdrawButton = new Button("Withdraw Art");
		withdrawButton.setMaxHeight(Double.MAX_VALUE);
		withdrawButton.setMinWidth(400);
		withdrawButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArtManagerPageEvents.withdrawButtonClicked();
			}
		});
		gridPane.add(withdrawButton, 3, 2, 1, 1);
		
		clearButton = new Button("Clear Fields");
		clearButton.setMaxHeight(Double.MAX_VALUE);
		clearButton.setMinWidth(400);
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArtManagerPageEvents.clearButtonClicked();
			}
		});
		gridPane.add(clearButton, 3, 3, 1, 1);
	}
	
	public void clearFields() {
		idField.setText("");
		artistField.setText("");
		dateField.setText("");
		titleField.setText("");
		urlField.setText("");
		priceField.getValueFactory().setValue(0);
		descriptionField.setText("");
	}

	public TableView<Art> getTableView() {
		return tableView;
	}
	
	public TextField getIdField() {
		return idField;
	}

	public TextField getArtistField() {
		return artistField;
	}

	public TextField getDateField() {
		return dateField;
	}

	public TextField getTitleField() {
		return titleField;
	}

	public TextField getUrlField() {
		return urlField;
	}

	public Spinner<Integer> getPriceField() {
		return priceField;
	}

	public TextField getDescriptionField() {
		return descriptionField;
	}
}
