module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    // Добавьте эти строки:
    exports main to javafx.graphics;
    opens main to javafx.fxml;

    opens main.Controllers to javafx.fxml;

    // Разрешаем доступ Gson к нужным пакетам
    opens main.Models.TCP to com.google.gson;
    opens main.Models.Entities to com.google.gson;
}

