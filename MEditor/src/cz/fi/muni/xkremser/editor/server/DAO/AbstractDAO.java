/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */
package cz.fi.muni.xkremser.editor.server.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.google.inject.Inject;

import cz.fi.muni.xkremser.editor.server.config.EditorConfiguration;
import cz.fi.muni.xkremser.editor.server.config.EditorConfigurationImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractDAO.
 */
public class AbstractDAO {

	/** The conn. */
	private Connection conn = null;

	/** The conf. */
	@Inject
	private EditorConfiguration conf;

	/** The logger. */
	@Inject
	public Log logger = null;

	/**
	 * Inits the connection.
	 */
	private void initConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			logger.error("Could not find the driver", ex);
		}
		if (conf == null) { // called from GWT module without injection support
			conf = new EditorConfigurationImpl(new Log4JLogger("MeditorLogger"));
		}
		String host = conf.getDBHost();
		String port = conf.getDBPort();
		String login = conf.getDBLogin();
		String password = conf.getDBPassword();
		String name = conf.getDBName();
		if (password == null || password.length() < 3) {
			logger.error("Unable to connect to database at 'jdbc:postgresql://" + host + ":" + port + "/" + name + "' reason: no password set.");
			return;
		}
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + name, login, password);

		} catch (SQLException ex) {
			logger.error("Unable to connect to database at 'jdbc:postgresql://" + host + ":" + port + "/" + name + "'", ex);
		}
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 */
	protected Connection getConnection() {
		if (conn == null) {
			initConnection();
		}
		return conn;
	}

	/**
	 * Close connection.
	 */
	protected void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			logger.error("Connection was not closed", ex);
		}
		conn = null;
	}

}
