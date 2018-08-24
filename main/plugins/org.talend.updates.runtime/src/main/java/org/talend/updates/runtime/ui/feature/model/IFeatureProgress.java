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
package org.talend.updates.runtime.ui.feature.model;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public interface IFeatureProgress extends IFeatureItem {

    void setProgressMonitor(IProgressMonitor monitor);

    IProgressMonitor getProgressMonitor();

}
