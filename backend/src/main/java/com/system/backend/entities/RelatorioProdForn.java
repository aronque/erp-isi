package com.system.backend.entities;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.Arrays;
import java.util.List;

public class RelatorioProdForn extends Relatorio {


    private static final String PATH_CSV = "\\src\\main\\resources\\tmp\\fornecedor_produtos.csv";
    private static final String PATH_XLS = "\\src\\main\\resources\\tmp\\fornecedor_produtos.xlsx";
    private static final String VW_NAME = "VW_FORNECEDOR_PRODUTO";


    public RelatorioProdForn() {
        setSht(getWb().createSheet("Produtos_Fornecedor"));
        createHeader();
    }


    /**
     * Cria a linha com os valores de referência
     */
    public void createHeader() {
        List<String> headers = Arrays.asList("FORNECEDOR", "PRODUTO", "QUANTIDADE_ESTOQUE", "PREÇO");

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
        for(int i = 0; i <= 3; i++) {
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
