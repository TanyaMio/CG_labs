import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.AnimationTimer;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Path;
import javafx.scene.Node;
import javafx.stage.Stage;
import static java.lang.Double.parseDouble;
import java.lang.Math;
import java.util.ArrayList;

public class Main extends Application{

    Stage window;
    private static Double r1 = 100.0;
    private static Double a1 = 6.0;
    private static Double b1 = 7.0;
    private static Double r2 = 50.0;
    private static Double a2 = 3.0;
    private static Double b2 = 3.0;
    private static Integer n = 2;
    private Integer dur1 = 15000;
    private Integer dur2 = 4000;
    private Double curt1 = 0.0;
    private Double curt2 = 0.0;
    private Long lastFrame = 0L;
    private Double nFrames1 = 0.0;
    private Double nFrames2 = 0.0;
    private Integer nFrames = 0;
    private Double frameDur = 0.0;
    private static int index = 0;
    private static double[] frameRates = new double[100];


    private Circle c1 = new Circle(750, 500, 5, Color.RED);
    private Circle c2 = new Circle(750, 500, 5, Color.BLUE);
    private Color[] colC1 = {Color.RED, Color.YELLOW};
    private Color[] colC2 = {Color.BLUE, Color.VIOLET};

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long nanosElapsed = l - lastFrame;
            double frameRate = 1000000000.0 / nanosElapsed;
            index++;
            index %= frameRates.length;
            frameRates[index] = frameRate;
            nFrames++;
            changeColor();
            if(nFrames >= 100){
                nFrames1 = nFrames*dur1/((double)l);
                nFrames2 = nFrames*dur2/((double)l);
                lastFrame = l;
            } else {
                nFrames1 = getAverageFPS()*dur1/(3655555555555.999999999);
                nFrames2 = getAverageFPS()*dur2/(3655555555555.999999999);
            }
            curt1 += 0.017/(nFrames1);
            curt2 += 0.017/(nFrames2);
            //999999999999.00182101
            //min = 2937500000000.0
            //max = 3000000000000.0
            //cen = 2937500000000.0
            //      2865000000000.0
            //      2901250000000.0

            //3655000000000.0
        }
    };

    public static void main(String[] args) {
        launch(args);
    }
    public static double getInstantFPS()
    {
        return frameRates[index % frameRates.length];
    }

    public static double getAverageFPS()
    {
        double total = 0.0d;

        for (int i = 0; i < frameRates.length; i++)
        {
            total += frameRates[i];
        }

        return total / frameRates.length;
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("CG Lab 2 Diachenko, Byhtiy, IV-81");

        Label label = new Label("Trajectory equations:\nX = R*(1+cos(A*t)+(sin(B*t))^2)*cos(t)\nY = R*(1+cos(A*t)+(sin(B*t))^2)*sin(t)\nt in range(0, 2*pi)");
        Label lr1 = new Label("R1");
        Label la1 = new Label("A1");
        Label lb1 = new Label("B1");
        Label lr2 = new Label("R2");
        Label la2 = new Label("A2");
        Label lb2 = new Label("B2");

        TextField r1Input = new TextField(Double.toString(r1));
        TextField a1Input = new TextField(Double.toString(a1));
        TextField b1Input = new TextField(Double.toString(b1));
        TextField r2Input = new TextField(Double.toString(r2));
        TextField a2Input = new TextField(Double.toString(a2));
        TextField b2Input = new TextField(Double.toString(b2));


        Button button = new Button("Apply");
        Button Sbutton = new Button("Start");

        button.setOnAction(e -> setValue(parseDouble(r1Input.getText()), parseDouble(a1Input.getText()),
                parseDouble(b1Input.getText()), parseDouble(r2Input.getText()), parseDouble(a2Input.getText()),
                parseDouble(b2Input.getText())));

        Sbutton.setOnAction(e -> start_anim());

        GridPane grid = new GridPane();
        grid.setVgap(50);
        grid.setHgap(20);
        GridPane.setConstraints(label, 1, 1);
        GridPane.setConstraints(lr1, 1, 2);
        GridPane.setConstraints(la1, 1, 3);
        GridPane.setConstraints(lb1, 1, 4);
        GridPane.setConstraints(lr2, 3, 2);
        GridPane.setConstraints(la2, 3, 3);
        GridPane.setConstraints(lb2, 3, 4);

        GridPane.setConstraints(r1Input, 2, 2);
        GridPane.setConstraints(a1Input, 2, 3);
        GridPane.setConstraints(b1Input, 2, 4);
        GridPane.setConstraints(r2Input, 4, 2);
        GridPane.setConstraints(a2Input, 4, 3);
        GridPane.setConstraints(b2Input, 4, 4);

        GridPane.setConstraints(button, 3, 6);
        GridPane.setConstraints(Sbutton, 4, 6);

        grid.getChildren().addAll(r1Input, a1Input, b1Input, r2Input, a2Input, b2Input, label, lr1, la1, lb1, lr2, la2, lb2, button, Sbutton);

        Scene scene = new Scene(grid, 1000, 500);
        window.setScene(scene);
        window.show();

    }

    private void setValue(double nr1, double na1, double nb1, double nr2, double na2, double nb2) {
        r1 = nr1;
        a1 = na1;
        b1 = nb1;
        r2 = nr2;
        a2 = na2;
        b2 = nb2;
    }

    private void start_anim() {

        Stage prim = new Stage();

        prim.setTitle("Animation");
        prim.setWidth(1500);
        prim.setHeight(1000);

        Scene scene = new Scene(new Group());

        Path track1 = drawBase(750, 500, r1, a1, b1, n, Color.LIGHTCORAL);
        Path track2 = drawBase(750, 500, r2, a2, b2, n, Color.LIGHTBLUE);
        ((Group) scene.getRoot()).getChildren().addAll(track1, track2);

        ((Group) scene.getRoot()).getChildren().addAll(c1, c2);

        PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setDuration(Duration.millis(dur1));
        pathTransition1.setPath(track1);
        pathTransition1.setNode(c1);
        pathTransition1.setCycleCount(Timeline.INDEFINITE);
        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.millis(dur2));
        pathTransition2.setPath(track2);
        pathTransition2.setNode(c2);
        pathTransition2.setCycleCount(Timeline.INDEFINITE);

        pathTransition1.play();
        pathTransition2.play();

        timer.start();

        prim.setScene(scene);
        prim.show();
    }

    public void changeColor() {
        double c1x = 750 + r1*(1+Math.cos(a1*curt1)+Math.pow(Math.sin(b1*curt1), 2))*Math.cos(curt1);
        double c2x = 750 + r2*(1+Math.cos(a2*curt2)+Math.pow(Math.sin(b2*curt2), 2))*Math.cos(curt2);
        double c1y = 500 + r1*(1+Math.cos(a1*curt1)+Math.pow(Math.sin(b1*curt1), 2))*Math.sin(curt1);
        double c2y = 500 + r2*(1+Math.cos(a2*curt2)+Math.pow(Math.sin(b2*curt2), 2))*Math.sin(curt2);
        double dist = Math.sqrt(Math.pow((c1x - c2x),2) + Math.pow((c1y - c2y),2));
        if (dist <= (c1.getRadius()+c2.getRadius())) {
            if(c1.getFill() == colC1[1]) {
                c1.setFill(colC1[0]);
                c2.setFill(colC2[0]);
            } else {
                c1.setFill(colC1[1]);
                c2.setFill(colC2[1]);
            }
        }
    }

    private Path drawBase(double startx, double starty, double r, double a, double b, int n, Color col) {
        Path fig = new Path();
        fig.setStroke(col);

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
