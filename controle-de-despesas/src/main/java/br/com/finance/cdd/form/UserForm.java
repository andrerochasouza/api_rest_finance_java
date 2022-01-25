package br.com.finance.cdd.form;

public class UserForm {

    private String name;
    private String cpf;
    private Double WalletValue;

    public UserForm(String name, String cpf, Double walletValue) {
        this.name = name;
        this.cpf = cpf;
        this.WalletValue = walletValue;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getWalletValue() {
        return WalletValue;
    }

}
