/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */
package cz.fi.muni.xkremser.editor.client.view;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.HasChangedHandlers;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import cz.fi.muni.xkremser.editor.client.Constants;
import cz.fi.muni.xkremser.editor.client.presenter.UserPresenter;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeView.
 */
public class UserView extends ViewImpl implements UserPresenter.MyView {

	/** The layout. */
	private final HLayout layout;

	/** The check button. */
	private IButton checkButton;

	/** The form. */
	private DynamicForm form;

	/** The open. */
	private IButton open;

	/** The uuid field. */
	private TextItem uuidField;

	/** The user grid. */
	private final ListGrid userGrid;
	
	/** The user roles grid. */
	private final ListGrid userRolesGrid;
	
	/** The user identities grid. */
	private final ListGrid userIdentitiesGrid;
	
	/** The remove user. */
	private final IButton removeUser;
	
	/** The remove role. */
	private final IButton removeRole;
	
	/** The remove identity. */
	private final IButton removeIdentity;
	
	/** The add user. */
	private final IButton addUser;
	
	/** The add role. */
	private final IButton addRole;
	
	/** The add identity. */
	private final IButton addIdentity;

	/**
	 * Instantiates a new home view.
	 */
	public UserView() {
		layout = new HLayout();
		layout.setHeight100();
		layout.setWidth(610);
		layout.setPadding(10);
		this.userGrid = new ListGrid();
		userGrid.setWidth(400);
		userGrid.setHeight(600);
		userGrid.setShowSortArrow(SortArrow.CORNER);
		userGrid.setShowAllRecords(true);
		userGrid.setAutoFetchData(true);
		userGrid.setCanHover(true);
		userGrid.setCanSort(false); // TODO: sort by date (define in datasource)
		userGrid.setHoverOpacity(75);
		userGrid.setHoverStyle("interactImageHover");
		userGrid.setCanEdit(true);
		userGrid.setMargin(5);

		VLayout detailLayout = new VLayout();
		detailLayout.setPadding(0);
		detailLayout.setMargin(0);

		VLayout rolesLayout = new VLayout();
		rolesLayout.setHeight(310);
		rolesLayout.setPadding(0);
		rolesLayout.setMargin(0);
		this.userRolesGrid = new ListGrid();
		userRolesGrid.setWidth(290);
		userRolesGrid.setHeight(255);
		userRolesGrid.setShowSortArrow(SortArrow.CORNER);
		userRolesGrid.setShowAllRecords(true);
		// userRolesGrid.setAutoFetchData(true);
		userRolesGrid.setCanHover(true);
		userRolesGrid.setCanSort(true);
		userRolesGrid.setHoverOpacity(75);
		userRolesGrid.setHoverStyle("interactImageHover");
		// userRolesGrid.setCanEdit(true);
		userRolesGrid.setMargin(5);

		DataSource source = new DataSource();
		DataSourceField field;
		field = new DataSourceTextField(Constants.ATTR_NAME, "Name");
		field.setRequired(true);
		field.setAttribute("width", "40%");
		source.addField(field);
		field = new DataSourceTextField(Constants.ATTR_DESC, "Description");
		field.setRequired(true);
		field.setAttribute("width", "*");
		source.addField(field);
		field = new DataSourceTextField(Constants.ATTR_GENERIC_ID, "id");
		field.setPrimaryKey(true);
		field.setHidden(true);
		field.setRequired(true);
		source.addField(field);
		userRolesGrid.setDataSource(source);
		HTMLFlow roles = new HTMLFlow("<b>Roles</b>");
		roles.setHeight(15);
		rolesLayout.addMember(roles);
		rolesLayout.addMember(userRolesGrid);
		HLayout buttonLayout2 = new HLayout();
		buttonLayout2.setPadding(5);
		addRole = new IButton("Add role");
		addRole.setExtraSpace(10);
		addRole.setDisabled(true);
		removeRole = new IButton("Remove selected");
		removeRole.setAutoFit(true);
		removeRole.setDisabled(true);
		buttonLayout2.addMember(addRole);
		buttonLayout2.addMember(removeRole);
		buttonLayout2.setAlign(Alignment.CENTER);
		rolesLayout.addMember(buttonLayout2);
		detailLayout.addMember(rolesLayout);

		VLayout identitiesLayout = new VLayout();
		identitiesLayout.setPadding(0);
		identitiesLayout.setMargin(0);
		identitiesLayout.setHeight(310);
		this.userIdentitiesGrid = new ListGrid();
		userIdentitiesGrid.setWidth(290);
		userIdentitiesGrid.setHeight(255);
		userIdentitiesGrid.setShowSortArrow(SortArrow.CORNER);
		userIdentitiesGrid.setShowAllRecords(true);
		userIdentitiesGrid.setCanHover(true);
		userIdentitiesGrid.setCanSort(false);
		userIdentitiesGrid.setHoverOpacity(75);
		userIdentitiesGrid.setHoverStyle("interactImageHover");
		userIdentitiesGrid.setMargin(5);
		userIdentitiesGrid.setCanSelectText(true);
		// userIdentitiesGrid.setCanEdit(true);
		DataSource source2 = new DataSource();
		field = new DataSourceTextField(Constants.ATTR_IDENTITY, "Identity");
		field.setRequired(true);
		source2.addField(field);
		field = new DataSourceTextField(Constants.ATTR_GENERIC_ID, "id");
		field.setPrimaryKey(true);
		field.setHidden(true);
		field.setRequired(true);
		source2.addField(field);
		userIdentitiesGrid.setDataSource(source2);
		HTMLFlow openIds = new HTMLFlow("<b>OpenID identities</b>");
		openIds.setHeight(15);
		identitiesLayout.addMember(openIds);
		identitiesLayout.addMember(userIdentitiesGrid);
		HLayout buttonLayout = new HLayout();
		buttonLayout.setPadding(5);
		buttonLayout.setAlign(Alignment.CENTER);
		addIdentity = new IButton("Add identity");
		addIdentity.setExtraSpace(10);
		addIdentity.setDisabled(true);
		removeIdentity = new IButton("Remove selected");
		removeIdentity.setAutoFit(true);
		removeIdentity.setDisabled(true);
		buttonLayout.addMember(addIdentity);
		buttonLayout.addMember(removeIdentity);
		identitiesLayout.addMember(buttonLayout);
		detailLayout.addMember(identitiesLayout);

		VLayout userLayout = new VLayout();
		HTMLFlow users = new HTMLFlow("<b>Users</b>");
		users.setHeight(15);
		userLayout.addMember(users);
		userLayout.addMember(userGrid);
		HLayout buttonLayout3 = new HLayout();
		buttonLayout3.setPadding(5);
		buttonLayout3.setAlign(Alignment.CENTER);
		addUser = new IButton("Add user");
		addUser.setExtraSpace(10);
		removeUser = new IButton("Remove selected");
		removeUser.setAutoFit(true);
		removeUser.setDisabled(true);
		buttonLayout3.addMember(addUser);
		buttonLayout3.addMember(removeUser);
		userLayout.addMember(buttonLayout3);
		layout.addMember(userLayout);
		layout.addMember(detailLayout);
	}

