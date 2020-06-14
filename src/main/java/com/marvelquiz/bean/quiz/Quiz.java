package com.marvelquiz.bean.quiz;

public class Quiz {
    private String pergunta;
    private String respostaCerta;
    private String[] respostas; // todas as respostas do quiz em questão
    // O conteúdo pode ser tanto uma imagem quanto uma frase ou nome
    // Convention: Tratar imagem como url (String), assim como frase/nome
    private String conteudo;
    private Boolean conteudoIsImage;

    public Quiz(String pergunta, String respostaCerta, String[] respostas, String conteudo,
            Boolean conteudoIsImage) {
        this.pergunta = pergunta;
        this.respostaCerta = respostaCerta;
        this.respostas = respostas;
        this.conteudo = conteudo;
        this.conteudoIsImage = conteudoIsImage;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Boolean getConteudoIsImage() {
        return conteudoIsImage;
    }

    public void setConteudoIsImage(Boolean conteudoIsImage) {
        this.conteudoIsImage = conteudoIsImage;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String[] getRespostas() {
        return respostas;
    }

    public void setRespostas(String[] respostas) {
        this.respostas = respostas;
    }

    public String getRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(String respostaCerta) {
        this.respostaCerta = respostaCerta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}