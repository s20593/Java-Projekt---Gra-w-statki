package Statki;

public class Statek {

    private int pion, poziom, number, zycie;

    public Statek(){
        this.number=-1;
    }

    public Statek(int pion, int poziom, int number, int size){
        if(poziom<size+1-number){
            this.pion=pion;
            this.poziom=poziom;
            this.number=number;
            this.zycie=1;
        }
        else {
            this.number=-1;
        }
    }

    public int getPion() {
        return pion;
    }

    public int getPoziom() {
        return poziom;
    }

    public int getNumber() {
        return number;
    }

    public void setZycie(int zycie) {
        this.zycie = zycie;
    }

    public int getZycie() {
        return zycie;
    }
}
