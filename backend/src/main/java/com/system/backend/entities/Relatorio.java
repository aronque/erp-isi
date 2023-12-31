package com.system.backend.entities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Relatorio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nomeRelatorio;

    private Workbook wb = new XSSFWorkbook();

    private Sheet sht;

    private List<Row> rows = new ArrayList<>();

    private String email;

    private Usuario requestUser;

    public Workbook getWb() {
        return wb;
    }

    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    public Sheet getSht() {
        return sht;
    }

    public void setSht(Sheet sht) {
        this.sht = sht;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void addRow(Row row) {
        getRows().add(row);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public Usuario getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(Usuario requestUser) {
        this.requestUser = requestUser;
    }

    public void print(List<String> rows) throws IOException {
        File currDir = new File(System.getProperty("user.dir") + getXslPath());
        currDir.createNewFile();

        parseData(rows);

        FileOutputStream outputStream = new FileOutputStream(currDir.getAbsolutePath());
        wb.write(outputStream);
        wb.close();
    }


    public void parseData(List<String> rows) {

        CellStyle rowStyle = getWb().createCellStyle();

        //itera sobre todas as linhas retornadas
        int rowIndex = 1;
        for(String rawRow : rows) {
            Row parsedRow = getSht().createRow(rowIndex++);

            //estilizando linhas
            if(rowIndex % 2 == 0) {
                rowStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            } else {
                rowStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
            }
            parsedRow.setRowStyle(rowStyle);

            //itera sobre as celulas da linha dividas por ;
            int cellIndex = 0;
            for(String cell : rawRow.split(";")) {
                Cell parsedCell = parsedRow.createCell(cellIndex++);
                parsedCell.setCellValue(cell);
            }

            addRow(parsedRow);
        }
    }

    abstract void createHeader();

    public abstract String getPathCsv();

    public abstract String getXslPath();

    public abstract String getVwName();
}
