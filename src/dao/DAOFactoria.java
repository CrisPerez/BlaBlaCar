package dao;

//Define una factoria abstracta que devuelve todos los DAO de la aplicacion
public abstract class DAOFactoria {
	// Metodos factoria
	public abstract UsuarioDAO getUsuarioDAO();

	public abstract CocheDAO getCocheDAO();
	
	public abstract ViajeDAO getViajeDAO();
	
	public abstract ParadaDAO getParadaDAO();
	
	public abstract ReservaDAO getReservaDAO();
	
	public abstract ValoracionDAO getValoracionDAO();

	// Declaracion como constantes de los tipos de factoria
	public final static int JDBC = 1;
	public final static int JPA = 2;
	
	public final static String emName = "AADD-MySQL";

	public static DAOFactoria getDAOFactoria(int tipo) throws DAOException {
		switch (tipo) {
		case JDBC: {
			try {
				return new JDBCDAOFactoria();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		}
		case JPA: {
			try {
				return new JPADAOFactoria();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		}
		default:
			return null;
		}
	}
}