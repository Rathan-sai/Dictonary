package com.example.helloworld;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    DictionaryUsingHashMap Dictionary = new DictionaryUsingHashMap();
    ListView<String> suggestionList;

    TextField wordTextField;
    Button searchButton;
    Label meaningLabel;
    AutocompleteTrieMap autocompleteTrieMap = new AutocompleteTrieMap();
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(600, 400);
//        root.setStyle("-fx-background-color:#87CEEB");

        wordTextField = new TextField();
        wordTextField.setTranslateX(10);
        wordTextField.setTranslateY(30);
        wordTextField.setPrefSize(270, 10);
//        autocompleteTrieMap.setWord( wordTextField.getText());
//        autocompleteTrieMap.search(wordTextField.getText());

        meaningLabel = new Label("Enter a word to search");
//        meaningLabel.setTranslateX(30);
//        meaningLabel.setTranslateY(60);
        meaningLabel.setFont(new Font(15));
        meaningLabel.setTextFill(Color.WHITE);
        meaningLabel.setWrapText(true);

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(580,270);
        gridPane.setTranslateY(70);
        gridPane.setTranslateX(10);
        gridPane.getChildren().add(meaningLabel);
//        gridPane.setStyle("-fx-background-color:\t#F5F5F5");

        searchButton = new Button("search");
        searchButton.setTranslateX(290);
        searchButton.setTranslateY(30);
        searchButton.setPrefSize(100, 10);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                meaningLabel.setText();
                String word = wordTextField.getText();
                if(word.isBlank() || word.isEmpty())
                {
                    meaningLabel.setText("please enter a valid word");
                    meaningLabel.setTextFill(Color.RED);
                }
                else
                {
                    String meaning = Dictionary.getMeaning(word);
                    meaningLabel.setText(meaning);
                    meaningLabel.setTextFill(Color.WHITE);
                    meaningLabel.setWrapText(true);
//                    gridPane.getChildren().add(meaningLabel);
                }
            }
        });

        suggestionList = new ListView<>();
        suggestionList.setTranslateX(30);
        suggestionList.setTranslateY(100);
        suggestionList.setMinSize(330, 50);
        suggestionList.setMaxSize(300, 50);

        String[] wordList = {"Rathan", "Rohit", "pravallika", "priyanka"};
//        suggestionList.getItems().addAll(wordList);
        suggestionList.setOrientation(Orientation.HORIZONTAL);

        suggestionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedItem = suggestionList.getSelectionModel().getSelectedItem();
                meaningLabel.setText(selectedItem);
            }
        });

        Image image = new Image("https://images2.alphacoders.com/261/26102.jpg");

        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                                    new BackgroundSize(1,1,true, true, false, false));
        Background bg = new Background(background);

        Image image1 = new Image("https://thumbs.dreamstime.com/b/books-forefront-group-stacked-32783428.jpg");

        BackgroundImage background1 = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true, true, false, false));
        Background bg1 = new Background(background1);

//        gridPane.setBackground(bg1);

        root.getChildren().addAll(wordTextField, searchButton, gridPane);
        root.setBackground(bg);

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Dictionary!");
        stage.getIcons().add(new Image("https://www.iconexperience.com/_img/v_collection_png/512x512/shadow/dictionary.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}