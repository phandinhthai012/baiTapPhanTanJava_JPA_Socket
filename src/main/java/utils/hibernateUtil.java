package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class hibernateUtil {
	private static hibernateUtil instance;
	private EntityManager em;
	
	public hibernateUtil() {
		em = Persistence.createEntityManagerFactory("quanLiThongTinSach_mssql").createEntityManager();
	}
	public static hibernateUtil getInstance() {
		if (instance == null) {
			instance = new hibernateUtil();
		}
		return instance;
	}
	public EntityManager getEntityManager() {
		return em;
	}
}

