public class Pattern {

    Primitive prim;
    int layers;

    public Pattern(){
        this.prim = new Primitive();
        this.layers = 2;
    }

    public Pattern(Primitive p, int l){
        this.prim = p;
        this.layers = l;
    }
}
