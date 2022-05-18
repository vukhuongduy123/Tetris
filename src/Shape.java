
import java.util.Random;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Shape {
    static public enum Type {
        ISHAPE, JSHAPE, LSHAPE, OSHAPE, SSHAPE, TSHAPE, ZSHAPE;

        public static Type getRandomType() {
            switch (Shape.Type.values()[new Random().nextInt(Shape.Type.values().length)]) {
                case ISHAPE:
                    return Shape.Type.ISHAPE;
                case JSHAPE:
                    return Shape.Type.JSHAPE;
                case LSHAPE:
                    return Shape.Type.LSHAPE;
                case OSHAPE:
                    return Shape.Type.OSHAPE;
                case SSHAPE:
                    return Shape.Type.SSHAPE;
                case TSHAPE:
                    return Shape.Type.TSHAPE;
                case ZSHAPE:
                    return Shape.Type.ZSHAPE;
                default:
                    return null;
            }
        }
    }

    static public enum Rotation {
        ROTATED_UP, ROTATED_LEFT, ROTATED_DOWN, ROTATED_RIGHT;

        static public final Rotation[] vals = values();

        public static Rotation getNextRotation(Rotation rotation) {
            return vals[(rotation.ordinal() + 1) % vals.length];
        }

        public static Rotation getPreviousRotation(Rotation rotation) {
            return vals[(rotation.ordinal() - 1 + vals.length) % vals.length];
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
    protected Point[][] point_list;
    protected BufferedImage[] blocks;

    public Shape() {
        blocks = new BufferedImage[4];
        currentRotation = Rotation.ROTATED_UP;
        initPointList();
    }

    abstract void initPointList();

    public void draw(Graphics g) {
        for (int i = 0; i < blocks.length; i++)
            g.drawImage(blocks[i], 26 * point_list[currentRotation.ordinal()][i].getX(),
                    26 * point_list[currentRotation.ordinal()][i].getY(),
                    point_list[currentRotation.ordinal()][i].getSize(),
                    point_list[currentRotation.ordinal()][i].getSize(), null);
        
    }

    public void drawNextShape(Graphics g) {
        for (int i = 0; i < blocks.length; i++)
            g.drawImage(blocks[i], 26 * (GameBoard.ROW + point_list[currentRotation.ordinal()][i].getX() - 5) + 10,
            26 * point_list[currentRotation.ordinal()][i].getY() + 20 + g.getFontMetrics().getHeight() * 4,
                    point_list[currentRotation.ordinal()][i].getSize(),
                    point_list[currentRotation.ordinal()][i].getSize(), null);
    }

    public Point[] getPointList() {
        return point_list[currentRotation.ordinal()];
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

        for (Point[] points : point_list) {
            for (Point p : points) {
                p.setX(p.getX() + x);
                p.setY(p.getY() + y);
            }
        }
    }

    public void loadImages() {
        for (int i = 0; i < blocks.length; i++)
            blocks[i] = ColorID.getBufferedImageFromColorID(getPointList()[i].getColorID());
        
    }
}
