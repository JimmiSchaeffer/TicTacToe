package Server;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Cell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TicTacToeClient extends Application implements TicTacToeConstants {
    //Indicate whether the player has the turn
    private boolean myTurn = false;

    // Indicate the token for the player
    private char myToken = ' ';

    // indicate the token for hte other player
    private char otherToken = ' ';

    // create and initialize cells
    private Cell[][] cell = new Cell[3][3];

    //create and initialize a title label
    private Label lblTitle = new Label();

    //create and initialize a status label
    private Label lblStatus = new Label();

    //indicate selected row and column by the current move
    private int rowSelected;
    private int columnSelected;

    //Input and output streams from/to server
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    // continue to play?
    private boolean continueToPlay = true;

    //wait for the player to mark a cell
    private boolean waiting = true;

    //Host name or IP
    private String host = "localhost";

    @Override // Override the start method from the Application class
    public void start(Stage primaryStage){
        // pane to hold cell
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                pane.add(cell[i][j] = new Cell(i, j), j, i);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(lblTitle);
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);

        //create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 320, 350);
        primaryStage.setTitle("TicTacToeClient"); // set the stage title
        primaryStage.setScene(scene); //Place scene in the stage
        primaryStage.show(); //Display the stage

        //connect to server
        connectToServer();
    }

    private void connectToServer(){
        try {
            //Create a socket to connect to the server
            Socket socket = new Socket(host, 8000);

            //Create an input stream to receive data to the server
            fromServer = new DataInputStream(socket.getInputStream());

            //Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}



























































































































//Her er mit stykke (Jimmi)

public class Cell extends Pane {

    private int row;
    private int column;


    private char token = ' ';

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.setPrefSize(2000, 2000);
        setStyle("-fx-border-color: black");
        this.setOnMouseClicked(e -> handleMouseClick());
    }


    public char getToken() {
        return token;
    }


    public void setToken(char c) {
        token = c;
        repaint();
    }

    protected void repaint() {
        if (token == 'X') {
            Line line1 = new Line(10, 10,
                    this.getWidth() - 10, this.getHeight() - 10);
            line1.endXProberty().bind(this.widthProberty().subtract(10));
            line1.endYProberty().bind(this.heightProberty().subtract(10));
            Line line2 = new Line(10, this.getHeight() - 10,
                    this.getWidth() - 10, 10);
            line2.startYProberty().bind(
               this.heightProberty().subtract(10));
            line2.endXProberty().bind(this.widthProberty().subtract(10));


            this.getChildren().addAll(line1, line2);
        }
        else if (token == 'O') {
            Ellipse ellipse = new Ellipse(this.getWidth() / 2,
                    this.getHeight() / 2, this.getWidth() / 2 - 10,
                    this.getHeight() / 2 - 10);
            ellipse.centerXProberty().bind(
                    this.widthProberty().divide(2));
            ellipse.centerYProberty().bind(
                    this.heightProberty().divide(2));
            ellipse.radiusXProberty().bind(
                    this.widthProberty().divide(2).subtract(10));
            ellipse.radiusYProberty().bind(
                    this.heightProberty().divide(2).subtract(10));
            ellipse.setStroke(Color.BLACK);
            ellipse.setFill(Color.WHITE);

            getChildren().add(ellipse);
        }
    }

}