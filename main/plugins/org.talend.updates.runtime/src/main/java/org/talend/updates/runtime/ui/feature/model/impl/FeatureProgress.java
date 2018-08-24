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
package org.talend.updates.runtime.ui.feature.model.impl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.updates.runtime.ui.feature.model.IFeatureProgress;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeatureProgress extends AbstractFeatureItem implements IFeatureProgress {

    private IProgressMonitor monitor;

    @Override
    public void setProgressMonitor(IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public IProgressMonitor getProgressMonitor() {
        return monitor;
    }

}
