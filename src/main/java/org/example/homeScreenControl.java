package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class homeScreenControl implements Initializable {
    // MYSQL database connection elements
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    // Background media elements
    File mediaFile;
    private Media backgroundMedia;
    private MediaPlayer player;
    @FXML
    private MediaView backgroundAnimation;
    @FXML
    private FontIcon exitButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Label loginState;

    public void setBackgroundAnimation() {
        mediaFile = new File("src/main/resources/org/example/animation.mp4"); // Background media file
        backgroundMedia = new Media(mediaFile.toURI().toString());
        player = new MediaPlayer(backgroundMedia);
        backgroundAnimation.setMediaPlayer(player);
        player.play();
    }

    public void setExitButton() {
        System.exit(0);
    }

    public void serverCheck() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
        } catch (SQLException e) {
            loginState.setTextFill(Color.RED);
            loginState.setText(" (!) Server Error ");
        }
    }

    public void setLoginButton() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select *from users");
            while (resultSet.next()) {
                if (usernameField.getText().equals(resultSet.getString(2))) {
                    if (passwordField.getText().equals(resultSet.getString(3))) {
                        loginState.setTextFill(Color.GREEN);
                        loginState.setText("Login Succesfull");
                        break;
                    } else {
                        loginState.setTextFill(Color.RED);
                        loginState.setText("Incorrect password");
                        break;
                    }
                } else {
                    loginState.setTextFill(Color.RED);
                    loginState.setText("Cannot find user");
                    break;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSignUpButton() throws IOException {
        App.setRoot("registerScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBackgroundAnimation();
        serverCheck();
    }
}
