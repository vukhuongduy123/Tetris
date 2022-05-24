public class TShape extends Shape {
    public TShape() {
        super();
    }

    @Override
    public void initPointList() {
        pointList = new Point[4][4];
        startPointList = new Point[4];

        pointList[0][0] = new Point(6, 0, Resource.Images.ID_PURPLE);
        pointList[0][1] = new Point(5, 1, Resource.Images.ID_PURPLE);
        pointList[0][2] = new Point(6, 1, Resource.Images.ID_PURPLE);
        pointList[0][3] = new Point(7, 1, Resource.Images.ID_PURPLE);

        pointList[1][0] = new Point(6, 0, Resource.Images.ID_PURPLE);
        pointList[1][1] = new Point(5, 1, Resource.Images.ID_PURPLE);
        pointList[1][2] = new Point(6, 1, Resource.Images.ID_PURPLE);
        pointList[1][3] = new Point(6, 2, Resource.Images.ID_PURPLE);

        pointList[2][0] = new Point(5, 1, Resource.Images.ID_PURPLE);
        pointList[2][1] = new Point(6, 1, Resource.Images.ID_PURPLE);
        pointList[2][2] = new Point(7, 1, Resource.Images.ID_PURPLE);
        pointList[2][3] = new Point(6, 2, Resource.Images.ID_PURPLE);

        pointList[3][0] = new Point(6, 0, Resource.Images.ID_PURPLE);
        pointList[3][1] = new Point(6, 1, Resource.Images.ID_PURPLE);
        pointList[3][2] = new Point(7, 1, Resource.Images.ID_PURPLE);
        pointList[3][3] = new Point(6, 2, Resource.Images.ID_PURPLE);

        startPointList[0] = new Point(6, 0, Resource.Images.ID_PURPLE);
        startPointList[1] = new Point(6, 0, Resource.Images.ID_PURPLE);
        startPointList[2] = new Point(5, 1, Resource.Images.ID_PURPLE);
        startPointList[3] = new Point(6, 0, Resource.Images.ID_PURPLE);

    };

}
