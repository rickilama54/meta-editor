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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for modsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{http://www.loc.gov/mods/v3}modsGroup" maxOccurs="unbounded"/>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="version" type="{http://www.loc.gov/mods/v3}versionType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modsType", propOrder = {
    "modsGroup"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class ModsType {

    @XmlElements({
        @XmlElement(name = "part", type = PartType.class),
        @XmlElement(name = "genre", type = GenreType.class),
        @XmlElement(name = "typeOfResource", type = TypeOfResourceType.class),
        @XmlElement(name = "originInfo", type = OriginInfoType.class),
        @XmlElement(name = "accessCondition", type = AccessConditionType.class),
        @XmlElement(name = "extension", type = ExtensionType.class),
        @XmlElement(name = "relatedItem", type = RelatedItemType.class),
        @XmlElement(name = "targetAudience", type = TargetAudienceType.class),
        @XmlElement(name = "classification", type = ClassificationType.class),
        @XmlElement(name = "abstract", type = AbstractType.class),
        @XmlElement(name = "recordInfo", type = RecordInfoType.class),
        @XmlElement(name = "physicalDescription", type = PhysicalDescriptionType.class),
        @XmlElement(name = "language", type = LanguageType.class),
        @XmlElement(name = "titleInfo", type = TitleInfoType.class),
        @XmlElement(name = "tableOfContents", type = TableOfContentsType.class),
        @XmlElement(name = "location", type = LocationType.class),
        @XmlElement(name = "subject", type = SubjectType.class),
        @XmlElement(name = "identifier", type = IdentifierType.class),
        @XmlElement(name = "name", type = NameType.class),
        @XmlElement(name = "note", type = NoteType.class)
    })
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected List<Object> modsGroup;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String id;
    @XmlAttribute
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String version;

    /**
     * Gets the value of the modsGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modsGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModsGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartType }
     * {@link GenreType }
     * {@link TypeOfResourceType }
     * {@link OriginInfoType }
     * {@link AccessConditionType }
     * {@link ExtensionType }
     * {@link RelatedItemType }
     * {@link TargetAudienceType }
     * {@link ClassificationType }
     * {@link AbstractType }
     * {@link RecordInfoType }
     * {@link PhysicalDescriptionType }
     * {@link LanguageType }
     * {@link TitleInfoType }
     * {@link TableOfContentsType }
     * {@link LocationType }
     * {@link SubjectType }
     * {@link IdentifierType }
     * {@link NameType }
     * {@link NoteType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public List<Object> getModsGroup() {
        if (modsGroup == null) {
            modsGroup = new ArrayList<Object>();
        }
        return this.modsGroup;
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
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2010-11-11T08:50:20+01:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setVersion(String value) {
        this.version = value;
    }

}