	/**
	 * Returns this widget as the {@link WidgetDisplay#asWidget()} value.
	 * 
	 * @return the widget
	 */
	@Override
	public Widget asWidget() {
		return layout;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getOpen()
	 */
	@Override
	public IButton getOpen() {
		return open;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getForm()
	 */
	@Override
	public DynamicForm getForm() {
		return form;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getUuidItem()
	 */
	@Override
	public HasChangedHandlers getUuidItem() {
		return uuidField;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#refreshFedora(boolean, java.lang.String)
	 */
	@Override
	public void refreshFedora(boolean fedoraRunning, String url) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#refreshKramerius(boolean, java.lang.String)
	 */
	@Override
	public void refreshKramerius(boolean krameriusRunning, String url) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#setURLs(java.lang.String, java.lang.String)
	 */
	@Override
	public void setURLs(String fedoraUrl, String krameriusUrl) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#setLoading()
	 */
	@Override
	public void setLoading() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getUserGrid()
	 */
	@Override
	public ListGrid getUserGrid() {
		return userGrid;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getUserRoleGrid()
	 */
	@Override
	public ListGrid getUserRoleGrid() {
		return userRolesGrid;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getUserIdentityGrid()
	 */
	@Override
	public ListGrid getUserIdentityGrid() {
		return userIdentitiesGrid;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getRemoveUser()
	 */
	@Override
	public IButton getRemoveUser() {
		return removeUser;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getRemoveRole()
	 */
	@Override
	public IButton getRemoveRole() {
		return removeRole;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getRemoveIdentity()
	 */
	@Override
	public IButton getRemoveIdentity() {
		return removeIdentity;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getAddUser()
	 */
	@Override
	public IButton getAddUser() {
		return addUser;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getAddRole()
	 */
	@Override
	public IButton getAddRole() {
		return addRole;
	}

	/* (non-Javadoc)
	 * @see cz.fi.muni.xkremser.editor.client.presenter.UserPresenter.MyView#getAddIdentity()
	 */
	@Override
	public IButton getAddIdentity() {
		return addIdentity;
	}

}