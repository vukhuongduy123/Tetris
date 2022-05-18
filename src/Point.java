public class Point {
    int x, y;
    final int size = 25;
    ColorID id;

    Point(int x, int y, ColorID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    int getSize() {
        return size;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    void setColorID(ColorID id) {
        this.id = id;
    }

    ColorID getColorID() {
        return id;
    }
}