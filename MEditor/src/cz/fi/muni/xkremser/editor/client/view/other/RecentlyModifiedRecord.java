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

package cz.fi.muni.xkremser.editor.client.view.other;

import com.smartgwt.client.widgets.grid.ListGridRecord;

import cz.fi.muni.xkremser.editor.client.util.Constants;

import cz.fi.muni.xkremser.editor.shared.domain.DigitalObjectModel;

// TODO: Auto-generated Javadoc
/**
 * The Class RecentlyModifiedRecord.
 */
public class RecentlyModifiedRecord
        extends ListGridRecord {

    /** The uuid. */
    private String uuid;

    /** The name. */
    private String name;

    /** The description. */
    private String description;

    /** The model. */
    private DigitalObjectModel model;

    private String lockOwner;

    private String lockDescription;

    // @SuppressWarnings("unused")
    /**
     * Instantiates a new recently modified record.
     */
    public RecentlyModifiedRecord() {

    }

    /**
     * Instantiates a new recently modified record.
     * 
     * @param uuid
     *        the uuid
     * @param name
     *        the name
     * @param description
     *        the description
     * @param model
     *        the model
     */
    public RecentlyModifiedRecord(String uuid, String name, String description, DigitalObjectModel model) {
        super();
        setUuid(uuid);
        setName(name);
        setDescription(description);
        setModel(model);
    }

    /**
     * Gets the uuid.
     * 
     * @return the uuid
     */
    public String getUuid() {
        return getAttribute(Constants.ATTR_UUID);
    }

    /**
     * Sets the uuid.
     * 
     * @param uuid
     *        the new uuid
     */
    public void setUuid(String uuid) {
        setAttribute(Constants.ATTR_UUID, uuid);
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return getAttribute(Constants.ATTR_NAME);
    }

    /**
     * Sets the name.
     * 
     * @param name
     *        the new name
     */
    public void setName(String name) {
        setAttribute(Constants.ATTR_NAME, name);
    }

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return getAttribute(Constants.ATTR_DESC);
    }

    /**
     * Sets the description.
     * 
     * @param description
     *        the new description
     */
    public void setDescription(String description) {
        setAttribute(Constants.ATTR_DESC, description);
    }

    /**
     * Gets the model.
     * 
     * @return the model
     */
    public DigitalObjectModel getModel() {
        return DigitalObjectModel.parseString(getAttribute(Constants.ATTR_MODEL));
    }

    /**
     * Sets the model.
     * 
     * @param model
     *        the new model
     */
    public void setModel(DigitalObjectModel model) {
        setAttribute(Constants.ATTR_MODEL, model);
    }

    public String getLockOwner() {
        return getAttribute(Constants.ATTR_LOCK_OWNER);
    }

    public void setLockOwner(String lockOwner) {
        setAttribute(Constants.ATTR_LOCK_OWNER, lockOwner);
    }

    public String getLockDescription() {
        return getAttribute(lockDescription);
    }

    public void setLockDescription(String lockDescription) {
        setAttribute(Constants.ATTR_LOCK_DESCRIPTION, lockDescription);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RecentlyModifiedRecord [uuid=" + uuid + ", name=" + name + ", description=" + description
                + ", model=" + model + ", lockOwner=" + lockOwner + ", lockDescription=" + lockDescription
                + "]";
    }

}
