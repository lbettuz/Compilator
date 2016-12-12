import java.util.ArrayList;

public class Arbre {

	Token token;
	ArrayList<Arbre> fils = new ArrayList<Arbre>();
	
	Arbre(Token token, ArrayList<Arbre> fils) {
		this.token = token;
		this.fils = fils;
	}

	public String toString() {
		
		String chaine = "-> " + token.type_token;
		if(token.charge != null){
			chaine += " - " + token.charge;
		}
		
		if(fils != null){
			
			chaine +=" : ";
			
			for(int i=0; i<fils.size();i++){
				chaine += "[";
				chaine += fils.get(i).token.type_token;
				if(fils.get(i).token.charge != null){
						chaine += " - " + fils.get(i).token.charge;
				}
				chaine += "]";
			}
			
			for(int i=0; i<fils.size();i++){
				chaine += "\n" + fils.get(i).toString();
			}	
		}	
		return chaine;	
	}
}
