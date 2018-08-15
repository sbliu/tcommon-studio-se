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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.listviewer.ControlListItem;
import org.talend.updates.runtime.ui.feature.model.IFeatureProgress;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeatureListProgress extends ControlListItem<IFeatureProgress> {

    private Composite panel;

    private ProgressMonitorPart progressBar;

    public FeatureListProgress(Composite parent, int style, IFeatureProgress element) {
        super(parent, style, element);
        init();
    }

    private void init() {
        FormLayout layout = new FormLayout();
        this.setLayout(layout);

        panel = new Composite(this, SWT.NONE);
        FormData formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(100, 0);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        panel.setLayoutData(formData);
        layout = new FormLayout();
        layout.marginWidth = getHorizonAlignWidth();
        layout.marginHeight = getVerticalAlignWidth();
        panel.setLayout(layout);

        initControl();
        layoutControl();
    }

    private void initControl() {
        progressBar = new ProgressMonitorPart(panel, null, true);
        progressBar.attachToCancelComponent(null);
    }

    private void layoutControl() {
        FormData formData = null;
        final int verticalAlignWidth = getVerticalAlignWidth();

        formData = new FormData();
        formData.top = new FormAttachment(0, verticalAlignWidth);
        formData.bottom = new FormAttachment(100, -1 * verticalAlignWidth);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        progressBar.setLayoutData(formData);

    }

    public IProgressMonitor getProgressMonitor() {
        return progressBar;
    }

    @Override
    protected void refresh() {
        getData().setProgressMonitor(getProgressMonitor());
    }

    private int getVerticalAlignWidth() {
        return 5;
    }

    private int getHorizonAlignWidth() {
        return 5;
    }

}
