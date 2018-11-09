package pckg;

public final class Vector2 {
    public static final Vector2 EMPTY = new Vector2(0f, 0f);
    private float x, y;

    public Vector2(final float x, final float y) {
        set(x, y);
    }

    public Vector2(final double x, final double y) {
        set((float) x, (float) y);
    }

    public Vector2(final Vector2 target) {
        this(target.getX(), target.getY());
    }

    public static float distance(final Vector2 a, final Vector2 b) {
        float x = a.getX() - b.getX();
        x *= x;
        float y = a.getY() - b.getY();
        y *= y;

        return (float) Math.sqrt(x + y);
    }

    public static float angle(final Vector2 a, final Vector2 b) {
        return (float) Math.atan2(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void set(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(final Vector2 vector) {
        x += vector.getX();
        y += vector.getY();
        return this;
    }

    public Vector2 mul(final float multiplier) {
        x *= multiplier;
        y *= multiplier;
        return this;
    }
}
