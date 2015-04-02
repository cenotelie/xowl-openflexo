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

import org.openflexo.foundation.FlexoProject;
import org.openflexo.foundation.fml.FlexoBehaviour;
import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.foundation.fml.rt.TypeAwareModelSlotInstance;
import org.openflexo.foundation.fml.rt.action.CreateVirtualModelInstance;
import org.openflexo.foundation.resource.FlexoResourceCenter;
import org.openflexo.foundation.technologyadapter.FlexoMetaModelResource;
import org.openflexo.foundation.technologyadapter.TypeAwareModelSlot;
import org.openflexo.model.ModelContextLibrary;
import org.openflexo.model.exceptions.ModelDefinitionException;
import org.openflexo.model.factory.ModelFactory;
import org.xowl.openflexo.connector.fml.*;
import org.xowl.openflexo.connector.model.XOWLObject;
import org.xowl.openflexo.connector.model.XOWLOntology;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a model slot for the xOWL technology
 *
 * @author Laurent Wouters
 */
public abstract class XOWLModelSlotImpl extends TypeAwareModelSlot.TypeAwareModelSlotImpl<XOWLOntology, XOWLOntology> implements XOWLModelSlot {
    /**
     * The model factory for this slot
     */
    private static ModelFactory MODEL_FACTORY;

    static {
        try {
            MODEL_FACTORY = new ModelFactory(ModelContextLibrary.getCompoundModelContext(XOWLClassRole.class, XOWLDataPropertyRole.class, XOWLObjectPropertyRole.class, XOWLPropertyRole.class));
        } catch (ModelDefinitionException e) {
            // TODO: report this error
            e.printStackTrace();
        }
    }

    /**
     * Gets the model factory associated to this slot
     *
     * @return The associated model factory
     */
    public static ModelFactory getModelFactory() {
        return MODEL_FACTORY;
    }

    @Override
    public Class<XOWLTechnologyAdapter> getTechnologyAdapterClass() {
        return XOWLTechnologyAdapter.class;
    }

    @Override
    public XOWLModelSlotInstanceConfiguration createConfiguration(CreateVirtualModelInstance action) {
        return new XOWLModelSlotInstanceConfiguration(this, action);
    }

    @Override
    public <PR extends FlexoRole<?>> String defaultFlexoRoleName(Class<PR> patternRoleClass) {
        if (XOWLClassRole.class.isAssignableFrom(patternRoleClass)) {
            return "class";
        } else if (XOWLIndividualRole.class.isAssignableFrom(patternRoleClass)) {
            return "individual";
        } else if (XOWLPropertyRole.class.isAssignableFrom(patternRoleClass)) {
            return "property";
        } else if (XOWLDataPropertyRole.class.isAssignableFrom(patternRoleClass)) {
            return "dataProperty";
        } else if (XOWLObjectPropertyRole.class.isAssignableFrom(patternRoleClass)) {
            return "objectProperty";
        }
        return null;
    }

    @Override
    public String getURIForObject(TypeAwareModelSlotInstance<XOWLOntology, XOWLOntology, ? extends TypeAwareModelSlot<XOWLOntology, XOWLOntology>> msInstance, Object o) {
        return ((XOWLObject) o).getURI();
    }

    @Override
    public Object retrieveObjectWithURI(TypeAwareModelSlotInstance<XOWLOntology, XOWLOntology, ? extends TypeAwareModelSlot<XOWLOntology, XOWLOntology>> msInstance, String objectURI) {
        return msInstance.getModel().getObject(objectURI);
    }

    @Override
    public Type getType() {
        return XOWLOntology.class;
    }

    @Override
    public XOWLTechnologyAdapter getModelSlotTechnologyAdapter() {
        return (XOWLTechnologyAdapter) super.getModelSlotTechnologyAdapter();
    }

    @Override
    public XOWLResource createProjectSpecificEmptyModel(FlexoProject project, String filename, String modelUri, FlexoMetaModelResource<XOWLOntology, XOWLOntology, ?> metaModelResource) {
        return getModelSlotTechnologyAdapter().newResource(project, filename, modelUri, (XOWLResource) metaModelResource);
    }

    @Override
    public XOWLResource createSharedEmptyModel(FlexoResourceCenter<?> resourceCenter, String relativePath, String filename, String modelUri, FlexoMetaModelResource<XOWLOntology, XOWLOntology, ?> metaModelResource) {
        // TODO: handle this case
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isStrictMetaModelling() {
        return false;
    }

    @Override
    public String getModelSlotDescription() {
        return "Ontology importing " + getMetaModelURI();
    }

    @Override
    public List<Class<? extends FlexoBehaviour>> getAvailableFlexoBehaviourTypes() {
        return new ArrayList<>(0);
    }
}
