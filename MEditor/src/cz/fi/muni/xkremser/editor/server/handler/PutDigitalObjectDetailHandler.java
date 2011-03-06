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
package cz.fi.muni.xkremser.editor.server.handler;

import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.xml.namespace.NamespaceContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import cz.fi.muni.xkremser.editor.client.Constants;
import cz.fi.muni.xkremser.editor.client.DublinCoreConstants;
import cz.fi.muni.xkremser.editor.client.KrameriusModel;
import cz.fi.muni.xkremser.editor.client.mods.ModsCollectionClient;
import cz.fi.muni.xkremser.editor.server.HttpCookies;
import cz.fi.muni.xkremser.editor.server.DAO.UserDAO;
import cz.fi.muni.xkremser.editor.server.config.EditorConfiguration;
import cz.fi.muni.xkremser.editor.server.fedora.FedoraAccess;
import cz.fi.muni.xkremser.editor.server.fedora.FedoraNamespaces;
import cz.fi.muni.xkremser.editor.server.fedora.RDFModels;
import cz.fi.muni.xkremser.editor.server.fedora.utils.BiblioModsUtils;
import cz.fi.muni.xkremser.editor.server.fedora.utils.RESTHelper;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutDigitalObjectDetailAction;
import cz.fi.muni.xkremser.editor.shared.rpc.action.PutDigitalObjectDetailResult;
import cz.fi.muni.xkremser.editor.shared.valueobj.AbstractDigitalObjectDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.PageDetail;
import cz.fi.muni.xkremser.editor.shared.valueobj.metadata.DublinCore;

// TODO: Auto-generated Javadoc
/**
 * The Class GetDigitalObjectDetailHandler.
 */
public class PutDigitalObjectDetailHandler implements ActionHandler<PutDigitalObjectDetailAction, PutDigitalObjectDetailResult> {

	/** The logger. */
	private final Log logger;

	/** The Constant RELS_EXT_PART_1. */
	private static final String RELS_EXT_PART_11 = "<kramerius:";
	private static final String RELS_EXT_PART_12 = "<";

	/** The Constant RELS_EXT_PART_2. */
	private static final String RELS_EXT_PART_21 = " rdf:resource=\"info:fedora/uuid:";

	private static final String RELS_EXT_PART_22 = " xmlns=\"http://www.nsdl.org/ontologies/relationships#\" rdf:resource=\"info:fedora/uuid:";

	/** The Constant RELS_EXT_PART_3. */
	private static final String RELS_EXT_PART_31 = "\"></kramerius:";
	private static final String RELS_EXT_PART_32 = "\"></";

	/** The Constant TERMINATOR1. */
	private static final String TERMINATOR1 = ">\n";

	/** The Constant TERMINATOR2. */
	private static final String TERMINATOR2 = ">";

	/** The Constant DC_HEAD. */
	private static final String DC_HEAD = "<oai_dc:dc xmlns:oai_dc=\"http://www.openarchives.org/OAI/2.0/oai_dc/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.openarchives.org/OAI/2.0/oai_dc/ http://www.openarchives.org/OAI/2.0/oai_dc.xsd\">\n";

	/** The Constant DC_TAIL. */
	private static final String DC_TAIL = "</oai_dc:dc>";

	/** The Constant DC_PART_1. */
	private static final String DC_PART_1 = "<dc:";

	/** The Constant DC_PART_2. */
	private static final String DC_PART_2 = "</dc:";

	/** The fedora access. */
	@Inject
	@Named("securedFedoraAccess")
	private FedoraAccess fedoraAccess;

	/** The ns context. */
	@Inject
	private NamespaceContext nsContext;

	/** The user dao. */
	private final UserDAO userDAO;

	/** The configuration. */
	private final EditorConfiguration configuration;

	/** The xpfactory. */
	private XPathFactory xpfactory;

	/** The http session provider. */
	private final Provider<HttpSession> httpSessionProvider;

	// /** The injector. */
	// @Inject
	// Injector injector;

