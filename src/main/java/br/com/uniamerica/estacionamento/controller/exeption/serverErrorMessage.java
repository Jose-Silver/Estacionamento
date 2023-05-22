package br.com.uniamerica.estacionamento.controller.exeption;

public class serverErrorMessage {

    private String message;
    private String table;
    private String detail;

    public serverErrorMessage(String message, String table, String detail) {
        this.message = message;
        this.table = table;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTable() {
        return table;
    }

    public serverErrorMessage() {
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
