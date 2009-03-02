/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitry Polivaev
 *
 *  This file is modified by Dimitry Polivaev in 2008.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.features.mindmapmode.edge;

import java.awt.event.ActionEvent;

import org.freeplane.core.modecontroller.ModeController;
import org.freeplane.core.model.NodeModel;
import org.freeplane.core.ui.MultipleNodeAction;
import org.freeplane.core.ui.SelectableAction;
import org.freeplane.features.common.edge.EdgeController;
import org.freeplane.features.common.edge.EdgeModel;

@SelectableAction(checkOnNodeChange = true)
class EdgeStyleAsParentAction extends MultipleNodeAction {
	public EdgeStyleAsParentAction(final ModeController controller) {
		super(controller.getController(), "edge_style_as_parent");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * freeplane.modes.mindmapmode.actions.MultipleNodeAction#actionPerformed
	 * (freeplane.modes.NodeModel)
	 */
	@Override
	protected void actionPerformed(final ActionEvent e, final NodeModel node) {
		((MEdgeController) EdgeController.getController(getModeController())).setStyle(node, null);
	}
	@Override
	public void setSelected() {
		final NodeModel node = getModeController().getMapController().getSelectedNode();
		final EdgeModel model = EdgeModel.getModel(node);
		if(model == null || model.getStyle() == null){
			setSelected(true);
		}
	}
}