	/**
	 * Instantiates a new gets the digital object detail handler.
	 * 
	 * @param logger
	 *          the logger
	 * @param userDAO
	 *          the user dao
	 * @param configuration
	 *          the configuration
	 * @param httpSessionProvider
	 *          the http session provider
	 */
	@Inject
	public PutDigitalObjectDetailHandler(final Log logger, final UserDAO userDAO, final EditorConfiguration configuration,
			Provider<HttpSession> httpSessionProvider) {
		this.logger = logger;
		this.configuration = configuration;
		this.userDAO = userDAO;
		this.httpSessionProvider = httpSessionProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#execute(com
	 * .gwtplatform.dispatch.shared.Action,
	 * com.gwtplatform.dispatch.server.ExecutionContext)
	 */
	@Override
	public PutDigitalObjectDetailResult execute(final PutDigitalObjectDetailAction action, final ExecutionContext context) throws ActionException {
		if (action == null || action.getDetail() == null)
			throw new NullPointerException("getDetail()");
		HttpSession session = httpSessionProvider.get();
		String openID = (String) session.getAttribute(HttpCookies.SESSION_ID_KEY);
		boolean write = userDAO.openIDhasRole(UserDAO.CAN_PUBLISH_STRING, openID) || HttpCookies.ADMIN_YES.equals(session.getAttribute(HttpCookies.ADMIN));

		if (write) {
			AbstractDigitalObjectDetail detail = action.getDetail();
			modifyRelations(detail);
			if (detail.isDcChanged())
				modifyDublinCore(detail);
			if (detail.isModsChanged())
				modifyMods(detail);
			if (detail.isOcrChanged())
				modifyOcr(detail);
			reindex(detail.getUuid());
		}
		// reindex(detail.getUuid());
		return new PutDigitalObjectDetailResult(write);
	}

