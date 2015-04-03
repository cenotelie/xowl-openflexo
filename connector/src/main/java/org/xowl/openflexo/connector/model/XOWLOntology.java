/*******************************************************************************
 * Copyright (c) 2015 Laurent Wouters
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Laurent Wouters - lwouters@xowl.org
 ******************************************************************************/

package org.xowl.openflexo.connector.model;

import org.openflexo.foundation.ontology.IFlexoOntology;
import org.openflexo.foundation.ontology.IFlexoOntologyAnnotation;
import org.openflexo.foundation.ontology.IFlexoOntologyContainer;
import org.openflexo.foundation.resource.ResourceData;
import org.openflexo.foundation.technologyadapter.FlexoMetaModel;
import org.openflexo.foundation.technologyadapter.FlexoModel;
import org.xowl.lang.owl2.Ontology;
import org.xowl.openflexo.connector.XOWLContextManager;
import org.xowl.openflexo.connector.XOWLResource;
import org.xowl.openflexo.connector.XOWLTechnologyAdapter;
import org.xowl.store.ProxyObject;
import org.xowl.store.Repository;
import org.xowl.store.Vocabulary;

import java.util.*;

/**
 * Represents an xOWL ontology for OpenFlexo
 *
 * @author Laurent Wouters
 */
public class XOWLOntology extends XOWLObject implements IFlexoOntology<XOWLTechnologyAdapter>, ResourceData<XOWLOntology>, FlexoMetaModel<XOWLOntology>, FlexoModel<XOWLOntology, XOWLOntology> {
    /**
     * The associated resource
     */
    private XOWLResource resource;
    /**
     * Whether this ontology is readonly
     */
    private boolean isReadonly;
    /**
     * The containing repository
     */
    private Repository repository;
    /**
     * The backend xOWL ontology
     */
    private Ontology backend;
    /**
     * The resolved entities
     */
    private Map<ProxyObject, XOWLEntity> entities;

    /**
     * Initializes this ontology
     *
     * @param context    The parent context
     * @param repository The containing repository
     * @param ontology   The represented xOWL ontology
     */
    public XOWLOntology(XOWLContextManager context, Repository repository, Ontology ontology) {
        super(context.getTechnologyAdapter());
        this.resource = null;
        this.isReadonly = false;
        this.repository = repository;
        this.backend = ontology;
        this.entities = new HashMap<>();
    }

    /*
    Begin implementation of ResourceData
     */

    @Override
    public XOWLResource getResource() {
        return resource;
    }

    @Override
    public void setResource(org.openflexo.foundation.resource.FlexoResource<XOWLOntology> resource) {
        this.resource = (XOWLResource) resource;
    }

    /*
    End implementation of ResourceData
     */

    /*
    Begin implementation of IFlexoOntologyObject
     */

    @Override
    public String getURI() {
        return backend.getHasIRI().getHasValue();
    }

    @Override
    public String getName() {
        return getURI();
    }

