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

import org.openflexo.foundation.FlexoObservable;
import org.openflexo.foundation.fml.rt.TypeAwareModelSlotInstance;
import org.openflexo.foundation.fml.rt.View;
import org.openflexo.foundation.fml.rt.action.CreateVirtualModelInstance;
import org.xowl.openflexo.connector.model.XOWLOntology;

/**
 * Represents the configuration of a xOWL model slot that does not provide data to be federated but observe the federated data
 *
 * @author Laurent Wouters
 */
public class XOWLSinkModelSlotInstanceConfiguration extends XOWLModelSlotInstanceConfiguration {
    /**
     * Initializes this configuration
     *
     * @param modelSlot The parent model slot
     * @param action    The creation action
     */
    protected XOWLSinkModelSlotInstanceConfiguration(XOWLModelSlot modelSlot, CreateVirtualModelInstance action) {
        super(modelSlot, action);
        setModelUri(getAction().getFocusedObject().getProject().getURI() + "/Models/myOntology");
        setRelativePath("/");
        setFilename("myOntology.owl");
    }

    @Override
    protected TypeAwareModelSlotInstance<XOWLOntology, XOWLOntology, XOWLModelSlot> configureModelSlotInstance(TypeAwareModelSlotInstance<XOWLOntology, XOWLOntology, XOWLModelSlot> msInstance, View view) {
        super.configureModelSlotInstance(msInstance, view);
        ((FlexoObservable) msInstance.getVirtualModelInstance()).addObserver(new XOWLSinkObserver((XOWLResource) msInstance.getResource()));
        return msInstance;
    }
}
