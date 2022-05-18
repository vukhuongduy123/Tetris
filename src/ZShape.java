public class ZShape extends Shape {
    public ZShape() {
        super();
    }

    @Override
    void initPointList() {
        point_list = new Point[4][4];

        point_list[0][0] = new Point(5, 0, ColorID.ID_LIGHT_BLUE);
        point_list[0][1] = new Point(6, 0, ColorID.ID_LIGHT_BLUE);
        point_list[0][2] = new Point(6, 1, ColorID.ID_LIGHT_BLUE);
        point_list[0][3] = new Point(7, 1, ColorID.ID_LIGHT_BLUE);

        point_list[1][0] = new Point(6, 0, ColorID.ID_LIGHT_BLUE);
        point_list[1][1] = new Point(5, 1, ColorID.ID_LIGHT_BLUE);
        point_list[1][2] = new Point(6, 1, ColorID.ID_LIGHT_BLUE);
        point_list[1][3] = new Point(5, 2, ColorID.ID_LIGHT_BLUE);

        point_list[2][0] = new Point(5, 0, ColorID.ID_LIGHT_BLUE);
        point_list[2][1] = new Point(6, 0, ColorID.ID_LIGHT_BLUE);
        point_list[2][2] = new Point(6, 1, ColorID.ID_LIGHT_BLUE);
        point_list[2][3] = new Point(7, 1, ColorID.ID_LIGHT_BLUE);

        point_list[3][0] = new Point(6, 0, ColorID.ID_LIGHT_BLUE);
        point_list[3][1] = new Point(5, 1, ColorID.ID_LIGHT_BLUE);
        point_list[3][2] = new Point(6, 1, ColorID.ID_LIGHT_BLUE);
        point_list[3][3] = new Point(5, 2, ColorID.ID_LIGHT_BLUE);
    };

}
