/**
 * Copyright (c) 2009
 * Philipp Giese, Sven Wagner-Boysen
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.hpi.bpmn2_0.model.diagram;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for collaborationDiagramType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="collaborationDiagramType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bpmndi.org}diagramType">
 *       &lt;sequence>
 *         &lt;element name="pool" type="{http://bpmndi.org}poolCompartmentType" maxOccurs="unbounded" minOccurs="2"/>
 *         &lt;element ref="{http://bpmndi.org}messageFlowConnector" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="collaborationRef" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "collaborationDiagramType", namespace = "http://bpmndi.org", propOrder = {
//    "pool",
//    "messageFlowConnector"
})
public class CollaborationDiagram
    extends BpmnDiagram
{

    @XmlElement(namespace = "http://bpmndi.org", required = true)
    protected List<PoolCompartment> pool;

//    @XmlElement(namespace = "http://bpmndi.org")
//    protected List<MessageFlowConnector> messageFlowConnector;
//    @XmlAttribute
//    @XmlIDREF
//    @XmlSchemaType(name = "IDREF")
//    protected Object collaborationRef;
//
    
    
    /* Getter & Setter */
    
    /**
     * Gets the value of the pool property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pool property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPool().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PoolCompartment }
     * 
     * 
     */
    public List<PoolCompartment> getPool() {
        if (pool == null) {
            pool = new ArrayList<PoolCompartment>();
        }
        return this.pool;
    }
//
//    /**
//     * Gets the value of the messageFlowConnector property.
//     * 
//     * <p>
//     * This accessor method returns a reference to the live list,
//     * not a snapshot. Therefore any modification you make to the
//     * returned list will be present inside the JAXB object.
//     * This is why there is not a <CODE>set</CODE> method for the messageFlowConnector property.
//     * 
//     * <p>
//     * For example, to add a new item, do as follows:
//     * <pre>
//     *    getMessageFlowConnector().add(newItem);
//     * </pre>
//     * 
//     * 
//     * <p>
//     * Objects of the following type(s) are allowed in the list
//     * {@link MessageFlowConnector }
//     * 
//     * 
//     */
//    public List<MessageFlowConnector> getMessageFlowConnector() {
//        if (messageFlowConnector == null) {
//            messageFlowConnector = new ArrayList<MessageFlowConnector>();
//        }
//        return this.messageFlowConnector;
//    }
//
//    /**
//     * Gets the value of the collaborationRef property.
//     * 
//     * @return
//     *     possible object is
//     *     {@link Object }
//     *     
//     */
//    public Object getCollaborationRef() {
//        return collaborationRef;
//    }
//
//    /**
//     * Sets the value of the collaborationRef property.
//     * 
//     * @param value
//     *     allowed object is
//     *     {@link Object }
//     *     
//     */
//    public void setCollaborationRef(Object value) {
//        this.collaborationRef = value;
//    }

}
