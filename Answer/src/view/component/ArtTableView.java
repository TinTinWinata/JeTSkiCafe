package view.component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Art;

public class ArtTableView extends TableView<Art>{
	private TableColumn<Art, String> idColumn = null;
	private TableColumn<Art, String> urlColumn = null;
	private TableColumn<Art, String> titleColumn = null;
	private TableColumn<Art, String> descriptionColumn = null;
	private TableColumn<Art, String> artistColumn = null;
	private TableColumn<Art, String> publishedDateColumn = null;
	private TableColumn<Art, String> priceColumn = null;
	
	@SuppressWarnings("unchecked")
	public ArtTableView() {
		super();
		setEditable(false);
		idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		urlColumn = new TableColumn<>("URL");
		urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
		urlColumn.setPrefWidth(300);
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		descriptionColumn.setPrefWidth(300);
		artistColumn = new TableColumn<>("Artist");
		artistColumn.setCellValueFactory(new PropertyValueFactory<>("artistUsername"));
		publishedDateColumn = new TableColumn<>("Published Date");
		publishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
		priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		getColumns().setAll(idColumn, urlColumn, titleColumn, descriptionColumn, artistColumn, publishedDateColumn, priceColumn);
	}
}
