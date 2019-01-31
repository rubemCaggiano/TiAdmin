package br.com.kadesh.model;

public enum EnumCondicao {

    OTIMA("Ótima"),
    BOA("Boa"),
    MEDIA("Média"),
    RUIM("Ruim"),
    PESSIMA("Péssima");

    private String condicao;

    private EnumCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getCondicao() {
        return condicao;
    }

}
