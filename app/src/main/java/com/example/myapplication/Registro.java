package com.example.myapplication;


public class Registro {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String sexo;
    private String local;
    private String cidade;
    private String dataOcorrencia;
    private String horaChamado;
    private String horaSaida;
    private String horaChegada;
    private String retornoBase;
    private String viaturas;
    private String cov;
    private String bombeiro1;
    private String bombeiro2;
    private String bombeiro3;
    private String tipoOcorrencia;
    private String relato;

    public Registro() {
    }

    public Registro(String nome, String cpf, String dataNascimento, String sexo, String local,
                    String cidade, String dataOcorrencia, String horaChamado, String horaSaida, String horaChegada,
                    String retornoBase, String viaturas, String cov, String bombeiro1,
                    String bombeiro2, String bombeiro3, String tipoOcorrencia, String relato) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.local = local;
        this.cidade = cidade;
        this.dataOcorrencia = dataOcorrencia;
        this.horaChamado = horaChamado;
        this.horaSaida = horaSaida;
        this.horaChegada = horaChegada;
        this.retornoBase = retornoBase;
        this.viaturas = viaturas;
        this.cov = cov;
        this.bombeiro1 = bombeiro1;
        this.bombeiro2 = bombeiro2;
        this.bombeiro3 = bombeiro3;
        this.tipoOcorrencia = tipoOcorrencia;
        this.relato = relato;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getHoraChamado() {
        return horaChamado;
    }

    public String getDataOcorrencia() {
        return dataOcorrencia;
    }
    public void setDataOcorrencia(String dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public void setHoraChamado(String horaChamado) {
        this.horaChamado = horaChamado;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public String getRetornoBase() {
        return retornoBase;
    }

    public void setRetornoBase(String retornoBase) {
        this.retornoBase = retornoBase;
    }

    public String getViaturas() {
        return viaturas;
    }

    public void setViaturas(String viaturas) {
        this.viaturas = viaturas;
    }

    public String getCov() {
        return cov;
    }

    public void setCov(String cov) {
        this.cov = cov;
    }

    public String getBombeiro1() {
        return bombeiro1;
    }

    public void setBombeiro1(String bombeiro1) {
        this.bombeiro1 = bombeiro1;
    }

    public String getBombeiro2() {
        return bombeiro2;
    }

    public void setBombeiro2(String bombeiro2) {
        this.bombeiro2 = bombeiro2;
    }

    public String getBombeiro3() {
        return bombeiro3;
    }

    public void setBombeiro3(String bombeiro3) {
        this.bombeiro3 = bombeiro3;
    }

    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }
}
