package tableAux;

public class PilhaEncad <tipo> {
	private static class Celula<tipo>{
		 tipo item;
		 Celula prox;
	}
	private Celula topo;
	private int tam;
	private int totalEmpilha;
	public PilhaEncad(){
		this.topo=null;
		this.tam=0;
	}
	
	public int getTotalEmpilha() {
		return totalEmpilha;
	}

	public void setTotalEmpilha(int totalEmpilha) {
		this.totalEmpilha = totalEmpilha;
	}

	public void empilha(tipo x){
		Celula aux=this.topo;
		this.topo=new Celula<tipo>();
		this.topo.item=x;
		this.topo.prox=aux;
		this.tam++;
		this.totalEmpilha++;
	}
	public Object desempilha() throws Exception{
		if(this.vazia()){
			throw new Exception("Erro:A pilha esta vazia");
		}
		tipo item=(tipo) this.topo.item;
		this.topo=this.topo.prox;
		this.tam--;
		return item;
	}
	public boolean vazia(){
		if(this.topo==null)
			return true;
		return false;
	}
	public int tamanho(){
		return this.tam;
	}
}