    @Override
    public void setName(String aName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDisplayableDescription() {
        return getDescription();
    }

    @Override
    public XOWLOntology getFlexoOntology() {
        return this;
    }

    /*
    End implementation of IFlexoOntologyObject
     */

    /*
    Begin implementation of FlexoModel
     */

    @Override
    public XOWLOntology getMetaModel() {
        // TODO: return the schema ontology
        return null;
    }

    @Override
    public Object getObject(String objectURI) {
        return resolve(objectURI);
    }

    /*
    End implementation of FlexoModel
     */

    /*
    Begin implementation of FlexoMetaModel
     */

    @Override
    public boolean isReadOnly() {
        return isReadonly;
    }

    @Override
    public void setIsReadOnly(boolean readOnly) {
        isReadonly = readOnly;
    }

    /*
    End implementation of FlexoMetaModel
     */

    /*
    Begin implementation of IFlexoOntologyConceptContainer
     */

    @Override
    public List<? extends IFlexoOntologyContainer<XOWLTechnologyAdapter>> getSubContainers() {
        // we do not support sub-containers
        return new ArrayList<>(0);
    }

    @Override
    public List<XOWLEntity> getConcepts() {
        Iterator<ProxyObject> proxies = repository.getProxiesIn(backend);
        List<XOWLEntity> result = new ArrayList<>();
        while (proxies.hasNext())
            result.add(resolve(proxies.next()));
        return result;
    }

    @Override
    public List<XOWLDatatype> getDataTypes() {
        List<XOWLDatatype> result = new ArrayList<>();
        for (XOWLEntity entity : entities.values()) {
            if (entity.entity.getClassifiers().contains(repository.getProxy(Vocabulary.rdfDatatype))) {
                result.add(new XOWLDatatype(entity));
            }
        }
        return result;
    }

    @Override
    public XOWLEntity getOntologyObject(String objectURI) {
        return resolve(objectURI);
    }

    @Override
    public XOWLClass getClass(String classURI) {
        return new XOWLClass(resolve(classURI));
    }

    @Override
    public XOWLIndividual getIndividual(String individualURI) {
        return new XOWLIndividual(resolve(individualURI));
    }

    @Override
    public XOWLObjectProperty getObjectProperty(String propertyURI) {
        return new XOWLObjectProperty(resolve(propertyURI));
    }

    @Override
    public XOWLDataProperty getDataProperty(String propertyURI) {
        return new XOWLDataProperty(resolve(propertyURI));
    }

    @Override
    public XOWLProperty getProperty(String objectURI) {
        XOWLEntity entity = resolve(objectURI);
        if (entity.entity.getClassifiers().contains(repository.getProxy(Vocabulary.owlObjectProperty)))
            return new XOWLObjectProperty(entity);
        else
            return new XOWLDataProperty(entity);
    }

    @Override
    public List<XOWLClass> getClasses() {
        List<XOWLClass> result = new ArrayList<>();
        for (XOWLEntity entity : entities.values()) {
            if (entity.entity.getClassifiers().contains(repository.getProxy(Vocabulary.owlClass))) {
                result.add(new XOWLClass(entity));
            }
        }
        return result;
    }

    @Override
    public List<XOWLIndividual> getIndividuals() {
        List<XOWLIndividual> result = new ArrayList<>();
        for (XOWLEntity entity : entities.values()) {
            Collection<ProxyObject> classifiers = entity.entity.getClassifiers();
            if (classifiers.contains(repository.getProxy(Vocabulary.owlNamedIndividual))) {
                // an explicit named individual, how lucky!
                result.add(new XOWLIndividual(entity));
                continue;
            }
            // exclude explicit classes, object properties, data properties, annotation properties and datatypes
            if (classifiers.contains(repository.getProxy(Vocabulary.owlClass)))
                continue;
            if (classifiers.contains(repository.getProxy(Vocabulary.owlObjectProperty)))
                continue;
            if (classifiers.contains(repository.getProxy(Vocabulary.owlDataProperty)))
                continue;
            if (classifiers.contains(repository.getProxy(Vocabulary.rdfsDatatype)))
                continue;
            if (classifiers.contains(repository.getProxy(Vocabulary.owlAnnotatedProperty)))
                continue;
            result.add(new XOWLIndividual(entity));
        }
        return result;
    }

    @Override
    public List<XOWLDataProperty> getDataProperties() {
        List<XOWLDataProperty> result = new ArrayList<>();
        for (XOWLEntity entity : entities.values()) {
            if (entity.entity.getClassifiers().contains(repository.getProxy(Vocabulary.owlDataProperty))) {
                result.add(new XOWLDataProperty(entity));
            }
        }
        return result;
    }

    @Override
    public List<XOWLObjectProperty> getObjectProperties() {
        List<XOWLObjectProperty> result = new ArrayList<>();
        for (XOWLEntity entity : entities.values()) {
            if (entity.entity.getClassifiers().contains(repository.getProxy(Vocabulary.owlObjectProperty))) {
                result.add(new XOWLObjectProperty(entity));
            }
        }
        return result;
    }

    /*
    End implementation of IFlexoOntologyConceptContainer
     */

    /*
    Begin implementation of IFlexoOntology
     */

    @Override
    public String getVersion() {
        // TODO: handle versions?
        return null;
    }

    @Override
    public Vector<XOWLOntology> getImportedOntologies() {
        // ontology importation is handled by the backend repository
        return new Vector<>(0);
    }

    @Override
    public List<? extends IFlexoOntologyAnnotation> getAnnotations() {
        // TODO: retrieve the annotations
        return new ArrayList<>(0);
    }

    @Override
    public XOWLClass getRootConcept() {
        XOWLEntity entity = resolve(Vocabulary.owl + "Thing");
        return new XOWLClass(entity);
    }

    @Override
    public List<XOWLClass> getAccessibleClasses() {
        return getClasses();
    }

    @Override
    public List<XOWLIndividual> getAccessibleIndividuals() {
        return getIndividuals();
    }

    @Override
    public List<XOWLObjectProperty> getAccessibleObjectProperties() {
        return getObjectProperties();
    }

    @Override
    public List<XOWLDataProperty> getAccessibleDataProperties() {
        return getDataProperties();
    }

    @Override
    public XOWLEntity getDeclaredOntologyObject(String objectURI) {
        return getOntologyObject(objectURI);
    }

    @Override
    public XOWLClass getDeclaredClass(String classURI) {
        return getClass(classURI);
    }

    @Override
    public XOWLIndividual getDeclaredIndividual(String individualURI) {
        return getIndividual(individualURI);
    }

    @Override
    public XOWLObjectProperty getDeclaredObjectProperty(String propertyURI) {
        return getObjectProperty(propertyURI);
    }

    @Override
    public XOWLDataProperty getDeclaredDataProperty(String propertyURI) {
        return getDataProperty(propertyURI);
    }

    @Override
    public XOWLProperty getDeclaredProperty(String objectURI) {
        return getProperty(objectURI);
    }

    /*
    End implementation of IFlexoOntology
     */

    /**
     * Resolves an entity
     *
     * @param uri The entity's uri
     * @return The entity
     */
    protected XOWLEntity resolve(String uri) {
        ProxyObject proxyObject = repository.getProxy(uri);
        XOWLOntology ontology = ((XOWLContextManager) getContext()).get(proxyObject.getOntology().getHasIRI().getHasValue());
        return ontology.resolve(proxyObject);
    }

    /**
     * Resolves an entity
     *
     * @param proxy The backend proxy object
     * @return The entity
     */
    protected XOWLEntity resolve(ProxyObject proxy) {
        XOWLEntity entity = entities.get(proxy);
        if (entity == null) {
            entity = new XOWLEntity(getTechnologyAdapter(), this, proxy);
            entities.put(proxy, entity);
        }
        return entity;
    }

    /**
     * Instantiate a new individual in this ontology
     *
     * @param clazz The class to instantiate
     * @return The new individual
     */
    public XOWLIndividual newIndividual(XOWLClass clazz) {
        ProxyObject proxy = repository.newObject(backend);
        proxy.setValue(Vocabulary.rdfType, clazz.entity);
        XOWLEntity entity = new XOWLEntity(getTechnologyAdapter(), this, proxy);
        entities.put(proxy, entity);
        return new XOWLIndividual(entity);
    }

    /**
     * Instantiate a new class in this ontology
     *
     * @return The new class
     */
    public XOWLClass newClass() {
        ProxyObject proxy = repository.newObject(backend);
        proxy.setValue(Vocabulary.rdfType, repository.getProxy(Vocabulary.owlClass));
        XOWLEntity entity = new XOWLEntity(getTechnologyAdapter(), this, proxy);
        entities.put(proxy, entity);
        return new XOWLClass(entity);
    }

    /**
     * Instantiate a new object property in this ontology
     *
     * @return The new object property
     */
    public XOWLObjectProperty newObjectProperty() {
        ProxyObject proxy = repository.newObject(backend);
        proxy.setValue(Vocabulary.rdfType, repository.getProxy(Vocabulary.owlObjectProperty));
        XOWLEntity entity = new XOWLEntity(getTechnologyAdapter(), this, proxy);
        entities.put(proxy, entity);
        return new XOWLObjectProperty(entity);
    }

    /**
     * Instantiate a new data property in this ontology
     *
     * @return The new data property
     */
    public XOWLDataProperty newDataProperty() {
        ProxyObject proxy = repository.newObject(backend);
        proxy.setValue(Vocabulary.rdfType, repository.getProxy(Vocabulary.owlDataProperty));
        XOWLEntity entity = new XOWLEntity(getTechnologyAdapter(), this, proxy);
        entities.put(proxy, entity);
        return new XOWLDataProperty(entity);
    }

    @Override
    public String toString() {
        return "OWLOntology:" + getURI();
    }
}