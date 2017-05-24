package pckg;

public final class Vector2 {
    public static final Vector2 EMPTY = new Vector2(0f, 0f);
    private float x, y;

    public Vector2(float x, float y) {
        set(x, y);
    }

    public Vector2(double x, double y) {
        set((float) x, (float) y);
    }

    public Vector2(Vector2 target) {
        this(target.getX(), target.getY());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 moveVec) {
        x += moveVec.getX();
        y += moveVec.getY();
    }

    public static float distance(Vector2 a, Vector2 b) {
        float x = a.getX() - b.getX();
        x *= x;
        float y = a.getY() - b.getY();
        y *= y;

        return (float) Math.sqrt(x + y);
    }
}
