//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.11 at 08:50:20 PM CET 
//


package cz.fi.muni.xkremser.editor.client.mods;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for nameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="nameType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="namePart" type="{http://www.loc.gov/mods/v3}namePartType"/>
 *         &lt;element name="displayForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="affiliation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="role" type="{http://www.loc.gov/mods/v3}roleType"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.loc.gov/mods/v3}idAuthorityXlinkLanguage"/>
 *       &lt;attribute name="type" type="{http://www.loc.gov/mods/v3}nameTypeAttribute" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nameType", propOrder = {
    "namePartOrDisplayFormOrAffiliation"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class NameType {

    @XmlElementRefs({
        @XmlElementRef(name = "description", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "role", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "displayForm", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "namePart", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "affiliation", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class)
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<JAXBElement<?>> namePartOrDisplayFormOrAffiliation;
    @XmlAttribute(name = "type")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected NameTypeAttribute valueToFixError2;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String id;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String authority;
    @XmlAttribute(name = "lang")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String valueToFixError3;
    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String lang;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String script;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String transliteration;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String type;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String href;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String role;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String arcrole;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String title;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String show;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String actuate;

    /**
     * Gets the value of the namePartOrDisplayFormOrAffiliation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namePartOrDisplayFormOrAffiliation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamePartOrDisplayFormOrAffiliation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link RoleType }{@code >}
     * {@link JAXBElement }{@code <}{@link NamePartType }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<JAXBElement<?>> getNamePartOrDisplayFormOrAffiliation() {
        if (namePartOrDisplayFormOrAffiliation == null) {
            namePartOrDisplayFormOrAffiliation = new ArrayList<JAXBElement<?>>();
        }
        return this.namePartOrDisplayFormOrAffiliation;
    }

    /**
     * Gets the value of the valueToFixError2 property.
     * 
     * @return
     *     possible object is
     *     {@link NameTypeAttribute }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public NameTypeAttribute getValueToFixError2() {
        return valueToFixError2;
    }

    /**
     * Sets the value of the valueToFixError2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameTypeAttribute }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setValueToFixError2(NameTypeAttribute value) {
        this.valueToFixError2 = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the authority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets the value of the authority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setAuthority(String value) {
        this.authority = value;
    }

    /**
     * Gets the value of the valueToFixError3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getValueToFixError3() {
        return valueToFixError3;
    }

    /**
     * Sets the value of the valueToFixError3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setValueToFixError3(String value) {
        this.valueToFixError3 = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getScript() {
        return script;
    }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setScript(String value) {
        this.script = value;
    }

    /**
     * Gets the value of the transliteration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getTransliteration() {
        return transliteration;
    }

    /**
     * Sets the value of the transliteration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTransliteration(String value) {
        this.transliteration = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getType() {
        if (type == null) {
            return "simple";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the arcrole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Sets the value of the arcrole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setArcrole(String value) {
        this.arcrole = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the show property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setShow(String value) {
        this.show = value;
    }

    /**
     * Gets the value of the actuate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getActuate() {
        return actuate;
    }

    /**
     * Sets the value of the actuate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setActuate(String value) {
        this.actuate = value;
    }

}
