import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	
	public static void main(String[] args){
		
		AnalyseurLexicale l = new AnalyseurLexicale("if(a>=b){int a=2;a=(a+b);}else{int i=1;a=(a+b)}");
		AnalyseurSyntaxiqueV2 s = new AnalyseurSyntaxiqueV2(l);
		Arbre a = s.getArbre();
		System.out.println(a.toString());
		
	}
	
	
	
	
	
	/*try {
		fileLines = open("src/test.txt");
	} catch (IOException e) {
		e.printStackTrace();
	}*/
	
	//System.out.println(fileLines.toString());
	
	
	/*public static ArrayList<String> open(String fichierPath) throws IOException{
	
	File codeSource = new File(fichierPath);
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

	

}
