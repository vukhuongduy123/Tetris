import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum ColorID {
    ID_RED, ID_YELLOW, ID_PURPLE, ID_ORANGE, ID_LIGHT_BLUE, ID_GREEN, ID_BLUE,ID_EMPTY;

    static Map<ColorID, BufferedImage> map = null;

    static private void initColorIDMap() {
        map = new HashMap<ColorID, BufferedImage>();
        try {
            map.put(ID_RED, ImageIO.read(ColorID.class.getResource("textures/tetris_block_red_32.png")));
            map.put(ID_YELLOW, ImageIO.read(ColorID.class.getResource("textures/tetris_block_yellow_32.png")));
            map.put(ID_PURPLE, ImageIO.read(ColorID.class.getResource("textures/tetris_block_purple_32.png")));
            map.put(ID_ORANGE, ImageIO.read(ColorID.class.getResource("textures/tetris_block_orange_32.png")));
            map.put(ID_LIGHT_BLUE, ImageIO.read(ColorID.class.getResource("textures/tetris_block_light_blue_32.png")));
            map.put(ID_GREEN, ImageIO.read(ColorID.class.getResource("textures/tetris_block_green_32.png")));
            map.put(ID_BLUE, ImageIO.read(ColorID.class.getResource("textures/tetris_block_blue_32.png")));
            map.put(ID_EMPTY, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public BufferedImage getBufferedImageFromColorID(ColorID colorID) {
        if (colorID == ID_EMPTY)
            return null;
        if (map == null)
            initColorIDMap();
        return map.get(colorID);
    }

}
