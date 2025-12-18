package com.proativaservicos.dao.integra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IntegraDAO {

	Connection conn;

	private String ipServer;
	private String userSql = "postgres";
	private String pswSql = "bh1234";

	public IntegraDAO() {

	}

	public IntegraDAO(String ipServer, String userSql, String pswSql) throws ClassNotFoundException, SQLException {

		this.ipServer = ipServer;
		this.userSql = userSql;
		this.pswSql = pswSql;
		conectar();

	}

	public IntegraDAO(String ipServer, boolean conectar) throws ClassNotFoundException, SQLException {

		this.ipServer = ipServer;
		if (conectar)
			conectar();

	}

	// CONECTA AO SERVIDOR
	public void conectar() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");

		conn = DriverManager.getConnection("jdbc:postgresql://" + this.ipServer + ":5432/correspondente", userSql,
				pswSql);

		System.out.println("Conectado AO servidor MySQL " + ipServer);
	}


}
