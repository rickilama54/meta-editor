//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.14 at 07:28:52 PM CET 
//


package cz.fi.muni.xkremser.editor.server.mods;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for titleInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="titleInfoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.loc.gov/mods/v3}baseTitleInfoType">
 *       &lt;attribute name="type">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="abbreviated"/>
 *             &lt;enumeration value="translated"/>
 *             &lt;enumeration value="alternative"/>
 *             &lt;enumeration value="uniform"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "titleInfoType")
public class TitleInfoType
    extends BaseTitleInfoType
{

    @XmlAttribute(name = "type")
    protected String atType;

    /**
     * Gets the value of the atType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtType() {
        return atType;
    }

    /**
     * Sets the value of the atType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtType(String value) {
        this.atType = value;
    }

}