/**********************************************************************
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
 **********************************************************************/
package org.xowl.openflexo.xowlconnector.model;

import org.openflexo.foundation.ontology.BuiltInDataType;
import org.openflexo.foundation.ontology.IFlexoOntologyDataType;
import org.xowl.openflexo.xowlconnector.XOWLTechnologyAdapter;

/**
 * New code
 *
 * @author Laurent Wouters
 */
public class XOWLDatatype extends XOWLObject implements IFlexoOntologyDataType<XOWLTechnologyAdapter> {
    /**
     * Initializes this object
     *
     * @param adapter The associated adapter
     */
    public XOWLDatatype(XOWLTechnologyAdapter adapter) {
        super(adapter);
    }

    @Override
    public Class<?> getAccessedType() {
        return null;
    }

    @Override
    public BuiltInDataType getBuiltInDataType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) throws Exception {

    }

    @Override
    public String getURI() {
        return null;
    }

    @Override
    public XOWLOntology getFlexoOntology() {
        return null;
    }

    @Override
    public String getDisplayableDescription() {
        return null;
    }
}
