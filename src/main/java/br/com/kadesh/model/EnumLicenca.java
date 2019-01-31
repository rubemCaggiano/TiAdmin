package br.com.kadesh.model;

public enum EnumLicenca {

    FPP("FPP"),
    MAK("MAK"),
    JACK("JACK");

    private String licenca;

    private EnumLicenca(String licenca) {
        this.licenca = licenca;
    }

    public String getLicenca() {
        return licenca;
    }

}
