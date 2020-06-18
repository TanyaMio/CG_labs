import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import static java.lang.Double.parseDouble;

public class Main extends Application{

    Stage window;
    private static Double r = 50.0;
    private static Double a = 3.0;
    private static Double b = 3.0;
    private static Integer n = 5;
    private static Integer l = 5;
    private static Integer s = 5;
    private static Double mr = 200.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("CG Lab 2 Diachenko, Byhtiy, IV-81");

        Label label = new Label("Figure equations:\nX = R*(1+cos(A*t)+(sin(B*t))^2)*cos(t)\nY = R*(1+cos(A*t)+(sin(B*t))^2)*sin(t)\nt in range(0, N*pi)");
        Label lr = new Label("R");
        Label la = new Label("A");
        Label lb = new Label("B");
        Label ln = new Label("N");
        Label ll = new Label("Layers in pattern");
        Label ls = new Label("Step in moire");
        Label lmr = new Label("Big radius of moire");

        TextField rInput = new TextField(Double.toString(r));
        TextField aInput = new TextField(Double.toString(a));
        TextField bInput = new TextField(Double.toString(b));
        TextField nInput = new TextField(Double.toString(n));
        TextField lInput = new TextField(Double.toString(l));
        TextField sInput = new TextField(Double.toString(s));
        TextField mrInput = new TextField(Double.toString(mr));

        Button button = new Button("Apply");
        Button button1 = new Button("Base figure");
        Button button2 = new Button("Pattern");
        Button button3 = new Button("Effect");

        button.setOnAction(e -> setValue(parseDouble(rInput.getText()), parseDouble(aInput.getText()),
                parseDouble(bInput.getText()), (int) parseDouble(nInput.getText()), (int) parseDouble(lInput.getText()),
                (int) parseDouble(sInput.getText()), parseDouble(mrInput.getText())));
        button1.setOnAction(e -> baseFig());
        button2.setOnAction(e -> pattern());
        button3.setOnAction(e -> effect());

        GridPane grid = new GridPane();
        grid.setVgap(50);
        grid.setHgap(20);
        GridPane.setConstraints(label, 1, 1);
        GridPane.setConstraints(lr, 1, 2);
        GridPane.setConstraints(la, 1, 3);
        GridPane.setConstraints(lb, 1, 4);
        GridPane.setConstraints(ln, 1, 5);
        GridPane.setConstraints(ll, 3, 2);
        GridPane.setConstraints(ls, 3, 3);
        GridPane.setConstraints(lmr, 3, 4);

        GridPane.setConstraints(rInput, 2, 2);
        GridPane.setConstraints(aInput, 2, 3);
        GridPane.setConstraints(bInput, 2, 4);
        GridPane.setConstraints(nInput, 2, 5);
        GridPane.setConstraints(lInput, 4, 2);
        GridPane.setConstraints(sInput, 4, 3);
        GridPane.setConstraints(mrInput, 4, 4);

        GridPane.setConstraints(button, 4, 6);
        GridPane.setConstraints(button1, 1, 6);
        GridPane.setConstraints(button2, 2, 6);
        GridPane.setConstraints(button3, 3, 6);

        grid.getChildren().addAll(rInput, aInput, bInput, nInput, lInput, sInput, mrInput, label, lr, la, lb, ln, ll, ls, lmr, button, button1, button2, button3);

        Scene scene = new Scene(grid, 1000, 500);
        window.setScene(scene);
        window.show();

    }

    private void setValue(double nr, double na, double nb, int nn, int nl, int ns, double nmr) {
        r = nr;
        a = na;
        b = nb;
        n = nn;
        l = nl;
        s = ns;
        mr = nmr;
    }

    private void baseFig() {

        Stage prim = new Stage();

        prim.setTitle("Base figure");
        prim.setWidth(800);
        prim.setHeight(600);

        Scene scene = new Scene(new Group());

        ((Group) scene.getRoot()).getChildren().addAll(drawBase(400, 300));

        prim.setScene(scene);
        prim.show();
    }

    private void pattern() {
        Stage patt = new Stage();

        patt.setTitle("Pattern");
        patt.setWidth(1500);
        patt.setHeight(1000);

        Scene scene = new Scene(new Group());

        double startx = 750;
        double starty = 500;
        ((Group) scene.getRoot()).getChildren().addAll(drawBase(startx, starty));

        for(int i = 1; i < l; i++) {
            for (double t = 0; t <= 2*Math.PI; t += Math.PI/3){
                startx = 750 + r*i*Math.cos(t);
                starty = 500 + r*i*Math.sin(t);
                ((Group) scene.getRoot()).getChildren().addAll(drawBase(startx, starty));
            }
        }

        patt.setScene(scene);
        patt.show();
    }

    private void effect() {
        Stage eff = new Stage();

        eff.setTitle("Effect");
        eff.setWidth(1500);
        eff.setHeight(1000);

        Scene scene = new Scene(new Group());

        double startx = 750;
        double starty = 500;
        ((Group) scene.getRoot()).getChildren().addAll(drawBase(startx, starty));

        for(int i = 1; i < (mr-r)/s; i++) {
            for (double t = 0; t <= 2*Math.PI; t += Math.PI/3){
                startx = 750 + (r+i*s)*Math.cos(t);
                starty = 500 + (r+i*s)*Math.sin(t);
                ((Group) scene.getRoot()).getChildren().addAll(drawBase(startx, starty));
            }
        }

        eff.setScene(scene);
        eff.show();
    }

    private Path drawBase(double startx, double starty) {
        Path fig = new Path();


        double t = 0.0;
        double cx = startx + r*(1+Math.cos(a*t)+Math.pow(Math.sin(b*t), 2))*Math.cos(t);
        double cy = starty + r*(1+Math.cos(a*t)+Math.pow(Math.sin(b*t), 2))*Math.sin(t);
        fig.getElements().add(new MoveTo(cx, cy));

        while (t <= n*Math.PI) {
            cx = startx + r*(1+Math.cos(a*t)+Math.pow(Math.sin(b*t), 2))*Math.cos(t);
            cy = starty + r*(1+Math.cos(a*t)+Math.pow(Math.sin(b*t), 2))*Math.sin(t);
            fig.getElements().add(new LineTo(cx, cy));
            fig.getElements().add(new MoveTo(cx, cy));
            t += 0.017;
        }

        return fig;
    }
}
