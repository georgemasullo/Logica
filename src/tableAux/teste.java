package tableAux;

import java.util.ArrayList;

public class teste {
	public static String FormulasNotaveis(int n){
		String alfabeto = "abcdefghijklmnopqrstuvwxyz";
		String formula=new String();
		for(int i=0; i<=n;i++){
			if (i==0)
				formula=alfabeto.charAt(i)+"V"+alfabeto.charAt(i+1);
			if (i==1){
				formula=formula+","+alfabeto.charAt(i-1)+">("+alfabeto.charAt(i+1)+"V"+alfabeto.charAt(i+2)
				+"),"+alfabeto.charAt(i)+">("+alfabeto.charAt(i+1)+"V"+alfabeto.charAt(i+2)+")";
				if(n==1){formula=formula+","+alfabeto.charAt(i+1)+"V"+alfabeto.charAt(i+2);break;}
			}
			if (i>1){
				int j=i;
				if (j!=2){j=j+(i-2);}
				formula=formula+","+alfabeto.charAt(j)+">("+alfabeto.charAt(j+2)+"V"+alfabeto.charAt(j+3)
				+"),"+alfabeto.charAt(j+1)+">("+alfabeto.charAt(j+2)+"V"+alfabeto.charAt(j+3)+")";
				if (i==n){formula=formula+","+alfabeto.charAt(j+2)+"V"+alfabeto.charAt(j+3);}
			}
		}
		System.out.println(formula);
		return formula;
	}
	public static void main(String[] args) {
		
		int n= 5;	
		if(n<=12){
			String formula = FormulasNotaveis(n);	
			String aux[] = new String[n*2+2];
			aux=formula.split(",");

			
			ArrayList<String> hip=new ArrayList<String>();
			for (int i=0;i<aux.length;i++){
				System.out.println(aux[i]);
			}
			for (int i=0;i<aux.length-1;i++){
				hip.add(aux[i]);
			}
			String p = aux[aux.length-1];
			t t =new t(hip,p);
			
			
			double inicio = (double) System.currentTimeMillis();
			t.ex();
			t.totalRamo();
			double fim = (double) System.currentTimeMillis();
			System.out.println("\n\nTempo: "+(fim-inicio));
			
		}else{
			System.out.println("tamanho de n excedido. n<=12");
		}
		
		/*String p ="(¬(p^q))>((¬p)V(¬q))";
		t t =new t(null,p);
		t.ex();*/
	}

}
