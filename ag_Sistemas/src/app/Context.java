package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.PRIVATE_MEMBER;

import dao.AlarmaDao;
import dao.CasaDao;
import dao.ClienteDao;
import dao.PersonaDao;
import negocio.AlarmaBO;
import negocio.CasaBO;
import negocio.ClienteBO;
import negocio.EnviarMailBO;
import negocio.MostrarTablaBO;
import negocio.PersonaBO;
import negocio.VencimientoBateriaBO;

public class Context {

	private String _usuario = "root";
	private String _pwd = "1220";
	private static String _bd = "ag_sistemas";
	static String _url = "jdbc:mysql://localhost/" + _bd;
	private Connection conn = null;
	private AlarmaDao aDao;
	private ClienteDao cDao;
	private CasaDao csDao;
	private PersonaDao pDao;
	private MostrarTablaBO mBO;
	private PersonaBO pBO;
	private ClienteBO clBO;
	private CasaBO csBO;
	private AlarmaBO aBO;
	private VencimientoBateriaBO vbBO;
	private EnviarMailBO emBO;

	public Context() {

		try {
			Class.forName("com.mysql.jdbc.Connection");
			conn = (Connection) DriverManager.getConnection(_url, _usuario, _pwd);
			if (conn != null) {
				System.out.println("Se establecio la coneccion a la base de datos " + _url);
				aDao = new AlarmaDao(conn);

				cDao = new ClienteDao(conn);
				csDao = new CasaDao(conn);
				pDao = new PersonaDao(conn);
				pBO = new PersonaBO(pDao);
				csBO = new CasaBO(csDao);
				clBO = new ClienteBO(cDao);
				aBO = new AlarmaBO(aDao);
				mBO = new MostrarTablaBO(aDao, csDao, cDao);
				vbBO = new VencimientoBateriaBO(aDao);
				emBO = new EnviarMailBO(aDao, csDao, cDao, pDao);

			}
		} catch (SQLException ex) {
			System.out.println("Hubo un problema al intentar conecarse a la base de datos " + _url);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}

	public void desconectar() {
		conn = null;
	}

	public Connection getcom() {
		return conn;
	}

	public AlarmaDao getaDao() {
		return aDao;
	}

	public ClienteDao getcDao() {
		return cDao;
	}

	public CasaDao getcsDao() {
		return csDao;
	}

	public PersonaDao getpDao() {
		return pDao;
	}

	public PersonaBO getpBO() {
		return pBO;
	}

	public ClienteBO getclBO() {
		return clBO;
	}

	public CasaBO getcsBO() {
		return csBO;
	}

	public AlarmaBO getaBO() {
		return aBO;
	}

	public MostrarTablaBO getmtBO() {
		return mBO;

	}

	public VencimientoBateriaBO getVbBO() {
		return vbBO;
	}

	public EnviarMailBO getEmBO() {
		return emBO;
	}

}