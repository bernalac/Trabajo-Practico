public interface DAOCompra {
	void grabar(Compra c);
	void consultart();
	void consultarn(String nombre);
	void consultari(int id);
	void crearSqlite();
	void crearMysql();
}
//interface para las consultas
