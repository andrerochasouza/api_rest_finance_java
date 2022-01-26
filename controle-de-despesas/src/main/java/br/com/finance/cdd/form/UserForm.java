package br.com.finance.cdd.form;

public class UserForm {

    private String name;
    private String cpf;

    public UserForm(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

}
