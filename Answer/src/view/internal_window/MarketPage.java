package view.internal_window;

import controller.event.internal_window.ArtMarketPageEvents;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.entity.Art;
import model.entity.Cart;
import view.component.ArtTableView;
import view.component.CartTableView;

public class MarketPage extends InternalWindow{
	private final String WINDOW_TITLE = "Marketplace";
	
	private BorderPane borderPane = null;
	private GridPane gridPane = null;
	private VBox artTableContainer = null;
	private Text marketTitle = null;
	private ArtTableView artTableView = null;
	private VBox cartContainer = null;
	private Text cartTitle = null;
	private CartTableView cartTableView = null;
	private VBox copiesContainer = null;
	private Text copiesText = null;
	private Spinner<Integer> copiesField = null;
	private VBox buttonContainer = null;
	private Button addCartButton = null;
	private Button removeCartButton = null;
	private Button checkoutButton = null;
	
	public MarketPage() {
		super();
		window.setTitle(WINDOW_TITLE);
		
		borderPane = new BorderPane();
		window.setContentPane(borderPane);
		
		// Art Table TOP
		artTableContainer = new VBox();
		artTableContainer.setPadding(new Insets(25, 25, 0, 25));
		borderPane.setTop(artTableContainer);
		
		marketTitle = new Text(WINDOW_TITLE);
		marketTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		artTableContainer.getChildren().add(marketTitle);
		
		artTableView = new ArtTableView();
		artTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Art>() {
			@Override
			public void changed(ObservableValue<? extends Art> obs, Art oldSel, Art newSel) {
				ArtMarketPageEvents.artTableDataSelected(newSel);
			}
		});
		artTableContainer.getChildren().add(artTableView);
		
		
		// Grid BOTTOM
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		gridPane.setAlignment(Pos.CENTER_LEFT);
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setHgrow(Priority.ALWAYS);
		gridPane.getColumnConstraints().add(columnConstraints);
		borderPane.setBottom(gridPane);
		
		cartContainer = new VBox();
		cartContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(cartContainer, 0, 0, 1, 4);
		
		cartTitle = new Text("My Cart");
		cartTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		cartContainer.getChildren().add(cartTitle);

		cartTableView = new CartTableView();
		cartTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cart>() {
			@Override
			public void changed(ObservableValue<? extends Cart> obs, Cart oldSel, Cart newSel) {
				ArtMarketPageEvents.cartTableDataSelected(newSel);
			}
		});
		cartContainer.getChildren().add(cartTableView);

		copiesContainer = new VBox();
		copiesContainer.setAlignment(Pos.CENTER_LEFT);
		gridPane.add(copiesContainer, 1, 0, 1, 1);

		copiesText = new Text("Copies");
		copiesField = new Spinner<Integer>(0, Integer.MAX_VALUE, 0);
		copiesField.setMinWidth(400);
		copiesContainer.getChildren().add(copiesText);
		copiesContainer.getChildren().add(copiesField);

		buttonContainer = new VBox();
		buttonContainer.setAlignment(Pos.CENTER_LEFT);
		buttonContainer.setSpacing(10);
		gridPane.add(buttonContainer, 1, 1, 1, 1);

		addCartButton = new Button("Add to Cart");
		addCartButton.setMinWidth(400);
		addCartButton.setMaxHeight(Double.MAX_VALUE);
		addCartButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArtMarketPageEvents.addCartButtonClicked();
			}
		});
		removeCartButton = new Button("Remove from Cart");
		removeCartButton.setMinWidth(400);
		removeCartButton.setMaxHeight(Double.MAX_VALUE);
		removeCartButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArtMarketPageEvents.removeCartButtonClicked();
			}
		});
		checkoutButton = new Button("Checkout");
		checkoutButton.setMinWidth(400);
		checkoutButton.setMaxHeight(Double.MAX_VALUE);
		checkoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ArtMarketPageEvents.checkoutButtonClicked();
			}
		});

		buttonContainer.getChildren().addAll(addCartButton, removeCartButton, checkoutButton);
	}
	
	public TableView<Art> getArtTableView() {
		return artTableView;
	}
	
	public TableView<Cart> getCartTableView() {
		return cartTableView;
	}

	public Spinner<Integer> getCopiesField() {
		return copiesField;
	}
}
