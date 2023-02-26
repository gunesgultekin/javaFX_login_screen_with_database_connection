package org.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class registerScreenControl implements Initializable {

    File mediaFile;
    private Media backgroundMedia;
    private MediaPlayer player;
    @FXML
    private MediaView backgroundAnimation;
    @FXML
    private FontIcon exitButton;
    @FXML
    private FontIcon returnButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField mailField;
    @FXML
    private DatePicker birthdateField;

    public void setExitButton() {
        System.exit(0);
    }

    public void setReturnButton() throws IOException {
        App.setRoot("homeScreen");
    }

    public void setBackgroundAnimation() {
        mediaFile = new File("src/main/resources/org/example/animation.mp4");
        backgroundMedia = new Media(mediaFile.toURI().toString());
        player = new MediaPlayer(backgroundMedia);
        backgroundAnimation.setMediaPlayer(player);
        player.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackgroundAnimation();
    }
}
