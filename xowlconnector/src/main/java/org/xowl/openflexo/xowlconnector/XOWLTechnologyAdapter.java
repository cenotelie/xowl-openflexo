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

package org.xowl.openflexo.xowlconnector;

import org.openflexo.foundation.FlexoProject;
import org.openflexo.foundation.fml.annotations.DeclareModelSlots;
import org.openflexo.foundation.fml.annotations.DeclareRepositoryType;
import org.openflexo.foundation.resource.FileFlexoIODelegate;
import org.openflexo.foundation.resource.FlexoResourceCenter;
import org.openflexo.foundation.resource.FlexoResourceCenterService;
import org.openflexo.foundation.resource.RepositoryFolder;
import org.openflexo.foundation.technologyadapter.TechnologyAdapter;
import org.openflexo.foundation.technologyadapter.TechnologyAdapterInitializationException;
import org.openflexo.model.ModelContextLibrary;
import org.openflexo.model.exceptions.ModelDefinitionException;
import org.openflexo.model.factory.ModelFactory;
import org.xowl.openflexo.xowlconnector.fml.binding.XOWLBindingFactory;
import org.xowl.store.AbstractRepository;
import org.xowl.utils.StandardLogger;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Technology adapter for xOWL
 *
 * @author Laurent wouters
 */
@DeclareModelSlots({XOWLModelSlot.class})
@DeclareRepositoryType({XOWLModelRepository.class})
public class XOWLTechnologyAdapter extends TechnologyAdapter {
    /**
     * The binding factory for this technology adapter
     */
    private static final XOWLBindingFactory BINDING_FACTORY = new XOWLBindingFactory();
    /**
     * The logger for Openflexo
     */
    private static final Logger FLEXO_LOGGER = Logger.getLogger(XOWLTechnologyAdapter.class.getPackage().getName());
    /**
     * The recognized file extensions for this technology
     */
    private static final String[] RECOGNIZED_EXTENSIONS = new String[]{
            ".nt", ".nq", ".ttl", ".rdf", ".rdft", ".owl", ".fs", ".xowl"
    };

    /**
     * The xOWL logger
     */
    private org.xowl.utils.Logger logger;

    /**
     * Gets the logger to use for xOWL
     *
     * @return The xOWL logger
     */
    public org.xowl.utils.Logger getLogger() {
        return logger;
    }

    /**
     * Initializes this adapter
     *
     * @throws TechnologyAdapterInitializationException
     */
    public XOWLTechnologyAdapter() throws TechnologyAdapterInitializationException {
        logger = new StandardLogger(FLEXO_LOGGER);
    }

    @Override
    public String getName() {
        return "xOWL technology adapter";
    }

    @Override
    public XOWLContextManager getTechnologyContextManager() {
        return (XOWLContextManager) super.getTechnologyContextManager();
    }

    @Override
    public <I> void initializeResourceCenter(FlexoResourceCenter<I> resourceCenter) {
        XOWLMetamodelRepository repoMetamodel = resourceCenter.getRepository(XOWLMetamodelRepository.class, this);
        XOWLModelRepository repoModel = resourceCenter.getRepository(XOWLModelRepository.class, this);
        if (repoMetamodel == null) {
            repoMetamodel = new XOWLMetamodelRepository(this, resourceCenter);
            resourceCenter.registerRepository(repoMetamodel, XOWLMetamodelRepository.class, this);
        }
        if (repoModel == null) {
            repoModel = new XOWLModelRepository(this, resourceCenter);
            resourceCenter.registerRepository(repoModel, XOWLModelRepository.class, this);
        }

        for (I item : resourceCenter) {
            if (item instanceof File) {
                File candidateFile = (File) item;
                lookupResource(resourceCenter, candidateFile);
            }
        }
        // Call it to update the current repositories
        getPropertyChangeSupport().firePropertyChange("getAllRepositories()", null, resourceCenter);
    }

    /**
     * Lookup a possible xOWL resource in the specified candidate file
     *
     * @param resourceCenter The parent resource center
     * @param candidate      The candidate file
     */
    private void lookupResource(FlexoResourceCenter<?> resourceCenter, File candidate) {
        if (isOntologyFile(candidate)) {
            XOWLResource resource = retrieveResource(candidate);
            XOWLMetamodelRepository repoMetamodel = resourceCenter.getRepository(XOWLMetamodelRepository.class, this);
            XOWLModelRepository repoModel = resourceCenter.getRepository(XOWLModelRepository.class, this);
            if (resource != null) {
                RepositoryFolder<XOWLResource> folder;
                try {
                    folder = repoModel.getRepositoryFolder(candidate, true);
                    repoModel.registerResource(resource, folder);
                    folder = repoMetamodel.getRepositoryFolder(candidate, true);
                    repoMetamodel.registerResource(resource, folder);
                } catch (IOException exception) {
                    logger.error(exception);
                }
                referenceResource(resource, resourceCenter);
            }
        }
    }

