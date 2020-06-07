package Statki;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Random;

public class StatkiController2 {
    private String poziom;
    private Plansza plansza = new Plansza();
    private Statek player1 = new Statek();
    private Statek player2 = new Statek();
    private Statek player3 = new Statek();
    private Statek enemy1 = new Statek();
    private Statek enemy2 = new Statek();
    private Statek enemy3 = new Statek();


    @FXML
    private GridPane grd;

    @FXML
    private GridPane grd1;

    @FXML
    private HBox litery;

    @FXML
    private VBox liczby;

    @FXML
    private Label tekst;

    @FXML
    private Label wynik;

    @FXML
    private Button replay;

    @FXML
    void setStatek(MouseEvent event) {

        String idVal=((Button)event.getSource()).getId().replace("b", "");
        int a = Character.getNumericValue(idVal.charAt(0));
        int b = Character.getNumericValue(idVal.charAt(1));

        Scene scene = grd.getScene();
        Scene scene2 = grd1.getScene();

        if (player1.getNumber()==1 && player2.getNumber()!=2){
            if(plansza.getPlayerPole(a,b)=="puste" && plansza.getPlayerPole(a,b+1)=="puste"){
                Statek player2 = new Statek(a, b, 2, plansza.getSize());
                if(player2.getNumber()==2) {
                    this.player2 = player2;
                    plansza.setPlayer(a, b, "player");
                    plansza.setPlayer(a, b+1, "player");

                    tekst.setText("Statek 2 ustawiony\nUstaw statek zajmujacy 3 pola");

                    ((Button) event.getSource()).setStyle("-fx-background-color: white;");
                    Button button = (Button) scene2.lookup("#b"+a+b+"1");
                    button.setStyle("-fx-background-color: white;");
                    button = (Button) scene.lookup("#b"+a+(b+1));
                    button.setStyle("-fx-background-color: white;");
                    button = (Button) scene2.lookup("#b"+a+(b+1)+"1");
                    button.setStyle("-fx-background-color: white;");

                }
            }
        }
        else if(player2.getNumber()==2){
            if(plansza.getPlayerPole(a,b)=="puste" && plansza.getPlayerPole(a, b+1)=="puste" && plansza.getPlayerPole(a,b+2)=="puste") {
                Statek player3 = new Statek(a, b, 3, plansza.getSize());
                if(player3.getNumber()==3) {
                    this.player3 = player3;
                    plansza.setPlayer(a, b, "player");
                    plansza.setPlayer(a, b+1, "player");
                    plansza.setPlayer(a, b+2, "player");

                    tekst.setText("Rozgrywka\nZniszcz okręty przeciwnika");

                    ((Button) event.getSource()).setStyle("-fx-background-color: white;");
                    Button button = (Button) scene2.lookup("#b"+a+b+"1");
                    button.setStyle("-fx-background-color: white;");
                    button = (Button) scene2.lookup("#b"+a+(b+1)+"1");
                    button.setStyle("-fx-background-color: white;");
                    button = (Button) scene2.lookup("#b"+a+(b+2)+"1");
                    button.setStyle("-fx-background-color: white;");

                    grd.setVisible(false);
                    grd1.setVisible(true);
                    grd1.setDisable(false);

                    setEnemy();
                }
            }
        }
        else {
            Statek player1 = new Statek(a, b, 1, plansza.getSize());
            if(player1.getNumber()==1){
                this.player1=player1;
                plansza.setPlayer(a, b, "player");

                Button button = (Button) scene2.lookup("#b"+a+b+"1");
                button.setStyle("-fx-background-color: white;");

                tekst.setText("Statek 1 ustawiony\nUstaw statek zajmujacy 2 pola");
                ((Button) event.getSource()).setStyle("-fx-background-color: white;");
            }
        }
    }

    public void setEnemy(){
        boolean set=false;
        Random rand = new Random();
        int a, b;

        Scene scene2 = grd1.getScene();

        while(!set){
            a=rand.nextInt(10);
            b=rand.nextInt(10);
            if (enemy1.getNumber()==1 && enemy2.getNumber()!=2){
                if(plansza.getPlayerPole(a,b)=="puste" && plansza.getPlayerPole(a,b+1)=="puste"){
                    Statek enemy2 = new Statek(a, b, 2, plansza.getSize());
                    if(enemy2.getNumber()==2) {
                        this.enemy2 = enemy2;
                        plansza.setEnemy(a, b);
                        plansza.setEnemy(a, b+1);
                    }
                }
            }
            else if(enemy2.getNumber()==2){
                if(plansza.getPlayerPole(a,b)=="puste" && plansza.getPlayerPole(a, b+1)=="puste" && plansza.getPlayerPole(a,b+2)=="puste") {
                    Statek enemy3 = new Statek(a, b, 3, plansza.getSize());
                    if(enemy3.getNumber()==3) {
                        this.enemy3 = enemy3;
                        plansza.setEnemy(a, b);
                        plansza.setEnemy(a, b+1);
                        plansza.setEnemy(a, b+2);

                        set=true;
                    }
                }
            }
            else {
                Statek enemy1 = new Statek(a, b, 1, plansza.getSize());
                if(enemy1.getNumber()==1 && plansza.getPlayerPole(a,b)=="puste"){
                    this.enemy1=enemy1;
                    plansza.setEnemy(a, b);
                }
            }

        }
    }

