public class JShape extends Shape {
    public JShape() {
        super();
    }

    @Override
    public void initPointList() {
        pointList = new Point[4][4];
        startPointList = new Point[4];

        pointList[0][0] = new Point(5, 0, Resource.Images.ID_RED);
        pointList[0][1] = new Point(6, 0, Resource.Images.ID_RED);
        pointList[0][2] = new Point(7, 0, Resource.Images.ID_RED);
        pointList[0][3] = new Point(8, 0, Resource.Images.ID_RED);

        pointList[1][0] = new Point(6, -1, Resource.Images.ID_RED);
        pointList[1][1] = new Point(6, 0, Resource.Images.ID_RED);
        pointList[1][2] = new Point(6, 1, Resource.Images.ID_RED);
        pointList[1][3] = new Point(6, 2, Resource.Images.ID_RED);

        pointList[2][0] = new Point(5, 0, Resource.Images.ID_RED);
        pointList[2][1] = new Point(6, 0, Resource.Images.ID_RED);
        pointList[2][2] = new Point(7, 0, Resource.Images.ID_RED);
        pointList[2][3] = new Point(8, 0, Resource.Images.ID_RED);

        pointList[3][0] = new Point(6, -1, Resource.Images.ID_RED);
        pointList[3][1] = new Point(6, 0, Resource.Images.ID_RED);
        pointList[3][2] = new Point(6, 1, Resource.Images.ID_RED);
        pointList[3][3] = new Point(6, 2, Resource.Images.ID_RED);

        startPointList[0] = new Point(5, 0, Resource.Images.ID_RED);
        startPointList[1] = new Point(6, -1, Resource.Images.ID_RED);
        startPointList[2] = new Point(5, 0, Resource.Images.ID_RED);
        startPointList[3] = new Point(6, -1, Resource.Images.ID_RED);
    };

}
