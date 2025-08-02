package main.Controllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Enums.RequestType;
import main.Enums.ResponseStatus;
import main.Models.Entities.PersonData;
import main.Models.Entities.User;
import main.Models.TCP.Request;
import main.Models.TCP.Response;
import main.Utility.ClientSocket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class AdminMenu {

    @FXML
    private TableView<User> PersonTable;
    @FXML
    private TableColumn<User, String> NameColumn;
    @FXML
    private TableColumn<User, String> EmailColumn;
    @FXML
    private TableColumn<User, Integer> AgeColumn;
    @FXML
    private TableColumn<User, String> SexColumn;
    @FXML
    private TableColumn<User, String> AddressColumn;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelMessage;
    @FXML
    private Button DeleteButton;

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        // Настройка колонок таблицы
        setupTableColumns();

        // Загрузка данных
        loadUsersData();

        // Настройка обработчиков кнопок
        DeleteButton.setOnAction(event -> deleteSelectedUsers());
    }

    private void setupTableColumns() {
        // Основные колонки пользователя
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        // Колонки из PersonData
        EmailColumn.setCellValueFactory(cellData -> {
            PersonData pd = cellData.getValue().getPersonData();
            return pd != null ? new SimpleStringProperty(pd.getMail()) : new SimpleStringProperty("");
        });

        AgeColumn.setCellValueFactory(cellData -> {
            PersonData pd = cellData.getValue().getPersonData();
            return pd != null ? new SimpleIntegerProperty(pd.getAge()).asObject() : new SimpleIntegerProperty(0).asObject();
        });

        SexColumn.setCellValueFactory(cellData -> {
            PersonData pd = cellData.getValue().getPersonData();
            return pd != null ? new SimpleStringProperty(pd.getSex()) : new SimpleStringProperty("");
        });

        AddressColumn.setCellValueFactory(cellData -> {
            PersonData pd = cellData.getValue().getPersonData();
            return pd != null ? new SimpleStringProperty(pd.getAddress()) : new SimpleStringProperty("");
        });

        PersonTable.setItems(usersData);
    }

    private void loadUsersData() {
        try {
            Request request = new Request();
            request.setRequestType(RequestType.DISPLAY_USER_DATA);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();

            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response response = new Gson().fromJson(answer, Response.class);

            if (response.getResponseStatus() == ResponseStatus.OK) {
                Type userListType = new TypeToken<List<User>>(){}.getType();
                List<User> users = new Gson().fromJson(response.getResponseData(), userListType);

                usersData.clear();
                usersData.addAll(users);
            } else {
                showError("Ошибка загрузки данных: " + response.getResponseData());
            }
        } catch (IOException e) {
            showError("Ошибка соединения с сервером");
            e.printStackTrace();
        }
    }

    private void deleteSelectedUsers() {
        User selectedUser = PersonTable.getSelectionModel().getSelectedItem();
        System.out.println("Удаляемый пользователь: " + new Gson().toJson(selectedUser));
        if (selectedUser == null) {
            showError("Выберите пользователя для удаления");
            return;
        }

        try {
            Request request = new Request();
            request.setRequestType(RequestType.DELETE_USER);
            request.setRequestMessage(new Gson().toJson(selectedUser));

            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();

            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response response = new Gson().fromJson(answer, Response.class);

            if (response.getResponseStatus() == ResponseStatus.OK) {
                loadUsersData(); // Обновляем таблицу
                showError(""); // Скрываем сообщение об ошибке
            } else {
                System.out.println("Ответ сервера: " + answer); // В методе deleteSelectedUsers()
                showError("Ошибка удаления: " + response.getResponseData());
            }
        } catch (IOException e) {
            showError("Ошибка соединения с сервером");
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        labelMessage.setText(message);
        labelMessage.setVisible(!message.isEmpty());
    }

    @FXML
    void Back_Pressed(ActionEvent event) {
        try {
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/main/Login.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            showError("Ошибка перехода на главный экран");
            e.printStackTrace();
        }
    }
}