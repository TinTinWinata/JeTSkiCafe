package view.internal_window;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import view.component.TransactionTableView;

public class TransactionHistoryPage extends InternalWindow{
	private BorderPane borderPane = null;
	private final String WINDOW_TITLE = "Transaction History";
	
	private VBox txTableContainer = null;
	private Text title = null;
	private TransactionTableView txTableView = null;
	
	public TransactionHistoryPage() {
		super();
		window.setTitle(WINDOW_TITLE);
		
		borderPane = new BorderPane();
		window.setContentPane(borderPane);
		
		// Tx Table TOP
		txTableContainer = new VBox();
		txTableContainer.setPadding(new Insets(25, 25, 0, 25));
		borderPane.setTop(txTableContainer);
		
		title = new Text(WINDOW_TITLE);
		title.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
		txTableContainer.getChildren().add(title);
		
		txTableView = new TransactionTableView();
		txTableContainer.getChildren().add(txTableView);
	}
	
	public TransactionTableView getTxTableView() {
		return txTableView;
	}
}