	/**
	 * Reindex.
	 * 
	 * @param uuid
	 *          the uuid
	 */
	private void reindex(String uuid) {
		String host = configuration.getKrameriusHost();
		String login = configuration.getKrameriusLogin();
		String password = configuration.getKrameriusPassword();
		if (host == null || login == null || password == null) {
			return;
		}
		String url = host + "/lr?action=start&def=reindex&out=text&params=fromKrameriusModel," + uuid + "," + uuid + "&userName=" + login + "&pswd=" + password;
		try {
			RESTHelper.openConnection(url, login, password);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#getActionType()
	 */
	@Override
	public Class<PutDigitalObjectDetailAction> getActionType() {
		return PutDigitalObjectDetailAction.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.dispatch.server.actionhandler.ActionHandler#undo(com.
	 * gwtplatform.dispatch.shared.Action, com.gwtplatform.dispatch.shared.Result,
	 * com.gwtplatform.dispatch.server.ExecutionContext)
	 */
	@Override
	public void undo(PutDigitalObjectDetailAction action, PutDigitalObjectDetailResult result, ExecutionContext context) throws ActionException {
		// idempotency -> no need for undo
	}

	/**
	 * Removes the elements.
	 * 
	 * @param parent
	 *          the parent
	 * @param doc
	 *          the doc
	 * @param expr
	 *          the expr
	 */
	private static void removeElements(Element parent, Document doc, XPathExpression expr) {
		NodeList nodes = null;
		try {
			nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0, lastIndex = nodes.getLength() - 1; i <= lastIndex; i++) {
				parent.removeChild(nodes.item(i));
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Make ns aware xpath.
	 * 
	 * @return the x path
	 */
	private XPath makeNSAwareXpath() {
		if (xpfactory == null) {
			xpfactory = XPathFactory.newInstance();
		}
		XPath xpath = xpfactory.newXPath();
		xpath.setNamespaceContext(nsContext);
		return xpath;
	}

	/**
	 * Modify relations.
	 * 
	 * @param detail
	 *          the detail
	 */
	private void modifyRelations(AbstractDigitalObjectDetail detail) {
		StringBuilder sb = new StringBuilder();
		boolean hasAnything = (detail.hasPages() && detail.getPages() != null) || detail.hasContainers() != 0;
		if (!hasAnything)
			return;
		Document relsExt = null;
		StringBuilder contentBuilder = new StringBuilder();
		try {
			relsExt = fedoraAccess.getRelsExt(detail.getUuid());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String hasPageXPath = "/rdf:RDF/rdf:Description/kramerius:hasPage";
			String hasUnitXPath = "/rdf:RDF/rdf:Description/kramerius:hasUnit";
			String hasVolumeXPath = "/rdf:RDF/rdf:Description/kramerius:hasVolume";
			String hasItemXPath = "/rdf:RDF/rdf:Description/kramerius:hasItem";
			String hasIntCompPartXPath = "/rdf:RDF/rdf:Description/kramerius:hasIntCompPart";
			String hasIsOnPageXPath = "/rdf:RDF/rdf:Description/kramerius:isOnPage";
			String paretnStr = "/rdf:RDF/rdf:Description";

			XPathExpression expr1 = makeNSAwareXpath().compile(paretnStr);
			XPathExpression expr2 = makeNSAwareXpath().compile(hasPageXPath);
			XPathExpression expr3 = makeNSAwareXpath().compile(hasUnitXPath);
			XPathExpression expr4 = makeNSAwareXpath().compile(hasVolumeXPath);
			XPathExpression expr5 = makeNSAwareXpath().compile(hasItemXPath);
			XPathExpression expr6 = makeNSAwareXpath().compile(hasIntCompPartXPath);
			XPathExpression expr7 = makeNSAwareXpath().compile(hasIsOnPageXPath);
			NodeList nodes1 = (NodeList) expr1.evaluate(relsExt, XPathConstants.NODESET);
			Element parent = null;
			if (nodes1.getLength() != 0) {
				parent = (Element) nodes1.item(0);
			}
			removeElements(parent, relsExt, expr2);
			removeElements(parent, relsExt, expr3);
			removeElements(parent, relsExt, expr4);
			removeElements(parent, relsExt, expr5);
			removeElements(parent, relsExt, expr6);
			removeElements(parent, relsExt, expr7);

			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = null;
			try {
				transformer = transFactory.newTransformer();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			StringWriter buffer = new StringWriter();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			try {
				transformer.transform(new DOMSource(relsExt), new StreamResult(buffer));
			} catch (TransformerException e) {
				e.printStackTrace();
			}

			String str = buffer.toString();
			int lastIndex = str.indexOf(Constants.RELS_EXT_LAST_ELEMENT);
			if (lastIndex == -1) {
				// error
			}
			boolean lameNS = str.contains(FedoraNamespaces.KRAMERIUS_URI);
			String head = str.substring(0, lastIndex).trim();
			String tail = str.substring(lastIndex, str.length());

			// page structure
			if (detail.hasPages() && detail.getPages() != null) {
				String relation = RDFModels.convertToRdf(KrameriusModel.PAGE);
				for (PageDetail page : detail.getPages()) {
					sb.append(lameNS ? RELS_EXT_PART_12 : RELS_EXT_PART_11).append(relation).append(lameNS ? RELS_EXT_PART_22 : RELS_EXT_PART_21).append(page.getUuid())
							.append(lameNS ? RELS_EXT_PART_32 : RELS_EXT_PART_31).append(relation).append(TERMINATOR1);
				}

			}
			// container structure
			if (detail.hasContainers() != 0) {
				for (int i = 0; i < detail.hasContainers(); i++) {
					if (detail.getContainers().size() <= i || detail.getContainers().get(i) == null) {
					} else {
						String relation = RDFModels.convertToRdf(detail.getChildContainerModels().get(i));
						for (AbstractDigitalObjectDetail obj : detail.getContainers().get(i)) {
							sb.append(lameNS ? RELS_EXT_PART_12 : RELS_EXT_PART_11).append(relation).append(lameNS ? RELS_EXT_PART_22 : RELS_EXT_PART_21)
									.append(obj.getUuid()).append(lameNS ? RELS_EXT_PART_32 : RELS_EXT_PART_31).append(relation).append(TERMINATOR1);
						}
					}
				}
			}

			contentBuilder.append(head).append(sb).append(tail);

		} catch (XPathExpressionException e) {
		}

		String url = configuration.getFedoraHost() + "/objects/" + Constants.FEDORA_UUID_PREFIX + detail.getUuid() + "/datastreams/RELS-EXT?versionable=false";
		String usr = configuration.getFedoraLogin();
		String pass = configuration.getFedoraPassword();
		String content = contentBuilder.toString();

		RESTHelper.put(url, content, usr, pass);
	}

	/**
	 * Append dc element.
	 * 
	 * @param contentBuilder
	 *          the content builder
	 * @param values
	 *          the values
	 * @param elementName
	 *          the element name
	 */
	private static void appendDCElement(StringBuilder contentBuilder, List<String> values, String elementName) {
		if (values != null && values.size() > 0) {
			for (String value : values) {
				contentBuilder.append(DC_PART_1).append(elementName).append(TERMINATOR2).append(value).append(DC_PART_2).append(elementName).append(TERMINATOR1);
			}
		}
	}

	/**
	 * Modify dublin core.
	 * 
	 * @param detail
	 *          the detail
	 */
	private void modifyDublinCore(AbstractDigitalObjectDetail detail) {
		DublinCore dc = null;
		if ((dc = detail.getDc()) != null) {
			StringBuilder contentBuilder = new StringBuilder();
			contentBuilder.append(DC_HEAD);
			appendDCElement(contentBuilder, dc.getContributor(), DublinCoreConstants.DC_CONTRIBUTOR);
			appendDCElement(contentBuilder, dc.getCoverage(), DublinCoreConstants.DC_COVERAGE);
			appendDCElement(contentBuilder, dc.getCreator(), DublinCoreConstants.DC_CREATOR);
			appendDCElement(contentBuilder, dc.getDate(), DublinCoreConstants.DC_DATE);
			appendDCElement(contentBuilder, dc.getDescription(), DublinCoreConstants.DC_DESCRIPTION);
			appendDCElement(contentBuilder, dc.getFormat(), DublinCoreConstants.DC_FORMAT);
			appendDCElement(contentBuilder, dc.getIdentifier(), DublinCoreConstants.DC_IDENTIFIER);
			appendDCElement(contentBuilder, dc.getLanguage(), DublinCoreConstants.DC_LANGUAGE);
			appendDCElement(contentBuilder, dc.getPublisher(), DublinCoreConstants.DC_PUBLISHER);
			appendDCElement(contentBuilder, dc.getRelation(), DublinCoreConstants.DC_RELATION);
			appendDCElement(contentBuilder, dc.getRights(), DublinCoreConstants.DC_RIGHTS);
			appendDCElement(contentBuilder, dc.getSource(), DublinCoreConstants.DC_SOURCE);
			appendDCElement(contentBuilder, dc.getSubject(), DublinCoreConstants.DC_SUBJECT);
			appendDCElement(contentBuilder, dc.getTitle(), DublinCoreConstants.DC_TITLE);
			appendDCElement(contentBuilder, dc.getType(), DublinCoreConstants.DC_TYPE);
			contentBuilder.append(DC_TAIL);
			String url = configuration.getFedoraHost() + "/objects/" + Constants.FEDORA_UUID_PREFIX + detail.getUuid() + "/datastreams/DC?versionable=false";
			String usr = configuration.getFedoraLogin();
			String pass = configuration.getFedoraPassword();
			String content = contentBuilder.toString();

			RESTHelper.put(url, content, usr, pass);
		}
	}

	/**
	 * Modify mods.
	 * 
	 * @param detail
	 *          the detail
	 */
	private void modifyMods(AbstractDigitalObjectDetail detail) {
		if (detail.getMods() != null) {
			ModsCollectionClient modsCollection = detail.getMods();
			String url = configuration.getFedoraHost() + "/objects/" + Constants.FEDORA_UUID_PREFIX + detail.getUuid() + "/datastreams/BIBLIO_MODS?versionable=false";
			String usr = configuration.getFedoraLogin();
			String pass = configuration.getFedoraPassword();
			String content = BiblioModsUtils.toXML(BiblioModsUtils.toMods(modsCollection));

			RESTHelper.put(url, content, usr, pass);
		}
	}

	/**
	 * Modify ocr.
	 * 
	 * @param detail
	 *          the detail
	 */
	private void modifyOcr(AbstractDigitalObjectDetail detail) {
		if (detail.getOcr() != null) {
			String url = configuration.getFedoraHost() + "/objects/" + Constants.FEDORA_UUID_PREFIX + detail.getUuid() + "/datastreams/TEXT_OCR?versionable=false";
			String usr = configuration.getFedoraLogin();
			String pass = configuration.getFedoraPassword();
			RESTHelper.put(url, detail.getOcr(), usr, pass);
		}
	}

}