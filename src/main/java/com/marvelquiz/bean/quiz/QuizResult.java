package com.marvelquiz.bean.quiz;

public class QuizResult {
    private int score;
    private int respostasCertas;
    private int respostasErradas;

    public int getScore() {
        return score;
    }

    public int getRespostasErradas() {
        return respostasErradas;
    }

    public void setRespostasErradas(int respostasErradas) {
        this.respostasErradas = respostasErradas;
    }

    public int getRespostasCertas() {
        return respostasCertas;
    }

    public void setRespostasCertas(int respostasCertas) {
        this.respostasCertas = respostasCertas;
    }

    public void setScore(int score) {
        this.score = score;
    }
}