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

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;

import cz.fi.muni.xkremser.editor.client.mods.BiblioModsClient;
import cz.fi.muni.xkremser.editor.client.util.Constants.DATASTREAM_CONTROLGROUP;
import cz.fi.muni.xkremser.editor.client.util.Constants.DATASTREAM_ID;

import cz.fi.muni.xkremser.editor.server.fedora.utils.FoxmlUtils;

import cz.fi.muni.xkremser.editor.shared.domain.DigitalObjectModel;
import cz.fi.muni.xkremser.editor.shared.domain.FedoraNamespaces;
import cz.fi.muni.xkremser.editor.shared.rpc.DublinCore;

/**
 * @author Jiri Kremser
 * @version 31.10.2011
 */
public abstract class FoxmlBuilder {

    private static final String PID_PREFIX = "uuid:";
    private final String uuid;
    private final String pid;
    private final String label;
    private final Policy policy;
    private final List<RelsExtRelation> children;
    private DublinCore dc;
    private BiblioModsClient mods;
    private Document document;
    protected Element rootElement;
    private Element dcXmlContent;
    private Element modsXmlContent;
    private Element relsExtXmlContent;
    private Element policyContentLocation;
    private static final String OVER_MAX_LENGTH_SUFFIX = "...";

    private static final int MAX_LABEL_LENGTH = 100;
    private static final Boolean VERSIONABLE = true;

    public FoxmlBuilder(String label) {
        this(label, Policy.PUBLIC);
    }

    public FoxmlBuilder(String label, Policy policy) {
        this.uuid = FoxmlUtils.getRandomUuid();
        this.pid = PID_PREFIX + uuid;
        this.label = trim(label, MAX_LABEL_LENGTH);
        this.children = new ArrayList<RelsExtRelation>();
        this.policy = policy;
    }

    public void createDocument() {
        createDocumentAndRootElement();
        decotateProperties();
        decorateDCStream();
        decorateMODSStream();
        //        addPolicyDatastream(policy);
        //dcXmlContent = createDcXmlContent();
        //        modsXmlContent = createModsXmlContent();
        //        relsExtXmlContent = createRelsExtXmlContent();
        //        policyContentLocation = createPolicyContentLocation();
    }

    public String getDocument() {
        return rootElement.getDocument().asXML();
    }

    private void createDocumentAndRootElement() {
        rootElement = DocumentHelper.createElement(new QName("digitalObject", Namespaces.foxml));
        document = DocumentHelper.createDocument(rootElement);
        document.getRootElement().add(Namespaces.foxml);
        document.getRootElement().add(Namespaces.xsi);
        document.getRootElement()
                .addAttribute(new QName("schemaLocation", Namespaces.xsi),
                              "info:fedora/fedora-system:def/foxml# http://www.fedora.info/definitions/1/0/foxml1-1.xsd");
        rootElement.addAttribute("VERSION", "1.1");
        rootElement.addAttribute("PID", pid);
    }

    private void addPolicyDatastream(Policy policy) {
        policyContentLocation.addAttribute("TYPE", "URL");
        switch (policy) {
            case PRIVATE:
                policyContentLocation
                        .addAttribute("REF", "http://local.fedora.server/fedora/get/policy:private/POLICYDEF");
            case PUBLIC:
                policyContentLocation
                        .addAttribute("REF", "http://local.fedora.server/fedora/get/policy:public/POLICYDEF");
        }
    }

    private Element createDatastreamElement(Element rootElement,
                                            String state,
                                            DATASTREAM_CONTROLGROUP controlGroup,
                                            DATASTREAM_ID dsId) {
        Element dataStream = rootElement.addElement(new QName("datastream", Namespaces.foxml));
        dataStream.addAttribute("VERSIONABLE", VERSIONABLE.toString());
        dataStream.addAttribute("STATE", state);
        dataStream.addAttribute("CONTROL_GROUP", controlGroup.name());
        dataStream.addAttribute("ID", dsId.getValue());
        return dataStream;
    }

    private void decotateProperties() {
        Element properties = rootElement.addElement(new QName("objectProperties", Namespaces.foxml));
        addProperty(properties, "info:fedora/fedora-system:def/model#label", label);
        addProperty(properties, "info:fedora/fedora-system:def/model#state", "A");
        //TODO: change to the responsible person later
        addProperty(properties, "info:fedora/fedora-system:def/model#ownerId", "fedoraAdmin");
    }

    private void addProperty(Element properties, String name, String value) {
        Element property = properties.addElement(new QName("property", Namespaces.foxml));
        property.addAttribute("NAME", name);
        property.addAttribute("VALUE", value);
    }

