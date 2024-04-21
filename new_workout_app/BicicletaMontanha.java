public class BicicletaMontanha extends Altimetria {
    private double variaçãoSuspensão; // mm
    private int númeroMudanças;
    private boolean discoTravão;

    public BicicletaMontanha() {
        super();
        this.variaçãoSuspensão = 0;
        this.númeroMudanças = 0;
        this.discoTravão = false;
    }

    public BicicletaMontanha(String codigo, String descrição, int duração, 
            Utilizador user, double dist, double velocidade, 
            String local, int altimetria, double variaçãoSuspensão,
            int númeroMudançãs, boolean discoTravão) {
        super(codigo, descrição, duração, user, dist, velocidade, local, altimetria);
        this.variaçãoSuspensão = variaçãoSuspensão;
        this.númeroMudanças = númeroMudançãs;
        this.discoTravão = discoTravão;
    }

    public BicicletaMontanha(BicicletaMontanha outro) {
        super(outro);
        this.variaçãoSuspensão = getVariaçãoSuspensão();
        this.númeroMudanças = getNúmeroMudanças();
        this.discoTravão = hasDiscoTravão();
    }

	public double getVariaçãoSuspensão() {
		return variaçãoSuspensão;
	}
 
	public void setVariaçãoSuspensão(double variaçãoSuspensão) {
		this.variaçãoSuspensão = variaçãoSuspensão;
	}

	public int getNúmeroMudanças() {
		return númeroMudanças;
	}

	public void setNúmeroMudanças(int númeroMudanças) {
		this.númeroMudanças = númeroMudanças;
	}

	public boolean hasDiscoTravão() {
		return discoTravão;
	}

	public void setDiscoTravão(boolean discoTravão) {
		this.discoTravão = discoTravão;
	}


    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        if(!super.equals(o)) return false;
        BicicletaMontanha bicicleta = (BicicletaMontanha) o;
        return Double.compare(this.variaçãoSuspensão, bicicleta.getVariaçãoSuspensão()) == 0
            && this.númeroMudanças == bicicleta.getNúmeroMudanças()
            && this.discoTravão == bicicleta.hasDiscoTravão();
    }

    @Override
    public String toString() {
        return "BicicletaMontanha{" +
            super.toString() + 
            "variaçãoSuspenção='" + this.variaçãoSuspensão + '\'' +
            ", númeroMudanças='" + this.númeroMudanças + '\'' +
            ", discoTravão='" + this.discoTravão + '\'' +
            '}';
    }

    @Override
    public BicicletaMontanha clone() {
        return new BicicletaMontanha(this);
    }

    @Override
    public double calorias() {
        return 9312.123;
    }
}
