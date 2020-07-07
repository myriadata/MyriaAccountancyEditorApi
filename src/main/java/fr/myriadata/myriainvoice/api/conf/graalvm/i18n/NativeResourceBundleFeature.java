package fr.myriadata.myriainvoice.api.conf.graalvm.i18n;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

@AutomaticFeature
public class NativeResourceBundleFeature implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        ImageSingletons
                .lookup(RuntimeClassInitializationSupport.class)
                .initializeAtBuildTime(ResourceBundleSupport.class, this.getClass().getName());

        ImageSingletons.add(ResourceBundleSupport.class, new ResourceBundleSupport());
    }

}
