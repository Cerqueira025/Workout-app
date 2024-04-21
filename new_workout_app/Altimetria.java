public abstract class Altimetria extends Distância {
    private int altimetria;

    public Altimetria() {
        super();
        this.altimetria = 0;
    }

    public Altimetria(String codigo, String descrição, int duração, 
            Utilizador user, double dist, double velocidade, 
            String local, int altimetria) {
        super(codigo, descrição, duração, user, dist, local);
        this.altimetria = altimetria;
    }

    public Altimetria(Altimetria outro) {
        super(outro);
        this.altimetria = outro.getAltimetria();
    }


	public int getAltimetria() {
		return altimetria;
	}

	public void setAltimetria(int altimetria) {
		this.altimetria = altimetria;
	}

    
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        if(!super.equals(o)) return false;
        Altimetria altimetria = (Altimetria) o;
        return this.altimetria == altimetria.getAltimetria();
    }

    @Override
    public String toString() {
        return "Altimetria{" +
            super.toString() + 
            ", altimetria='" + this.altimetria + '}';
    }

    public abstract Distância clone();

    public abstract double calorias();
}
