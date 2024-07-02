import java.awt.Color;

import java.util.HashMap;
import java.util.Map;

public class ColorPaint {

    private Map<Integer, Color> colorDictionnary;

    ColorPaint() {

        colorDictionnary = new HashMap<>();
        // We add the the colors to the Map
        colorDictionnary.put(1, Color.red);
        colorDictionnary.put(2, Color.blue);
        colorDictionnary.put(3, Color.yellow);
        colorDictionnary.put(4, Color.green);
        colorDictionnary.put(5, Color.magenta);
        colorDictionnary.put(6, Color.orange);
        colorDictionnary.put(7, Color.black);

    }

    public Color getColor(Integer colorValue) {
        return colorDictionnary.get(colorValue);
    }

}



