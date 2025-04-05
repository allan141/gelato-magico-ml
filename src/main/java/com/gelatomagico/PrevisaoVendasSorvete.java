package com.gelatomagico;

import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.regression.OLS;

import java.io.IOException;

public class PrevisaoVendasSorvete {

    public static void main(String[] args) throws IOException {
        // Caminho para o arquivo CSV
        String csvPath = "src/main/resources/vendas_sorvetes.csv";

        // Carregar os dados CSV para um DataFrame (Smile)
        DataFrame df = LeituraCSV.lerCSV(csvPath);

        // Definindo a fórmula para o modelo de regressão (prever vendas com base na temperatura)
        Formula formula = Formula.lhs("vendas").rhs("temperatura");

        // Criar o modelo de regressão linear (OLS)
        OLS modelo = OLS.fit(formula, df);

        // Exibir coeficientes do modelo
        System.out.println("Coeficiente (Temperatura): " + modelo.coefficients()[1]);
        System.out.println("Intercepto: " + modelo.coefficients()[0]);

        // Previsões para novas temperaturas
        double[] novasTemperaturas = {25, 30, 35};
        for (double temp : novasTemperaturas) {
            double previsao = modelo.predict(new double[]{temp});
            System.out.println("Temperatura: " + temp + "°C, Previsão de vendas: " + previsao);
        }
    }
}