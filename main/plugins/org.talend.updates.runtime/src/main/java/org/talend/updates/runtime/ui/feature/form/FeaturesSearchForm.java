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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.commons.ui.runtime.image.ImageProvider;
import org.talend.updates.runtime.EUpdatesImage;
import org.talend.updates.runtime.feature.FeaturesManager;
import org.talend.updates.runtime.feature.model.Category;
import org.talend.updates.runtime.feature.model.Type;
import org.talend.updates.runtime.i18n.Messages;
import org.talend.updates.runtime.model.ExtraFeature;
import org.talend.updates.runtime.ui.ImageFactory;
import org.talend.updates.runtime.ui.feature.model.IFeatureItem;
import org.talend.updates.runtime.ui.feature.model.impl.FeatureProgress;
import org.talend.updates.runtime.ui.feature.model.impl.FeatureTitle;
import org.talend.updates.runtime.ui.feature.model.impl.ModelAdapter;
import org.talend.updates.runtime.ui.feature.model.runtime.FeaturesManagerRuntimeData;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeaturesSearchForm extends AbstractFeatureListForm {

    private ComboViewer marketsComboViewer;

    private ComboViewer categoriesComboViewer;

    private Text searchText;

    private Label findLabel;

    private Button searchButton;

    private FeatureListViewer featureListViewer;

    public FeaturesSearchForm(Composite parent, int style, FeaturesManagerRuntimeData runtimeData) {
        super(parent, style, runtimeData);
    }

    @Override
    protected void init() {
        FormLayout panelLayout = new FormLayout();
        this.setLayout(panelLayout);
        this.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
        initControl();
        initLayout();
        initData();
        addListeners();
    }

    private void initControl() {
        marketsComboViewer = new ComboViewer(this, SWT.READ_ONLY);
        categoriesComboViewer = new ComboViewer(this, SWT.READ_ONLY);
        searchText = new Text(this, SWT.BORDER);

        findLabel = new Label(this, SWT.NONE);
        findLabel.setText(Messages.getString("ComponentsManager.form.install.label.find")); //$NON-NLS-1$
        findLabel.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_BACKGROUND));

        searchButton = new Button(this, SWT.NONE);
        searchButton.setImage(ImageProvider.getImage(EUpdatesImage.FIND_16));

        featureListViewer = new FeatureListViewer(this, SWT.BORDER);
        featureListViewer.setContentProvider(ArrayContentProvider.getInstance());
    }

    private void initLayout() {
        FormData formData = null;

        final int comboWidth = getComboWidth();
        final int horizonAlignWidth = getHorizonAlignWidth();
        final int versionAlignWidth = getVersionAlignWidth();

        formData = new FormData();
        formData.top = new FormAttachment(0, versionAlignWidth);
        formData.left = new FormAttachment(0, 0);
        findLabel.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(findLabel, 0, SWT.CENTER);
        // formData.top = new FormAttachment(searchButton, 0, SWT.TOP);
        // formData.bottom = new FormAttachment(searchButton, 0, SWT.BOTTOM);
        formData.left = new FormAttachment(findLabel, horizonAlignWidth, SWT.RIGHT);
        formData.width = comboWidth;
        marketsComboViewer.getControl().setLayoutData(formData);
        marketsComboViewer.setContentProvider(ArrayContentProvider.getInstance());
        marketsComboViewer.setLabelProvider(new TypeLabelProvider());

        formData = new FormData();
        formData.top = new FormAttachment(marketsComboViewer.getControl(), 0, SWT.CENTER);
        // formData.top = new FormAttachment(searchButton, 0, SWT.TOP);
        // formData.bottom = new FormAttachment(searchButton, 0, SWT.BOTTOM);
        formData.left = new FormAttachment(marketsComboViewer.getControl(), horizonAlignWidth, SWT.RIGHT);
        formData.width = 100;
        categoriesComboViewer.getControl().setLayoutData(formData);
        categoriesComboViewer.setContentProvider(ArrayContentProvider.getInstance());
        categoriesComboViewer.setLabelProvider(new CategoryLabelProvider());

        formData = new FormData();
        formData.top = new FormAttachment(categoriesComboViewer.getControl(), 0, SWT.CENTER);
        // formData.top = new FormAttachment(searchButton, 0, SWT.TOP);
        // formData.bottom = new FormAttachment(searchButton, 0, SWT.BOTTOM);
        formData.left = new FormAttachment(categoriesComboViewer.getControl(), horizonAlignWidth, SWT.RIGHT);
        formData.right = new FormAttachment(searchButton, -1 * horizonAlignWidth, SWT.LEFT);
        searchText.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(searchText, 0, SWT.CENTER);
        formData.right = new FormAttachment(100, 0);
        searchButton.setLayoutData(formData);

        formData = new FormData();
        formData.top = new FormAttachment(searchButton, versionAlignWidth, SWT.BOTTOM);
        formData.left = new FormAttachment(0, 0);
        formData.right = new FormAttachment(100, 0);
        formData.bottom = new FormAttachment(100, 0);
        featureListViewer.getControl().setLayoutData(formData);
    }

    private void initData() {
        List<Type> types = new ArrayList<>();
        types.add(Type.ALL);
        marketsComboViewer.setInput(types);
        marketsComboViewer.setSelection(new StructuredSelection(Type.ALL));

        List<Category> categories = new ArrayList<>();
        categories.add(Category.ALL);
        categoriesComboViewer.setInput(categories);
        categoriesComboViewer.setSelection(new StructuredSelection(Category.ALL));

        doSearch();

    }

    private void addListeners() {
        searchButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                onSearchButtonSelected(e);
            }
        });
    }

    private void onSearchButtonSelected(SelectionEvent e) {
        doSearch();
    }

    private void doSearch() {
        final String keyword = searchText.getText();
        final Type type = getSelectedType();
        final Category category = getSelectedCategory();

        final FeatureProgress progress = showProgress();

        execute(new Runnable() {

            @Override
            public void run() {
                try {

                    ModalContext.run(new IRunnableWithProgress() {

                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            doSearch(monitor, type, category, keyword);
                        }
                    }, true, progress.getProgressMonitor(), getDisplay());
                } catch (Exception e1) {
                    ExceptionHandler.process(e1);
                }
            }
        });
    }

    private void doSearch(IProgressMonitor monitor, Type type, Category category, String keyword) {
        monitor.beginTask(Messages.getString("ComponentsManager.form.install.label.progress.begin"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
        FeaturesManager componentsManager = getRuntimeData().getComponentsManager();
        try {
            Set<ExtraFeature> allComponentFeatures = componentsManager.searchFeatures(monitor, type, category, keyword);
            Set<IFeatureItem> featureItems = ModelAdapter.convert(allComponentFeatures);
            if (featureItems != null) {
                List<IFeatureItem> features = new ArrayList<>();

                FeatureTitle title = new FeatureTitle();
                title.setTitle(Messages.getString("ComponentsManager.form.install.label.head.featured")); //$NON-NLS-1$
                features.add(title);

                features.addAll(featureItems);
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        featureListViewer.setInput(features);
                    }
                });
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
    }

    private Type getSelectedType() {
        ISelection selection = marketsComboViewer.getSelection();
        return (Type) ((StructuredSelection) selection).getFirstElement();
    }

    private Category getSelectedCategory() {
        ISelection selection = categoriesComboViewer.getSelection();
        return (Category) ((StructuredSelection) selection).getFirstElement();
    }

    private void execute(Runnable runnable) {
        ImageFactory.getInstance().getThreadPoolExecutor().execute(runnable);
    }

    private FeatureProgress showProgress() {
        List<IFeatureItem> progressList = new ArrayList<>();
        final FeatureProgress progress = new FeatureProgress();
        progressList.add(progress);
        featureListViewer.setInput(progressList);
        return progress;
    }

    private class CategoryLabelProvider extends LabelProvider {

        @Override
        public String getText(Object element) {
            if (element instanceof Category) {
                return ((Category) element).getLabel();
            } else {
                return super.getText(element);
            }
        }
    }

    private class TypeLabelProvider extends LabelProvider {

        @Override
        public String getText(Object element) {
            if (element instanceof Type) {
                return ((Type) element).getLabel();
            } else {
                return super.getText(element);
            }
        }
    }

}
