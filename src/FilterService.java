import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author nayef
 */
public class FilterService {

    public static final int[][] filter9 = {{1, 1, 1},
    {1, 1, 1},
    {1, 1, 1}
    };
    public static final int[][] filter16 = {{1, 2, 1},
    {2, 4, 2},
    {1, 2, 1}
    };

    public BufferedImage getFilteredImage(BufferedImage givenImage, int[][] filter, int iterationNum) {

        int count = 0;
        while (count < iterationNum) {

            for (int y = 1; y + 1 < givenImage.getHeight(); y++) {
                for (int x = 1; x + 1 < givenImage.getWidth(); x++) {
                    Color tempColor = getFilteredValue(givenImage, y, x, filter);
                    givenImage.setRGB(x, y, tempColor.getRGB());

                }
            }
            count++;
        }
        return givenImage;
    }

    private Color getFilteredValue(final BufferedImage givenImage, int y, int x, int[][] filter) {
        int r = 0, g = 0, b = 0;
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {

                r += (filter[1 + j][1 + k] * (new Color(givenImage.getRGB(x + k, y + j))).getRed());
                g += (filter[1 + j][1 + k] * (new Color(givenImage.getRGB(x + k, y + j))).getGreen());
                b += (filter[1 + j][1 + k] * (new Color(givenImage.getRGB(x + k, y + j))).getBlue());
            }

        }
        r = r / sum(filter);
        g = g / sum(filter);
        b = b / sum(filter);
        return new Color(r, g, b);
    }

    private int sum(int[][] filter) {
        int sum = 0;
        for (int y = 0; y < filter.length; y++) {
            for (int x = 0; x < filter[y].length; x++) {
                sum += filter[y][x];
            }
        }
        return sum;
    }
}
