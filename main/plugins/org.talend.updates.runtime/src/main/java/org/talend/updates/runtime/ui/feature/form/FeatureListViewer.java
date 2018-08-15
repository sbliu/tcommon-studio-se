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
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.swt.listviewer.ControlListItem;
import org.talend.commons.ui.swt.listviewer.ControlListViewer;
import org.talend.updates.runtime.ui.feature.model.IFeatureDetail;
import org.talend.updates.runtime.ui.feature.model.IFeatureProgress;
import org.talend.updates.runtime.ui.feature.model.IFeatureTitle;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeatureListViewer extends ControlListViewer {

    public FeatureListViewer(Composite parent, int style) {
        super(parent, style);
    }

    @Override
    protected ControlListItem<?> doCreateItem(Composite parent, Object element) {
        if (element instanceof IFeatureDetail) {
            return new FeatureListItem(parent, SWT.NONE, (IFeatureDetail) element);
        } else if (element instanceof IFeatureTitle) {
            return new FeatureListTitle(parent, SWT.NONE, (IFeatureTitle) element);
        } else if (element instanceof IFeatureProgress) {
            return new FeatureListProgress(parent, SWT.NONE, (IFeatureProgress) element);
        }
        return null;
    }

}
