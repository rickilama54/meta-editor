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
package cz.fi.muni.xkremser.editor.server.modelHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess;
import cz.fi.muni.xkremser.editor.shared.valueobj.AbstractDigitalObjectDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.InternalPartDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.MonographDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.MonographUnitDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.PageDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.metadata.DublinCore;

// TODO: Auto-generated Javadoc
/**
 * The Class MonographHandler.
 */
public class MonographHandler extends DigitalObjectHandler implements CanGetObject {
	
	/** The page handler. */
	private transient final PageHandler pageHandler;
	
	/** The mon unit handler. */
	private transient final MonographUnitHandler monUnitHandler;
	
	/** The int part handler. */
	private transient final InternalPartHandler intPartHandler;

	/**
	 * Instantiates a new monograph handler.
	 *
	 * @param logger the logger
	 * @param fedoraAccess the fedora access
	 * @param pageHandler the page handler
	 * @param monUnitHandler the mon unit handler
	 * @param intPartHandler the int part handler
	 */
	@Inject
	public MonographHandler(Log logger, @Named("securedFedoraAccess") FedoraAccess fedoraAccess, PageHandler pageHandler, MonographUnitHandler monUnitHandler,
			InternalPartHandler intPartHandler) {
		super(logger, fedoraAccess);
		this.pageHandler = pageHandler;
		this.intPartHandler = intPartHandler;
		this.monUnitHandler = monUnitHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cz.fi.muni.xkremser.editor.server.modelHandler.DigitalObjectHandler#
	 * getDigitalObject(java.lang.String)
	 */
	@Override
	public AbstractDigitalObjectDetail getDigitalObject(String uuid, final boolean findRelated) {
		MonographDetail detail = new MonographDetail(findRelated ? getRelated(uuid) : null);
		DublinCore dc = handleDc(uuid, findRelated);
		ArrayList<PageDetail> pages = new ArrayList<PageDetail>();
		ArrayList<InternalPartDetail> intParts = new ArrayList<InternalPartDetail>();
		ArrayList<MonographUnitDetail> monUnits = new ArrayList<MonographUnitDetail>();
		try {
			if (findRelated) {
				List<String> pageUuids = getFedoraAccess().getPagesUuid(uuid);
				for (String pageUuid : pageUuids) {
					pages.add((PageDetail) pageHandler.getDigitalObject(pageUuid, false));
				}
				List<String> internalPartsUuids = getFedoraAccess().getIntCompPartsUuid(uuid);
				for (String intPartUuid : internalPartsUuids) {
					intParts.add((InternalPartDetail) intPartHandler.getDigitalObject(intPartUuid, false));
				}
				List<String> monographUnitsUuids = getFedoraAccess().getMonographUnitsUuid(uuid);
				for (String monUnitUuid : monographUnitsUuids) {
					monUnits.add((MonographUnitDetail) monUnitHandler.getDigitalObject(monUnitUuid, false));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		detail.setDc(dc);
		detail.setPages(pages);
		detail.setIntParts(intParts);
		detail.setMonUnits(monUnits);

		return detail;
	}

}
