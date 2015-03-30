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

import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.resource.FlexoResourceImpl;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.foundation.resource.SaveResourceException;
import org.openflexo.foundation.technologyadapter.FlexoMetaModelResource;
import org.openflexo.toolbox.IProgress;
import org.xowl.openflexo.xowlconnector.model.XOWLOntology;

import java.io.FileNotFoundException;

/**
 * Represents a xOWL resource
 *
 * @author Laurent Wouters
 */
public abstract class XOWLResourceImpl extends FlexoResourceImpl<XOWLOntology> implements XOWLResource {
    @Override
    public XOWLOntology loadResourceData(IProgress progress) throws ResourceLoadingCancelledException, FileNotFoundException, FlexoException {
        return ((XOWLContextManager) getContext()).load(getURI());
    }

    @Override
    public void save(IProgress progress) throws SaveResourceException {
        // TODO: implement
    }

    @Override
    public FlexoMetaModelResource<XOWLOntology, XOWLOntology, XOWLTechnologyAdapter> getMetaModelResource() {
        return null;
    }

    @Override
    public void setMetaModelResource(FlexoMetaModelResource<XOWLOntology, XOWLOntology, XOWLTechnologyAdapter> aMetaModelResource) {

    }

    @Override
    public XOWLOntology getModelData() {
        try {
            return getResourceData(null);
        } catch (ResourceLoadingCancelledException e) {
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (FlexoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public XOWLOntology getMetaModelData() {
        return getModelData();
    }

    @Override
    public XOWLOntology getModel() {
        return getModelData();
    }

    @Override
    public Class<XOWLOntology> getResourceDataClass() {
        return XOWLOntology.class;
    }
}
