package view.component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Cart;

public class CartTableView extends TableView<Cart>{
	private TableColumn<Cart, String> idColumn = null;
	private TableColumn<Cart, String> urlColumn = null;
	private TableColumn<Cart, String> titleColumn = null;
	private TableColumn<Cart, String> artistColumn = null;
	private TableColumn<Cart, String> copiesColumn = null;
	private TableColumn<Cart, String> pricePerCopyColumn = null;
	private TableColumn<Cart, String> totalPriceColumn = null;
	
	@SuppressWarnings("unchecked")
	public CartTableView() {
		super();
		setEditable(false);
		idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		urlColumn = new TableColumn<>("URL");
		urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
		urlColumn.setPrefWidth(300);
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		artistColumn = new TableColumn<>("Artist");
		artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
		copiesColumn = new TableColumn<>("Copies");
		copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));
		pricePerCopyColumn = new TableColumn<>("Price Per-Copy");
		pricePerCopyColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerCopy"));
		totalPriceColumn = new TableColumn<>("Total Price");
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		getColumns().setAll(idColumn, urlColumn, titleColumn, artistColumn, copiesColumn, pricePerCopyColumn, totalPriceColumn);
	}
}
