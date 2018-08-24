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
package org.talend.updates.runtime.ui.feature.wizard.page;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.updates.runtime.EUpdatesImage;
import org.talend.updates.runtime.i18n.Messages;
import org.talend.updates.runtime.ui.feature.form.FeaturesManagerForm;
import org.talend.updates.runtime.ui.feature.model.runtime.FeaturesManagerRuntimeData;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeaturesManagerPage extends WizardPage {

    private FeaturesManagerRuntimeData runtimeData;

    public FeaturesManagerPage(FeaturesManagerRuntimeData runtimeData) {
        super(Messages.getString("ComponentsManager.page.manager.title")); //$NON-NLS-1$
        setDescription(Messages.getString("ComponentsManager.page.manager.desc")); //$NON-NLS-1$
        this.runtimeData = runtimeData;
    }

    @Override
    public void createControl(Composite parent) {
        Composite panel = new Composite(parent, SWT.NONE);
        this.setControl(panel);
        panel.setLayout(new FillLayout());
        FeaturesManagerForm form = new FeaturesManagerForm(panel, SWT.NONE, getRuntimeData());
    }

    @Override
    public Image getImage() {
        return ImageProvider.getImage(EUpdatesImage.COMPONENTS_MANAGER_BANNER);
    }

    private FeaturesManagerRuntimeData getRuntimeData() {
        return this.runtimeData;
    }
}
