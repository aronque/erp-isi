package com.system.backend.entities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.Arrays;
import java.util.List;

public class RelatorioProdEstoque extends Relatorio {


    private static final String PATH_CSV = "\\src\\main\\resources\\tmp\\produtos_estoque.csv";
    private static final String PATH_XLS = "\\src\\main\\resources\\tmp\\produtos_estoque.xlsx";
    private static final String VW_NAME = "VW_PRODUTO_ESTOQUE";

    public RelatorioProdEstoque() {
        setSht(getWb().createSheet("Produtos_Estoque"));
        createHeader();
    }


    /**
     * Cria a linha com os valores de referência
     */
    public void createHeader() {
        List<String> headers = Arrays.asList("PRODUTO", "QUANTIDADE_ESTOQUE");

        Font headerStyle = getWb().createFont();
        headerStyle.setBold(true);
        headerStyle.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = getWb().createCellStyle();
        headerCellStyle.setFont(headerStyle);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setShrinkToFit(true);
        headerCellStyle.setWrapText(false);

        CellStyle headerRowStyle = getWb().createCellStyle();
        headerRowStyle.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());

        addRow(getSht().createRow(0));
        for(int i = 0; i <= 1; i++) {
            getRows().get(0).createCell(i);
            getRows().get(0).getCell(i).setCellStyle(headerCellStyle);
            getRows().get(0).getCell(i).setCellValue(headers.get(i));
            getRows().get(0).setRowStyle(headerRowStyle);
            getSht().autoSizeColumn(i);
        }
    }

    @Override
    public String getPathCsv(){
        return PATH_CSV;
    }

    @Override
    public String getXslPath(){
        return PATH_XLS;
    }

    @Override
    public String getVwName() {
        return VW_NAME;
    }
}
