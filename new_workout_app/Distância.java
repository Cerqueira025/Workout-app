public abstract class Distância extends Atividade {
    private double distância;
    private double velocidade;
    private String local;

    public Distância() {
       super(); 
       distância = 0;
       local = "";
    }

    public Distância(String codigo, String descrição, int duração, 
            Utilizador user, double dist, double velocidade, String local) {
       super(codigo, descrição, duração, user); 
       this.distância = dist;
       this.velocidade = velocidade;
       this.local = local;
    }

    public Distância(Distância outro) {
       super(outro); 
       this.distância = outro.getDistância();
       this.velocidade = outro.getVelocidade();
       this.local = outro.getPercurso();
    }

	public double getDistância() {
		return distância;
	}

	public void setDistância(double distância) {
		this.distância = distância;
	}

    double getVelocidade() {
        return this.velocidade;
    }

    void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

	public String getPercurso() {
		return local;
	}

	public void setPercurso(String local) {
		this.local = local;
	}

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        if(!super.equals(o)) return false;
        Distância distância = (Distância) o;
        return Double.compare(this.distância, distância.getDistância()) == 0
               && Double.compare(this.velocidade, distância.getVelocidade()) == 0
               && this.local.equals(distância.getPercurso());
    }

    @Override
    public String toString() {
        return "Distância{" +
            super.toString() + 
            "distância='" + this.distância + '\'' +
            ", velocidade='" + this.velocidade + '\'' +
            ", local='" + this.local + '}';
    }


    public abstract Distância clone();
    public abstract double calorias();
}
