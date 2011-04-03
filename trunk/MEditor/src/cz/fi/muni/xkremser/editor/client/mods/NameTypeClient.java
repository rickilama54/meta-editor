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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

// TODO: Auto-generated Javadoc
/**
 * The Class NameTypeClient.
 */
public class NameTypeClient implements IsSerializable {

	/** The name part. */
	protected List<NamePartTypeClient> namePart;

	/** The display form. */
	protected List<String> displayForm;

	/** The affiliation. */
	protected List<String> affiliation;

	/** The role. */
	protected List<RoleTypeClient> role;

	/** The description. */
	protected List<String> description;

	/** The type. */
	protected NameTypeAttributeClient type;

	/** The id. */
	protected String id;

	/** The authority. */
	protected String authority;

	/** The xlink. */
	protected String xlink;

	/** The xml lang. */
	protected String xmlLang;

	/** The lang. */
	protected String lang;

	/** The script. */
	protected String script;

	/** The transliteration. */
	protected String transliteration;

	/**
	 * Gets the name part.
	 * 
	 * @return the name part
	 */
	public List<NamePartTypeClient> getNamePart() {
		return namePart;
	}

	/**
	 * Sets the name part.
	 * 
	 * @param namePart
	 *          the new name part
	 */
	public void setNamePart(List<NamePartTypeClient> namePart) {
		this.namePart = namePart;
	}

	/**
	 * Gets the display form.
	 * 
	 * @return the display form
	 */
	public List<String> getDisplayForm() {
		return displayForm;
	}

	/**
	 * Sets the display form.
	 * 
	 * @param displayForm
	 *          the new display form
	 */
	public void setDisplayForm(List<String> displayForm) {
		this.displayForm = displayForm;
	}

	/**
	 * Gets the affiliation.
	 * 
	 * @return the affiliation
	 */
	public List<String> getAffiliation() {
		return affiliation;
	}

	/**
	 * Sets the affiliation.
	 * 
	 * @param affiliation
	 *          the new affiliation
	 */
	public void setAffiliation(List<String> affiliation) {
		this.affiliation = affiliation;
	}

	/**
	 * Gets the role.
	 * 
	 * @return the role
	 */
	public List<RoleTypeClient> getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 * 
	 * @param role
	 *          the new role
	 */
	public void setRole(List<RoleTypeClient> role) {
		this.role = role;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public List<String> getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *          the new description
	 */
	public void setDescription(List<String> description) {
		this.description = description;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public NameTypeAttributeClient getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *          the new type
	 */
	public void setType(NameTypeAttributeClient type) {
		this.type = type;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *          the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the authority.
	 * 
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * Sets the authority.
	 * 
	 * @param authority
	 *          the new authority
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * Gets the xlink.
	 * 
	 * @return the xlink
	 */
	public String getXlink() {
		return xlink;
	}

	/**
	 * Sets the xlink.
	 * 
	 * @param xlink
	 *          the new xlink
	 */
	public void setXlink(String xlink) {
		this.xlink = xlink;
	}

	/**
	 * Gets the xml lang.
	 * 
	 * @return the xml lang
	 */
	public String getXmlLang() {
		return xmlLang;
	}

	/**
	 * Sets the xml lang.
	 * 
	 * @param xmlLang
	 *          the new xml lang
	 */
	public void setXmlLang(String xmlLang) {
		this.xmlLang = xmlLang;
	}

	/**
	 * Gets the lang.
	 * 
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * Sets the lang.
	 * 
	 * @param lang
	 *          the new lang
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * Gets the script.
	 * 
	 * @return the script
	 */
	public String getScript() {
		return script;
	}

	/**
	 * Sets the script.
	 * 
	 * @param script
	 *          the new script
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * Gets the transliteration.
	 * 
	 * @return the transliteration
	 */
	public String getTransliteration() {
		return transliteration;
	}

	/**
	 * Sets the transliteration.
	 * 
	 * @param transliteration
	 *          the new transliteration
	 */
	public void setTransliteration(String transliteration) {
		this.transliteration = transliteration;
	}

}
