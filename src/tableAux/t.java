package tableAux;

import java.util.ArrayList;

import javax.swing.SingleSelectionModel;


public class t {
	
	private class No {
		public String seten;
		public boolean valorLogico;
		@Override
		public String toString() {
			return seten+" "+valorLogico;
		}
			
	}
	private class inf{
		public No b2;
		public ArrayList<Integer> ramoAtual;
		public int tamAtual;
	}
	private class retorno{
		No a=new No();
		No b=new No();
		boolean tipo;//false alfa ; true beta
	}
	public ArrayList<No>lista;
	public ArrayList<Integer> beta;
	public PilhaEncad<inf> pilhaDeRamo;
	
	public t(ArrayList<String> hip,String p) {
			this.lista=new ArrayList<No>();
			if(hip!=null){
				for (int i = 0; i < hip.size(); i++) {
					No e=new No();
					e.seten=hip.get(i);
					e.valorLogico=true;
					lista.add(e);
				}
			}
			No e=new No();
			e.seten=p;
			e.valorLogico=false;
			lista.add(e);
			beta=new ArrayList<Integer>();
			pilhaDeRamo=new PilhaEncad<inf>();
	}
	public void ex(){
		exp_alfa();
		isBeta();
		while(beta.size()>0){
			inf inf =new inf();
			inf.ramoAtual=beta;
			retorno r=separa(lista.get(beta.remove(beta.size()-1)));
			inf.b2=r.b;
			inf.tamAtual=lista.size();
			pilhaDeRamo.empilha(inf);
			if(r.a!=null){
				lista.add(r.a);
			}
			isBeta(lista.size()-1);
			if(isAlfa(lista.size()-1)==true){
				retorno a= separa(lista.get(lista.size()-1));
				if(a.a!=null){
					lista.add(a.a);
				}
				if(a.b != null){
					lista.add(a.b);
				}
			}
			if(isFechou()==true){
				break;
			}
		}
		
		while(pilhaDeRamo.tamanho()>0){
			try {
				inf aux= (inf) pilhaDeRamo.desempilha();
				beta=aux.ramoAtual;
				for(int i=lista.size()-1;i>=aux.tamAtual;i--){
					lista.remove(i);
				}
				if(aux.b2!=null){
					lista.add(aux.b2);
				}
				while(verificaBeta(lista.get(lista.size()-1)) ){
					inf inf =new inf();
					inf.ramoAtual=beta;
					retorno r=separa(lista.get(lista.size()-1));
					inf.b2=r.b;
					inf.tamAtual=lista.size();
					pilhaDeRamo.empilha(inf);
					if(r.a!=null){
						lista.add(r.a);
					}
					if(isAlfa(lista.size()-1)==true){
						retorno a= separa(lista.get(lista.size()-1));
						if(a.a!=null){
							lista.add(a.a);
						}
						if(a.b.seten!= null){
							lista.add(a.b);
						}
					}
					/*for(int i=0;i<lista.size();i++){
						System.out.println(lista.get(i)+" "+i);
					}*/
					if(isFechou()==true){
						break;
					}
				}
				if(isAlfa(lista.size()-1)==true){
					retorno a= separa(lista.get(lista.size()-1));
					if(a.a!=null){
						lista.add(a.a);
					}
					if(a.b != null){
						lista.add(a.b);
					}
				}
				if(isFechou()==false){
					for(int i=0;i<lista.size();i++){
						System.out.println(lista.get(i));
					}
					return;
					
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("numero de nos "+lista.size());
		System.out.println(true);
		
	}
	public void totalRamo(){
		System.out.println("Total de ramo: "+(this.pilhaDeRamo.getTotalEmpilha()+1));
	}
	public void exp_alfa(){
		boolean aux;
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).seten.length()>1){
				aux=verificaBeta(lista.get(i));
				if(aux==false  && lista.get(i).seten.length()>1){
					retorno r= separa(lista.get(i));
					lista.add(r.a);
					if(r.b != null){
						lista.add(r.b);
					}
				
				}
			}
		}
	}
	public boolean isFechou(){
		/*for(int i=0;i<lista.size();i++){
			System.out.println("isfechou"+lista.get(i)+" "+i);
		}*/
		for(int i=0;i<lista.size();i++){
			if (lista.get(i).seten.length()==1){
				for(int j=0;j<lista.size();j++){
					if(lista.get(j).seten.length()==1 && lista.get(i).seten.equals(lista.get(j).seten) && 
							((lista.get(j).valorLogico ==false && lista.get(i).valorLogico==true)||
								(lista.get(j).valorLogico ==true && lista.get(i).valorLogico==false))){
							//System.out.println("isFechou2:"+lista.get(j)+" "+lista.get(i));
							return true;
					}
				}
			}
		}
		return false;
	}
	public void isBeta(int x){
		for(int i=x;i<lista.size();i++){
			if(lista.get(i).seten.length()>1){
				if(verificaBeta(lista.get(i))==true){
					beta.add(i);
				}
			}
			
		}
	}
	public boolean isAlfa(int i){
		if(verificaBeta(lista.get(i))==false && lista.get(i).seten.length()>1){
			return true;
		}
		return false;
	}
	public void isBeta(){
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).seten.length()>1){
				if(verificaBeta(lista.get(i))==true){
					beta.add(i);
				}
			}
			
		}
	}
	public boolean verificaBeta(No p){
		
		if(p.seten.length()>1){
			int pos=this.conec(p.seten);
			retorno r=new retorno();
			if(p.valorLogico==true){
				if(p.seten.charAt(pos)=='V'){//beta
					return true;
				}else if(p.seten.charAt(pos)=='>'){//beta
					return true;
				}else{
					return false;
				}
			}else if(p.valorLogico== false){
				if(p.seten.charAt(pos)=='^'){//beta
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		return false;
		
	}
	public retorno separa(String s,int pos,boolean a,boolean b){
		retorno r=new retorno();
		if(s.length()==1){
			r.a.seten=s;
			r.a.valorLogico=a;
			r.b=null;
			return r;
		}
		if(s.length()==2){
			r.a.seten=s.substring(1);
			r.a.valorLogico=a;
			r.b=null;
			return r;
		}
		String aux="";
		String aux2="";
		if(s.charAt(0)=='(' && s.charAt(pos-1)==')' ){
			for(int i=1;i<pos-1;i++){
				aux=aux+s.charAt(i);
			}
		}else{
			for(int i=0;i<pos;i++){
				aux=aux+s.charAt(i);
			}
		}
		if(s.charAt(pos+1)=='(' && s.charAt(s.length()-1)==')'){
			for(int i=pos+2;i<s.length()-1;i++){
				aux2=aux2+s.charAt(i);
			}
		}else{
			for(int i=pos+1;i<s.length();i++){
				aux2=aux2+s.charAt(i);
			}
		}
		r.a.seten=aux;
		r.a.valorLogico=a;
		r.b.seten=aux2;
		r.b.valorLogico=b;
		return r;
	}
	public retorno separa(No p){
		retorno r=new retorno();
		if(p.seten.length()>1){
			int pos=this.conec(p.seten);
			if(p.valorLogico==true){
				if(p.seten.charAt(pos)=='^'){//alfa
					r=separa(p.seten, pos, true, true);
					r.tipo=false;
					return r;
				}else if(p.seten.charAt(pos)=='V'){//beta
					r=separa(p.seten, pos, true, true);
					r.tipo=true;
					return r;
				}else if(p.seten.charAt(pos)=='>'){//beta
					r=separa(p.seten, pos, false, true);
					r.tipo=true;
					return r;
				}else if(p.seten.charAt(pos)=='¬'){//alfa
					r=separa(p.seten, pos, false, false);
					r.tipo=false;
					return r;
				}
			}else if(p.valorLogico== false && p.seten.charAt(pos)=='¬'){//alfa
				r=separa(p.seten, pos, true, true);
				r.tipo=false;
				return r;
			}else{
				if(p.seten.charAt(pos)=='^'){//beta
					r=separa(p.seten, pos, false, false);
					r.tipo=true;
					return r;
				}else if(p.seten.charAt(pos)=='V'){//alfa
					r=separa(p.seten, pos, false, false);
					r.tipo=false;
					return r;
				}else if(p.seten.charAt(pos)=='>'){//alfa
					r=separa(p.seten, pos, true, false);
					r.tipo=false;
					return r;
				}
			}
		}
		r.a.seten=p.seten;
		r.a.valorLogico=p.valorLogico;
		r.b.seten=null;
		return r;
		
	}
	public int conec(String s){
		
		int cont=0;
		int pos=0;
		for(int c=0;c<s.length();c++){
			if(s.charAt(c)=='('){ 
				cont=cont+1;
			}
			else if(s.charAt(c)==')'){ 
				cont=cont-1;
			}
			if(cont==0){
				if(s.charAt(c)=='>'){
					pos=c;
					break;
				}else if(s.charAt(c)=='V'){
					pos=c;
					break;
				}else if(s.charAt(c)=='¬'){
					pos=c;
					break;
				}else if(s.charAt(c)=='^'){
					pos=c;
					break;
				}else{
					if(s.charAt(c)=='>'){
						pos=c;
						break;
					}else if(s.charAt(c)=='V'){
						pos=c;
						break;
					}else if(s.charAt(c)=='^'){
						pos=c;
						break;
					}
				}
			}
		}
		
		
		return pos;
	}
}
