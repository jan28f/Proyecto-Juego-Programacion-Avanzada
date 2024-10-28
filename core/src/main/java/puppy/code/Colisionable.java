package puppy.code;

public interface Colisionable
{
    /**
     * Se verifica si existe la colision entre un elemento y la pelota.
     * @param pelota Pelota a verificar si colisiona con el elemento.
     * @return true si existe colision entre los elementos, false en caso contrario.
     */
    public boolean verificarColision(PingBall pelota);

    /**
     * Se verifica y se realiza una accion en case de existir colision con una pelota.
     * @param pelota Pelota a verificar si colisiona con el elemento.
     */
    public void colisionaCon(PingBall pelota);
}
