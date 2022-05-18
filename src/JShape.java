public class JShape extends Shape {
    public JShape() {
        super();
    }

    @Override
    void initPointList() {
        point_list = new Point[4][4];

        point_list[0][0] = new Point(5, 0, ColorID.ID_RED);
        point_list[0][1] = new Point(6, 0, ColorID.ID_RED);
        point_list[0][2] = new Point(7, 0, ColorID.ID_RED);
        point_list[0][3] = new Point(8, 0, ColorID.ID_RED);

        point_list[1][0] = new Point(6, -1, ColorID.ID_RED);
        point_list[1][1] = new Point(6, 0, ColorID.ID_RED);
        point_list[1][2] = new Point(6, 1, ColorID.ID_RED);
        point_list[1][3] = new Point(6, 2, ColorID.ID_RED);

        point_list[2][0] = new Point(5, 0, ColorID.ID_RED);
        point_list[2][1] = new Point(6, 0, ColorID.ID_RED);
        point_list[2][2] = new Point(7, 0, ColorID.ID_RED);
        point_list[2][3] = new Point(8, 0, ColorID.ID_RED);

        point_list[3][0] = new Point(6, -1, ColorID.ID_RED);
        point_list[3][1] = new Point(6, 0, ColorID.ID_RED);
        point_list[3][2] = new Point(6, 1, ColorID.ID_RED);
        point_list[3][3] = new Point(6, 2, ColorID.ID_RED);
    };

}
