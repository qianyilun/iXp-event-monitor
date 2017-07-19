package bargraph;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by I860745 on 7/18/2017.
 */

public class BarGraphModel {
    private final int MIN_VALUE = 0;

    private List<Bar> bars;

    public BarGraphModel(int[] data, String[] barTitles) {
        bars = new ArrayList<Bar>();
        if (data.length != barTitles.length) {
            throw new InvalidParameterException(
                    "Number of elements in data array must match number of elements in names array.");
        }
        for (int i = 0; i < data.length; i++) {
            assert data[i] >= MIN_VALUE;
            bars.add(new Bar(data[i], barTitles[i]));
        }
    }

    public void setData(int[] data) {
        if (data.length != bars.size()) {
            throw new InvalidParameterException(
                    "Number of elements of data must match number names in constructor.");
        }
        List<Bar> newBars = new ArrayList<Bar>();
        for (int i = 0; i < data.length; i++) {
            assert data[i] >= MIN_VALUE;
            newBars.add(new Bar(data[i], bars.get(i).getTitle()));
        }
        bars = newBars;
    }

    public int getNumberBars() {
        return bars.size();
    }

    public Iterable<Bar> bars() {
        return Collections.unmodifiableList(bars);
    }

    public int getMaxBarHeight() {
        // Bar's minimum bar height is 0, even if no bars.
        int max = 0;
        for (Bar bar : bars) {
            if (bar.getValue() > max) {
                max = bar.getValue();
            }
        }
        return max;
    }


    public class Bar {
        private int value;
        private String title;

        public Bar(int value, String title) {
            this.value = value;
            this.title = title;
        }

        public int getValue() {
            return value;
        }

        public String getTitle() {
            return title;
        }
    }
}
