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

import org.eclipse.swt.widgets.Composite;
import org.talend.updates.runtime.ui.feature.model.runtime.FeaturesManagerRuntimeData;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public abstract class AbstractFeatureListForm extends Composite {

    private FeaturesManagerRuntimeData runtimeData;

    public AbstractFeatureListForm(Composite parent, int style, FeaturesManagerRuntimeData runtimeData) {
        super(parent, style);
        this.runtimeData = runtimeData;
        init();
    }

    abstract protected void init();

    protected FeaturesManagerRuntimeData getRuntimeData() {
        return this.runtimeData;
    }

    protected int getHorizonAlignWidth() {
        return 5;
    }

    protected int getVersionAlignWidth() {
        return 5;
    }

    protected int getComboWidth() {
        return 100;
    }
}
