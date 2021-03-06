/*
 * Copyright(C) 2014-2015 Java 8 Workshop participants. All rights reserved.
 * https://github.com/aosn/java8
 */

package com.tasktoys.java8ws.mikan.ch3.ex13;


import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * Manages R/G/B and provides calculation methods.
 */
public class Pixel {
    private double r = 0;
    private double g = 0;
    private double b = 0;

    public static Pixel of(Color color) {
        Objects.requireNonNull(color);
        Pixel rgb = new Pixel();
        rgb.r = color.getRed();
        rgb.g = color.getGreen();
        rgb.b = color.getBlue();
        return rgb;
    }

    public static Pixel of(LatentImage image, int x, int y) {
        Pixel pixel = ofNullable(image, x, y);
        if (pixel == null) {
            throw new IllegalArgumentException("out of image: (" + x + ", " + y + ")");
        }
        return pixel;
    }

    public static Pixel ofNullable(LatentImage image, int x, int y) {
        Objects.requireNonNull(image);
        if (x < 0 || image.getImage().getWidth() <= x) {
            return null;
        }
        if (y < 0 || image.getImage().getHeight() <= y) {
            return null;
        }
        return Pixel.of(image.getImage().getPixelReader().getColor(x, y));
    }

    public Pixel add(Pixel value) {
        Objects.requireNonNull(value);
        r += value.r;
        g += value.g;
        b += value.b;
        return this;
    }

    public Pixel div(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("value is zero.");
        }
        r /= value;
        g /= value;
        b /= value;
        return this;
    }

    public Pixel edge(int count, Pixel value) {
        Objects.requireNonNull(value);
        r = r * count - value.r;
        g = g * count - value.g;
        b = b * count - value.b;
        return this;
    }

    public Color toColor() {
        return Color.color(safeValue(r), safeValue(g), safeValue(b));
    }

    private double safeValue(double source) {
        return source < 0 ? 0 : source > 1 ? 1 : source;
    }
}