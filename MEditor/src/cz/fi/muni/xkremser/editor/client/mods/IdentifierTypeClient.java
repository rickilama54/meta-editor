//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import com.google.gwt.user.client.rpc.IsSerializable;

import cz.fi.muni.xkremser.editor.server.mods.Yes;

public class IdentifierTypeClient implements IsSerializable {

	protected String value;
	protected String type;
	protected String displayLabel;
	protected YesClient invalid;
	protected String xmlLang;
	protected String lang;
	protected String script;
	protected String transliteration;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setType(String value) {
		this.type = value;
	}

	/**
	 * Gets the value of the displayLabel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDisplayLabel() {
		return displayLabel;
	}

	/**
	 * Sets the value of the displayLabel property.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setDisplayLabel(String value) {
		this.displayLabel = value;
	}

	/**
	 * Gets the value of the invalid property.
	 * 
	 * @return possible object is {@link Yes }
	 * 
	 */
	public YesClient getInvalid() {
		return invalid;
	}

	/**
	 * Sets the value of the invalid property.
	 * 
	 * @param value
	 *          allowed object is {@link Yes }
	 * 
	 */
	public void setInvalid(YesClient value) {
		this.invalid = value;
	}

	public String getXmlLang() {
		return xmlLang;
	}

	public void setXmlLang(String xmlLang) {
		this.xmlLang = xmlLang;
	}

	/**
	 * Gets the value of the lang property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * Sets the value of the lang property.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setLang(String value) {
		this.lang = value;
	}

	/**
	 * Gets the value of the script property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getScript() {
		return script;
	}

	/**
	 * Sets the value of the script property.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setScript(String value) {
		this.script = value;
	}

	/**
	 * Gets the value of the transliteration property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransliteration() {
		return transliteration;
	}

	/**
	 * Sets the value of the transliteration property.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setTransliteration(String value) {
		this.transliteration = value;
	}

}
