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
package cz.fi.muni.xkremser.editor.client.view;

import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceImageField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class AnimalXmlDS.
 */
public class AnimalXmlDS extends DataSource {

	/** The instance. */
	private static AnimalXmlDS instance = null;

	/**
	 * Gets the single instance of AnimalXmlDS.
	 * 
	 * @return single instance of AnimalXmlDS
	 */
	public static AnimalXmlDS getInstance() {
		if (instance == null) {
			instance = new AnimalXmlDS("animalDS");
		}
		return instance;
	}

	/**
	 * Instantiates a new animal xml ds.
	 * 
	 * @param id
	 *          the id
	 */
	public AnimalXmlDS(String id) {

		setID(id);
		setRecordXPath("/List/Object");
		DataSourceTextField commonNameField = new DataSourceTextField("commonName", "Animal");

		DataSourceTextField scientificName = new DataSourceTextField("scientificName", "Scientific Name");
		scientificName.setRequired(true);
		scientificName.setPrimaryKey(true);

		DataSourceIntegerField lifeSpanField = new DataSourceIntegerField("lifeSpan", "Life Span");

		DataSourceTextField statusField = new DataSourceTextField("status", "Endangered Status");
		statusField.setValueMap("Threatened", "Endangered", "Not Endangered", "Not currently listed", "May become threatened", "Protected");

		DataSourceTextField dietField = new DataSourceTextField("diet", "Diet");

		DataSourceTextField infoField = new DataSourceTextField("information", "Interesting Facts");
		infoField.setLength(1000);

		DataSourceImageField pictureField = new DataSourceImageField("picture", "Picture");
		pictureField.setImageURLPrefix("animals/");

		setFields(commonNameField, scientificName, lifeSpanField, statusField, dietField, infoField, pictureField);

		setDataURL("ds/test_data/animals.data.xml");
		setClientOnly(true);
	}

	/**
	 * Override transformRequest. Here for illustration purposes only and this
	 * override implementation simply calls super.transformReRequest
	 * 
	 * @param dsRequest
	 *          the DSRequest being processed
	 * @return the transformed request
	 */
	@Override
	protected Object transformRequest(DSRequest dsRequest) {
		return super.transformRequest(dsRequest);
	}
}