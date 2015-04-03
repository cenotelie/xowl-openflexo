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
package org.xowl.openflexo.connector;

import org.openflexo.foundation.resource.FlexoResourceCenterService;
import org.openflexo.foundation.technologyadapter.TechnologyContextManager;
import org.xowl.lang.owl2.Ontology;
import org.xowl.openflexo.connector.model.XOWLOntology;
import org.xowl.store.AbstractRepository;
import org.xowl.store.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Context Manager for the xOWL connector
 *
 * @author Laurent Wouters
 */
public class XOWLContextManager extends TechnologyContextManager<XOWLTechnologyAdapter> {
    /**
     * The backend repository
     */
    private Repository repository;
    /**
     * Map to the ontologies
     */
    private Map<String, XOWLOntology> ontologies;
    /**
     * Map to the resources
     */
    private Map<String, XOWLResource> resources;

    /**
     * Initializes this context
     *
     * @param adapter               The xOWL technology adapter
     * @param resourceCenterService The parent resource center service
     */
    public XOWLContextManager(XOWLTechnologyAdapter adapter, FlexoResourceCenterService resourceCenterService) {
        super(adapter, resourceCenterService);
        try {
            repository = new Repository();
            // add IRI mapping for local files
            repository.getIRIMapper().addRegexpMap(AbstractRepository.SCHEME_FILE + "(.*)", AbstractRepository.SCHEME_FILE + "\\1");
        } catch (IOException ex) {
            adapter.getLogger().error(ex);
        }
        ontologies = new HashMap<>();
        resources = new HashMap<>();
    }

    /**
     * Registers the specified resource
     *
     * @param resource The resource to register
     */
    public void register(XOWLResource resource) {
        resources.put(resource.getURI(), resource);
    }

    /**
     * Loads the resource at the specified URI
     *
     * @param resourceURI The resource's URI
     * @return The corresponding ontology
     */
    public XOWLOntology load(String resourceURI) {
        Ontology ontology = repository.load(getTechnologyAdapter().getLogger(), resourceURI);
        XOWLOntology result = ontologies.get(ontology.getHasIRI().getHasValue());
        if (result != null)
            return result;
        result = new XOWLOntology(this, repository, ontology);
        result.setContext(this);
        ontologies.put(ontology.getHasIRI().getHasValue(), result);
        return result;
    }

    /**
     * Gets the ontology for the specified URI
     *
     * @param ontologyURI The ontology's URI
     * @return The corresponding ontology
     */
    public XOWLOntology get(String ontologyURI) {
        XOWLOntology result = ontologies.get(ontologyURI);
        if (result != null)
            return result;
        result = new XOWLOntology(this, repository, repository.resolveOntology(ontologyURI));
        ontologies.put(ontologyURI, result);
        return result;
    }
}
