/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.HasClickHandlers;
import com.smartgwt.client.widgets.events.HoverEvent;
import com.smartgwt.client.widgets.events.HoverHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

import cz.fi.muni.xkremser.editor.client.Constants;
import cz.fi.muni.xkremser.editor.client.gwtrpcds.RecentlyTreeGwtRPCDS;
import cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter;
import cz.fi.muni.xkremser.editor.client.view.tree.SideNavInputTree;
import cz.fi.muni.xkremser.editor.client.view.tree.SideNavRecentlyGrid;
import cz.fi.muni.xkremser.editor.shared.rpc.RecentlyModifiedItem;

// TODO: Auto-generated Javadoc
/**
 * The Class DigitalObjectMenuView.
 */
public class DigitalObjectMenuView extends ViewWithUiHandlers<DigitalObjectMenuView.MyUiHandlers> implements DigitalObjectMenuPresenter.MyView {
	private static final String SECTION_RELATED_ID = "related";

	/**
	 * The Interface MyUiHandlers.
	 */
	public interface MyUiHandlers extends UiHandlers {

		/**
		 * On refresh.
		 */
		void onRefresh();

		/**
		 * On show input queue.
		 */
		void onShowInputQueue();

		/**
		 * On add digital object.
		 * 
		 * @param item
		 *          the item
		 */
		void onAddDigitalObject(final RecentlyModifiedItem item, final List<? extends List<String>> related);

		/**
		 * Reveal modified item.
		 * 
		 * @param uuid
		 *          the uuid
		 */
		void revealItem(String uuid);

	}

	/**
	 * The Interface Refreshable.
	 */
	public interface Refreshable {

		/**
		 * Refresh tree.
		 */
		void refreshTree();
	}

	/** The input tree. */
	private SideNavInputTree inputTree;

	/** The side nav grid. */
	private final SideNavRecentlyGrid sideNavGrid;

	/** The section stack. */
	private final SectionStack sectionStack;

	/** The section recently modified. */
	private final SectionStackSection sectionRecentlyModified;

	private final SectionStackSection sectionRelated;
	/** The section related. */
	private final ListGrid relatedGrid;

	/** The refresh button. */
	private ImgButton refreshButton;

	/** The layout. */
	private final VLayout layout;