    /**
     * Gets whether the specified candidate file is a supported ontology file
     *
     * @param candidate The candidate file
     * @return Whether the file is supported
     */
    private boolean isOntologyFile(File candidate) {
        if (!candidate.isFile())
            return false;
        String name = candidate.getName();
        for (String extension : RECOGNIZED_EXTENSIONS) {
            if (name.endsWith(extension))
                return true;
        }
        return false;
    }

    /**
     * Retrieve the resource for the specified file
     *
     * @param file A file
     * @return The resource
     */
    private XOWLResource retrieveResource(File file) {
        ModelFactory factory;
        try {
            factory = new ModelFactory(ModelContextLibrary.getCompoundModelContext(FileFlexoIODelegate.class, XOWLResource.class));
        } catch (ModelDefinitionException exception) {
            logger.error(exception);
            return null;
        }
        XOWLContextManager contextManager = getTechnologyContextManager();
        XOWLResourceImpl resource = (XOWLResourceImpl) factory.newInstance(XOWLResource.class);
        resource.setTechnologyAdapter(this);
        resource.setContext(contextManager);
        resource.setFlexoIODelegate(FileFlexoIODelegate.FileFlexoIODelegateImpl.makeFileFlexoIODelegate(file, factory));
        resource.setServiceManager(getTechnologyAdapterService().getServiceManager());
        resource.setName(file.getName());
        resource.setURI(AbstractRepository.SCHEME_FILE + file.getAbsolutePath());
        contextManager.register(resource);
        return resource;
    }

    @Override
    public <I> boolean isIgnorable(FlexoResourceCenter<I> resourceCenter, I contents) {
        return (!(contents instanceof File) || !isOntologyFile((File) contents));
    }

    @Override
    public <I> void contentsAdded(FlexoResourceCenter<I> resourceCenter, I contents) {
        if (contents instanceof File) {
            File candidate = (File) contents;
            lookupResource(resourceCenter, candidate);
        }
    }

    @Override
    public <I> void contentsDeleted(FlexoResourceCenter<I> resourceCenter, I contents) {
        // TODO: remove the resource
    }

    @Override
    public XOWLContextManager createTechnologyContextManager(FlexoResourceCenterService resourceCenterService) {
        return new XOWLContextManager(this, resourceCenterService);
    }

    @Override
    public XOWLBindingFactory getTechnologyAdapterBindingFactory() {
        return BINDING_FACTORY;
    }

    /**
     * Creates a new empty resource for a new model in a project
     *
     * @param project           The project
     * @param filename          The file that will store the resource
     * @param modelURI          The URI of the ontology within the new resource
     * @param metaModelResource The resource of the new model's metamodel
     * @return The resource
     */
    protected XOWLResource newResource(FlexoProject project, String filename, String modelURI, XOWLResource metaModelResource) {
        File resourceFile = new File(FlexoProject.getProjectSpecificModelsDirectory(project), filename);
        XOWLResource resource = newResource(modelURI, resourceFile);
        resource.setMetaModelResource(metaModelResource);
        return resource;
    }

    /**
     * Creates a new empty resource
     *
     * @param ontologyURI The URI of the ontology within the new resource
     * @param file        The file that will store the resource
     * @return The resource
     */
    private XOWLResource newResource(String ontologyURI, File file) {
        ModelFactory factory;
        try {
            factory = new ModelFactory(ModelContextLibrary.getCompoundModelContext(FileFlexoIODelegate.class, XOWLResource.class));
        } catch (ModelDefinitionException exception) {
            logger.error(exception);
            return null;
        }
        XOWLContextManager contextManager = getTechnologyContextManager();
        XOWLResourceImpl resource = (XOWLResourceImpl) factory.newInstance(XOWLResource.class);
        resource.setTechnologyAdapter(this);
        resource.setContext(contextManager);
        resource.setFlexoIODelegate(FileFlexoIODelegate.FileFlexoIODelegateImpl.makeFileFlexoIODelegate(file, factory));
        resource.setServiceManager(getTechnologyAdapterService().getServiceManager());
        resource.setName(file.getName());
        resource.setURI(AbstractRepository.SCHEME_FILE + file.getAbsolutePath());
        resource.setResourceData(contextManager.get(ontologyURI));
        contextManager.register(resource);
        return resource;
    }
}