    @FXML
    void attack(MouseEvent event) throws IOException {

        String idVal=((Button)event.getSource()).getId().replace("b", "");
        int a = Character.getNumericValue(idVal.charAt(0));
        int b = Character.getNumericValue(idVal.charAt(1));

        Scene scene2 = grd1.getScene();

        if(plansza.getPlayerPole(a, b)=="puste"){
            tekst.setText("Pudło! Tu nic nie ma");
            plansza.setPlayer(a,b, "pudlo");
            ((Button) event.getSource()).setStyle("-fx-background-color: blue;");
        }
        else if(plansza.getPlayerPole(a,b)=="enemy"){
            tekst.setText("Trafiony!");
            plansza.setPlayer(a,b, "trafiony");
            ((Button) event.getSource()).setStyle("-fx-background-color: red;");
            if(a==enemy1.getPion() && b==enemy1.getPoziom()){
                tekst.setText("Statek 1 zatopiony!");
                ((Button) event.getSource()).setStyle("-fx-background-color: darkred;");
                enemy1.setZycie(0);
            }
            else if(a==enemy2.getPion() && (b==enemy2.getPoziom() || b==enemy2.getPoziom()+1)){
                if(plansza.getPlayerPole(a, b+1)=="trafiony" && b==enemy2.getPoziom()) {
                    tekst.setText("Statek 2 zatopiony!");
                    ((Button) event.getSource()).setStyle("-fx-background-color: darkred;");
                    Button button = (Button) scene2.lookup("#b"+a+(b+1)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    enemy2.setZycie(0);
                }
                else if(plansza.getPlayerPole(a, b-1)=="trafiony" && b==enemy2.getPoziom()+1){
                    tekst.setText("Statek 2 zatopiony!");
                    ((Button) event.getSource()).setStyle("-fx-background-color: darkred;");
                    Button button = (Button) scene2.lookup("#b"+a+(b-1)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    enemy2.setZycie(0);
                }
            }
            else if(a==enemy3.getPion() && (b==enemy3.getPoziom() || b==enemy3.getPoziom()+1) || b==enemy3.getPoziom()+2){
                if(plansza.getPlayerPole(a, b+1)=="trafiony" && plansza.getPlayerPole(a, b+2)=="trafiony" && b==enemy3.getPoziom()) {
                    tekst.setText("Statek 3 zatopiony!");
                    ((Button) event.getSource()).setStyle("-fx-background-color: darkred;");
                    Button button = (Button) scene2.lookup("#b"+a+(b+1)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    button = (Button) scene2.lookup("#b"+a+(b+2)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    enemy3.setZycie(0);
                }
                else if(plansza.getPlayerPole(a, b-1)=="trafiony" && plansza.getPlayerPole(a, b+1)=="trafiony" && b==enemy3.getPoziom()+1){
                    tekst.setText("Statek 3 zatopiony!");
                    ((Button) event.getSource()).setStyle("-fx-background-color: darkred;");
                    Button button = (Button) scene2.lookup("#b"+a+(b-1)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    button = (Button) scene2.lookup("#b"+a+(b+1)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    enemy3.setZycie(0);
                }
                else if(plansza.getPlayerPole(a, b-2)=="trafiony" && plansza.getPlayerPole(a, b-1)=="trafiony" && b==enemy3.getPoziom()+2){
                    tekst.setText("Statek 3 zatopiony!");
                    ((Button) event.getSource()).setStyle("-fx-background-color: darkred;");
                    Button button = (Button) scene2.lookup("#b"+a+(b-2)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    button = (Button) scene2.lookup("#b"+a+(b-1)+"1");
                    button.setStyle("-fx-background-color: darkred;");
                    enemy3.setZycie(0);
                }
            }
        }
        else if(plansza.getPlayerPole(a,b)=="player" || plansza.getPlayerPole(a,b)=="obrazenia" || plansza.getPlayerPole(a,b)=="zniszczony"){
            tekst.setText("Nie strzelaj w swoje statki!");
        }
        else if(plansza.getPlayerPole(a, b)=="trafiony" || plansza.getPlayerPole(a, b)=="zatopiony" || plansza.getPlayerPole(a,b)=="pudlo"){
            tekst.setText("Już tu strzelałeś!");
        }

        if(enemy1.getZycie()==0 && enemy2.getZycie()==0 && enemy3.getZycie()==0){
            wynik.setText("Wygrałeś");
            grd1.setVisible(false);
            liczby.setVisible(false);
            litery.setVisible(false);
            tekst.setVisible(false);
            wynik.setVisible(true);
            replay.setVisible(true);
        }
        else {
            attackEnemy();
        }

    }

    public void attackEnemy(){
        boolean set=false;
        Random rand = new Random();
        int a, b;

        Scene scene2 = grd1.getScene();

        while(!set) {
            a = rand.nextInt(10);
            b = rand.nextInt(10);

            if(plansza.getPlayerPole(a, b)=="puste"){
                tekst.setText(tekst.getText()+"\nKomputer nie trafił!");
                plansza.setEnemyPole(a,b, "pudlo");
                set=true;
            }
            else if(plansza.getPlayerPole(a,b)=="player") {
                tekst.setText(tekst.getText() + "\nKomputer trafił!");
                plansza.setPlayer(a, b, "obrazenia");
                Button button = (Button) scene2.lookup("#b" + a + b + "1");
                button.setStyle("-fx-background-color: gray;");
                set = true;
                if (a == player1.getPion() && b == player1.getPoziom()) {
                    tekst.setText(tekst.getText() + "\nTwój statek 1 został zatopiony!");
                    button = (Button) scene2.lookup("#b" + a + b + "1");
                    button.setStyle("-fx-background-color: black;");
                    player1.setZycie(0);
                } else if (a == player2.getPion() && (b == player2.getPoziom() || b == player2.getPoziom() + 1)) {
                    if (plansza.getPlayerPole(a, b + 1) == "obrazenia" && b == player2.getPoziom()) {
                        tekst.setText(tekst.getText() + "\nTwój statek 2 został zatopiony!");
                        button = (Button) scene2.lookup("#b" + a + b + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b + 1) + "1");
                        button.setStyle("-fx-background-color: black;");
                        player2.setZycie(0);
                    } else if (plansza.getPlayerPole(a, b - 1) == "obrazenia" && b == player2.getPoziom() + 1) {
                        tekst.setText(tekst.getText() + "\nTwój statek 2 został zatopiony!");
                        button = (Button) scene2.lookup("#b" + a + b + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b - 1) + "1");
                        button.setStyle("-fx-background-color: black;");
                        player2.setZycie(0);
                    }
                } else if (a == player3.getPion() && (b == player3.getPoziom() || b == player3.getPoziom() + 1) || b == player3.getPoziom() + 2) {
                    if (plansza.getPlayerPole(a, b + 1) == "obrazenia" && plansza.getPlayerPole(a, b + 2) == "obrazenia" && b == player3.getPoziom()) {
                        tekst.setText(tekst.getText() + "\nTwój statek 3 został zatopiony!");
                        button = (Button) scene2.lookup("#b" + a + b + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b + 1) + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b + 2) + "1");
                        button.setStyle("-fx-background-color: black;");
                        player3.setZycie(0);
                    } else if (plansza.getPlayerPole(a, b - 1) == "obrazenia" && plansza.getPlayerPole(a, b + 1) == "obrazenia" && b == player3.getPoziom() + 1) {
                        tekst.setText(tekst.getText() + "\nTwój statek 3 został zatopiony!");
                        button = (Button) scene2.lookup("#b" + a + b + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b - 1) + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b + 1) + "1");
                        button.setStyle("-fx-background-color: black;");
                        player3.setZycie(0);
                    } else if (plansza.getPlayerPole(a, b - 2) == "obrazenia" && plansza.getPlayerPole(a, b - 1) == "obrazenia" && b == player3.getPoziom() + 2) {
                        tekst.setText(tekst.getText() + "\nTwój statek 3 został zatopiony!");
                        button = (Button) scene2.lookup("#b" + a + b + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b - 2) + "1");
                        button.setStyle("-fx-background-color: black;");
                        button = (Button) scene2.lookup("#b" + a + (b - 1) + "1");
                        button.setStyle("-fx-background-color: black;");
                        player3.setZycie(0);
                    }
                }
            }
            else if(poziom=="Easy"){
                if(plansza.getPlayerPole(a,b)=="pudlo"){
                    tekst.setText(tekst.getText()+"\nKomputer nie trafił!");
                    plansza.setEnemyPole(a,b, "pudlo");
                    set=true;
                }
            }
        }
        if(player1.getZycie()==0 && player2.getZycie()==0 && player3.getZycie()==0){
            wynik.setText("Przegrałeś");
            grd1.setVisible(false);
            liczby.setVisible(false);
            litery.setVisible(false);
            tekst.setVisible(false);
            wynik.setVisible(true);
            replay.setVisible(true);
        }
    }

    @FXML
    void replayStart(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Trudnosc.fxml"));
        Parent root = loader.load();

        Scene scene = replay.getScene();
        scene.setRoot(root);
    }

    public void setPoziom(String poziom){
        this.poziom=poziom;
    }



}
