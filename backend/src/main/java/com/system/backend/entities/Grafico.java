package com.system.backend.entities;

import java.util.*;

public class Grafico {

    Map<String, Integer> xy = new HashMap<>();

    private String vwName;

    private Date filtroInicial;

    private Date filtroFinal;

    public Map<String, Integer> getXy() {
        return xy;
    }

    public void addItem(String x, Integer y) {
        getXy().put(x, y);
    }

    public Date getFiltroInicial() {
        return filtroInicial;
    }

    public void setFiltroInicial(Date filtroInicial) {
        this.filtroInicial = filtroInicial;
    }

    public Date getFiltroFinal() {
        return filtroFinal;
    }

    public void setFiltroFinal(Date filtroFinal) {
        this.filtroFinal = filtroFinal;
    }

    public String getVwName() {
        return vwName;
    }

    public void setVwName(String vwName) {
        this.vwName = vwName;
    }
}
