import java.util.ArrayList;

public class AnalyseurSyntaxique {
	
	int position;
	int saved_position;
	ArrayList<Token> tokens = new ArrayList<Token>();

	public AnalyseurSyntaxique(ArrayList<Token> tokens){
		this.tokens = tokens;
		position = 0;
	}
	
	public boolean primitive(){
		
		if(accepte("IDENT")){
			return true;
		}
		if(accepte("CST_INT")){
			return true; 
		}
		if(accepte("(") && expression() && accepte(")")){
			return true;
		}		
		annuler();
		return false;
	}
	
	public boolean multiplieur(){
		
		if(!primitive()){
			annuler();
			return false;
		}		
		if(accepte("*")){
			saved_position = position;
			return multiplieur();
		}
		if(accepte("/")){
			saved_position = position;
			return multiplieur();
		}
		return true;
	}
	
	public boolean additive(){
		
		if(!multiplieur()){
			annuler();
			return false;
		}
		if(accepte("+")){
			saved_position = position;
			return additive();
		}
		if(accepte("-")){
			saved_position = position;
			return additive();
		}
		return true;
	}
	
	public boolean expression(){
		return additive();
	}
	
	public void annuler(){
		if (saved_position >= 0)
		{
			position = saved_position;
			saved_position = -1;
		}
	}
	
	public boolean accepte(String s){

		if(s.equals(tokens.get(position).type_token)){
			if(position+1 < tokens.size()){
				position++;
			}
			return true;
		}
		return false;
	}
	
	public boolean isExpression(){
		if(expression() && (position == tokens.size()-1)){
			return true;
		}
		return false;
	}
	
}
