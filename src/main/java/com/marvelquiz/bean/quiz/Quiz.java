package com.marvelquiz.bean.quiz;

public class Quiz {
    private String pergunta;
    private String respostaCerta;
    private String[] respostasErradas; // 3
    // O conteúdo pode ser tanto uma imagem quanto uma frase ou nome
    // Convention: Tratar imagem como url (String), assim como frase/nome
    private String conteudo;
    private Boolean conteudoIsImage;

    public Quiz(String pergunta, String respostaCerta, String[] respostasErradas, String conteudo,
            Boolean conteudoIsImage) {
        this.pergunta = pergunta;
        this.respostaCerta = respostaCerta;
        this.respostasErradas = respostasErradas;
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

    public String[] getRespostasErradas() {
        return respostasErradas;
    }

    public void setRespostasErradas(String[] respostasErradas) {
        this.respostasErradas = respostasErradas;
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