package Model;

public class Tiro {
    public Tiro (Coordenada coordenada){
        Tabuleiro t = new Tabuleiro();
        private int pos = t.getCasa(coordenada);
        if (pos == 0){
            t.setCasa(coordenada,-100);
        }
        else if (pos == 1) {
            t.setCasa(coordenada, -1);
        }
        else if (pos == 2) {
            t.setCasa(coordenada, -2);
        }
        else if (pos == 3) {
            t.setCasa(coordenada, -3);
        }
        else if (pos == 4) {
            t.setCasa(coordenada, -4);
        }
        else if (pos == 5) {
            t.setCasa(coordenada, -5);
        }
    }
}
