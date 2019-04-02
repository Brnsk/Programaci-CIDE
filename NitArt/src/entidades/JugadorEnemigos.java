package entidades;

public interface JugadorEnemigos {
	public void mover();
	public void moverDiagonal();
	public void disparar();
	public void comprobarColision();
	public boolean colisionY(int playerY,int enemigoY);
	public boolean colisionX(int playerX, int enemigoX);
}