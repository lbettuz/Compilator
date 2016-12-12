import java.util.ArrayList;

public class AnalyseurSyntaxiqueV2 {

	int position;
	int saved_position;
	AnalyseurLexicale l;
	Arbre arbre = new Arbre(null,null);

	public AnalyseurSyntaxiqueV2(AnalyseurLexicale l){
		this.l = l;
	}
	
	public Arbre primitive(){
		
		if (l.look().type_token.equals("IDENT")){
			return new Arbre(l.next(), null);
		}
		if (l.look().type_token.equals("CST_INT")){
			return new Arbre(l.next(), null);
		}
		if (l.look().type_token.equals("(")){
			l.next();
			Arbre res = instruction();
			if (res == null || !l.next().type_token.equals(")")){
				System.out.println("ERREUR : \")\" ou expression manquante - Position : " + l.look().position);
			}else{
				return res;
			}
		}
		return null;
		
	}
	
	public Arbre multiplieur(){
		
		ArrayList<Arbre> fils = new ArrayList<Arbre>();
		Arbre a1 = primitive();
			
		if(a1 != null){
			fils.add(a1);
		}	
		
		if(l.look().type_token.equals("*") || l.look().type_token.equals("/") || l.look().type_token.equals("%")){
			Token token = l.next();
			Arbre a2 = multiplieur();
			if(a2!=null){
				fils.add(a2);
			}
			return new Arbre(token, fils);
		}
		return a1;
		
	}
	
	public Arbre additive(){
		
		ArrayList<Arbre> fils = new ArrayList<Arbre>();
		Arbre a1 = multiplieur();
		
		if(a1!=null){
			fils.add(a1);
		}else{
			return null;
		}
		
		if(l.look().type_token.equals("+") || l.look().type_token.equals("-")){
			Token t = l.next();
			
			Arbre a2 = additive();
			
			if(a2!=null){
				fils.add(a2);
			}else{
				return null;
			}
			return new Arbre(t, fils);
		}
		return a1;
	}
	
	public Arbre expression(){
		Arbre a1 = additive();
		return a1;
	}
	
	public Arbre instruction(){
		
		ArrayList<Arbre> fils = new ArrayList<Arbre>();
			
		if(l.look().type_token.equals("IF")){
			
			Token t = l.next();
			Arbre condition = expression();
			
			if(condition!=null){
				fils.add(condition);
			}	

			if (l.look().type_token.equals("{")){									
				fils = getBlock("BLOC",fils);
			}else{
				System.out.println("ERREUR : Pas de bloc pour condition - Position : " + l.look().position);
				return null;
			}
			
			//Gerer le else	
			if(l.look().type_token.equals("ELSE")){
					l.next();
					if (l.look().type_token.equals("{")){									
						fils = getBlock("ELSE", fils);
					}else{
						System.out.println("ERREUR : Pas de bloc pour else statement - Position : " + l.look().position);
						return null;
					}
			}
			
			
			return new Arbre(t, fils);
		}
		
		if(l.look().type_token.matches("[\\w[^\\{\\}\\(\\)\\;]]+")){
					
			Token t = l.next();
			Arbre res = instruction();
			
			if(res!=null){
				fils.add(res);
			}else{
				System.out.println("Erreur : pas de valeur à affecter ou comparer - Position : " + l.look().position);
				return null;
			}
			return new Arbre(t, fils);	
		}
		return null;
	}
	
	
	public ArrayList<Arbre> getBlock(String name, ArrayList<Arbre> fils){
		
		Arbre bloc = new Arbre(new Token(name, null, l.next().position), new ArrayList<Arbre>());
		Arbre statement = instruction();
		if(statement != null){			
			bloc.fils.add(statement);
			fils.add(bloc);
		}
		
		if(!l.look().type_token.equals("}")){
			while(l.look().type_token.equals(";")){
				l.next();
				Arbre suiteBloc = instruction();
				if(suiteBloc != null){
					bloc.fils.add(suiteBloc);
				}else if(!l.next().type_token.equals("}")){
					System.out.println("ERREUR : \"}\" ou expression manquante - Position : " + l.look().position);
					return null;
				}
			}
		}
		
		return fils;
	}
	
	public Arbre getArbre(){
		Arbre a = instruction();
		return a;
	}
	
}

