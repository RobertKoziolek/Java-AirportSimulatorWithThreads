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

    public Vector2 add(Vector2 vector) {
        x += vector.getX();
        y += vector.getY();
        return this;
    }

    public Vector2 mul(float multiplier) {
        x *=multiplier;
        y *=multiplier;
        return this;
    }

    public static float distance(Vector2 a, Vector2 b) {
        float x = a.getX() - b.getX();
        x *= x;
        float y = a.getY() - b.getY();
        y *= y;

        return (float) Math.sqrt(x + y);
    }

    public static float angle(Vector2 a, Vector2 b){
        return (float)Math.atan2(a.getX() - b.getX(), a.getY() - b.getY());
    }
}
