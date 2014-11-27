package dao;

public class JPADAOFactoria extends DAOFactoria {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return (UsuarioDAO) new JPAUsuarioDAO();
	}

	@Override
	public CocheDAO getCocheDAO() {
		return (CocheDAO) new JPACocheDAO();
	}

	@Override
	public ViajeDAO getViajeDAO() {
		return (ViajeDAO) new JPAViajeDAO();
	}

	@Override
	public ParadaDAO getParadaDAO() {
		return (ParadaDAO) new JPAParadaDAO();
	}

	@Override
	public ReservaDAO getReservaDAO() {
		return (ReservaDAO) new JPAReservaDAO();
	}

	@Override
	public ValoracionDAO getValoracionDAO() {
		return (ValoracionDAO) new JPAValoracionDAO();
	}

}
