import java.util.ArrayList;

public class AnalyseurLexicale {

	public ArrayList<String> motCles = new ArrayList<String>();
	public ArrayList<Token> tokens = new ArrayList<Token>();
	
	private String chaine_test = "";
	private int position = 0;
	private Token prochainToken;
	
	//Constructeur
	public AnalyseurLexicale(String chaine_test) {
		
		this.chaine_test = chaine_test;
		init();
			
	}
	
	//Ouvre un fichier de code source ligne par ligne
	/*public ArrayList<String> open() throws IOException{
		
		File codeSource = new File(fichier);
		ArrayList<String> lines = new ArrayList<String>();
		
			BufferedReader br = new BufferedReader(new FileReader(codeSource));
			String line="";
			
			while((line = br.readLine()) != null){
				System.out.println(line);
				lines.add(line);				
			}
		
		br.close();
		return lines; 
	}*/
	
	public void init(){
		motCles.add("if");
		motCles.add("else");
		motCles.add("for");
		motCles.add("while");
		motCles.add("var");
		motCles.add("int");
		chaine_test += ";";
		prochain();
	}
	//Renvoi le prochain token sans changer la position
	public Token look(){		
		return prochainToken;
	}
	
	//Renvoi le prochain token
	public Token next(){
		Token t = prochainToken;
		prochain();
		return t;
	}
		
	//Renvoi le prochain token 
	public void prochain(){
				
			//Ignorer les espaces
		while(chaine_test.charAt(position)==' '){
			if(position+1 < chaine_test.length()){
				position++;
			}			
		}
			
			//Gérer les entiers
		if(Character.toString(chaine_test.charAt(position)).matches("[0-9]")){
			String number = "";
			while(Character.toString(chaine_test.charAt(position)).matches("[0-9]")){
				number += Character.toString(chaine_test.charAt(position));
				if(position+1 < chaine_test.length()){
					position++;
				}
			}
			prochainToken =  new Token("CST_INT", number, position);
			
		//Gérer les identifiants & mots clés
		}else if(Character.toString(chaine_test.charAt(position)).matches("[A-Za-z_]")){
			String mot = "";
			while(Character.toString(chaine_test.charAt(position)).matches("[A-Za-z_]")){
				mot += Character.toString(chaine_test.charAt(position));
				if(position+1 < chaine_test.length()){
					position++;
				}
			}
			//Est un mot clé IF, FOR, WHILE, VAR, INT sinon un nom de variable ou de fonction
			if(motCles.contains(mot)){
				this.prochainToken = new Token(mot.toUpperCase(),null,position);
			}else{
				prochainToken = new Token("IDENT",mot,position);
			}
			
		//Gérer les opérateurs
		}else if(Character.toString(chaine_test.charAt(position)).matches("[^ \\w]")){
			String operateur = Character.toString(chaine_test.charAt(position));
			if(position+1 < chaine_test.length()){
				position++;
			}
	
			if(chaine_test.charAt(position)=='='){
				operateur += Character.toString(chaine_test.charAt(position));
				if(position+1 < chaine_test.length()){
					position++;
				}
			}
			prochainToken =  new Token(operateur, null, position);
		}
		
		//Gérer les affectations
		else if(chaine_test.charAt(position)=='='){
			prochainToken =  new Token("=", null, position);
			position++;
		}
	}
}

