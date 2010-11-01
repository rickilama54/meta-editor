/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */

package org.fedora.api;

import java.math.BigInteger;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


// TODO: Auto-generated Javadoc
/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "Fedora-API-M", targetNamespace = "http://www.fedora.info/definitions/1/0/api/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FedoraAPIM {


    /**
     * Ingest.
     *
     * @param objectXML the object xml
     * @param format the format
     * @param logMessage the log message
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#ingest")
    @WebResult(name = "objectPID", targetNamespace = "")
    @RequestWrapper(localName = "ingest", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.Ingest")
    @ResponseWrapper(localName = "ingestResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.IngestResponse")
    public String ingest(
        @WebParam(name = "objectXML", targetNamespace = "")
        byte[] objectXML,
        @WebParam(name = "format", targetNamespace = "")
        String format,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage);

    /**
     * Modify object.
     *
     * @param pid the pid
     * @param state the state
     * @param label the label
     * @param ownerId the owner id
     * @param logMessage the log message
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#modifyObject")
    @WebResult(name = "modifiedDate", targetNamespace = "")
    @RequestWrapper(localName = "modifyObject", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ModifyObject")
    @ResponseWrapper(localName = "modifyObjectResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ModifyObjectResponse")
    public String modifyObject(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "state", targetNamespace = "")
        String state,
        @WebParam(name = "label", targetNamespace = "")
        String label,
        @WebParam(name = "ownerId", targetNamespace = "")
        String ownerId,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage);

    /**
     * Gets the object xml.
     *
     * @param pid the pid
     * @return the object xml
     * returns byte[]
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getObjectXML")
    @WebResult(name = "objectXML", targetNamespace = "")
    @RequestWrapper(localName = "getObjectXML", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetObjectXML")
    @ResponseWrapper(localName = "getObjectXMLResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetObjectXMLResponse")
    public byte[] getObjectXML(
        @WebParam(name = "pid", targetNamespace = "")
        String pid);

    /**
     * Export.
     *
     * @param pid the pid
     * @param format the format
     * @param context the context
     * @return the byte[]
     * returns byte[]
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#export")
    @WebResult(name = "objectXML", targetNamespace = "")
    @RequestWrapper(localName = "export", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.Export")
    @ResponseWrapper(localName = "exportResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ExportResponse")
    public byte[] export(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "format", targetNamespace = "")
        String format,
        @WebParam(name = "context", targetNamespace = "")
        String context);

    /**
     * Purge object.
     *
     * @param pid the pid
     * @param logMessage the log message
     * @param force the force
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#purgeObject")
    @WebResult(name = "purgedDate", targetNamespace = "")
    @RequestWrapper(localName = "purgeObject", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.PurgeObject")
    @ResponseWrapper(localName = "purgeObjectResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.PurgeObjectResponse")
    public String purgeObject(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage,
        @WebParam(name = "force", targetNamespace = "")
        boolean force);

    /**
     * Adds the datastream.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param altIDs the alt i ds
     * @param dsLabel the ds label
     * @param versionable the versionable
     * @param mimeType the mime type
     * @param formatURI the format uri
     * @param dsLocation the ds location
     * @param controlGroup the control group
     * @param dsState the ds state
     * @param checksumType the checksum type
     * @param checksum the checksum
     * @param logMessage the log message
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#addDatastream")
    @WebResult(name = "datastreamID", targetNamespace = "")
    @RequestWrapper(localName = "addDatastream", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.AddDatastream")
    @ResponseWrapper(localName = "addDatastreamResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.AddDatastreamResponse")
    public String addDatastream(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "altIDs", targetNamespace = "")
        ArrayOfString altIDs,
        @WebParam(name = "dsLabel", targetNamespace = "")
        String dsLabel,
        @WebParam(name = "versionable", targetNamespace = "")
        boolean versionable,
        @WebParam(name = "MIMEType", targetNamespace = "")
        String mimeType,
        @WebParam(name = "formatURI", targetNamespace = "")
        String formatURI,
        @WebParam(name = "dsLocation", targetNamespace = "")
        String dsLocation,
        @WebParam(name = "controlGroup", targetNamespace = "")
        String controlGroup,
        @WebParam(name = "dsState", targetNamespace = "")
        String dsState,
        @WebParam(name = "checksumType", targetNamespace = "")
        String checksumType,
        @WebParam(name = "checksum", targetNamespace = "")
        String checksum,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage);

    /**
     * Modify datastream by reference.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param altIDs the alt i ds
     * @param dsLabel the ds label
     * @param mimeType the mime type
     * @param formatURI the format uri
     * @param dsLocation the ds location
     * @param checksumType the checksum type
     * @param checksum the checksum
     * @param logMessage the log message
     * @param force the force
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#modifyDatastreamByReference")
    @WebResult(name = "modifiedDate", targetNamespace = "")
    @RequestWrapper(localName = "modifyDatastreamByReference", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ModifyDatastreamByReference")
    @ResponseWrapper(localName = "modifyDatastreamByReferenceResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ModifyDatastreamByReferenceResponse")
    public String modifyDatastreamByReference(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "altIDs", targetNamespace = "")
        ArrayOfString altIDs,
        @WebParam(name = "dsLabel", targetNamespace = "")
        String dsLabel,
        @WebParam(name = "MIMEType", targetNamespace = "")
        String mimeType,
        @WebParam(name = "formatURI", targetNamespace = "")
        String formatURI,
        @WebParam(name = "dsLocation", targetNamespace = "")
        String dsLocation,
        @WebParam(name = "checksumType", targetNamespace = "")
        String checksumType,
        @WebParam(name = "checksum", targetNamespace = "")
        String checksum,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage,
        @WebParam(name = "force", targetNamespace = "")
        boolean force);

    /**
     * Modify datastream by value.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param altIDs the alt i ds
     * @param dsLabel the ds label
     * @param mimeType the mime type
     * @param formatURI the format uri
     * @param dsContent the ds content
     * @param checksumType the checksum type
     * @param checksum the checksum
     * @param logMessage the log message
     * @param force the force
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#modifyDatastreamByValue")
    @WebResult(name = "modifiedDate", targetNamespace = "")
    @RequestWrapper(localName = "modifyDatastreamByValue", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ModifyDatastreamByValue")
    @ResponseWrapper(localName = "modifyDatastreamByValueResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ModifyDatastreamByValueResponse")
    public String modifyDatastreamByValue(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "altIDs", targetNamespace = "")
        ArrayOfString altIDs,
        @WebParam(name = "dsLabel", targetNamespace = "")
        String dsLabel,
        @WebParam(name = "MIMEType", targetNamespace = "")
        String mimeType,
        @WebParam(name = "formatURI", targetNamespace = "")
        String formatURI,
        @WebParam(name = "dsContent", targetNamespace = "")
        byte[] dsContent,
        @WebParam(name = "checksumType", targetNamespace = "")
        String checksumType,
        @WebParam(name = "checksum", targetNamespace = "")
        String checksum,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage,
        @WebParam(name = "force", targetNamespace = "")
        boolean force);

    /**
     * Sets the datastream state.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param dsState the ds state
     * @param logMessage the log message
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#setDatastreamState")
    @WebResult(name = "modifiedDate", targetNamespace = "")
    @RequestWrapper(localName = "setDatastreamState", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.SetDatastreamState")
    @ResponseWrapper(localName = "setDatastreamStateResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.SetDatastreamStateResponse")
    public String setDatastreamState(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "dsState", targetNamespace = "")
        String dsState,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage);

    /**
     * Sets the datastream versionable.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param versionable the versionable
     * @param logMessage the log message
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#setDatastreamVersionable")
    @WebResult(name = "modifiedDate", targetNamespace = "")
    @RequestWrapper(localName = "setDatastreamVersionable", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.SetDatastreamVersionable")
    @ResponseWrapper(localName = "setDatastreamVersionableResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.SetDatastreamVersionableResponse")
    public String setDatastreamVersionable(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "versionable", targetNamespace = "")
        boolean versionable,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage);

    /**
     * Compare datastream checksum.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param versionDate the version date
     * @return the string
     * returns java.lang.String
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#compareDatastreamChecksum")
    @WebResult(name = "checksum", targetNamespace = "")
    @RequestWrapper(localName = "compareDatastreamChecksum", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.CompareDatastreamChecksum")
    @ResponseWrapper(localName = "compareDatastreamChecksumResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.CompareDatastreamChecksumResponse")
    public String compareDatastreamChecksum(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "versionDate", targetNamespace = "")
        String versionDate);

    /**
     * Gets the datastream.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param asOfDateTime the as of date time
     * @return the datastream
     * returns org.fedora.api.Datastream
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getDatastream")
    @WebResult(name = "datastream", targetNamespace = "")
    @RequestWrapper(localName = "getDatastream", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastream")
    @ResponseWrapper(localName = "getDatastreamResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastreamResponse")
    public Datastream getDatastream(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "asOfDateTime", targetNamespace = "")
        String asOfDateTime);

    /**
     * Gets the datastreams.
     *
     * @param pid the pid
     * @param asOfDateTime the as of date time
     * @param dsState the ds state
     * @return the datastreams
     * returns java.util.List<org.fedora.api.Datastream>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getDatastreams")
    @WebResult(name = "datastream", targetNamespace = "")
    @RequestWrapper(localName = "getDatastreams", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastreams")
    @ResponseWrapper(localName = "getDatastreamsResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastreamsResponse")
    public List<Datastream> getDatastreams(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "asOfDateTime", targetNamespace = "")
        String asOfDateTime,
        @WebParam(name = "dsState", targetNamespace = "")
        String dsState);

    /**
     * Gets the datastream history.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @return the datastream history
     * returns java.util.List<org.fedora.api.Datastream>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getDatastreamHistory")
    @WebResult(name = "datastream", targetNamespace = "")
    @RequestWrapper(localName = "getDatastreamHistory", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastreamHistory")
    @ResponseWrapper(localName = "getDatastreamHistoryResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastreamHistoryResponse")
    public List<Datastream> getDatastreamHistory(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID);

    /**
     * Purge datastream.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param startDT the start dt
     * @param endDT the end dt
     * @param logMessage the log message
     * @param force the force
     * @return the list
     * returns java.util.List<java.lang.String>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#purgeDatastream")
    @WebResult(name = "purgedVersionDate", targetNamespace = "")
    @RequestWrapper(localName = "purgeDatastream", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.PurgeDatastream")
    @ResponseWrapper(localName = "purgeDatastreamResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.PurgeDatastreamResponse")
    public List<String> purgeDatastream(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "startDT", targetNamespace = "")
        String startDT,
        @WebParam(name = "endDT", targetNamespace = "")
        String endDT,
        @WebParam(name = "logMessage", targetNamespace = "")
        String logMessage,
        @WebParam(name = "force", targetNamespace = "")
        boolean force);

    /**
     * Gets the next pid.
     *
     * @param numPIDs the num pi ds
     * @param pidNamespace the pid namespace
     * @return the next pid
     * returns java.util.List<java.lang.String>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getNextPID")
    @WebResult(name = "pid", targetNamespace = "")
    @RequestWrapper(localName = "getNextPID", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetNextPID")
    @ResponseWrapper(localName = "getNextPIDResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetNextPIDResponse")
    public List<String> getNextPID(
        @WebParam(name = "numPIDs", targetNamespace = "")
        BigInteger numPIDs,
        @WebParam(name = "pidNamespace", targetNamespace = "")
        String pidNamespace);

    /**
     * Gets the relationships.
     *
     * @param pid the pid
     * @param relationship the relationship
     * @return the relationships
     * returns java.util.List<org.fedora.api.RelationshipTuple>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getRelationships")
    @WebResult(name = "relationships", targetNamespace = "")
    @RequestWrapper(localName = "getRelationships", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetRelationships")
    @ResponseWrapper(localName = "getRelationshipsResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetRelationshipsResponse")
    public List<RelationshipTuple> getRelationships(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "relationship", targetNamespace = "")
        String relationship);

    /**
     * Adds the relationship.
     *
     * @param pid the pid
     * @param relationship the relationship
     * @param object the object
     * @param isLiteral the is literal
     * @param datatype the datatype
     * @return true, if successful
     * returns boolean
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#addRelationship")
    @WebResult(name = "added", targetNamespace = "")
    @RequestWrapper(localName = "addRelationship", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.AddRelationship")
    @ResponseWrapper(localName = "addRelationshipResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.AddRelationshipResponse")
    public boolean addRelationship(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "relationship", targetNamespace = "")
        String relationship,
        @WebParam(name = "object", targetNamespace = "")
        String object,
        @WebParam(name = "isLiteral", targetNamespace = "")
        boolean isLiteral,
        @WebParam(name = "datatype", targetNamespace = "")
        String datatype);

    /**
     * Purge relationship.
     *
     * @param pid the pid
     * @param relationship the relationship
     * @param object the object
     * @param isLiteral the is literal
     * @param datatype the datatype
     * @return true, if successful
     * returns boolean
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#purgeRelationship")
    @WebResult(name = "purged", targetNamespace = "")
    @RequestWrapper(localName = "purgeRelationship", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.PurgeRelationship")
    @ResponseWrapper(localName = "purgeRelationshipResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.PurgeRelationshipResponse")
    public boolean purgeRelationship(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "relationship", targetNamespace = "")
        String relationship,
        @WebParam(name = "object", targetNamespace = "")
        String object,
        @WebParam(name = "isLiteral", targetNamespace = "")
        boolean isLiteral,
        @WebParam(name = "datatype", targetNamespace = "")
        String datatype);

}
