import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;

public class Main extends JFrame {
    public Main(){
        setTitle("Лаб1 Дьяченко Тетяна, Бухтій Олександр, ІВ-81");
        setSize(1200, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void paint(Graphics g) {

        //primitive parameters
        Primitive prim1 = new Primitive(150);

        //primitive 1
        g.setColor(Color.green);
        g.drawRect(100,100,prim1.size,prim1.size);
        g.setColor(Color.blue);
        g.drawOval(100, 100, prim1.size, prim1.size);


        //primitive 2
        g.setColor(Color.red);
        g.drawRect(100+prim1.size+50,100,prim1.size,prim1.size);
        g.setColor(Color.orange);
        g.fillOval(100+prim1.size+50,100,prim1.size,prim1.size);
        g.setColor(Color.red);



        //pattern parameters
        Pattern pat1 = new Pattern(new Primitive(30), 5);

        //pattern draw
        g.setColor(Color.black);
        int xs = 100+100+prim1.size*2;
        int ys = 100;
        drawPattern(g, pat1, xs, ys);



        //effect parameters
        Pattern pat2 = new Pattern(new Primitive(20), 7);
        Effect eff1 = new Effect(pat2, 30, 40, 1, 3);

        //effect draw
        g.setColor(Color.black);
        xs = 100;
        ys = 100 + 100 + prim1.size;
        int cx, cy;
        if(eff1.end_s > eff1.start_s){
            cx = xs + eff1.end_s*eff1.pat.layers/2;
            cy = ys + eff1.end_s*eff1.pat.layers/2;
        } else {
            cx = xs + eff1.start_s * eff1.pat.layers / 2;
            cy = ys + eff1.start_s * eff1.pat.layers / 2;
        }
        for (int i=0; i<eff1.repeats; i++){
            eff1.pat.prim.size = eff1.start_s;
            if (eff1.end_s > eff1.start_s){
                while(eff1.pat.prim.size < eff1.end_s) {
                    xs = cx - eff1.pat.prim.size*eff1.pat.layers/2;
                    ys = cy - eff1.pat.prim.size*eff1.pat.layers/2;
                    drawPattern(g, eff1.pat, xs, ys);
                    pause();
                    g.clearRect(xs, ys, eff1.pat.layers*eff1.pat.prim.size+1, eff1.pat.layers*eff1.pat.prim.size+1);
                    eff1.pat.prim.size += eff1.step;
                }
                eff1.pat.prim.size = eff1.end_s;
                while (eff1.pat.prim.size > eff1.start_s) {
                    xs = cx - eff1.pat.prim.size * eff1.pat.layers / 2;
                    ys = cy - eff1.pat.prim.size * eff1.pat.layers / 2;
                    drawPattern(g, eff1.pat, xs, ys);
                    pause();
                    g.clearRect(xs, ys, eff1.pat.layers * eff1.pat.prim.size + 1, eff1.pat.layers * eff1.pat.prim.size + 1);
                    eff1.pat.prim.size -= eff1.step;
                }
            } else {
                while(eff1.pat.prim.size > eff1.end_s) {
                    xs = cx - eff1.pat.prim.size*eff1.pat.layers/2;
                    ys = cy - eff1.pat.prim.size*eff1.pat.layers/2;
                    drawPattern(g, eff1.pat, xs, ys);
                    pause();
                    g.clearRect(xs, ys, eff1.pat.layers*eff1.pat.prim.size+1, eff1.pat.layers*eff1.pat.prim.size+1);
                    eff1.pat.prim.size -= eff1.step;
                }
                eff1.pat.prim.size = eff1.end_s;
                while (eff1.pat.prim.size < eff1.start_s) {
                    xs = cx - eff1.pat.prim.size * eff1.pat.layers / 2;
                    ys = cy - eff1.pat.prim.size * eff1.pat.layers / 2;
                    drawPattern(g, eff1.pat, xs, ys);
                    pause();
                    g.clearRect(xs, ys, eff1.pat.layers * eff1.pat.prim.size + 1, eff1.pat.layers * eff1.pat.prim.size + 1);
                    eff1.pat.prim.size += eff1.step;
                }
            }

        }
        eff1.pat.prim.size = eff1.start_s;
        xs = cx - eff1.pat.prim.size*eff1.pat.layers/2;
        ys = cy - eff1.pat.prim.size*eff1.pat.layers/2;

        drawPattern(g, eff1.pat, xs, ys);

    }

    public static void main(String [] args) {
        Main frame = new Main();
        frame.paint(null);
    }

    public static void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void drawPattern(Graphics gr, Pattern p, int x, int y){
        for (int i=0; i<p.layers; i++){

            for(int j=0; j<p.layers-i; j++){
                gr.drawRect(x,y,p.prim.size,p.prim.size);
                gr.drawOval(x,y,p.prim.size,p.prim.size);
                x += p.prim.size;
            }
            x -= p.prim.size;

            for(int j=0; j<p.layers-i; j++){
                gr.drawRect(x,y,p.prim.size,p.prim.size);
                gr.drawOval(x,y,p.prim.size,p.prim.size);
                y += p.prim.size;
            }
            y -= p.prim.size;

            for(int j=0; j<p.layers-i; j++){
                gr.drawRect(x,y,p.prim.size,p.prim.size);
                gr.drawOval(x,y,p.prim.size,p.prim.size);
                x -= p.prim.size;
            }
            x += p.prim.size;

            for(int j=0; j<p.layers-1-i; j++){
                gr.drawRect(x,y,p.prim.size,p.prim.size);
                gr.drawOval(x,y,p.prim.size,p.prim.size);
                y -= p.prim.size;
            }
            y += p.prim.size;

            x += p.prim.size/2;
            y -= p.prim.size/2;
        }
    }
}
