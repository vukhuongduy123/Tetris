import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Resource {
    public class Images {
        final public static BufferedImage ID_RED, ID_YELLOW, ID_PURPLE, ID_ORANGE, ID_LIGHT_BLUE, ID_GREEN, ID_BLUE,
                ID_EMPTY, ID_BACKGROUND, ID_BTN;
        final private static HashMap<BufferedImage, String> map;
        final private static HashMap<String, BufferedImage> reverseMap;
    
        static {
            BufferedImage id_red, id_yellow, id_purple, id_orange, id_light_blue, id_green, id_blue, id_empty,
                    id_background, id_btn;
            id_red = id_yellow = id_purple = id_orange = id_light_blue = id_green = id_blue = id_empty = id_background = id_btn = null;
            try {
                id_red = ImageIO.read(new File("resources/tetris_block_red_32.png"));
                id_yellow = ImageIO.read(new File("resources/tetris_block_yellow_32.png"));
                id_purple = ImageIO.read(new File("resources/tetris_block_purple_32.png"));
                id_orange = ImageIO.read(new File("resources/tetris_block_orange_32.png"));
                id_light_blue = ImageIO.read(new File("resources/tetris_block_light_blue_32.png"));
                id_green = ImageIO.read(new File("resources/tetris_block_green_32.png"));
                id_blue = ImageIO.read(new File("resources/tetris_block_blue_32.png"));
                id_empty = null;
                id_background = ImageIO.read(new File("resources/background.png"));
                id_btn = ImageIO.read(new File("resources/button.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ID_RED = id_red;
            ID_YELLOW = id_yellow;
            ID_PURPLE = id_purple;
            ID_ORANGE = id_orange;
            ID_LIGHT_BLUE = id_light_blue;
            ID_GREEN = id_green;
            ID_BLUE = id_blue;
            ID_EMPTY = id_empty;
            ID_BACKGROUND = id_background;
            ID_BTN = id_btn;

            map = new HashMap<BufferedImage, String>();
            map.put(ID_RED, "Red");
            map.put(ID_YELLOW, "Yellow");
            map.put(ID_PURPLE, "Purple");
            map.put(ID_ORANGE, "Orange");
            map.put(ID_LIGHT_BLUE, "LBlue");
            map.put(ID_GREEN, "Green");
            map.put(ID_BLUE, "Blue");
            map.put(ID_EMPTY, "Empty");

            reverseMap = new HashMap<String, BufferedImage>();
            reverseMap.put("Red", ID_RED);
            reverseMap.put("Yellow", ID_YELLOW);
            reverseMap.put("Purple", ID_PURPLE);
            reverseMap.put("Orange", ID_ORANGE);
            reverseMap.put("LBlue", ID_LIGHT_BLUE);
            reverseMap.put("Green", ID_GREEN);
            reverseMap.put("Blue", ID_BLUE);
            reverseMap.put("Empty", ID_EMPTY);
        }

        public static String getImageString(BufferedImage image) {
            return map.get(image);
        }

        public static BufferedImage getImageFromString(String id) {
            return reverseMap.get(id);
        }
    }

    public class CustomFont {
        final static Font FONT;
        static {
            Font tmpFont = null;
            try {
                tmpFont = Font.createFont(Font.TRUETYPE_FONT,
                        new File("resources/font.ttf"));
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(tmpFont);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FONT = tmpFont;
        }
    }

    public class Music {
        private static final String MUSIC_PATH = "music/BackgroundMusic.wav";
        private static Clip clip;

        static {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(MUSIC_PATH));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void playOrStop(boolean play) {
            if (play)
                clip.start();
            else
                clip.stop();
        }
    }

    
}
