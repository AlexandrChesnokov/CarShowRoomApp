package com.alex.model;

import java.util.Objects;

public class Parameters {

    String fromYearParam;
    String toYearParam;
    double fromPriceParam;
    double toPriceParam;
    String colorNameParam;
    double fromHpParam;
    double toHpParam;

    public Parameters() {
    }

    public Parameters(String fromYearParam, String toYearParam, double fromPriceParam, double toPriceParam, String colorNameParam, double fromHpParam, double toHpParam) {
        this.fromYearParam = fromYearParam;
        this.toYearParam = toYearParam;
        this.fromPriceParam = fromPriceParam;
        this.toPriceParam = toPriceParam;
        this.colorNameParam = colorNameParam;
        this.fromHpParam = fromHpParam;
        this.toHpParam = toHpParam;
    }

    public String getFromYearParam() {
        return fromYearParam;
    }

    public void setFromYearParam(String fromYearParam) {
        this.fromYearParam = fromYearParam;
    }

    public String getToYearParam() {
        return toYearParam;
    }

    public void setToYearParam(String toYearParam) {
        this.toYearParam = toYearParam;
    }

    public double getFromPriceParam() {
        return fromPriceParam;
    }

    public void setFromPriceParam(double fromPriceParam) {
        this.fromPriceParam = fromPriceParam;
    }

    public double getToPriceParam() {
        return toPriceParam;
    }

    public void setToPriceParam(double toPriceParam) {
        this.toPriceParam = toPriceParam;
    }

    public String getColorNameParam() {
        return colorNameParam;
    }

    public void setColorNameParam(String colorNameParam) {
        this.colorNameParam = colorNameParam;
    }

    public double getFromHpParam() {
        return fromHpParam;
    }

    public void setFromHpParam(double fromHpParam) {
        this.fromHpParam = fromHpParam;
    }

    public double getToHpParam() {
        return toHpParam;
    }

    public void setToHpParam(double toHpParam) {
        this.toHpParam = toHpParam;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "fromYearParam='" + fromYearParam + '\'' +
                ", toYearParam='" + toYearParam + '\'' +
                ", fromPriceParam=" + fromPriceParam +
                ", toPriceParam=" + toPriceParam +
                ", colorNameParam='" + colorNameParam + '\'' +
                ", fromHpParam=" + fromHpParam +
                ", toHpParam=" + toHpParam +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Double.compare(that.fromPriceParam, fromPriceParam) == 0 &&
                Double.compare(that.toPriceParam, toPriceParam) == 0 &&
                Double.compare(that.fromHpParam, fromHpParam) == 0 &&
                Double.compare(that.toHpParam, toHpParam) == 0 &&
                Objects.equals(fromYearParam, that.fromYearParam) &&
                Objects.equals(toYearParam, that.toYearParam) &&
                Objects.equals(colorNameParam, that.colorNameParam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromYearParam, toYearParam, fromPriceParam, toPriceParam, colorNameParam, fromHpParam, toHpParam);
    }
}
