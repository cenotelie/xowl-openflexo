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

import org.openflexo.foundation.fml.rt.action.CreateVirtualModelInstance;

/**
 * Implementation of a model slot for the xOWL technology that does not provide data to be federated but observe the federated data
 *
 * @author Laurent Wouters
 */
public abstract class XOWLSinkModelSlotImpl extends XOWLModelSlotImpl implements XOWLSinkModelSlot {

    @Override
    public XOWLModelSlotInstanceConfiguration createConfiguration(CreateVirtualModelInstance action) {
        return new XOWLSinkModelSlotInstanceConfiguration(this, action);
    }
}
