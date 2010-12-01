//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.google.gwt.user.client.rpc.IsSerializable;

import cz.fi.muni.xkremser.editor.server.mods.DateType;
import cz.fi.muni.xkremser.editor.server.mods.LanguageType;
import cz.fi.muni.xkremser.editor.server.mods.RecordInfoType;
import cz.fi.muni.xkremser.editor.server.mods.StringPlusAuthority;
import cz.fi.muni.xkremser.editor.server.mods.StringPlusAuthorityPlusLanguage;

public class RecordInfoTypeClient implements IsSerializable {

	protected List<JAXBElement<?>> recordContentSourceOrRecordCreationDateOrRecordChangeDate;
	protected String valueToFixError3;
	protected String lang;
	protected String script;
	protected String transliteration;

	/**
	 * Gets the value of the
	 * recordContentSourceOrRecordCreationDateOrRecordChangeDate property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the recordContentSourceOrRecordCreationDateOrRecordChangeDate property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRecordContentSourceOrRecordCreationDateOrRecordChangeDate().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link String }{@code >} {@link JAXBElement }
	 * {@code <}{@link DateType }{@code >} {@link JAXBElement }{@code <}
	 * {@link RecordInfoType.RecordIdentifier }{@code >} {@link JAXBElement }
	 * {@code <}{@link LanguageType }{@code >} {@link JAXBElement }{@code <}
	 * {@link DateType }{@code >} {@link JAXBElement }{@code <}
	 * {@link StringPlusAuthorityPlusLanguage }{@code >} {@link JAXBElement }
	 * {@code <}{@link StringPlusAuthority }{@code >}
	 * 
	 * 
	 */
	public List<JAXBElement<?>> getRecordContentSourceOrRecordCreationDateOrRecordChangeDate() {
		if (recordContentSourceOrRecordCreationDateOrRecordChangeDate == null) {
			recordContentSourceOrRecordCreationDateOrRecordChangeDate = new ArrayList<JAXBElement<?>>();
		}
		return this.recordContentSourceOrRecordCreationDateOrRecordChangeDate;
	}

	/**
	 * Gets the value of the valueToFixError3 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValueToFixError3() {
		return valueToFixError3;
	}

	/**
	 * Sets the value of the valueToFixError3 property.
	 * 
	 * @param value
	 *          allowed object is {@link String }
	 * 
	 */
	public void setValueToFixError3(String value) {
		this.valueToFixError3 = value;
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

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;simpleContent>
	 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *       &lt;attribute name="source" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/extension>
	 *   &lt;/simpleContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	public static class RecordIdentifier implements IsSerializable {

		protected String value;
		protected String source;

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
		 * Gets the value of the source property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSource() {
			return source;
		}

		/**
		 * Sets the value of the source property.
		 * 
		 * @param value
		 *          allowed object is {@link String }
		 * 
		 */
		public void setSource(String value) {
			this.source = value;
		}

	}

}