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

package org.xowl.openflexo.connector.controller;

import org.openflexo.components.widget.OntologyBrowserModel;
import org.openflexo.components.widget.OntologyView;
import org.openflexo.fib.utils.InspectorGroup;
import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.foundation.fml.editionaction.EditionAction;
import org.openflexo.foundation.ontology.IFlexoOntology;
import org.openflexo.foundation.technologyadapter.TechnologyObject;
import org.openflexo.icon.IconFactory;
import org.openflexo.icon.IconLibrary;
import org.openflexo.view.EmptyPanel;
import org.openflexo.view.ModuleView;
import org.openflexo.view.controller.ControllerActionInitializer;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.controller.IFlexoOntologyTechnologyAdapterController;
import org.openflexo.view.controller.TechnologyAdapterController;
import org.openflexo.view.controller.model.FlexoPerspective;
import org.xowl.openflexo.connector.XOWLTechnologyAdapter;
import org.xowl.openflexo.connector.fml.editionaction.AddXOWLClass;
import org.xowl.openflexo.connector.fml.editionaction.AddXOWLIndividual;
import org.xowl.openflexo.connector.gui.XOWLIconLibrary;
import org.xowl.openflexo.connector.gui.XOWLOntologyBrowserModel;
import org.xowl.openflexo.connector.gui.XOWLOntologyView;
import org.xowl.openflexo.connector.model.XOWLObject;
import org.xowl.openflexo.connector.model.XOWLOntology;

import javax.swing.*;

/**
 * Represents the main controller for the xOWL technology adapter
 *
 * @author Laurent Wouters
 */
public class XOWLAdapterController extends TechnologyAdapterController<XOWLTechnologyAdapter> implements IFlexoOntologyTechnologyAdapterController {
    /**
     * The registered inspectors
     */
    private InspectorGroup inspectors;

    @Override
    public Class<XOWLTechnologyAdapter> getTechnologyAdapterClass() {
        return XOWLTechnologyAdapter.class;
    }

    @Override
    protected void initializeInspectors(FlexoController controller) {
        //inspectors = controller.loadInspectorGroup("XOWL", getFMLTechnologyAdapterInspectorGroup());
    }

    @Override
    public InspectorGroup getTechnologyAdapterInspectorGroup() {
        return inspectors;
    }

    @Override
    protected void initializeActions(ControllerActionInitializer actionInitializer) {

    }

    @Override
    public ImageIcon getTechnologyBigIcon() {
        return XOWLIconLibrary.ADAPTER_BIG_ICON;
    }

    @Override
    public ImageIcon getTechnologyIcon() {
        return XOWLIconLibrary.ADAPTER_ICON;
    }

    @Override
    public ImageIcon getModelIcon() {
        return XOWLIconLibrary.ONTOLOGY_ICON;
    }

    @Override
    public ImageIcon getMetaModelIcon() {
        return XOWLIconLibrary.ONTOLOGY_ICON;
    }

    @Override
    public ImageIcon getIconForTechnologyObject(Class<? extends TechnologyObject<?>> objectClass) {
        if (XOWLObject.class.isAssignableFrom(objectClass)) {
            return XOWLIconLibrary.getIconForObject((Class<? extends XOWLObject>) objectClass);
        }
        return null;
    }

    @Override
    public ImageIcon getIconForPatternRole(Class<? extends FlexoRole<?>> patternRoleClass) {
        return XOWLIconLibrary.getIconForRole(patternRoleClass);
    }

    @Override
    public ImageIcon getIconForEditionAction(Class<? extends EditionAction> editionActionClass) {
        if (AddXOWLClass.class.isAssignableFrom(editionActionClass)) {
            return IconFactory.getImageIcon(XOWLIconLibrary.CLASS_ICON, IconLibrary.DUPLICATE);
        } else if (AddXOWLIndividual.class.isAssignableFrom(editionActionClass)) {
            return IconFactory.getImageIcon(XOWLIconLibrary.INDIVIDUAL_ICON, IconLibrary.DUPLICATE);
        }
        return super.getIconForEditionAction(editionActionClass);
    }

    @Override
    public boolean hasModuleViewForObject(TechnologyObject object, FlexoController controller) {
        return object instanceof XOWLOntology;
    }

    @Override
    public String getWindowTitleforObject(TechnologyObject object, FlexoController controller) {
        if (object instanceof XOWLOntology) {
            return ((XOWLOntology) object).getName();
        }
        return object.toString();
    }

    @Override
    public ModuleView<?> createModuleViewForObject(TechnologyObject<XOWLTechnologyAdapter> object, FlexoController controller, FlexoPerspective perspective) {
        if (object instanceof XOWLOntology) {
            OntologyView<XOWLOntology> returned = new XOWLOntologyView((XOWLOntology) object, controller, perspective);
            returned.setShowClasses(true);
            returned.setShowDataProperties(true);
            returned.setShowObjectProperties(true);
            returned.setShowAnnotationProperties(true);
            return returned;
        }
        return new EmptyPanel<>(controller, perspective, object);
    }

    @Override
    public OntologyBrowserModel makeOntologyBrowserModel(IFlexoOntology context) {
        if (context instanceof XOWLOntology) {
            return new XOWLOntologyBrowserModel((XOWLOntology) context);
        } else {
            return null;
        }
    }
}
