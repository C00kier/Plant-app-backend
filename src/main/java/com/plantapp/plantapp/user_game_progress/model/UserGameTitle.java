package com.plantapp.plantapp.user_game_progress.model;

public enum UserGameTitle {

    POCZĄTKUJĄCY_ZIELONOSKÓRNIK(0,14),
    NOWICJUSZ_ROŚLINNY(15,35),
    LISCIOPIEŃ(35, 59),
    ŚREDNIOZAAWANSOWANY_OGRODNIK(60,89),
    MISTRZ_ZIELONEGO_KCIUKA(90,124),
    MAG_OGRÓDKA_RÓŻNORODNOŚCI(165,219),
    ZIELONY_ARCYMISTRZ(210,259),
    KRÓL_ZIEONYCH_DŻUNGLI(260,314),
    OGRODNIK_WSZECHCZASÓW(315,374),
    ROŚLINNY_GENIUSZ(375,10000);

    private final int MIN_EXPERIENCE;
    private final int MAX_EXPERIENCE;

    UserGameTitle(int MIN_EXPERIENCE, int MAX_EXPERIENCE) {
        this.MIN_EXPERIENCE = MIN_EXPERIENCE;
        this.MAX_EXPERIENCE = MAX_EXPERIENCE;
    }
    public int getMIN_EXPERIENCE() {
        return MIN_EXPERIENCE;
    }
    public int getMAX_EXPERIENCE() {
        return MAX_EXPERIENCE;
    }
}
