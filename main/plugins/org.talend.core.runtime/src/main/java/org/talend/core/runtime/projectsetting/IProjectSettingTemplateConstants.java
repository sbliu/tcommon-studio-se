// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.runtime.projectsetting;

/**
 * DOC ggu class global comment. Detailled comment
 */
public interface IProjectSettingTemplateConstants {

    final static String PATH_RESOURCES = "resources";

    final static String PATH_RESOURCES_TEMPLATES = PATH_RESOURCES + '/' + "templates";

    final static String PATH_OSGI_BUNDLE = PATH_RESOURCES_TEMPLATES + "/osgi"; //$NON-NLS-1$

    final static String PATH_KARAF = PATH_RESOURCES_TEMPLATES + "/karaf"; //$NON-NLS-1$

    final static String PATH_ROUTE = PATH_KARAF + "/routes"; //$NON-NLS-1$

    final static String PATH_SERVICES = PATH_KARAF + "/services"; //$NON-NLS-1$

    static final String POM_FILE_NAME = "pom.xml";

    static final String ASSEMBLY_FILE_NAME = "assembly.xml";

    static final String OSGI_POM_FILE_NAME = "pom_osgi.xml";

    /*
     * pom templates
     */
    final static String POM_ROUTINGS_TEMPLATE_FILE_NAME = "pom_routines_template.xml";

    final static String POM_JOB_TEMPLATE_FILE_NAME = "pom_job_template.xml";

    final static String POM_TEST_TEMPLATE_FILE_NAME = "pom_test_template.xml";

    final static String ASSEMBLY_JOB_TEMPLATE_FILE_NAME = "assembly_job_template.xml";

    final static String PROJECT_TEMPLATE_FILE_NAME = "pom_project_template.xml";

    /*
     * in runprocess bundle
     */
    final static String JOB_RUN_BAT_TEMPLATE_FILE_NAME = "Job_run_template.bat";

    final static String JOB_RUN_SH_TEMPLATE_FILE_NAME = "Job_run_template.sh";

    final static String JOB_INFO_TEMPLATE_FILE_NAME = "jobInfo_template.properties";

    /*
     * karaf
     */
    static final String MAVEN_CONTROL_BUILD_BUNDLE_FILE_NAME = "pom-control-bundle.xml"; //$NON-NLS-1$

    static final String MAVEN_KARAF_BUILD_BUNDLE_FILE_NAME = "pom-bundle.xml"; //$NON-NLS-1$

    static final String MAVEN_KARAF_BUILD_FEATURE_FILE_NAME = "pom-feature.xml"; //$NON-NLS-1$

    static final String MAVEN_KARAF_BUILD_PARENT_FILE_NAME = "pom-parent.xml"; //$NON-NLS-1$

}
