package com.system.backend.entities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Arrays;
import java.util.List;

public class RelatorioProdForn extends Relatorio {


    public RelatorioProdForn() {
        setSht(getWb().createSheet("Produtos_Fornecedor"));
        createHeader();
    }

    private Workbook parseData(List<?> rows) {

        //itera sobre todas as linhas retornadas
        int rowIndex = 0;
        for(Object rawRow : rows) {
            String aux = (String) rawRow;
            Row parsedRow = getSht().createRow(rowIndex++);

            //itera sobre as celulas da linha dividas por ;
            int cellIndex = 0;
            for(String cell : aux.split(";")) {
                Cell parsedCell = parsedRow.createCell(cellIndex++);
                parsedCell.setCellValue(cell);
            }

            getRows().add(parsedRow);
        }

        return getWb();
    }


    /**
     * Cria a linha com os valores de referência
     */
    private void createHeader() {
        List<String> headers = Arrays.asList("FORNECEDOR", "PRODUTO", "QUANTIDADE_ESTOQUE", "PREÇO");
        for(int i = 0; i <= 4; i++) {
            getRows().get(0).createCell(i);
            getRows().get(0).getCell(0).setCellValue(headers.get(i));
        }
    }
}
