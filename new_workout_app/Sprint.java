public class Sprint extends Distância {

    public Sprint() {
        super();
    }

    public Sprint(String codigo, String descrição, int duração, Utilizador user, double dist, double velocidade, String local) {
        super(codigo, descrição, duração, user, dist, velocidade, local);
    }

    public Sprint(Sprint outro) {
        super(outro);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Sprint{" +
            super.toString() + '}';
    }

    @Override
    public Sprint clone() {
        return new Sprint(this);
    }

    @Override
    public double calorias() {
        return 818.32;
    }
}
