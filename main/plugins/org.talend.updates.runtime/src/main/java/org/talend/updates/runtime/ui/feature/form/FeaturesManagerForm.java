// ============================================================================
//
// Copyright (C) 2006-2018 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.updates.runtime.ui.feature.form;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.talend.updates.runtime.i18n.Messages;
import org.talend.updates.runtime.ui.feature.model.runtime.FeaturesManagerRuntimeData;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeaturesManagerForm extends AbstractFeatureListForm {

    private CTabFolder tabFolder;

    private CTabItem searchTabItem;

    private CTabItem installedTabItem;

    public FeaturesManagerForm(Composite parent, int style, FeaturesManagerRuntimeData runtimeData) {
        super(parent, style, runtimeData);
    }

    @Override
    protected void init() {
        FormLayout layout = new FormLayout();
        this.setLayout(layout);
        addTabFolder();
    }

    private void addTabFolder() {
        tabFolder = new CTabFolder(this, SWT.BORDER);

        tabFolder.setTabPosition(SWT.TOP);
        tabFolder.setSelectionBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

        FormData tabFolderFormData = new FormData();
        tabFolderFormData.left = new FormAttachment(0);
        tabFolderFormData.right = new FormAttachment(100);
        tabFolderFormData.top = new FormAttachment(0);
        tabFolderFormData.bottom = new FormAttachment(100);
        tabFolder.setLayoutData(tabFolderFormData);

        FillLayout tFolderLayout = new FillLayout();
        tabFolder.setLayout(tFolderLayout);

        addSearchTab();
        addInstalledTab();
    }

    private void addSearchTab() {
        searchTabItem = new CTabItem(tabFolder, SWT.NONE);
        searchTabItem.setText(Messages.getString("ComponentsManager.tab.label.search")); //$NON-NLS-1$
        FeaturesSearchForm searchForm = new FeaturesSearchForm(tabFolder, SWT.NONE, getRuntimeData());
        searchTabItem.setControl(searchForm);
    }

    private void addInstalledTab() {
        installedTabItem = new CTabItem(tabFolder, SWT.NONE);
        installedTabItem.setText(Messages.getString("ComponentsManager.tab.label.installed")); //$NON-NLS-1$
        FeaturesInstalledForm installedForm = new FeaturesInstalledForm(tabFolder, SWT.NONE, getRuntimeData());
        installedTabItem.setControl(installedForm);
    }
}
