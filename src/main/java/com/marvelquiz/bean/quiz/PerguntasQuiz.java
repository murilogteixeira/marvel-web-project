package com.marvelquiz.bean.quiz;

public class PerguntasQuiz {
    // Convenção de nomenclatura: <conteúdo pergunta> + <conteúdo resposta> +
    // <entidade>
    private String imagemNomePersonagem;
    private String titlePeriodoEvento;
    private String imagemTitleEvento;
    private String descricaoTitleEvento;
    private String imagemComecoEvento;
    private String descricaoFinalEvento;
    private String imageTitleComic;
    private String titleAutoresComic;

    public PerguntasQuiz() {
        this.imagemNomePersonagem = "Who is this character?";
        this.titlePeriodoEvento = "When did this event happen?";
        this.imagemTitleEvento = "What is the correct name for this event?";
        this.descricaoTitleEvento = "What is the correct name for this event?";
        this.imagemComecoEvento = "When did this event begin?";
        this.descricaoFinalEvento = "When did this event end?";
        this.imageTitleComic = "What is the correct comic?";
        this.titleAutoresComic = "Who are the authors of this comic?";
    }

    public String getTitleAutoresComic() {
        return titleAutoresComic;
    }

    public String getImageTitleComic() {
        return imageTitleComic;
    }

    public String getDescricaoFinalEvento() {
        return descricaoFinalEvento;
    }

    public String getImagemComecoEvento() {
        return imagemComecoEvento;
    }

    public String getDescricaoTitleEvento() {
        return descricaoTitleEvento;
    }

    public String getImagemTitleEvento() {
        return imagemTitleEvento;
    }

    public String getTitlePeriodoEvento() {
        return titlePeriodoEvento;
    }

    public String getImagemNomePersonagem() {
        return imagemNomePersonagem;
    }
}