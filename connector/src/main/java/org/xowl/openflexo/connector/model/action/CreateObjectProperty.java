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

package org.xowl.openflexo.connector.model.action;

import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoObject.FlexoObjectImpl;
import org.openflexo.foundation.action.FlexoAction;
import org.openflexo.foundation.action.FlexoActionType;
import org.openflexo.foundation.ontology.DuplicateURIException;
import org.openflexo.toolbox.StringUtils;
import org.xowl.openflexo.connector.model.XOWLEntity;
import org.xowl.openflexo.connector.model.XOWLObjectProperty;
import org.xowl.openflexo.connector.model.XOWLObject;
import org.xowl.openflexo.connector.model.XOWLOntology;

import java.util.Vector;

/**
 * Represents an action for the creation of a class
 *
 * @author Laurent Wouters
 */
public class CreateObjectProperty extends FlexoAction<CreateObjectProperty, XOWLObject, XOWLEntity> {
    /**
     * Represents the power type of this action
     */
    public static final FlexoActionType<CreateObjectProperty, XOWLObject, XOWLEntity> ACTION_TYPE = new FlexoActionType<CreateObjectProperty, XOWLObject, XOWLEntity>("create_object_property", FlexoActionType.newMenu, FlexoActionType.defaultGroup, FlexoActionType.ADD_ACTION_TYPE) {
        @Override
        public CreateObjectProperty makeNewAction(XOWLObject focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
            return new CreateObjectProperty(focusedObject, globalSelection, editor);
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
        FlexoObjectImpl.addActionForClass(CreateObjectProperty.ACTION_TYPE, XOWLOntology.class);
        FlexoObjectImpl.addActionForClass(CreateObjectProperty.ACTION_TYPE, XOWLObjectProperty.class);
    }

    /**
     * The name of the new class
     */
    private final String name;
    /**
     * The new object property
     */
    private XOWLObjectProperty property;

    /**
     * Initializes this action
     *
     * @param focusedObject   The object holding the focus
     * @param globalSelection The selected objects
     * @param editor          The editor requesting the action
     */
    private CreateObjectProperty(XOWLObject focusedObject, Vector<XOWLEntity> globalSelection, FlexoEditor editor) {
        super(ACTION_TYPE, focusedObject, globalSelection, editor);
        this.name = "NewClass";
    }

    @Override
    protected void doAction(Object context) throws DuplicateURIException {
        property = getFocusedObject().getFlexoOntology().newObjectProperty();
    }

    @Override
    public boolean isValid() {
        return !StringUtils.isEmpty(name);
    }
}
