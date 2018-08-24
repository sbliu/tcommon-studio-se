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

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IFormColors;
import org.talend.commons.ui.swt.composites.GradientCanvas;
import org.talend.commons.ui.swt.listviewer.ControlListItem;
import org.talend.updates.runtime.ui.feature.model.IFeatureTitle;
import org.talend.updates.runtime.ui.util.UIUtils;


/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeatureListTitle extends ControlListItem<IFeatureTitle> {

    private Composite panel;

    private Label verticalLine;

    private Label titleLabel;

    private GradientCanvas titleBackgroundPanel;

    public FeatureListTitle(Composite parent, int style, IFeatureTitle element) {
        super(parent, style, element);
        init();
    }

    private void init() {
        FormLayout layout = new FormLayout();
        this.setLayout(layout);

        panel = new Composite(this, SWT.NONE);

        FormData formData = new FormData();
        formData.height = 50;
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        panel.setLayoutData(formData);

        layout = new FormLayout();
        panel.setLayout(layout);

        initControl();
        layoutControl();
    }

    private void initControl() {

        FormColors formColors = UIUtils.getFormColors();
        titleBackgroundPanel = new GradientCanvas(panel, SWT.NONE);
        titleBackgroundPanel.setSeparatorVisible(true);
        titleBackgroundPanel.setSeparatorAlignment(SWT.TOP);
        titleBackgroundPanel.setBackgroundGradient(new Color[] { formColors.getColor(IFormColors.H_GRADIENT_END),
                formColors.getColor(IFormColors.H_GRADIENT_START) }, new int[] { 100 }, true);
        titleBackgroundPanel.putColor(GradientCanvas.H_BOTTOM_KEYLINE1, formColors.getColor(IFormColors.H_GRADIENT_END));
        titleBackgroundPanel.putColor(GradientCanvas.H_BOTTOM_KEYLINE2, formColors.getColor(IFormColors.H_GRADIENT_START));

        verticalLine = new Label(titleBackgroundPanel, SWT.NONE);

        titleLabel = new Label(titleBackgroundPanel, SWT.NONE);
        titleLabel.setFont(getTitleFont());
    }

    private void layoutControl() {
        titleBackgroundPanel.setLayout(new FormLayout());

        FormData formData = null;

        formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(100, 0);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        titleBackgroundPanel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(0, 0);
        formData.bottom = new FormAttachment(100, 0);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(0, 0);
        verticalLine.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(verticalLine, 0, SWT.CENTER);
        formData.left = new FormAttachment(verticalLine, 10, SWT.RIGHT);
        titleLabel.setLayoutData(formData);

    }

    @Override
    protected void refresh() {
        titleLabel.setText(getData().getTitle());
    }

    private Font getTitleFont() {
        final String titleFontKey = this.getClass().getName() + ".titleFont"; //$NON-NLS-1$
        FontRegistry fontRegistry = JFaceResources.getFontRegistry();
        if (!fontRegistry.hasValueFor(titleFontKey)) {
            FontDescriptor fontDescriptor = FontDescriptor.createFrom(JFaceResources.getDialogFont()).setHeight(12)
                    .setStyle(SWT.BOLD);
            fontRegistry.put(titleFontKey, fontDescriptor.getFontData());
        }
        return fontRegistry.get(titleFontKey);
    }

}
