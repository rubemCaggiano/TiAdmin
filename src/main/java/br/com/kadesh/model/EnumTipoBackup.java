package br.com.kadesh.model;

public enum EnumTipoBackup {
    COMPLETO("Completo"),
    INCREMENTAL("Incremental"),
    DIFERENCIAL("Diferencial");

    private String tipoBackup;

    private EnumTipoBackup(String tipoBackup) {
        this.tipoBackup = tipoBackup;
    }

    public String getTipoBackup() {
        return tipoBackup;
    }

}
