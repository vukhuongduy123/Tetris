import java.util.Random;
import java.awt.Graphics;

class Shape {
    static public enum Type {
        ISHAPE, JSHAPE, LSHAPE, OSHAPE, SSHAPE, TSHAPE, ZSHAPE;
    }

    static public enum Rotation {
        ROTATED_UP, ROTATED_LEFT, ROTATED_DOWN, ROTATED_RIGHT;

        static private final Rotation[] VALS = values();

        public static Rotation getNextRotation(Rotation rotation) {
            return VALS[(rotation.ordinal() + 1) % VALS.length];
        }

        public static Rotation getPreviousRotation(Rotation rotation) {
            return VALS[(rotation.ordinal() - 1 + VALS.length) % VALS.length];
        }
    }

    static public enum Direction {
        MOVE_UP, MOVE_LEFT, MOVE_DOWN, MOVE_RIGHT;

        static Direction getOppositeDirection(Direction d) {
            switch (d) {
                case MOVE_UP:
                    return MOVE_DOWN;
                case MOVE_LEFT:
                    return MOVE_RIGHT;
                case MOVE_DOWN:
                    return MOVE_UP;
                case MOVE_RIGHT:
                    return MOVE_LEFT;
            }
            return d;
        }
    }

    protected Rotation currentRotation;
    protected Point[][] pointList;
    protected Point[] startPointList;

    public Shape() {
        currentRotation = Rotation.ROTATED_UP;
        initPointList();
    }

    protected void initPointList() {};

    public void draw(Graphics g) {
        for (Point p : pointList[currentRotation.ordinal()])
            g.drawImage(p.getImage(), 26 * p.getX(),
                    26 * p.getY(), Point.SIZE, Point.SIZE, null);

    }

    public void drawNextShape(Graphics g) {
        for (Point p : pointList[currentRotation.ordinal()])
            g.drawImage(p.getImage(), 26 * (GameBoard.ROW + p.getX() - 5) + 10,
                    26 * p.getY() + 20 + g.getFontMetrics().getHeight() * 4,
                    Point.SIZE, Point.SIZE, null);
    }

    public Point[] getPointList() {
        return pointList[currentRotation.ordinal()];
    }

    public void rotateLeft() {
        currentRotation = Rotation.getNextRotation(currentRotation);
    }

    public void rotateRight() {
        currentRotation = Rotation.getPreviousRotation(currentRotation);
    }

    public void move(Direction direction) {
        int x = 0, y = 0;
        switch (direction) {
            case MOVE_UP:
                y = -1;
                break;
            case MOVE_LEFT:
                x = -1;
                break;
            case MOVE_DOWN:
                y = 1;
                break;
            case MOVE_RIGHT:
                x = 1;
                break;
        }

        for (Point[] points : pointList) {
            for (Point p : points) {
                p.setX(p.getX() + x);
                p.setY(p.getY() + y);
            }
        }
    }

    public Rotation getCurRotation() {
        return currentRotation;
    }

    
    public static Shape getShapeFromID(String id, Rotation r, int x, int y) {
        Shape shape = null;
        switch (id) {
            case "Red":
                shape = new JShape();
                break;
            case "Yellow":
                shape = new IShape();
                break;
            case "Orange":
                shape = new OShape();
                break;
            case "LBlue":
                shape = new ZShape();
                break;
            case "Green":
                shape = new SShape();
                break;
            case "Blue":
                shape = new LShape();
                break;
            case "Purple":
                shape = new TShape();
                break;
        }
        shape.currentRotation = r;
        int posX = x - shape.startPointList[shape.currentRotation.ordinal()].getX();
        int posY = y - shape.startPointList[shape.currentRotation.ordinal()].getY();

        for (Point[] points : shape.pointList) {
            for (Point p : points) {
                p.setX(p.getX() + posX);
                p.setY(p.getY() + posY);
            }
        }
        return shape;
    }

    public static Shape getShapeFromID(String id) {
        Shape shape = null;
        switch (id) {
            case "Red":
                shape = new JShape();
                break;
            case "Yellow":
                shape = new IShape();
                break;
            case "Orange":
                shape = new OShape();
                break;
            case "LBlue":
                shape = new ZShape();
                break;
            case "Green":
                shape = new SShape();
                break;
            case "Blue":
                shape = new LShape();
                break;
            case "Purple":
                shape = new TShape();
                break;
        }
        return shape;
    }

    public static Shape getRandomShape() {
        switch (Shape.Type.values()[new Random().nextInt(Shape.Type.values().length)]) {
            case ISHAPE:
                return new IShape();
            case JSHAPE:
                return new JShape();
            case LSHAPE:
                return new LShape();
            case OSHAPE:
                return new OShape();
            case SSHAPE:
                return new SShape();
            case TSHAPE:
                return new TShape();
            case ZSHAPE:
                return new ZShape();
            default:
                return null;
        }
    }
}
