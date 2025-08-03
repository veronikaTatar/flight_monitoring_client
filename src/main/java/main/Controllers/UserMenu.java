package main.Controllers;

import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Models.Entities.User;
import main.Utility.ClientSocket;
import main.Models.TCP.*;
import main.Enums.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UserMenu {
    @FXML private TextField AddressField;
    @FXML private Slider AgeSlider;
    @FXML private TextField LoginField;
    @FXML private RadioButton MRadioButton;
    @FXML private PasswordField PassField;
    @FXML private PasswordField PassTwoField;
    @FXML private Button UpdateAddressButton;
    @FXML private Button UpdateAgeButton;
    @FXML private Button UpdateLoginButton;
    @FXML private Button UpdatePasswordButton;
    @FXML private Button UpdateSexButton;
    @FXML private RadioButton WRadioButton;
    @FXML private Button buttonBack;
    @FXML private Label labelMessage;

    private User currentUser;
    private final Gson gson = new Gson();

    @FXML
    void initialize() {
        // Загружаем данные пользователя при инициализации
        loadUserData();

        // Настройка ToggleGroup для RadioButton
        ToggleGroup sexToggleGroup = new ToggleGroup();
        MRadioButton.setToggleGroup(sexToggleGroup);
        WRadioButton.setToggleGroup(sexToggleGroup);

        // Настройка AgeSlider
        AgeSlider.setMin(1);
        AgeSlider.setMax(100);
        AgeSlider.setValue(30);
        AgeSlider.setShowTickLabels(true);
        AgeSlider.setShowTickMarks(true);
        AgeSlider.setMajorTickUnit(10);
        AgeSlider.setMinorTickCount(5);
        AgeSlider.setBlockIncrement(1);
        AgeSlider.setSnapToTicks(true); // Привязка к делениям

        // Добавляем Label для отображения текущего значения
        Label ageValueLabel = new Label();
        ageValueLabel.textProperty().bind(
                AgeSlider.valueProperty().asString("%.0f")
        );
        ageValueLabel.setLayoutX(450);
        ageValueLabel.setLayoutY(260);
        ((Pane) AgeSlider.getParent()).getChildren().add(ageValueLabel);
    }

    private void loadUserData() {
        try {
            // Получаем ID текущего пользователя (должен быть сохранен при входе)
            int currentUserId = ClientSocket.getInstance().getCurrentUserId();

            Request request = new Request();
            request.setRequestType(RequestType.GET_USER_BY_ID); // Новый тип запроса
            request.setRequestMessage(String.valueOf(currentUserId)); // Передаем ID

            ClientSocket.getInstance().getOut().println(gson.toJson(request));
            ClientSocket.getInstance().getOut().flush();

            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response response = gson.fromJson(answer, Response.class);

            if (response.getResponseStatus() == ResponseStatus.OK) {
                // Десериализуем одного пользователя, а не список
                currentUser = gson.fromJson(response.getResponseData(), User.class);
                updateFields();
                showMessage("Данные успешно загружены");
            } else {
                showMessage("Ошибка загрузки данных: " + response.getResponseMessage());
            }
        } catch (IOException e) {
            showMessage("Ошибка соединения с сервером");
            e.printStackTrace();
        }
    }

    private void updateFields() {
        if (currentUser != null && currentUser.getPersonData() != null) {
            LoginField.setText(currentUser.getLogin());
            AddressField.setText(currentUser.getPersonData().getAddress());
            AgeSlider.setValue(currentUser.getPersonData().getAge());

            if ("M".equals(currentUser.getPersonData().getSex())) {
                MRadioButton.setSelected(true);
            } else {
                WRadioButton.setSelected(true);
            }
        }
    }

    @FXML
    void UpdateLogin_Pressed(ActionEvent event) {
        if (!LoginField.getText().isEmpty()) {
            currentUser.setLogin(LoginField.getText());
            updateUser();
        } else {
            showMessage("Логин не может быть пустым");
        }
    }

    @FXML
    void UpdatePassword_Pressed(ActionEvent event) {
        if (!PassField.getText().isEmpty() && PassField.getText().equals(PassTwoField.getText())) {
            currentUser.setPassword(PassField.getText());
            updateUser();
            PassField.clear();
            PassTwoField.clear();
        } else {
            showMessage("Пароли не совпадают или пустые");
        }
    }

    @FXML
    void UpdateAddress_Pressed(ActionEvent event) {
        if (!AddressField.getText().isEmpty()) {
            currentUser.getPersonData().setAddress(AddressField.getText());
            updateUser();
        } else {
            showMessage("Адрес не может быть пустым");
        }
    }

    @FXML
    void UpdateAge_Pressed(ActionEvent event) {
        currentUser.getPersonData().setAge((int) AgeSlider.getValue());
        updateUser();
    }

    @FXML
    void UpdateSex_Pressed(ActionEvent event) {
        String sex = MRadioButton.isSelected() ? "M" : "Ж";
        currentUser.getPersonData().setSex(sex);
        updateUser();
    }

    private void updateUser() {
        try {
            Request request = new Request();
            request.setRequestType(RequestType.UPDATE_USER);
            request.setRequestMessage(gson.toJson(currentUser));

            ClientSocket.getInstance().getOut().println(gson.toJson(request));
            ClientSocket.getInstance().getOut().flush();

            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response response = gson.fromJson(answer, Response.class);

            if (response.getResponseStatus() == ResponseStatus.OK) {
                showMessage("Данные успешно обновлены");
                currentUser = gson.fromJson(response.getResponseData(), User.class);
            } else {
                showMessage("Ошибка обновления: " + response.getResponseMessage());
            }
        } catch (IOException e) {
            showMessage("Ошибка соединения с сервером");
            e.printStackTrace();
        }
    }

    @FXML
    void Back_Pressed(ActionEvent event) {
        try {
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/main/Login.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        labelMessage.setText(message);
        labelMessage.setVisible(true);

        // Автоматическое скрытие через 5 секунд
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                javafx.application.Platform.runLater(() -> labelMessage.setVisible(false));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}