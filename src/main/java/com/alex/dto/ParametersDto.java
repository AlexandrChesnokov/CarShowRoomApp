package com.alex.dto;

import com.alex.model.Parameters;
import com.alex.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */
@JsonIgnoreProperties
public class ParametersDto {


    String fromYearParam;
    String toYearParam;
    String fromPriceParam;
    String toPriceParam;
    String colorNameParam;
    String fromHpParam;
    String toHpParam;





    public Parameters toParameters() {
        Parameters prm = new Parameters();
        prm.setFromYearParam(fromYearParam);
        prm.setToYearParam(toYearParam);
        prm.setFromPriceParam(Double.parseDouble(fromPriceParam));
        prm.setToPriceParam(Double.parseDouble(toPriceParam));
        prm.setColorNameParam(colorNameParam);
        prm.setFromHpParam(Double.parseDouble(fromHpParam));
        prm.setToHpParam(Double.parseDouble(toHpParam));
        return prm;
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

    public String getFromPriceParam() {
        return fromPriceParam;
    }

    public void setFromPriceParam(String fromPriceParam) {
        this.fromPriceParam = fromPriceParam;
    }

    public String getToPriceParam() {
        return toPriceParam;
    }

    public void setToPriceParam(String toPriceParam) {
        this.toPriceParam = toPriceParam;
    }

    public String getColorNameParam() {
        return colorNameParam;
    }

    public void setColorNameParam(String colorNameParam) {
        this.colorNameParam = colorNameParam;
    }

    public String getFromHpParam() {
        return fromHpParam;
    }

    public void setFromHpParam(String fromHpParam) {
        this.fromHpParam = fromHpParam;
    }

    public String getToHpParam() {
        return toHpParam;
    }

    public void setToHpParam(String toHpParam) {
        this.toHpParam = toHpParam;
    }
}
