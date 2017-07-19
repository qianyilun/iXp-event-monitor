package bargraph;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by I860745 on 7/18/2017.
 */

public class BarGraphIcon implements Icon {
    private final int PADDING_WIDTH = 10;
    private final int TEXT_HEIGHT = 15;
    private final int BARTEXT_OFFSET = 10;
    private final int SPACE_BETWEEN_BARS = 5;
    private final int SPACE_TOP = PADDING_WIDTH + BARTEXT_OFFSET + TEXT_HEIGHT;
    private final int NUM_ROWS_AXIS_TEXT = 1;
    private final int SPACE_BOTTOM = PADDING_WIDTH + NUM_ROWS_AXIS_TEXT * TEXT_HEIGHT;
    private final int SPACE_SIDES = PADDING_WIDTH;

    private final Color BACKGROUND_COLOUR = Color.WHITE;
    private final Color LINE_COLOUR = Color.BLACK;
    private final Color TEXT_COLOUR = Color.BLACK;

    private BarGraphModel barGraphModel;
    private int height;
    private int width;

    private Color barColors[] = {Color.BLUE, Color.GREEN, Color.RED,
            Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK
    };

    public BarGraphIcon(BarGraphModel barGraphModel, int width, int height) {
        this.barGraphModel = barGraphModel;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;

        clearIcon(g2d, x, y);

        int originX = x + SPACE_SIDES;
        int originY = y + height - SPACE_BOTTOM;
        int graphTop = y + SPACE_TOP;
        int graphRight = x + width - SPACE_SIDES;

        drawAxis(g2d, originX, graphTop, graphRight, originY);

        if (barGraphModel.getMaxBarHeight() == 0) {
            return;
        }

        double oneCountHeight = (originY - graphTop) / (double) barGraphModel.getMaxBarHeight();
        int totalWidthPerBar = (graphRight - originX) / barGraphModel.getNumberBars();
        int barWidth = totalWidthPerBar - SPACE_BETWEEN_BARS;

        // Process each bar (draw bar and text)
        int countBars = 0;
        for (BarGraphModel.Bar bar : barGraphModel.bars()) {
            int barLeft = originX + SPACE_BETWEEN_BARS + countBars * totalWidthPerBar;
            int barHeight = (int)(oneCountHeight * bar.getValue());
            int barTop = originY - barHeight;
            int barMiddleX = barLeft + barWidth / 2;

            g2d.setColor(barColors[countBars % barColors.length]);

            drawBar(g2d, barLeft, barTop, barWidth, barHeight);
            printRange(g2d, barMiddleX, originY, countBars, bar);
            printBarHeight(g2d, barMiddleX, barTop, bar);

            // Move on:
            countBars++;
        }
    }

    private void clearIcon(Graphics2D g2d, int x, int y) {
        g2d.setBackground(BACKGROUND_COLOUR);
        g2d.clearRect(x, y, getIconWidth(), getIconHeight());
    }

    private void drawAxis(Graphics2D g2d, int left, int top, int right, int bottom) {
        Point2D origin = new Point2D.Double(left, bottom);
        Point2D endX = new Point2D.Double(right, bottom);
        Point2D endY = new Point2D.Double(left, top);

        g2d.setColor(LINE_COLOUR);
        g2d.draw(new Line2D.Double(origin, endX));
        g2d.draw(new Line2D.Double(origin, endY));
    }


    private void drawBar(Graphics2D g2d, int barLeft, int barTop,	int barWidth, int barHeight) {
        g2d.fill(new Rectangle2D.Double(barLeft, barTop, barWidth, barHeight));
    }
    private void printRange(Graphics2D g2d, int barMiddleX, int originY, int countBars, BarGraphModel.Bar bar) {
        g2d.setColor(TEXT_COLOUR);
        String range = bar.getTitle();
        int textHeightOffset = TEXT_HEIGHT * (1 + countBars % NUM_ROWS_AXIS_TEXT);
        drawStringCentredOnX(g2d, originY + textHeightOffset, barMiddleX, range);
    }
    private void printBarHeight(Graphics2D g2d, int barMiddleX, int barTop, BarGraphModel.Bar bar) {
        String heightText = "" + bar.getValue();
        drawStringCentredOnX(g2d, barTop - BARTEXT_OFFSET, barMiddleX, heightText);
    }

    private void drawStringCentredOnX(Graphics2D g2d, int bottom, int middle, String message) {
        Font font = g2d.getFont();
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(message, context);
        double width = bounds.getWidth();

        int left = (int) (middle - width/2);
        g2d.drawString(message, left, bottom);
    }
}



