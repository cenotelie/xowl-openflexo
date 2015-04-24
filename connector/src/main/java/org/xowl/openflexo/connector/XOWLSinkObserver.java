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

import org.openflexo.foundation.DataModification;
import org.openflexo.foundation.FlexoObservable;
import org.openflexo.foundation.FlexoObserver;

/**
 * Represents the synchronization observer for a sink model slot
 * The observer is notified when a virtual model instance is modified and consequently update the backend xOWL repository
 *
 * @author Laurent Wouters
 */
public class XOWLSinkObserver implements FlexoObserver {
    /**
     * The associated backend resource
     */
    private final XOWLResource resource;

    /**
     * Initializes this observer
     * @param resource The associated resource
     */
    public XOWLSinkObserver(XOWLResource resource) {
        this.resource = resource;
    }

    @Override
    public void update(FlexoObservable observable, DataModification dataModification) {
        System.out.println("plop");
    }
}
