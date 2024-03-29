/*
 * Metadata Editor
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

package cz.fi.muni.xkremser.editor.server.newObject;

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import cz.fi.muni.xkremser.editor.client.util.Constants.DATASTREAM_CONTROLGROUP;
import cz.fi.muni.xkremser.editor.client.util.Constants.DATASTREAM_ID;

import cz.fi.muni.xkremser.editor.server.fedora.utils.FoxmlUtils;

import cz.fi.muni.xkremser.editor.shared.domain.DigitalObjectModel;
import cz.fi.muni.xkremser.editor.shared.rpc.NewDigitalObject;

/**
 * @author Jiri Kremser
 * @version 28.11.2011
 */
public class PeriodicalItemBuilder
        extends FoxmlBuilder {

    /**
     * @param uuid
     * @param label
     */
    public PeriodicalItemBuilder(NewDigitalObject object) {
        super(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void decorateMODSStream() {
        String volumeLabel = getLabel();
        Element modsCollection = FoxmlUtils.createModsCollectionEl();
        Namespace modsNs = Namespaces.mods;
        Element mods = modsCollection.addElement(new QName("mods", modsNs));
        mods.addAttribute("version", "3.3");
        Element idUrn = mods.addElement(new QName("identifier", modsNs));
        idUrn.addAttribute("type", "urn");
        idUrn.addText(getUuid());

        Element titleInfo = mods.addElement(new QName("titleInfo", modsNs));
        Element title = titleInfo.addElement(new QName("title", modsNs));
        title.addText(volumeLabel);

        String typeOfResource = getTypeOfResource();
        if (typeOfResource != null) {
            Element typeOfResourceEl = mods.addElement(new QName("typeOfResource", modsNs));
            typeOfResourceEl.addText(getTypeOfResource());
        }

        Element genre = mods.addElement(new QName("genre", modsNs));
        genre.addAttribute("type", "issue");

        Element originInfo = mods.addElement(new QName("originInfo", modsNs));
        Element dateIssued = originInfo.addElement(new QName("dateIssued", modsNs));
        dateIssued.addText(getDateIssued() != null ? getDateIssued() : "");
        Element issuance = originInfo.addElement(new QName("issuance", modsNs));
        issuance.addText("continuing");

        String language = getLanguage();
        if (language != null) {
            Element languageEl = mods.addElement(new QName("language", modsNs));
            Element languageTerm = languageEl.addElement(new QName("languageTerm", modsNs));
            languageTerm.addAttribute("type", "code");
            languageTerm.addAttribute("authority", "iso639-2b");
            languageTerm.addText(language);
        }

        Element part = mods.addElement(new QName("part", modsNs));
        Element detail = part.addElement(new QName("detail", modsNs));
        detail.addAttribute("type", "issue");
        Element number = detail.addElement(new QName("number", modsNs));
        number.addText(volumeLabel);

        appendDatastream(DATASTREAM_CONTROLGROUP.X, DATASTREAM_ID.BIBLIO_MODS, modsCollection, null, null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DigitalObjectModel getModel() {
        return DigitalObjectModel.PERIODICALITEM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createOtherStreams() {
        // TODO Auto-generated method stub

    }

}
