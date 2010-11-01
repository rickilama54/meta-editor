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
@WebService(name = "Fedora-API-A", targetNamespace = "http://www.fedora.info/definitions/1/0/api/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FedoraAPIA {


    /**
     * Describe repository.
     *
     * @return the repository info
     * returns org.fedora.api.RepositoryInfo
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#describeRepository")
    @WebResult(name = "repositoryInfo", targetNamespace = "")
    @RequestWrapper(localName = "describeRepository", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.DescribeRepository")
    @ResponseWrapper(localName = "describeRepositoryResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.DescribeRepositoryResponse")
    public RepositoryInfo describeRepository();

    /**
     * Gets the object profile.
     *
     * @param pid the pid
     * @param asOfDateTime the as of date time
     * @return the object profile
     * returns org.fedora.api.ObjectProfile
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getObjectProfile")
    @WebResult(name = "objectProfile", targetNamespace = "")
    @RequestWrapper(localName = "getObjectProfile", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetObjectProfile")
    @ResponseWrapper(localName = "getObjectProfileResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetObjectProfileResponse")
    public ObjectProfile getObjectProfile(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "asOfDateTime", targetNamespace = "")
        String asOfDateTime);

    /**
     * List methods.
     *
     * @param pid the pid
     * @param asOfDateTime the as of date time
     * @return the list
     * returns java.util.List<org.fedora.api.ObjectMethodsDef>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#listMethods")
    @WebResult(name = "objectMethod", targetNamespace = "")
    @RequestWrapper(localName = "listMethods", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ListMethods")
    @ResponseWrapper(localName = "listMethodsResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ListMethodsResponse")
    public List<ObjectMethodsDef> listMethods(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "asOfDateTime", targetNamespace = "")
        String asOfDateTime);

    /**
     * List datastreams.
     *
     * @param pid the pid
     * @param asOfDateTime the as of date time
     * @return the list
     * returns java.util.List<org.fedora.api.DatastreamDef>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#listDatastreams")
    @WebResult(name = "datastreamDef", targetNamespace = "")
    @RequestWrapper(localName = "listDatastreams", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ListDatastreams")
    @ResponseWrapper(localName = "listDatastreamsResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ListDatastreamsResponse")
    public List<DatastreamDef> listDatastreams(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "asOfDateTime", targetNamespace = "")
        String asOfDateTime);

    /**
     * Gets the datastream dissemination.
     *
     * @param pid the pid
     * @param dsID the ds id
     * @param asOfDateTime the as of date time
     * @return the datastream dissemination
     * returns org.fedora.api.MIMETypedStream
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getDatastreamDissemination")
    @WebResult(name = "dissemination", targetNamespace = "")
    @RequestWrapper(localName = "getDatastreamDissemination", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastreamDissemination")
    @ResponseWrapper(localName = "getDatastreamDisseminationResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDatastreamDisseminationResponse")
    public MIMETypedStream getDatastreamDissemination(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "dsID", targetNamespace = "")
        String dsID,
        @WebParam(name = "asOfDateTime", targetNamespace = "")
        String asOfDateTime);

    /**
     * Gets the dissemination.
     *
     * @param pid the pid
     * @param serviceDefinitionPid the service definition pid
     * @param methodName the method name
     * @param parameters the parameters
     * @param asOfDateTime the as of date time
     * @return the dissemination
     * returns org.fedora.api.MIMETypedStream
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getDissemination")
    @WebResult(name = "dissemination", targetNamespace = "")
    @RequestWrapper(localName = "getDissemination", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDissemination")
    @ResponseWrapper(localName = "getDisseminationResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetDisseminationResponse")
    public MIMETypedStream getDissemination(
        @WebParam(name = "pid", targetNamespace = "")
        String pid,
        @WebParam(name = "serviceDefinitionPid", targetNamespace = "")
        String serviceDefinitionPid,
        @WebParam(name = "methodName", targetNamespace = "")
        String methodName,
        @WebParam(name = "parameters", targetNamespace = "")
        org.fedora.api.GetDissemination.Parameters parameters,
        @WebParam(name = "asOfDateTime", targetNamespace = "")
        String asOfDateTime);

    /**
     * Find objects.
     *
     * @param resultFields the result fields
     * @param maxResults the max results
     * @param query the query
     * @return the field search result
     * returns org.fedora.api.FieldSearchResult
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#findObjects")
    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "findObjects", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.FindObjects")
    @ResponseWrapper(localName = "findObjectsResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.FindObjectsResponse")
    public FieldSearchResult findObjects(
        @WebParam(name = "resultFields", targetNamespace = "")
        ArrayOfString resultFields,
        @WebParam(name = "maxResults", targetNamespace = "")
        BigInteger maxResults,
        @WebParam(name = "query", targetNamespace = "")
        FieldSearchQuery query);

    /**
     * Resume find objects.
     *
     * @param sessionToken the session token
     * @return the field search result
     * returns org.fedora.api.FieldSearchResult
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#resumeFindObjects")
    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "resumeFindObjects", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ResumeFindObjects")
    @ResponseWrapper(localName = "resumeFindObjectsResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.ResumeFindObjectsResponse")
    public FieldSearchResult resumeFindObjects(
        @WebParam(name = "sessionToken", targetNamespace = "")
        String sessionToken);

    /**
     * Gets the object history.
     *
     * @param pid the pid
     * @return the object history
     * returns java.util.List<java.lang.String>
     */
    @WebMethod(action = "http://www.fedora.info/definitions/1/0/api/#getObjectHistory")
    @WebResult(name = "modifiedDate", targetNamespace = "")
    @RequestWrapper(localName = "getObjectHistory", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetObjectHistory")
    @ResponseWrapper(localName = "getObjectHistoryResponse", targetNamespace = "http://www.fedora.info/definitions/1/0/types/", className = "org.fedora.api.GetObjectHistoryResponse")
    public List<String> getObjectHistory(
        @WebParam(name = "pid", targetNamespace = "")
        String pid);

}
