package dao;

import javax.sql.DataSource;
import pool.ConnectionPool;

public class JDBCDAOFactoria extends DAOFactoria {

	DataSource ds;

	public JDBCDAOFactoria() throws DAOException, ClassNotFoundException,
			java.sql.SQLException {ds = ConnectionPool.getInstance("jdbc:mysql://localhost:3306/aaddjdbc","root", "");
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return (UsuarioDAO) new JDBCUsuarioDAO(ds);
	}

	@Override
	public CocheDAO getCocheDAO() {
		return (CocheDAO) new JDBCCocheDAO(ds);
	}

	@Override
	public ViajeDAO getViajeDAO() {
		return (ViajeDAO) new JDBCViajeDAO(ds);
	}

	@Override
	public ParadaDAO getParadaDAO() {
		return (ParadaDAO) new JDBCParadaDAO(ds);
	}

	@Override
	public ReservaDAO getReservaDAO() {
		return (ReservaDAO) new JDBCReservaDAO(ds);
	}

	@Override
	public ValoracionDAO getValoracionDAO() {
		return (ValoracionDAO) new JDBCValoracionDAO(ds);
	}

}
