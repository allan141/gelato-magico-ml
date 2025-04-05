package com.gelatomagico;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraCSV {
    
    // Método para ler as temperaturas do arquivo CSV
    public static List<Double> lerTemperaturas(String arquivo) throws IOException {
        List<Double> temperaturas = new ArrayList<>();
        
        try (FileReader reader = new FileReader(arquivo)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            // Itera sobre as linhas do CSV e converte para lista de doubles
            for (CSVRecord record : records) {
                try {
                    // Verifica se a coluna de temperatura está disponível e tenta fazer o parse
                    String tempStr = record.get("temperatura");
                    if (tempStr != null && !tempStr.isEmpty()) {
                        temperaturas.add(Double.parseDouble(tempStr));
                    }
                } catch (NumberFormatException e) {
                    // Caso o valor não seja um número válido, log o erro
                    System.err.println("Valor inválido para temperatura: " + record.get("temperatura"));
                }
            }
        }

        return temperaturas;
    }

    // Método para ler as vendas do arquivo CSV
    public static List<Double> lerVendas(String arquivo) throws IOException {
        List<Double> vendas = new ArrayList<>();
        
        try (FileReader reader = new FileReader(arquivo)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            // Itera sobre as linhas do CSV e converte para lista de doubles
            for (CSVRecord record : records) {
                try {
                    // Verifica se a coluna de vendas está disponível e tenta fazer o parse
                    String vendasStr = record.get("sorvetes_vendidos");
                    if (vendasStr != null && !vendasStr.isEmpty()) {
                        vendas.add(Double.parseDouble(vendasStr));
                    }
                } catch (NumberFormatException e) {
                    // Caso o valor não seja um número válido, log o erro
                    System.err.println("Valor inválido para vendas: " + record.get("sorvetes_vendidos"));
                }
            }
        }

        return vendas;
    }

    // Método principal para testar a leitura do CSV
    public static void main(String[] args) throws IOException {
        // Caminho do arquivo CSV (verifique se o caminho está correto)
        String arquivo = "src/main/resources/vendas_sorvetes.csv";
        
        // Lê as temperaturas e as vendas
        List<Double> temperaturas = lerTemperaturas(arquivo);
        List<Double> vendas = lerVendas(arquivo);

        // Apenas para testar a leitura
        System.out.println("Temperaturas: " + temperaturas);
        System.out.println("Vendas: " + vendas);
    }
}