	/**
	 * Instantiates a new digital object menu view.
	 */
	public DigitalObjectMenuView() {
		layout = new VLayout();

		layout.setHeight100();
		layout.setWidth100();
		layout.setOverflow(Overflow.AUTO);

		relatedGrid = new ListGrid();
		relatedGrid.setWidth100();
		relatedGrid.setHeight100();
		relatedGrid.setShowSortArrow(SortArrow.CORNER);
		relatedGrid.setShowAllRecords(true);
		relatedGrid.setAutoFetchData(false);
		relatedGrid.setCanHover(true);
		relatedGrid.setCanSort(false);
		ListGridField field1 = new ListGridField("uuid", "uuid");
		ListGridField field2 = new ListGridField("relation", "Relation");
		relatedGrid.setFields(field1, field2);
		sectionRelated = new SectionStackSection();
		sectionRelated.setID(SECTION_RELATED_ID);
		sectionRelated.setTitle("Referenced by");
		sectionRelated.setResizeable(true);
		sectionRelated.setItems(relatedGrid);
		sectionRelated.setExpanded(false);

		sideNavGrid = new SideNavRecentlyGrid();

		final DynamicForm form = new DynamicForm();
		form.setHeight(1);
		form.setWidth(60);
		form.setNumCols(1);
		form.setCanHover(true);
		form.addHoverHandler(new HoverHandler() {
			@Override
			public void onHover(HoverEvent event) {
				form.setPrompt("Show only objects modified by...");
			}
		});

		final SelectItem selectItem = new SelectItem();
		selectItem.setWidth(60);
		selectItem.setShowTitle(false);
		selectItem.setValueMap("me", "all");
		selectItem.setDefaultValue("me");
		selectItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				Criteria criteria = new Criteria();
				boolean all = "all".equals(event.getValue());
				criteria.addCriteria(Constants.ATTR_ALL, all);
				// sideNavTree.fetchData(criteria);
				sideNavGrid.filterData(criteria);
			}
		});

		form.setFields(selectItem);
		form.setTitle("by:");

		sectionRecentlyModified = new SectionStackSection();
		sectionRecentlyModified.setTitle("Recently modified");
		sectionRecentlyModified.setResizeable(true);
		sectionRecentlyModified.setItems(sideNavGrid);
		sectionRecentlyModified.setControls(form);
		sectionRecentlyModified.setExpanded(true);

		sectionStack = new SectionStack();
		sectionStack.addSection(sectionRelated);
		sectionStack.addSection(sectionRecentlyModified);
		sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sectionStack.setAnimateSections(true);
		sectionStack.setWidth100();
		sectionStack.setHeight100();
		// sectionStack.setOverflow(Overflow.AUTO);
		sectionStack.setOverflow(Overflow.HIDDEN);
		layout.addMember(sectionStack);
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

	// @UiHandler("saveButton")
	// void onSaveButtonClicked(ClickEvent event) {
	// if (getUiHandlers() != null) {
	// getUiHandlers().onRefresh();
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
	 * MyView#getSelected()
	 */
	@Override
	public HasValue<String> getSelected() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
	 * MyView#setDS(com.gwtplatform.dispatch.client.DispatchAsync)
	 */
	@Override
	public void setDS(DispatchAsync dispatcher) {
		this.sideNavGrid.setDataSource(new RecentlyTreeGwtRPCDS(dispatcher));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
	 * MyView#showInputQueue(com.gwtplatform.dispatch.client.DispatchAsync)
	 */
	@Override
	public void showInputQueue(DispatchAsync dispatcher) {
		SectionStackSection section1 = new SectionStackSection();
		section1.setTitle("Input queue");
		inputTree = new SideNavInputTree(dispatcher);
		section1.setItems(inputTree);
		refreshButton = new ImgButton();
		refreshButton.setSrc("[SKIN]headerIcons/refresh.png");
		refreshButton.setSize(16);
		refreshButton.setShowRollOver(true);
		refreshButton.setCanHover(true);
		refreshButton.setShowDownIcon(false);
		refreshButton.setShowDown(false);
		refreshButton.addHoverHandler(new HoverHandler() {
			@Override
			public void onHover(HoverEvent event) {
				refreshButton.setPrompt("Rescan directory structure.");
			}
		});

		section1.setControls(refreshButton);
		section1.setResizeable(true);
		section1.setExpanded(true);
		sectionStack.addSection(section1, 0);
		// inputTree.setHeight("600");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
	 * MyView#expandNode(java.lang.String)
	 */
	@Override
	public void expandNode(String id) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
	 * MyView#getRefreshWidget()
	 */
	@Override
	public HasClickHandlers getRefreshWidget() {
		return refreshButton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
	 * MyView#getInputTree()
	 */
	@Override
	public Refreshable getInputTree() {
		return inputTree;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
	 * MyView#getRecentlyModifiedTree()
	 */
	@Override
	public ListGrid getRecentlyModifiedGrid() {
		return sideNavGrid;
	}

	@Override
	public void setRelatedDocuments(List<? extends List<String>> data) {
		if (data != null && data.size() != 0) {
			sectionStack.getSection(SECTION_RELATED_ID).setExpanded(true);
			// sectionRelated.setExpanded(true);
			Record[] records = new Record[data.size()];
			for (int i = 0; i < data.size(); i++) {
				records[i] = new ListGridRecord();
				records[i].setAttribute("uuid", data.get(i).get(0));
				records[i].setAttribute("relation", data.get(i).get(1));
			}
			relatedGrid.setData(records);
		} else
			sectionStack.getSection(SECTION_RELATED_ID).setExpanded(false);
	}

	@Override
	public ListGrid getRelatedGrid() {
		return relatedGrid;
	}
}