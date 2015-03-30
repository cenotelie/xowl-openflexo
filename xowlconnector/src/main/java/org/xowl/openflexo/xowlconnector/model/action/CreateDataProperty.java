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

package org.xowl.openflexo.xowlconnector.model.action;

import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoObject.FlexoObjectImpl;
import org.openflexo.foundation.action.FlexoAction;
import org.openflexo.foundation.action.FlexoActionType;
import org.openflexo.foundation.ontology.DuplicateURIException;
import org.openflexo.toolbox.StringUtils;
import org.xowl.openflexo.xowlconnector.model.XOWLEntity;
import org.xowl.openflexo.xowlconnector.model.XOWLDataProperty;
import org.xowl.openflexo.xowlconnector.model.XOWLObject;
import org.xowl.openflexo.xowlconnector.model.XOWLOntology;

import java.util.Vector;

/**
 * Represents an action for the creation of a class
 *
 * @author Laurent Wouters
 */
public class CreateDataProperty extends FlexoAction<CreateDataProperty, XOWLObject, XOWLEntity> {
    /**
     * Represents the power type of this action
     */
    public static final FlexoActionType<CreateDataProperty, XOWLObject, XOWLEntity> ACTION_TYPE = new FlexoActionType<CreateDataProperty, XOWLObject, XOWLEntity>("create_data_property", FlexoActionType.newMenu, FlexoActionType.defaultGroup, FlexoActionType.ADD_ACTION_TYPE) {
        @Override
        public CreateDataProperty makeNewAction(XOWLObject focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
            return new CreateDataProperty(focusedObject, globalSelection, editor);
        }

        @Override
        public boolean isVisibleForSelection(XOWLObject object, Vector<XOWLEntity> globalSelection) {
            return object != null;
        }

        @Override
        public boolean isEnabledForSelection(XOWLObject object, Vector<XOWLEntity> globalSelection) {
            return object != null && !object.getOntology().isReadOnly();
        }
    };

    static {
        FlexoObjectImpl.addActionForClass(CreateDataProperty.ACTION_TYPE, XOWLOntology.class);
        FlexoObjectImpl.addActionForClass(CreateDataProperty.ACTION_TYPE, XOWLDataProperty.class);
    }

    /**
     * The name of the new class
     */
    private final String name;
    /**
     * The new object property
     */
    private XOWLDataProperty property;

    /**
     * Initializes this action
     *
     * @param focusedObject   The object holding the focus
     * @param globalSelection The selected objects
     * @param editor          The editor requesting the action
     */
    private CreateDataProperty(XOWLObject focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
        super(ACTION_TYPE, focusedObject, globalSelection, editor);
        this.name = "NewClass";
    }

    @Override
    protected void doAction(Object context) throws DuplicateURIException {
        property = getFocusedObject().getFlexoOntology().newDataProperty();
    }

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(name);
    }
}
