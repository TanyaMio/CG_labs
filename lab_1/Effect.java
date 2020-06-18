public class Effect {

    Pattern pat;
    int start_s;
    int end_s;
    int step;
    int repeats;

    public Effect(Pattern p, int st, int en, int step, int r){
        this.pat = p;
        this.start_s = st;
        this.end_s = en;
        this.step = step;
        this.repeats = r;
    }

    public Effect(){
        this.pat = new Pattern(new Primitive(50), 3);
        this.start_s = 50;
        this.end_s = 150;
        this.step = 10;
        this.repeats = 3;
    }

}
