package snake;

public class Snake {
	protected static int largo;
	protected static int x = 400,y = 400;
	
	public Snake() {
		iniciarSnake();
	}
	
	private void iniciarSnake() {
		largo = 3;
	}
}