    private String trim(String originalString, int maxLength) {
        if (originalString.length() <= maxLength) {
            return originalString;
        } else {
            return originalString.substring(0, maxLength - OVER_MAX_LENGTH_SUFFIX.length())
                    + OVER_MAX_LENGTH_SUFFIX;
        }
    }

    protected void decorateDCStream() {
        Element rootElement = DocumentHelper.createElement(new QName("dc", Namespaces.oai_dc));
        rootElement.add(Namespaces.dc);
        Element title = rootElement.addElement(new QName("title", Namespaces.dc));
        title.addText(getLabel());
        Element identifier = rootElement.addElement(new QName("identifier", Namespaces.dc));
        identifier.setText(getPid());
        Element type = rootElement.addElement(new QName("type", Namespaces.dc));
        type.addText("model:" + getModel().getValue());
        Element rights = rootElement.addElement(new QName("rights", Namespaces.dc));
        rights.addText("policy:" + getPolicy().toString().toLowerCase());
        appendDatastream(DATASTREAM_CONTROLGROUP.X, DATASTREAM_ID.DC, rootElement, null, null);
    }

    protected void appendDatastream(DATASTREAM_CONTROLGROUP dsCGroup,
                                    DATASTREAM_ID dsId,
                                    Element content,
                                    String name,
                                    String reference) {
        Element datastreamEl = createDatastreamElement(rootElement, "A", dsCGroup, dsId);
        String versionId = dsId.toString() + ".0";
        Element dataStreamVersion = null;
        switch (dsId) {
            case DC:
                dataStreamVersion =
                        createDatastreamVersionElement(datastreamEl,
                                                       FedoraNamespaces.OAI_DC_NAMESPACE_URI,
                                                       "text/xml",
                                                       "Dublin Core Record for this object",
                                                       versionId);
                break;
            case BIBLIO_MODS:
                dataStreamVersion =
                        createDatastreamVersionElement(datastreamEl,
                                                       FedoraNamespaces.BIBILO_MODS_URI,
                                                       "text/xml",
                                                       "BIBLIO_MODS description of current object",
                                                       versionId);
                break;
            case RELS_EXT:
                dataStreamVersion =
                        createDatastreamVersionElement(datastreamEl,
                                                       FedoraNamespaces.RELS_EXT_NAMESPACE_URI,
                                                       "application/rdf+xml",
                                                       "RDF Statements about this object",
                                                       versionId);
                break;
        }
        switch (dsCGroup) {
            case X:
                addXmlContent(content, dataStreamVersion);
                break;
            case E:
                addContentLocation(name, reference, dataStreamVersion);
                break;
        }
    }

    protected Element createDatastreamVersionElement(Element rootEl,
                                                     String formatUri,
                                                     String mimetype,
                                                     String label,
                                                     String id) {
        Element dataStreamVersion = rootEl.addElement(new QName("datastreamVersion", Namespaces.foxml));
        if (formatUri != null) {
            dataStreamVersion.addAttribute("FORMAT_URI", formatUri);
        }
        dataStreamVersion.addAttribute("MIMETYPE", mimetype);
        //probably will be generated
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dataStreamVersion.addAttribute("CREATED", sdf.format(now));
        if (label != null) {
            dataStreamVersion.addAttribute("LABEL", label);
        }
        dataStreamVersion.addAttribute("ID", id);
        return dataStreamVersion;
    }

    private void addXmlContent(Element content, Element dataStreamVersion) {
        Element xmlContent = dataStreamVersion.addElement(new QName("xmlContent", Namespaces.foxml));
        xmlContent.add(content);
    }

    private void addContentLocation(String name, String reference, Element dataStreamVersion) {
        Element contentLocation =
                dataStreamVersion.addElement(new QName("contentLocation", Namespaces.foxml));
        contentLocation.addAttribute("TYPE", name);
        contentLocation.addAttribute("REF", reference);
    }

    protected abstract void decorateMODSStream();

    protected abstract DigitalObjectModel getModel();

    public DublinCore getDc() {
        return dc;
    }

    public void setDc(DublinCore dc) {
        this.dc = dc;
    }

    public BiblioModsClient getMods() {
        return mods;
    }

    public void setMods(BiblioModsClient mods) {
        this.mods = mods;
    }

    public String getLabel() {
        return label;
    }

    public String getPid() {
        return pid;
    }

    public String getUuid() {
        return uuid;
    }

    public Policy getPolicy() {
        return policy;
    }

    public List<RelsExtRelation> getChildren() {
        return children;
    }

    public static void main(String... args) {
        FoxmlBuilder test = new PageBuilder("FC");
        test.createDocument();
        System.out.println(test.getDocument());

    }
}
