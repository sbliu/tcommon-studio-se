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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.commons.ui.swt.listviewer.ControlListItem;
import org.talend.updates.runtime.EUpdatesImage;
import org.talend.updates.runtime.i18n.Messages;
import org.talend.updates.runtime.ui.ImageFactory;
import org.talend.updates.runtime.ui.feature.model.IFeatureDetail;
import org.talend.updates.runtime.ui.util.UIUtils;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeatureListItem extends ControlListItem<IFeatureDetail> {

    private Composite panel;

    private Label imageLabel;

    private Label titleLabel;

    /**
     * used to center the image
     */
    private Label verticalLine;

    private Label horizonLine;

    private StyledText descText;

    private Button installButton;

    private Image compImage;

    private Object compImageLock = new Object();

    public FeatureListItem(Composite parent, int style, IFeatureDetail element) {
        super(parent, style, element);
        init();
    }

    private void init() {
        FormLayout layout = new FormLayout();
        this.setLayout(layout);

        boolean useNewPanel = true;
        if (useNewPanel) {
            panel = new Composite(this, SWT.NONE);

            layout = new FormLayout();
            layout.marginWidth = 5;
            panel.setLayout(layout);

            FormData layoutData = new FormData();
            layoutData.height = 150;
            layoutData.left = new FormAttachment(0, 0);
            layoutData.right = new FormAttachment(100, 0);
            panel.setLayoutData(layoutData);
        } else {
            panel = this;
        }

        initControl();
        layoutControl();
    }

    private void initControl() {
        verticalLine = new Label(panel, SWT.NONE);
        horizonLine = new Label(panel, SWT.SEPARATOR | SWT.HORIZONTAL);
        imageLabel = new Label(panel, SWT.CENTER);

        titleLabel = new Label(panel, SWT.NONE);
        titleLabel.setFont(getTitleFont());

        descText = new StyledText(panel, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI | SWT.NO_FOCUS);
        descText.setCursor(Display.getDefault().getSystemCursor(SWT.CURSOR_ARROW));
        descText.setEditable(false);
        descText.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {

            @Override
            public void getRole(AccessibleControlEvent e) {
                e.detail = ACC.ROLE_LABEL;
            }
        });
        installButton = new Button(panel, SWT.NONE);
        installButton.setText(Messages.getString("ComponentsManager.form.install.label.install")); //$NON-NLS-1$
        installButton.setFont(getInstallButtonFont());
    }

    private void layoutControl() {
        final int horizonAlignWidth = getHorizonAlignWidth();
        final int verticalAlignWidth = getVerticalAlignWidth();
        FormData formData = null;

        formData = new FormData();
        formData.bottom = new FormAttachment(100, 0);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        horizonLine.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(titleLabel, 0, SWT.TOP);
        formData.bottom = new FormAttachment(descText, 0, SWT.BOTTOM);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(0, 0);
        verticalLine.setLayoutData(formData);

        formData = new FormData();
        formData.left = new FormAttachment(0, horizonAlignWidth);
        formData.top = new FormAttachment(verticalLine, 0, SWT.CENTER);
        Point imageSize = getImageSize();
        formData.height = imageSize.y;
        formData.width = imageSize.x;
        imageLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(0, verticalAlignWidth);
        formData.left = new FormAttachment(imageLabel, horizonAlignWidth, SWT.RIGHT);
        formData.right = new FormAttachment(100, 0);
        titleLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(titleLabel, verticalAlignWidth, SWT.BOTTOM);
        formData.left = new FormAttachment(titleLabel, 0, SWT.LEFT);
        formData.right = new FormAttachment(100, 0);
        formData.bottom = new FormAttachment(installButton, -1 * verticalAlignWidth, SWT.TOP);
        descText.setLayoutData(formData);

        formData = new FormData();
        formData.right = new FormAttachment(100, -1 * horizonAlignWidth);
        formData.bottom = new FormAttachment(horizonLine, -1 * verticalAlignWidth, SWT.TOP);
        installButton.setLayoutData(formData);
    }

    @Override
    protected void refresh() {
        final IFeatureDetail cd = getData();
        if (cd != null) {
            titleLabel.setText(cd.getTitle());
            descText.setText(cd.getDescription());
            if (compImage != null) {
                setImage(compImage);
            } else {
                setImage(ImageProvider.getImage(EUpdatesImage.LOADING));
                ImageFactory.getInstance().getThreadPoolExecutor().execute(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (compImageLock) {
                            if (compImage == null) {
                                try {
                                    Image image = cd.getImage(new NullProgressMonitor());
                                    if (image != null) {
                                        Point imageSize = getImageSize();
                                        Rectangle originalImageBound = image.getBounds();
                                        if (imageSize.x < originalImageBound.width || imageSize.y < originalImageBound.height) {
                                            compImage = UIUtils.scaleImage(image, imageSize.x, imageSize.y);
                                            ImageFactory.getInstance().registFeatureImage(compImage);
                                        } else {
                                            // keep original size
                                            compImage = image;
                                        }
                                    }
                                } catch (Exception e) {
                                    ExceptionHandler.process(e);
                                }
                            }
                        }
                        if (compImage != null) {
                            Display.getDefault().asyncExec(new Runnable() {

                                @Override
                                public void run() {
                                    setImage(compImage);
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    private void setImage(Image image) {
        imageLabel.setImage(image);
        imageLabel.getParent().layout();
    }

    private int getHorizonAlignWidth() {
        return 5;
    }

    private int getVerticalAlignWidth() {
        return 5;
    }
    
    private Point getImageSize() {
        return new Point(74, 74);
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

    private Font getInstallButtonFont() {
        final String installBtnFontKey = this.getClass().getName() + ".installButtonFont"; //$NON-NLS-1$
        FontRegistry fontRegistry = JFaceResources.getFontRegistry();
        if (!fontRegistry.hasValueFor(installBtnFontKey)) {
            FontDescriptor fontDescriptor = FontDescriptor.createFrom(JFaceResources.getDialogFont()).setStyle(SWT.BOLD);
            fontRegistry.put(installBtnFontKey, fontDescriptor.getFontData());
        }
        return fontRegistry.get(installBtnFontKey);
    }
}
