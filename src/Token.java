
public class Token {


	String type_token;
	String charge;
	int ligne;
	int position; 
	
	
	public Token(String type_token, String charge, int position) {
		this.type_token = type_token;
		this.charge = charge;
		this.position = position;
	}


	@Override
	public String toString() {
		return "Token [type_token=\"" + type_token + "\", charge=" + charge + ", ligne=" + ligne + ", position=" + position
				+ "]";
	}
	
	
}
