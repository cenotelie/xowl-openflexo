/**
 * ****************************************************************************
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
 * Laurent Wouters - lwouters@xowl.org
 * ****************************************************************************
 */

package org.xowl.openflexo.connector.gui;

import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.rm.ResourceLocator;
import org.openflexo.toolbox.ImageIconResource;
import org.xowl.openflexo.connector.fml.XOWLClassRole;
import org.xowl.openflexo.connector.fml.XOWLDataPropertyRole;
import org.xowl.openflexo.connector.fml.XOWLIndividualRole;
import org.xowl.openflexo.connector.fml.XOWLObjectPropertyRole;
import org.xowl.openflexo.connector.model.*;

/**
 * The library of icons for this technology adapter
 *
 * @author Laurent Wouters
 */
public class XOWLIconLibrary {
    /**
     * The big icon for the xOWL adapter
     */
    public static final ImageIconResource ADAPTER_BIG_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/xowl32.png"));
    /**
     * The small icon for the xOWL adapter
     */
    public static final ImageIconResource ADAPTER_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/xowl16.png"));
    /**
     * The icon for an ontology
     */
    public static final ImageIconResource ONTOLOGY_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/ontology.png"));
    /**
     * The icon for an entity
     */
    public static final ImageIconResource ENTITY_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/entity.png"));
    /**
     * The icon for a class
     */
    public static final ImageIconResource CLASS_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/class.png"));
    /**
     * The icon for an individual
     */
    public static final ImageIconResource INDIVIDUAL_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/individual.png"));
    /**
     * The icon for an object property
     */
    public static final ImageIconResource OBJECT_PROPERTY_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/objectproperty.png"));
    /**
     * The icon for a data property
     */
    public static final ImageIconResource DATA_PROPERTY_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/dataproperty.png"));
    /**
     * The icon for a datatype
     */
    public static final ImageIconResource DATATYPE_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/datatype.png"));
    /**
     * The icon for an annotation
     */
    public static final ImageIconResource ANNOTATATION_ICON = new ImageIconResource(ResourceLocator.locateResource("org/xowl/openflexo/connector/icons/annotation.png"));


    /**
     * Gets the icon the represents the specified type of object
     * @param type A type in the xOWL technology adapter
     * @return The appropriate icon
     */
    public static ImageIconResource getIconForObject(Class<? extends XOWLObject> type) {
        if (XOWLOntology.class.isAssignableFrom(type)) {
            return ONTOLOGY_ICON;
        } else if (XOWLClass.class.isAssignableFrom(type)) {
            return CLASS_ICON;
        } else if (XOWLIndividual.class.isAssignableFrom(type)) {
            return INDIVIDUAL_ICON;
        } else if (XOWLObjectProperty.class.isAssignableFrom(type)) {
            return OBJECT_PROPERTY_ICON;
        } else if (XOWLDataProperty.class.isAssignableFrom(type)) {
            return DATA_PROPERTY_ICON;
        } else if (XOWLDatatype.class.isAssignableFrom(type)) {
            return DATATYPE_ICON;
        } else if (XOWLEntity.class.isAssignableFrom(type)) {
            return ENTITY_ICON;
        }
        return ADAPTER_ICON;
    }

    /**
     * Gets the icon the represents the specified type of role
     * @param type A type in the xOWL technology adapter
     * @return The appropriate icon
     */
    public static ImageIconResource getIconForRole(Class<? extends FlexoRole<?>> type) {
        if (XOWLClassRole.class.isAssignableFrom(type)) {
            return CLASS_ICON;
        } else if (XOWLIndividualRole.class.isAssignableFrom(type)) {
            return INDIVIDUAL_ICON;
        } else if (XOWLObjectPropertyRole.class.isAssignableFrom(type)) {
            return OBJECT_PROPERTY_ICON;
        } else if (XOWLDataPropertyRole.class.isAssignableFrom(type)) {
            return DATA_PROPERTY_ICON;
        }
        return ENTITY_ICON;
    }
}
