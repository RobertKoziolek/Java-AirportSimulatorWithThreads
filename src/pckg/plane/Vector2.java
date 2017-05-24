package pckg.plane;

public final class Vector2 {
    private float x, y;

    public Vector2(float x, float y){
        set(x,y);
    }
    public Vector2(double x, double y){set((float)x, (float)y);
    }

    public Vector2(Vector2 target) {
        this(target.getX(),target.getY());
    }

    public float getX(){return x;}
    public float getY(){return y;}

    public void set(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void add(Vector2 moveVec) {
        x+=moveVec.getX();
        y+=moveVec.getY();
    }
}
