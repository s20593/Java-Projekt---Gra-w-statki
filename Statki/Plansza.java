package Statki;

public class Plansza {

    private String[][] player = new String[10][10];
    private String[][] enemy = new String[10][10];

    public Plansza(){
        for (int i=0; i<player.length; i++) {
            for (char j=0; j<player.length; j++) {
                player[i][j]="puste";
                enemy[i][j]="puste";
            }
        }
    }

    public void setPlayer(int pion, int poz, String pole) {
        this.player[pion][poz]=pole;
    }

    public void setEnemy(int pion, int poz) {
        this.player[pion][poz]="enemy";
        this.enemy[pion][poz]="enemy";
    }

    public void setEnemyPole(int pion, int poz, String pole) {
        this.enemy[pion][poz]=pole;
    }

    public int getSize(){
        return player.length;
    }

    public String getPlayerPole(int pion, int poz){
        if(poz<player.length && poz>=0){
            return player[pion][poz];
        }
        return "";
    }

    public String getEnemyPole(int pion, int poz){
        if(poz<enemy.length){
            return enemy[pion][poz];
        }
        return "";
    }
}
