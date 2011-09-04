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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cz.fi.muni.xkremser.editor.client.util.Constants;

import cz.fi.muni.xkremser.editor.server.exception.DatabaseException;

import cz.fi.muni.xkremser.editor.shared.rpc.ImageItem;

// TODO: Auto-generated Javadoc
/**
 * The Class InputQueueItemDAOImpl.
 */
public class ImageResolverDAOImpl
        extends AbstractDAO
        implements ImageResolverDAO {

    /** The Constant DELETE_ITEMS_STATEMENT. */
    public static final String DELETE_ITEMS_STATEMENT = "DELETE FROM " + Constants.TABLE_INPUT_QUEUE_NAME
            + " WHERE shown > (NOW() - INTERVAL '(?) month')";

    /** The Constant SELECT_ITEMS_FOR_DELETION_STATEMENT. */
    public static final String SELECT_ITEMS_FOR_DELETION_STATEMENT = "SELECT imageFile FROM "
            + Constants.TABLE_INPUT_QUEUE_NAME + " WHERE shown > (NOW() - INTERVAL '(?) month')";

    /** The Constant SELECT_ITEM_STATEMENT. */
    public static final String SELECT_ITEM_STATEMENT = "SELECT id, imageFile FROM "
            + Constants.TABLE_IMAGE_NAME + " WHERE old_fs_path = ((?))";

    /** The Constant UPDATE_ITEM_STATEMENT. */
    public static final String UPDATE_ITEM_STATEMENT = "UPDATE " + Constants.TABLE_IMAGE_NAME
            + " SET shown = CURRENT_TIMESTAMP WHERE id = (?)";

    /** The Constant INSERT_ITEM_STATEMENT. */
    public static final String INSERT_ITEM_STATEMENT = "INSERT INTO " + Constants.TABLE_IMAGE_NAME
            + " (identifier, imageFile, old_fs_path, shown) VALUES ((?),(?),(?),(CURRENT_TIMESTAMP))";

    private static final Logger LOGGER = Logger.getLogger(ImageResolverDAOImpl.class);

    /**
     * Gets the item insert statement.
     * 
     * @param item
     *        the item
     * @return the item insert statement
     * @throws DatabaseException
     */
    private PreparedStatement getItemInsertStatement(ImageItem item) throws DatabaseException {
        PreparedStatement itemStmt = null;
        try {
            itemStmt = getConnection().prepareStatement(INSERT_ITEM_STATEMENT);
            itemStmt.setString(1, item.getIdentifier());
            itemStmt.setString(2, item.getJpeg2000FsPath());
            itemStmt.setString(3, item.getJpgFsPath());
        } catch (SQLException ex) {
            LOGGER.error("Could not get insert item statement " + itemStmt, ex);
        }
        return itemStmt;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void insertItems(List<ImageItem> toInsert) throws DatabaseException {
        if (toInsert == null) throw new NullPointerException("toInsert");

        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.warn("Unable to set autocommit off", e);
        }
        try {
            // TX start
            int updated = 0;
            for (ImageItem item : toInsert) {
                updated += getItemInsertStatement(item).executeUpdate();
            }
            if (updated == toInsert.size()) {
                getConnection().commit();
                LOGGER.debug("DB has been updated.");
            } else {
                getConnection().rollback();
                LOGGER.error("DB has not been updated -> rollback!");
            }
            // TX end
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public ArrayList<String> resolveItems(List<String> oldJpgFsPaths) throws DatabaseException {
        if (oldJpgFsPaths == null) throw new NullPointerException("oldJpgFsPaths");
        ArrayList<String> ret = new ArrayList<String>(oldJpgFsPaths.size());
        for (String oldJpgFsPath : oldJpgFsPaths) {
            ret.add(resolveItem(oldJpgFsPath));
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String resolveItem(String oldJpgFsPath) throws DatabaseException {
        if (oldJpgFsPath == null || "".equals(oldJpgFsPath)) throw new NullPointerException("oldJpgFsPath");
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.warn("Unable to set autocommit off", e);
        }
        PreparedStatement statement = null;
        String ret = null;
        try {
            // TX start
            statement = getConnection().prepareStatement(SELECT_ITEM_STATEMENT);
            statement.setString(1, oldJpgFsPath);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            int id = -1;
            int rowsAffected = 0;
            while (rs.next()) {
                id = rs.getInt("id");
                ret = rs.getString("imageFile");
                i++;
            }
            if (id != -1) {
                statement = getConnection().prepareStatement(UPDATE_ITEM_STATEMENT);
                statement.setInt(1, id);
                rowsAffected = statement.executeUpdate();
            } else {
                return null;
            }
            if (rowsAffected == 1) {
                getConnection().commit();
                LOGGER.debug("DB has been updated.");
            } else {
                getConnection().rollback();
                LOGGER.error("DB has not been updated -> rollback!");
            }
            // TX end
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }

        return ret;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public ArrayList<String> cacheAgeingProcess(int monthsOld) throws DatabaseException {
        if (monthsOld < 0) throw new IllegalArgumentException("monthsOld");
        PreparedStatement statement = null;
        ArrayList<String> ret = new ArrayList<String>();
        try {
            // TX start
            statement = getConnection().prepareStatement(SELECT_ITEMS_FOR_DELETION_STATEMENT);
            statement.setInt(1, monthsOld);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            int rowsAffected = 0;
            while (rs.next()) {
                ret.add(rs.getString("imageFile"));
                i++;
            }
            if (i > 0) {
                statement = getConnection().prepareStatement(DELETE_ITEMS_STATEMENT);
                statement.setInt(1, monthsOld);
                rowsAffected = statement.executeUpdate();
            }
            if (rowsAffected == i) {
                getConnection().commit();
                LOGGER.debug("DB has been updated.");
            } else {
                getConnection().rollback();
                LOGGER.error("DB has not been updated -> rollback!");
            }
            // TX end
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeConnection();
        }
        return ret;
    }
}
