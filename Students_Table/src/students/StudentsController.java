package students;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentsController implements Initializable{
	
	private ElementCollection<Student, String> model;

	@FXML TextField idInput;
	@FXML TextField firstNameInput;
	@FXML TextField lastNameInput;
	@FXML Button addButton;

	@FXML TableView<Student> studentsTable;

	@FXML TableColumn<String, Student> idCol;

	@FXML TableColumn<String, Student> firstNameCol;

	@FXML TableColumn<String, Student> lastNameCol;

	@FXML Label errorOuput;

	@FXML Button clearButton;

	@FXML Button searchButton;

	@FXML Button deleteByIdButton;

	@FXML Button deleteAllButton;

	@FXML Button deleteSelectedButton;

	@FXML Button searchByLastName;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StudentsFactory factory = new StudentsFactory();
		this.model = factory.createStudents();
		
		this.idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		this.lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		
		this.addButton.setOnAction(event->{
			this.errorOuput.setText("");
			
			model.add(
				new Student(
					idInput.getText(), 
					firstNameInput.getText(), 
					lastNameInput.getText()));
			
			refreshTable();
		});
		
		this.clearButton.setOnAction(event->{
			this.errorOuput.setText("");
			this.idInput.setText("");
			this.firstNameInput.setText("");
			this.lastNameInput.setText("");
			this.studentsTable.getSelectionModel().clearSelection();
			refreshTable();
		});
		
		this.searchButton.setOnAction(event->{
			this.errorOuput.setText("");
			String id = this.idInput.getText();
			Optional<Student> student = this.model.getByKey(id);
			if (student.isPresent()) {
				Student theStudent = student.get();
				this.firstNameInput.setText(theStudent.getFirstName());
				this.lastNameInput.setText(theStudent.getLastName());
			}else {
				this.errorOuput.setText("No student with id: " + id);
			}
		});
		
		this.deleteAllButton.setOnAction(event->{
			this.errorOuput.setText("");
			this.model.clear();
			refreshTable();
		});
		
		this.deleteByIdButton.setOnAction(event->{
			this.errorOuput.setText("");
		
			this.model.remove(
					this.model.getByKey(
						this.idInput.getText())
				.orElse(new Student(null, null, null))
					);
			
			refreshTable();
		});
		
		this.deleteSelectedButton.setDisable(true);
		
		this.studentsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
			this.deleteSelectedButton.setDisable(this.studentsTable.getSelectionModel().getSelectedItem() == null);
		});
		
		this.deleteSelectedButton.setOnAction(event->{
			this.errorOuput.setText("");
			this.model.remove(this.studentsTable.getSelectionModel().getSelectedItem());
			refreshTable();
		});
		
		this.searchByLastName.setOnAction(event->{
			this.errorOuput.setText("");
			this.studentsTable.getItems().setAll(
					this.model.getByCriteria(s->s.getLastName().equals(this.lastNameInput.getText()))
			);
		});
		
	}
	
	private void refreshTable() {
		this.studentsTable.getItems().setAll(this.model.getAll());
	}
	
}
