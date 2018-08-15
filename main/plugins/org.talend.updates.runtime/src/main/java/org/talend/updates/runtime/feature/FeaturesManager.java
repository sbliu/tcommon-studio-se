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
package org.talend.updates.runtime.feature;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.talend.updates.runtime.engine.ExtraFeaturesUpdatesFactory;
import org.talend.updates.runtime.feature.model.Category;
import org.talend.updates.runtime.feature.model.Type;
import org.talend.updates.runtime.model.ExtraFeature;

/**
 * DOC cmeng  class global comment. Detailled comment
 */
public class FeaturesManager {

    private ExtraFeaturesUpdatesFactory extraFeaturesFactory;

    public FeaturesManager() {
        extraFeaturesFactory = new ExtraFeaturesUpdatesFactory();
    }

    public Set<ExtraFeature> searchFeatures(IProgressMonitor monitor, Type type, Category catagory, String keyword)
            throws Exception {
        Set<ExtraFeature> componentFeatures = new LinkedHashSet<>();
        getExtraFeatureFactory().retrieveAllComponentFeatures(monitor, componentFeatures);
        return componentFeatures;
    }

    private ExtraFeaturesUpdatesFactory getExtraFeatureFactory() {
        return this.extraFeaturesFactory;
    }

}
