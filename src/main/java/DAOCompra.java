public interface DAOCompra {
	void grabar(Compra c);
	void consultart();
	void consultarn(String nombre);
	void consultari(int id);
	boolean idexistente(int ids);
}
//interface para las consultas
