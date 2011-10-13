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

import java.util.Map;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.HasClickHandlers;
import com.smartgwt.client.widgets.events.HoverEvent;
import com.smartgwt.client.widgets.events.HoverHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.TreeNode;

import cz.fi.muni.xkremser.editor.client.LangConstants;
import cz.fi.muni.xkremser.editor.client.domain.DigitalObjectModel;
import cz.fi.muni.xkremser.editor.client.presenter.CreateObjectMenuPresenter;
import cz.fi.muni.xkremser.editor.client.util.Constants;
import cz.fi.muni.xkremser.editor.client.view.other.SideNavInputTree;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateObjectMenuView.
 */
public class CreateObjectMenuView
        extends ViewWithUiHandlers<CreateObjectMenuView.MyUiHandlers>
        implements CreateObjectMenuPresenter.MyView {

    private static final String SECTION_INPUT_ID = "input";

    private final LangConstants lang;

    /**
     * The Interface MyUiHandlers.
     */
    public interface MyUiHandlers
            extends UiHandlers {

        void onRefresh();

        void revealItem(String uuid);

        Map<String, DigitalObjectModel> getModelFromLabel();

        Map<String, String> getLabelFromModel();

    }

    /** The input tree. */
    private SideNavInputTree inputTree;

    private final TreeGrid structureTreeGrid;

    private final Tree structureTree;

    /** The section stack. */
    private final SectionStack sectionStack;

    private final SectionStackSection createStructure;

    private final SectionStackSection structure;

    /** The refresh button. */
    private ImgButton refreshButton;

    private final ButtonItem createButton;

    private final CheckboxItem keepCheckbox;

    private final SelectItem selectModel;

    /** The layout. */
    private final VLayout layout;

    /**
     * Instantiates a new digital object menu view.
     */
    @Inject
    public CreateObjectMenuView(LangConstants lang) {
        this.lang = lang;
        layout = new VLayout();

        layout.setHeight100();
        layout.setWidth100();
        layout.setOverflow(Overflow.AUTO);

        structureTreeGrid = new TreeGrid();
        structureTreeGrid.setWidth100();
        structureTreeGrid.setHeight100();
        structureTreeGrid.setShowSortArrow(SortArrow.CORNER);
        structureTreeGrid.setShowAllRecords(true);
        structureTreeGrid.setCanHover(true);
        structureTreeGrid.setHoverOpacity(75);
        structureTreeGrid.setCanEdit(true);
        structureTreeGrid.setCanReparentNodes(true);
        structureTreeGrid.setHoverStyle("interactImageHover");
        structureTreeGrid.setCanReorderRecords(true);
        structureTreeGrid.setCanAcceptDroppedRecords(true);
        structureTreeGrid.setShowOpenIcons(false);
        structureTreeGrid.setDropIconSuffix("into");
        structureTreeGrid.setTreeRootValue("1");
        structureTreeGrid.setFolderIcon("icons/16/structure.png");

        structureTree = new Tree();
        structureTree.setModelType(TreeModelType.PARENT);
        structureTree.setRootValue("1");
        structureTree.setNameProperty(Constants.ATTR_NAME);
        structureTree.setIdField(Constants.ATTR_ID);
        structureTree.setParentIdField(Constants.ATTR_PARENT);
        structureTree.setOpenProperty("isOpen");

        TreeGridField rangeField = new TreeGridField();
        rangeField.setCanFilter(true);
        rangeField.setName(Constants.ATTR_NAME);
        rangeField.setTitle(lang.name());

        TreeGridField typeField = new TreeGridField();
        typeField.setCanFilter(true);
        typeField.setName(Constants.ATTR_TYPE);
        typeField.setTitle(lang.dcType());

        structureTreeGrid.setFields(rangeField, typeField);

        createStructure = new SectionStackSection();
        createStructure.setTitle(lang.createSubStructure());
        createStructure.setResizeable(true);
        selectModel = new SelectItem();
        selectModel.setTitle(lang.dcType());

        keepCheckbox = new CheckboxItem();
        keepCheckbox.setTitle(lang.keepOnRight());
        createButton = new ButtonItem();
        createButton.setTitle(lang.create());
        createButton.setAlign(Alignment.CENTER);
        createButton.setColSpan(2);
        final DynamicForm form = new DynamicForm();
        form.setNumCols(2);
        form.setPadding(5);
        form.setWidth100();
        form.setExtraSpace(10);

        form.setFields(selectModel, keepCheckbox, createButton);

        createStructure.setItems(form);
        createStructure.setExpanded(false);

        structure = new SectionStackSection();
        structure.setTitle(lang.substructures());
        structure.setResizeable(true);
        structure.setItems(structureTreeGrid);
        structure.setExpanded(false);

        sectionStack = new SectionStack();
        sectionStack.addSection(createStructure);
        sectionStack.addSection(structure);
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setAnimateSections(true);
        sectionStack.setWidth100();
        sectionStack.setHeight100();
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

    /*
     * (non-Javadoc)
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
     * @see
     * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
     * MyView#getInputTree()
     */
    @Override
    public SideNavInputTree getInputTree() {
        return inputTree;
    }

    /*
     * (non-Javadoc)
     * @see
     * cz.fi.muni.xkremser.editor.client.presenter.DigitalObjectMenuPresenter.
     * MyView#getRecentlyModifiedTree()
     */
    @Override
    public ListGrid getSubelementsGrid() {
        return structureTreeGrid;
    }

    @Override
    public SectionStack getSectionStack() {
        return sectionStack;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void setInputTree(SideNavInputTree tree) {
        String isInputSection = sectionStack.getSection(0).getAttribute(SECTION_INPUT_ID);
        if (isInputSection != null && "yes".equals(isInputSection)) {
            return;
        }
        inputTree = tree;
        SectionStackSection section1 = new SectionStackSection();
        section1.setTitle(lang.inputQueue());
        section1.setItems(inputTree);
        refreshButton = new ImgButton();
        refreshButton.setSrc("[SKIN]headerIcons/refresh.png");
        refreshButton.setSize(16);
        refreshButton.setShowRollOver(true);
        refreshButton.setCanHover(true);
        refreshButton.setShowDownIcon(false);
        refreshButton.setShowDown(false);
        refreshButton.setHoverOpacity(75);
        refreshButton.setHoverStyle("interactImageHover");
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
        section1.setAttribute(SECTION_INPUT_ID, "yes");
    }

    @Override
    public ButtonItem getCreateButton() {
        return createButton;
    }

    @Override
    public CheckboxItem getKeepCheckbox() {
        return keepCheckbox;
    }

    @Override
    public SelectItem getSelectModel() {
        return selectModel;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void enableCheckbox(boolean isEnabled) {
        if (isEnabled) {
            keepCheckbox.enable();
        } else {
            keepCheckbox.disable();
        }
    }

    public static class SubstructureTreeNode
            extends TreeNode {

        public SubstructureTreeNode(String id,
                                    String parent,
                                    String name,
                                    String type,
                                    String typeId,
                                    boolean isOpen) {
            setAttribute(Constants.ATTR_ID, id);
            setAttribute(Constants.ATTR_PARENT, parent);
            setAttribute(Constants.ATTR_NAME, name);
            setAttribute(Constants.ATTR_TYPE, type);
            setAttribute(Constants.ATTR_TYPE_ID, typeId);
            setAttribute("isOpen", isOpen);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSubstructure(int id, String name, String type, String typeId, String parent, boolean isOpen) {
        TreeNode parentNode = structureTree.findById(parent);
        structureTree.add(new SubstructureTreeNode(String.valueOf(id), parent, name, type, typeId, isOpen),
                          parentNode);
        structureTreeGrid.setData(structureTree);
    }
}