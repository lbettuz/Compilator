import java.util.Hashtable;
import java.util.Stack;


public class TableSymboles {

	Stack<Hashtable<String, Symbole>> tableMere = new Stack<Hashtable<String, Symbole>>();
	
	public String toString() {
		return "TableSymboles [tableMere=" + tableMere + "]";
	}
	
	public Symbole definir(String ident){
		
		if(this.tableMere.isEmpty()){
			Hashtable<String, Symbole> bloc = new Hashtable<String, Symbole>();
			this.tableMere.push(bloc);
		}
		
		Symbole result = this.tableMere.lastElement().putIfAbsent(ident, new Symbole(ident));

		if(result!=null){
			System.out.println("Erreur : Symbole "+ident+" déjà présent");
		}
		
		return result;
		
	}
	
	public Symbole chercher(String ident){
			
		int i = this.tableMere.size()-1;
		while (this.tableMere.elementAt(i).get(ident) == null && i>0){
			i--;
		}
		
		if(i>=0){
			return this.tableMere.elementAt(i).get(ident);
		}else{
			System.out.println("Erreur : Symbole manquant");
			return null;
		}
		
	}
	
	public static void main(String[] args){
		
		TableSymboles t1 = new TableSymboles();
		Hashtable<String, Symbole> bloc1 = new Hashtable<String, Symbole>();
		Hashtable<String, Symbole> bloc2 = new Hashtable<String, Symbole>();
		t1.definir("z");
		t1.definir("t");
		t1.definir("t");
		Symbole S1 = new Symbole("x");
		Symbole S2 = new Symbole("y");
		Symbole S3 = new Symbole("z");
		
		bloc1.put("x", S1);
		bloc1.put("y", S2);
		t1.tableMere.push(bloc1);
		
		bloc2.put("z", S3);
		t1.tableMere.push(bloc2);
		
		System.out.println(t1.tableMere);
		t1.definir("z");
		System.out.println(t1.tableMere);
		System.out.println();
		
	}


	
}
